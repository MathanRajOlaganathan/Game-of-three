package com.game.app.config.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mathan Raj O
 * @version 1.0
 * @since 27/01/2021
 */

@Component
@Slf4j
public class Receiver {

    @Value("${app.play.uri}")
    private String playURI;

    @Autowired
    private RestTemplate restTemplate;

    @JmsListener(destination = "play", containerFactory = "myFactory")
    public void playGame(Integer number) {
        log.info(String.format("Sending the number %d to the other gamer", number));
        restTemplate.postForObject(playURI, number, String.class);
    }
}
