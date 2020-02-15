package com.thowv.reversiboard;

import com.thowv.reversiboard.behaviors.ReversiBoardBehavior;
import com.thowv.reversiboard.skins.ReversiBoardSkin;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class ReversiBoard extends Control {
    private ReversiBoardBehavior reversiBoardBehavior;

    // region Constructors
    public ReversiBoard() {
        this(8);
    }

    public ReversiBoard(int size) {
        this.reversiBoardBehavior = new ReversiBoardBehavior(this, size);
    }
    // endregion

    // region Overrides
    @Override
    public String getUserAgentStylesheet() {
        return ReversiBoard.class.getResource("/control-style.css").toExternalForm();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ReversiBoardSkin(this);
    }
    // endregion

    // region Control behavior
    public void start() {
        reversiBoardBehavior.refreshPopulateBoard();
    }

    public void setTilePieceType(int xCord, int yCord) {
        reversiBoardBehavior.setTilePieceType(xCord, yCord);
    }

    public void setTilePieceType(int xCord, int yCord, BoardTile.TilePieceType tilePieceType) {
        reversiBoardBehavior.setTilePieceType(xCord, yCord, tilePieceType);
    }
    // endregion

    // region Getters and setters
    public ReversiBoardSkin getReversiBoardSkin() {
        return (ReversiBoardSkin)getSkin();
    }

    public int getSize() {
        return reversiBoardBehavior.getBoardSize();
    }

    public BoardTile getTile(int xCord, int yCord) {
        return reversiBoardBehavior.getBoardTileReference(xCord, yCord);
    }
    // endregion
}
