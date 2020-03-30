package com.thowv.javafxgridgameboard.events;

import com.thowv.javafxgridgameboard.GameBoardTile;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class GameBoardTilePressedEvent extends Event {
    public static final EventType<GameBoardTilePressedEvent> TILE_PRESSED_EVENT_EVENT_TYPE =
            new EventType<>(Event.ANY, "TILE_PRESSED_EVENT_EVENT_TYPE");
    private GameBoardTile gameBoardTile;

    public GameBoardTilePressedEvent(Object source, EventTarget target) {
        super(source, target, TILE_PRESSED_EVENT_EVENT_TYPE);

        this.gameBoardTile = (GameBoardTile) target;
    }

    public int getXCord() {
        return gameBoardTile.getXCord();
    }

    public int getYCord() {
        return gameBoardTile.getYCord();
    }
}
