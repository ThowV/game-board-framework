package com.thowv.reversiboard;

import javafx.event.EventHandler;

public class BoardTileActivatedEventHandler implements EventHandler<BoardTileActivatedEvent> {
    @Override
    public void handle(BoardTileActivatedEvent event) {
        BoardTile boardTile = (BoardTile)event.getTarget();
    }
}
