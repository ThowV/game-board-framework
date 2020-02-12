package com.thowv.reversiboard;

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

        this.xCord = ((BoardTile)target).getXCord();
        this.yCord = ((BoardTile)target).getYCord();
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
