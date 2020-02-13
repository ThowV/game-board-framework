package com.thowv.reversiboard;

import com.thowv.reversiboard.skins.ReversiBoardSkin;
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
        return ReversiBoard.class.getResource("/control-style.css").toExternalForm();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ReversiBoardSkin(this);
    }

    private ReversiBoardSkin getBoardSkin() {
        return (ReversiBoardSkin)getSkin();
    }

    // region Behavior
    public void clear() {
        getBoardSkin().refreshBoardTiles();
    }

    public int getSize() {
        return boardSize;
    }

    public BoardTile getTile(int xCord, int yCord) {
        return getBoardSkin().getBoardTile(xCord, yCord);
    }

    public void setTilePieceType(BoardTile.TilePieceType tilePieceType, int xCord, int yCord) {
        getBoardSkin().getBoardTile(xCord, yCord).setTilePieceType(tilePieceType);
    }
    // endregion
}
