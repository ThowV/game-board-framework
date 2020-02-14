package com.thowv.reversiboard.behaviors;

import com.thowv.reversiboard.BoardTile;

public class BoardTileBehavior {
    private BoardTile boardTileControl;
    private BoardTile.TilePieceType tilePieceType;
    private int xCord;
    private int yCord;

    public BoardTileBehavior(BoardTile boardTileControl, BoardTile.TilePieceType tilePieceType, int xCord, int yCord) {
        this.boardTileControl = boardTileControl;
        this.tilePieceType = BoardTile.TilePieceType.INACTIVE;
        this.xCord = xCord;
        this.yCord = yCord;
    }

    public void onMouseClicked() {
        setTilePieceType(BoardTile.TilePieceType.WHITE);
    }

    public void setTilePieceType(BoardTile.TilePieceType tilePieceType) {
        this.tilePieceType = tilePieceType;
        boardTileControl.getBoardTileSkin().setTilePieceType(tilePieceType);
    }

    public BoardTile.TilePieceType getTilePieceType() {
        return tilePieceType;
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }
}
