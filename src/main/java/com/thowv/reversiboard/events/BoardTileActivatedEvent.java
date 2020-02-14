package com.thowv.reversiboard.events;

import com.thowv.reversiboard.BoardTile;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class BoardTileActivatedEvent extends Event {
    private int xCord;
    private int yCord;

    public BoardTileActivatedEvent() {
        super(TILE_ACTIVATED);
    }

    public BoardTileActivatedEvent(Object source, EventTarget target) {
        super(source, target, TILE_ACTIVATED);

        BoardTile boardTile = (BoardTile)target;

        this.xCord = boardTile.getXCord();
        this.yCord = boardTile.getYCord();
    }

    public static final EventType<BoardTileActivatedEvent> TILE_ACTIVATED =
            new EventType<>(Event.ANY, "TILE_ACTIVATED");

    @Override
    public BoardTileActivatedEvent copyFor(Object newSource, EventTarget newTarget) {
        return (BoardTileActivatedEvent) super.copyFor(newSource, newTarget);
    }

    @Override
    public EventType<? extends BoardTileActivatedEvent> getEventType() {
        return (EventType<? extends BoardTileActivatedEvent>) super.getEventType();
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }
}