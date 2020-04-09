package com.thowv.javafxgridgameboard.listeners;

import com.thowv.javafxgridgameboard.AbstractTurnEntity;

public interface GameEndListener {
    void onGameEnd(AbstractTurnEntity winningTurnEntity, AbstractTurnEntity losingTurnEntity);
    void onGameEnd(AbstractTurnEntity[] tieTurnEntities);
}
