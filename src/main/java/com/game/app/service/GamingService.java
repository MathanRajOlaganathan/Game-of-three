package com.game.app.service;

/**
 * @author Mathan Raj O
 * @version 1.0
 * @since 27/01/2021
 */
public interface GamingService {

    public String joinGame(boolean isManual, Integer number);

    public String play(Integer number);
}
