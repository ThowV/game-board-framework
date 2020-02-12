package com.thowv.reversiboard;

import javafx.event.EventHandler;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class BoardTile extends Control {
    private int xCord;
    private int yCord;

    BoardTile(int xCord, int yCord, EventHandler<? super BoardTileActivatedEvent> boardTileActivatedEventHandler) {
        this.xCord = xCord;
        this.yCord = yCord;

        addEventHandler(
                BoardTileActivatedEvent.TILE_ACTIVATED,
                boardTileActivatedEventHandler
        );
    }

    @Override
    public String getUserAgentStylesheet() {
        return ReversiBoard.class.getResource("/tile-view.css").toExternalForm();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new BoardTileSkin(this);
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }
}
