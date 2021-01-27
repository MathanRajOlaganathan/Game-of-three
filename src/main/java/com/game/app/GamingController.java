package com.game.app;

import com.game.app.service.GamingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.game.app.constants.ApplicationConstants.*;

/**
 * @author Mathan Raj O
 * @version 1.0
 * @since 27/01/2021
 */
@RestController
@RequestMapping(ROOT_API)
@RequiredArgsConstructor
@Slf4j
public class GamingController {

    private final GamingService gamingService;

    @ApiOperation("Start the game")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully started the game")})
    @GetMapping(START_PATH + "/{isManual}")
    public ResponseEntity<String> joinGame(@PathVariable(value = "isManual") boolean isManual, @RequestParam(value = "number", required = false) Integer number) {
        log.info("Starting the game");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gamingService.joinGame(isManual, number));

    }

    @PostMapping(PLAY_PATH)
    public ResponseEntity<String> playGame(@RequestBody Integer number) {
        log.info("Playing the game");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(gamingService.play(number));
    }

}
