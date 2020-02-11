package com.thowv.reversiboard;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class ReversiBoard extends Control {
    private int boardSize;

    public ReversiBoard() {
        this(10);
    }

    public ReversiBoard(int size) {
        this.boardSize = size;

        setFocusTraversable(true);
    }

    @Override
    public String getUserAgentStylesheet() {
        return ReversiBoard.class.getResource("/board-view.css").toExternalForm();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ReversiBoardSkin(this);
    }

    int getBoardSize() {
        return boardSize;
    }
}
