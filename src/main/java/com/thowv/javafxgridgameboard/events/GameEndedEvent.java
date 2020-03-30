package com.thowv.javafxgridgameboard.events;

import com.thowv.javafxgridgameboard.GameBoardTileType;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class GameEndedEvent extends Event {
    public static final EventType<GameEndedEvent> GAME_ENDED_EVENT_TYPE =
            new EventType<>(Event.ANY, "GAME_ENDED_EVENT_TYPE");
    private GameBoardTileType winningTileType;

    public GameEndedEvent(Object source, EventTarget target, GameBoardTileType winningTileType) {
        super(source, target, GAME_ENDED_EVENT_TYPE);
        this.winningTileType = winningTileType;
    }

    public GameBoardTileType getWinningTileType() {
        return winningTileType;
    }
}
