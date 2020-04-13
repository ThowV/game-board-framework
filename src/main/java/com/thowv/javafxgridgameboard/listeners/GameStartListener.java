package com.thowv.javafxgridgameboard.listeners;

import com.thowv.javafxgridgameboard.AbstractTurnEntity;

/**
 * @author Thomas
 */
public interface GameStartListener {
    /**
     * Gets fired when the game starts.
     * @param currentTurnEntity The turn entity that has the first turn
     */
    void onGameStart(AbstractTurnEntity currentTurnEntity);
}
