package com.game.app.service.Impl;

import com.game.app.exception.GamerNotAvailableException;
import com.game.app.exception.InternalServerError;
import com.game.app.exception.InvalidInputException;
import com.game.app.service.GamingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import static com.game.app.constants.ApplicationConstants.*;

/**
 * @author Mathan Raj O
 * @version 1.0
 * @since 27/01/2021
 */
@Service
@Slf4j
public class GamingServiceImpl implements GamingService {

    @Value("${app.gamer.uri}")
    private String gamerURI;

    @Value("${app.play.uri}")
    private String playURI;

    private RestTemplate restTemplate;

    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    public GamingServiceImpl(RestTemplate restTemplate, ConfigurableApplicationContext configurableApplicationContext) {
        this.restTemplate = restTemplate;
        this.configurableApplicationContext = configurableApplicationContext;
    }

    /**
     * Check for second gamer and send the number.
     *
     * @param isManual
     * @param number
     * @return
     */
    @Override
    public String joinGame(boolean isManual, Integer number) {
        if (locatePlayer()) {
            log.info("Gamer 2 is available and the game has begun");
            if (!isManual) {
                number = generateRandomNumber();
            }
            JmsTemplate jmsTemplate = configurableApplicationContext.getBean(JmsTemplate.class);
            // Send the no to the other gamer
            jmsTemplate.convertAndSend("play", number);

        } else throw new GamerNotAvailableException("Gamer 2 is not available, please try later");
        return SUCCESS_JOINING;
    }

    /**
     * Validate the input and play by rules
     *
     * @param number
     * @return
     */

    @Override
    public String play(Integer number) {

        Optional.of(number).orElseThrow(() -> new InvalidInputException(INVALID_INPUT));
        if (number > 0) {
            Integer remainder = checkRules(number);
            if (remainder == 1) {
                log.info("The game has ended, you have won the battle");
            } else {
                if (locatePlayer()) {
                    log.info(String.format("Sending number %d to the other gamer", remainder));
                    restTemplate.postForObject(playURI, remainder, String.class);
                } else {
                    throw new GamerNotAvailableException("The game has ended. Gamer2 no longer available. Please Start the game from the beginning.");
                }
            }
        } else {
            throw new InvalidInputException(INVALID_NUMBER);
        }
        return SUCCESS_END;

    }

    /**
     * Validate the game rules
     *
     * @param number
     * @return
     */
    private Integer checkRules(Integer number) {
        var rem = 0;
        if (number % 3 == 0) {
            log.info("Adding 0 to the number");
            rem = number / 3;
        } else if ((number + 1) % 3 == 0) {
            log.info("Adding 1 to the number");
            rem = (number + 1) / 3;
        } else if ((number - 1) % 3 == 0) {
            log.info("Adding -1 to the number");
            rem = (number - 1) / 3;
        } else {
            throw new InternalServerError("Not able to apply rules to the number, please restart the game");
        }
        return rem;
    }

    /**
     * Generate random number with origin and bound
     *
     * @return
     */
    private int generateRandomNumber() {
        Random random = new Random();
        return random.ints(10, (10000 + 1)).findFirst().getAsInt();

    }

    /**
     * Locate the other gamer
     *
     * @return
     */
    private boolean locatePlayer() {
        log.info("Looking for the other gamer");
        Map result = null;
        try {
            result = restTemplate.getForObject(gamerURI, Map.class);
        } catch (Exception ex) {
            throw new GamerNotAvailableException("Gamer 2 is not available, please try later");
        }

        if (result.get(APP_STATUS).equals(STATUS_UP)) {
            return true;
        }
        return false;

    }
}
