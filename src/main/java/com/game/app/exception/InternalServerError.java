package com.game.app.exception;

/**
 * @author Mathan Raj O
 * @version 1.0
 * @since 27/01/2021
 */
public class InternalServerError extends RuntimeException {

    public InternalServerError() {
    }

    public InternalServerError(String message) {
        super(message);
    }

    public InternalServerError(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerError(Throwable cause) {
        super(cause);
    }
}
