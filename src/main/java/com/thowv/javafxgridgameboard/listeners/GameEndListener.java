package com.thowv.javafxgridgameboard.listeners;

import com.thowv.javafxgridgameboard.AbstractTurnEntity;

/**
 * @author Thomas
 */
public interface GameEndListener {
    /**
     * Gets fired when the game ends.
     * @param winningTurnEntity The turn entity that wins
     * @param losingTurnEntity The turn entity that loses
     */
    void onGameEnd(AbstractTurnEntity winningTurnEntity, AbstractTurnEntity losingTurnEntity);

    /**
     * Gets fired when the game ends.
     * @param tieTurnEntities The turn entities that tie
     */
    void onGameEnd(AbstractTurnEntity[] tieTurnEntities);
}
