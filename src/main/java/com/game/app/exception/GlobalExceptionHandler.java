package com.game.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Mathan Raj O
 * @version 1.0
 * @since 27/01/2021
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(GamerNotAvailableException.class)
    public ResponseEntity<ApiCallError<String>> handleNotAvailableException(HttpServletRequest httpServletRequest, GamerNotAvailableException ex) {
        log.error("GamerNotAvailableException - {} - {}", httpServletRequest.getRequestURI(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiCallError<>("Gamer Not Available Exception", List.of(ex.getMessage())));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ApiCallError<String>> handleInvalidInputException(HttpServletRequest httpServletRequest, InvalidInputException ex) {
        log.error("InValidInputException - {} - {}", httpServletRequest.getRequestURI(), ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiCallError<>("Invalid Input Exception", List.of(ex.getMessage())));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiCallError<String>> handleInternalServerError(HttpServletRequest request, Exception ex) {
        log.error("handleInternalServerError - {} - {}", request.getRequestURI(), ex.toString());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiCallError<>("Internal server error", List.of(ex.getMessage())));
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    private class ApiCallError<T> {
        private String message;
        private List<T> details;

    }
}
