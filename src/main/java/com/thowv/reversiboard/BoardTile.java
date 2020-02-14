package com.thowv.reversiboard;

import com.thowv.reversiboard.behaviors.BoardTileBehavior;
import com.thowv.reversiboard.skins.BoardTileSkin;
import com.thowv.reversiboard.skins.ReversiBoardSkin;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class BoardTile extends Control {
    public enum TilePieceType { INACTIVE, ACTIVE, WHITE, BLACK }

    private BoardTileBehavior boardTileBehavior;

    // region Constructors
    public BoardTile(int xCord, int yCord) {
        this(TilePieceType.INACTIVE, xCord, yCord);
    }

    public BoardTile(TilePieceType tilePieceType, int xCord, int yCord) {
        this.boardTileBehavior = new BoardTileBehavior(this, tilePieceType,
                xCord, yCord);
    }
    // endregion

    // region Overrides
    @Override
    protected Skin<?> createDefaultSkin() {
        return new BoardTileSkin(this);
    }
    // endregion

    // region Control behavior

    // endregion

    // region Getters and setters
    public BoardTileSkin getBoardTileSkin() {
        return (BoardTileSkin)getSkin();
    }

    public BoardTileBehavior getBoardTileBehavior() {
        return boardTileBehavior;
    }

    public int getXCord() {
        return boardTileBehavior.getXCord();
    }

    public int getYCord() {
        return boardTileBehavior.getYCord();
    }

    public void setTilePieceType(TilePieceType tilePieceType) {
        boardTileBehavior.setTilePieceType(tilePieceType);
    }

    public TilePieceType getTilePieceType() {
        return boardTileBehavior.getTilePieceType();
    }
    // endregion
}
