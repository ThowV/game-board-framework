package com.thowv.reversiboard.behaviors;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;

public class BoardTileBehavior {
    private BoardTile boardTileControl;
    private BoardTile.TilePieceType tilePieceType;
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
                    new BoardTileActivatedEvent(boardTileControl.getBoardTileSkin(), boardTileControl)
            );
    }
    // endregion

    // region Getters and setters
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
    // endregion
}
