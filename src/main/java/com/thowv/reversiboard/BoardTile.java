package com.thowv.reversiboard;

import com.thowv.reversiboard.skins.BoardTileSkin;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class BoardTile extends Control {
    private int xCord;
    private int yCord;

    public BoardTile(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;
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
