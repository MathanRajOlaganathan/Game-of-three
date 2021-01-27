package com.game.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static com.game.app.constants.ApplicationConstants.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GameOfThreeApplicationTests {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @DisplayName("Testing Gamer 2 not available case")
    void testJoinGame() {

        assertFalse(this.restTemplate.getForObject("http://localhost:" + port + ROOT_API + START_PATH + "/false", String.class)
                .equals(SUCCESS_JOINING), "Failed Join Game API Call");
    }

    @Test
    @DisplayName("Testing Invalid Input Exception")
    void playGame() {

        String actual = restTemplate.postForObject("http://localhost:" + port + ROOT_API + PLAY_PATH, -1, String.class);
        assertTrue(actual.contains(INVALID_NUMBER));

    }


}
