package com.thowv.reversiboard.events;

import com.thowv.reversiboard.BoardTile;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class BoardTileActivatedEvent extends Event {
    public static final EventType<BoardTileActivatedEvent> TILE_ACTIVATED =
            new EventType<>(Event.ANY, "TILE_ACTIVATED");
    private BoardTile boardTile;

    public BoardTileActivatedEvent(Object source, EventTarget target) {
        super(source, target, TILE_ACTIVATED);

        this.boardTile = (BoardTile)target;
    }

    public int getXCord() {
        return boardTile.getXCord();
    }

    public int getYCord() {
        return boardTile.getYCord();
    }
}
