package com.thowv.javafxgridgameboard.listeners;

import com.thowv.javafxgridgameboard.AbstractTurnEntity;

public interface TurnSwitchListener {
    void onTurnSwitch(AbstractTurnEntity previousTurnEntity, AbstractTurnEntity currentTurnEntity);
}
