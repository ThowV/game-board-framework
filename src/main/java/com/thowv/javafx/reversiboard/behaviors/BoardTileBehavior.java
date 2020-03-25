package com.thowv.javafx.reversiboard.behaviors;

import com.thowv.javafx.reversiboard.BoardTile;
import com.thowv.javafx.reversiboard.events.BoardTileActivatedEvent;

public class BoardTileBehavior {
    private BoardTile boardTileControl;
    private BoardTile.TilePieceType tilePieceType;
    private boolean ofColor = false;
    private int xCord;
    private int yCord;

    // region Constructors
    public BoardTileBehavior(BoardTile boardTileControl, BoardTile.TilePieceType tilePieceType, int xCord, int yCord) {
        this.boardTileControl = boardTileControl;
        this.tilePieceType = tilePieceType;
        this.xCord = xCord;
        this.yCord = yCord;
    }
    // endregion

    // region Events
    public void onBoardTileClick() {
        if (tilePieceType == BoardTile.TilePieceType.ACTIVE)
            boardTileControl.fireEvent(
                    new BoardTileActivatedEvent(this, boardTileControl)
            );
    }
    // endregion

    // region Getters and setters
    public void setTilePieceType(BoardTile.TilePieceType tilePieceType) {
        this.tilePieceType = tilePieceType;
        boardTileControl.getBoardTileSkin().setTilePieceType(tilePieceType);

        ofColor = tilePieceType == BoardTile.TilePieceType.WHITE || tilePieceType == BoardTile.TilePieceType.BLACK;
    }

    public BoardTile.TilePieceType getTilePieceType() {
        return tilePieceType;
    }

    public boolean isOfColor() {
        return ofColor;
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }
    // endregion
}
