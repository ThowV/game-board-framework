package com.thowv.reversiboard;

import com.thowv.reversiboard.skins.BoardTileSkin;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

public class BoardTile extends Control {
    public enum TilePieceType { WHITE, BLACK, NONE }
    private TilePieceType tilePieceType;

    private int xCord;
    private int yCord;

    public BoardTile(int xCord, int yCord) {
        this.xCord = xCord;
        this.yCord = yCord;

        this.tilePieceType = TilePieceType.NONE;
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

    public void setTilePieceType(TilePieceType tilePieceType) {
        this.tilePieceType = tilePieceType;
        ((BoardTileSkin)getSkin()).setTilePieceType(tilePieceType);
    }

    public TilePieceType getTilePieceType() {
        return tilePieceType;
    }
}
