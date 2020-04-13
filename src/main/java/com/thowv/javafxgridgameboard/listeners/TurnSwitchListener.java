package com.thowv.javafxgridgameboard.listeners;

import com.thowv.javafxgridgameboard.AbstractTurnEntity;

/**
 * @author Thomas
 */
public interface TurnSwitchListener {
    /**
     * Gets fired when the game switches turns.
     * @param currentTurnEntity The turn entity that gets to do the next turn
     * @param previousTurnEntity The tuurn entity that got to do the last turn
     */
    void onTurnSwitch(AbstractTurnEntity currentTurnEntity, AbstractTurnEntity previousTurnEntity);
}
