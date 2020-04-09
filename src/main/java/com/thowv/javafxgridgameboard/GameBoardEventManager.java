package com.thowv.javafxgridgameboard;

import com.thowv.javafxgridgameboard.listeners.GameEndListener;
import com.thowv.javafxgridgameboard.listeners.GameStartListener;
import com.thowv.javafxgridgameboard.listeners.TurnSwitchListener;

import java.util.ArrayList;
import java.util.List;

public class GameBoardEventManager {
    private List<GameStartListener> gameStartListeners = new ArrayList<>();
    private List<GameEndListener> gameEndListeners = new ArrayList<>();
    private List<TurnSwitchListener> turnSwitchListeners = new ArrayList<>();

    // region onGameStart event
    public void onGameStart(GameStartListener listener) {
        gameStartListeners.add(listener);
    }

    protected void notifyOnGameStart(AbstractTurnEntity currentTurnEntity) {
        for (GameStartListener listener : gameStartListeners)
            listener.onGameStart(currentTurnEntity);
    }
    // endregion

    // region onGameEnd event
    public void onGameEnd(GameEndListener listener) {
        gameEndListeners.add(listener);
    }

    protected void notifyOnGameEnd(AbstractTurnEntity winningTurnEntity, AbstractTurnEntity losingTurnEntity) {
        for (GameEndListener listener : gameEndListeners)
            listener.onGameEnd(winningTurnEntity, losingTurnEntity);
    }

    protected void notifyOnGameEnd(AbstractTurnEntity[] tieTurnEntities) {
        for (GameEndListener listener : gameEndListeners)
            listener.onGameEnd(tieTurnEntities);
    }
    // endregion

    // region onTurnSwitch event
    public void onTurnSwitch(TurnSwitchListener listener) {
        turnSwitchListeners.add(listener);
    }

    protected void notifyOnTurnSwitch(AbstractTurnEntity currentTurnEntity, AbstractTurnEntity previousTurnEntity) {
        for (TurnSwitchListener listener : turnSwitchListeners)
            listener.onTurnSwitch(currentTurnEntity, previousTurnEntity);
    }
    // endregion
}
