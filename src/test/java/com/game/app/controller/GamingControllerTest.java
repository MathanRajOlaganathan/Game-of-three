package com.game.app.controller;

import com.game.app.GamingController;
import com.game.app.service.GamingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static com.game.app.constants.ApplicationConstants.SUCCESS_END;
import static com.game.app.constants.ApplicationConstants.SUCCESS_JOINING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

/**
 * @author Mathan Raj O
 * @version 1.0
 * @since 27/01/2021
 */
@ExtendWith(MockitoExtension.class)
class GamingControllerTest {

    @InjectMocks
    GamingController gamingController;

    @Mock
    GamingService gamingService;

    @Test
    @DisplayName("Testing the join game unit positive case")
    void joinGameSuccess() {
        when(gamingService.joinGame(false, 13)).thenReturn(SUCCESS_JOINING);
        ResponseEntity<String> responseEntity = gamingController.joinGame(false, 13);
        assertEquals(responseEntity.getStatusCodeValue(), 200, "The expected response status is different from the actual");
        assertEquals(responseEntity.getBody(), SUCCESS_JOINING, "The expected and actual response are not same");
    }

    @Test
    @DisplayName("Testing the join game unit negative case")
    void joinGameFailure() {
        when(gamingService.joinGame(false, 12)).thenReturn("Failure Msg");
        ResponseEntity<String> responseEntity = gamingController.joinGame(false, 12);
        assertNotEquals(responseEntity.getBody(), SUCCESS_JOINING, "The expected and actual response are same");
    }

    @Test
    @DisplayName("Testing the play game unit positive case")
    void playGameSuccess() {
        when(gamingService.play(12)).thenReturn(SUCCESS_END);
        ResponseEntity<String> responseEntity = gamingController.playGame(12);
        assertEquals(responseEntity.getStatusCodeValue(), 200, "The expected response status is different from the actual");
        assertEquals(responseEntity.getBody(), SUCCESS_END, "The expected and actual response are not same");
    }


    @Test
    @DisplayName("Testing the play game unit negative case")
    void playGameFailure() {
        when(gamingService.play(12)).thenReturn("Failure Msg");
        ResponseEntity<String> responseEntity = gamingController.playGame(12);
        assertNotEquals(responseEntity.getBody(), SUCCESS_JOINING, "The expected and actual response are same");
    }
}