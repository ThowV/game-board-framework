package com.thowv.reversiboard.behaviors;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;

public class ReversiBoardBehavior {
    private ReversiBoard reversiBoardControl;

    private int boardSize;
    private BoardTile[][] boardTileReferences;

    public ReversiBoardBehavior(ReversiBoard reversiBoardControl, int boardSize) {
        this.reversiBoardControl = reversiBoardControl;
        this.boardSize = boardSize;
    }

    public void refreshPopulateBoard() {
        boardTileReferences = reversiBoardControl.getReversiBoardSkin().createTiles(boardSize);
    }

    public BoardTile getBoardTileReference(int xCord, int yCord) {
        return boardTileReferences[xCord][yCord];
    }

    public int getBoardSize() {
        return boardSize;
    }
}
