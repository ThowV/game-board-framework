package com.thowv.reversiboard.events;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.skins.BoardTileSkin;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class BoardTileSkinLoadedEvent extends Event {
    private BoardTileSkin boardTileSkin;
    private int xCord;
    private int yCord;

    public BoardTileSkinLoadedEvent() {
        super(TILE_SKIN_LOADED);
    }

    public BoardTileSkinLoadedEvent(Object source, EventTarget target) {
        super(source, target, TILE_SKIN_LOADED);

        BoardTile boardTile = (BoardTile)target;

        this.boardTileSkin = (BoardTileSkin)boardTile.getSkin();
        this.xCord = boardTile.getXCord();
        this.yCord = boardTile.getYCord();
    }

    public static final EventType<BoardTileSkinLoadedEvent> TILE_SKIN_LOADED =
            new EventType<>(Event.ANY, "TILE_ACTIVATED");

    @Override
    public BoardTileSkinLoadedEvent copyFor(Object newSource, EventTarget newTarget) {
        return (BoardTileSkinLoadedEvent) super.copyFor(newSource, newTarget);
    }

    @Override
    public EventType<? extends BoardTileSkinLoadedEvent> getEventType() {
        return (EventType<? extends BoardTileSkinLoadedEvent>) super.getEventType();
    }

    public BoardTileSkin getBoardTileSkin() {
        return boardTileSkin;
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }
}
