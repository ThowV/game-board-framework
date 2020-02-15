package com.thowv.reversiboard.behaviors;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;

public class ReversiBoardBehavior {
    private ReversiBoard reversiBoardControl;
    private int boardSize;
    private BoardTile[][] boardTileReferences;
    private BoardTile.TilePieceType colorTurn;

    // region Constructors
    public ReversiBoardBehavior(ReversiBoard reversiBoardControl, int boardSize) {
        this.reversiBoardControl = reversiBoardControl;
        this.boardSize = boardSize;

        reversiBoardControl.addEventHandler(
                BoardTileActivatedEvent.TILE_ACTIVATED,
                this::onBoardTileClick
        );
    }
    // endregion

    // region Behavior
    public void refreshPopulateBoard() {
        boardTileReferences = reversiBoardControl.getReversiBoardSkin().createTiles(boardSize);
    }

    private void flipColorTurn() {
        if (colorTurn == BoardTile.TilePieceType.BLACK)
            colorTurn = BoardTile.TilePieceType.WHITE;
        else
            colorTurn = BoardTile.TilePieceType.BLACK;
    }
    // endregion

    // region Events
    private void onBoardTileClick(BoardTileActivatedEvent e) {
        setTilePieceType(e.getXCord(), e.getYCord());
    }
    // endregion

    // region Getters and setters
    public void setTilePieceType(int xCord, int yCord) {
        setTilePieceType(xCord, yCord, null);
    }

    public void setTilePieceType(int xCord, int yCord, BoardTile.TilePieceType tilePieceType) {
        if (tilePieceType == null)
            flipColorTurn();
        else
            colorTurn = tilePieceType;

        boardTileReferences[xCord][yCord].setTilePieceType(colorTurn);
    }

    public BoardTile getBoardTileReference(int xCord, int yCord) {
        return boardTileReferences[xCord][yCord];
    }

    public int getBoardSize() {
        return boardSize;
    }
    // endregion
}
