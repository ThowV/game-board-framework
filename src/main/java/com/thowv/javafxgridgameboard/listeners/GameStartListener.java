package com.thowv.javafxgridgameboard.listeners;

import com.thowv.javafxgridgameboard.AbstractTurnEntity;

public interface GameStartListener {
    void onGameStart(AbstractTurnEntity currentTurnEntity);
}
