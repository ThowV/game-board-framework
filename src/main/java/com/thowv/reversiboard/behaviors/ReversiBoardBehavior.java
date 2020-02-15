package com.thowv.reversiboard.behaviors;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;

public class ReversiBoardBehavior {
    private ReversiBoard reversiBoardControl;
    private int boardSize;
    private BoardTile[][] boardTileReferences;
    private BoardTile.TilePieceType colorTurn;

    // region Constructors
    public ReversiBoardBehavior(ReversiBoard reversiBoardControl, int boardSize) {
        this.reversiBoardControl = reversiBoardControl;
        this.boardSize = boardSize;
        this.colorTurn = BoardTile.TilePieceType.BLACK;

        reversiBoardControl.addEventHandler(
                BoardTileActivatedEvent.TILE_ACTIVATED,
                this::onBoardTileClick
        );
    }
    // endregion

    // region Behavior
    public void refreshPopulateBoard() {
        boardTileReferences = reversiBoardControl.getReversiBoardSkin().createTiles(boardSize);

        // Wait for board tile skins to load
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(100));
        pauseTransition.setOnFinished(e -> {
            setTilePieceType(3, 3, false);
            setTilePieceType(4, 3, false);
            setTilePieceType(4, 4, false);
            setTilePieceType(3, 4, true);
        });
        pauseTransition.play();
    }

    private BoardTile.TilePieceType flipColorTurn(BoardTile.TilePieceType colorTurn) {
        if (colorTurn == BoardTile.TilePieceType.WHITE)
            colorTurn = BoardTile.TilePieceType.BLACK;
        else
            colorTurn = BoardTile.TilePieceType.WHITE;

        return colorTurn;
    }

    // region Determine active board tiles behavior
    private enum BoardDirection { W, NW, N, NE, E, SE, S, SW }

    private void determineActiveBoardTiles(BoardTile.TilePieceType colorTurn) {
        // Determine all board tiles that match the current colorTurn
        ArrayList<BoardTile> colorMatchedBoardTiles = getBoardTilesByType(colorTurn);

        for (BoardTile boardTile : colorMatchedBoardTiles) {
            for (BoardDirection boardDirection : BoardDirection.values()) {
                determineActiveBoardTiles(boardDirection, colorTurn, boardTile.getXCord(), boardTile.getYCord());
            }
        }
    }

    private void determineActiveBoardTiles(BoardDirection boardDirection, BoardTile.TilePieceType colorTurn,
                                           int startXCord, int startYCord) {
        determineActiveBoardTiles(boardDirection, colorTurn, startXCord, startYCord, false);
    }

    private void determineActiveBoardTiles(BoardDirection boardDirection, BoardTile.TilePieceType colorTurn,
                                           int startXCord, int startYCord, boolean canBeActivated) {
        int[] newCoordinates = translateDirToCords(boardDirection, startXCord, startYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        if (newXCord >= boardSize || newXCord < 0 || newYCord >= boardSize || newYCord < 0)
            return;

        BoardTile boardTile = boardTileReferences[newCoordinates[0]][newCoordinates[1]];
        BoardTile.TilePieceType boardTilePieceType = boardTile.getTilePieceType();

        if (boardTilePieceType == colorTurn || boardTilePieceType == BoardTile.TilePieceType.ACTIVE)
            return;
        else if (boardTilePieceType == flipColorTurn(colorTurn))
            canBeActivated = true;
        else if (boardTilePieceType == BoardTile.TilePieceType.INACTIVE
                && canBeActivated) {
            setTilePieceType(newXCord, newYCord, BoardTile.TilePieceType.ACTIVE, false);
            return;
        }

        determineActiveBoardTiles(boardDirection, colorTurn, newXCord, newYCord, canBeActivated);
    }

    private int[] translateDirToCords(BoardDirection boardDirection, int xCord, int yCord) {
        switch (boardDirection) {
            case W:
                xCord -= 1;
                break;
            case NW:
                xCord -= 1;
                yCord -= 1;
                break;
            case N:
                yCord -= 1;
                break;
            case NE:
                xCord += 1;
                yCord -= 1;
                break;
            case E:
                xCord += 1;
                break;
            case SE:
                xCord += 1;
                yCord += 1;
                break;
            case S:
                yCord += 1;
                break;
            case SW:
                xCord -= 1;
                yCord += 1;
        }

        return new int[]{xCord, yCord};
    }
    // endregion

    // region Flip board tiles from origin behavior
    private void flipBoardTilesFromOrigin(int xCord, int yCord) {
        for (BoardDirection boardDirection : BoardDirection.values()) {
            flipBoardTilesFromOrigin(boardDirection, colorTurn, xCord, yCord);
        }
    }

    private boolean flipBoardTilesFromOrigin(BoardDirection boardDirection, BoardTile.TilePieceType colorTurn,
                                           int startXCord, int startYCord) {
        int[] newCoordinates = translateDirToCords(boardDirection, startXCord, startYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        if (newXCord >= boardSize || newXCord < 0 || newYCord >= boardSize || newYCord < 0)
            return false;

        BoardTile boardTile = boardTileReferences[newCoordinates[0]][newCoordinates[1]];
        BoardTile.TilePieceType boardTilePieceType = boardTile.getTilePieceType();

        if (boardTilePieceType == BoardTile.TilePieceType.ACTIVE
                || boardTilePieceType == BoardTile.TilePieceType.INACTIVE)
            return false;
        else if (boardTilePieceType == colorTurn)
            return true;

        boolean canBeFlipped = flipBoardTilesFromOrigin(boardDirection, colorTurn, newXCord, newYCord);

        if (canBeFlipped) {
            setTilePieceType(newXCord, newYCord, colorTurn, false);
            return true;
        }
        else
            return false;
    }
    // endregion

    private void clearActiveBoardTiles() {
        // Determine all board tiles that match the active enum
        ArrayList<BoardTile> typeMatchedBoardTiles = getBoardTilesByType(BoardTile.TilePieceType.ACTIVE);

        for (BoardTile boardTile : typeMatchedBoardTiles) {
            boardTile.setTilePieceType(BoardTile.TilePieceType.INACTIVE);
        }
    }
    // endregion

    // region Events
    private void onBoardTileClick(BoardTileActivatedEvent e) {
        setTilePieceType(e.getXCord(), e.getYCord());
    }
    // endregion

    // region Getters and setters
    // region Tile piece type setters
    public void setTilePieceType(int xCord, int yCord) {
        setTilePieceType(xCord, yCord, null, true);
    }

    public void setTilePieceType(int xCord, int yCord, boolean determineActiveTiles) {
        setTilePieceType(xCord, yCord, null, determineActiveTiles);
    }

    public void setTilePieceType(int xCord, int yCord, BoardTile.TilePieceType forcedTilePieceType) {
        setTilePieceType(xCord, yCord, forcedTilePieceType, true);
    }

    public void setTilePieceType(int xCord, int yCord, BoardTile.TilePieceType forcedTilePieceType,
                                 boolean determineActiveTiles) {
        // Clear the board if this turn was not forced
        if (forcedTilePieceType == null)
            clearActiveBoardTiles();

        // Determine what tile piece type to use
        BoardTile.TilePieceType tilePieceTypeToUse = forcedTilePieceType;
        if (tilePieceTypeToUse == null)
            tilePieceTypeToUse = colorTurn;

        // Set the tile and all the tiles in between two colors to the correct type
        boardTileReferences[xCord][yCord].setTilePieceType(tilePieceTypeToUse);
        if (determineActiveTiles)
            flipBoardTilesFromOrigin(xCord, yCord);

        // Flip the colorTurn variable for the next move
        if (forcedTilePieceType == null)
            colorTurn = flipColorTurn(colorTurn);

        // Determine all possible active board tiles for the next move
        if (determineActiveTiles)
            determineActiveBoardTiles(colorTurn);
    }
    // endregion

    private ArrayList<BoardTile> getBoardTilesByType(BoardTile.TilePieceType tilePieceType) {
        ArrayList<BoardTile> typeMatchedBoardTiles = new ArrayList<>();

        for (BoardTile[] boardTileReference : boardTileReferences) {
            for (BoardTile boardTile : boardTileReference) {
                if (boardTile.getTilePieceType() == tilePieceType)
                    typeMatchedBoardTiles.add(boardTile);
            }
        }

        return typeMatchedBoardTiles;
    }

    public BoardTile getBoardTileReference(int xCord, int yCord) {
        return boardTileReferences[xCord][yCord];
    }

    public int getBoardSize() {
        return boardSize;
    }
    // endregion
}
