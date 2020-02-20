package com.thowv.reversiboard.behaviors;

import com.thowv.reversiboard.AbstractReversiTurnEntity;
import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.*;

public class ReversiBoardBehavior {
    private ReversiBoard reversiBoardControl;

    public AbstractReversiTurnEntity[] turnEntities;
    public int currTurnEntity;

    private int boardSize;
    private BoardTile[][] boardTiles;
    private ArrayList<BoardTile> possibleBoardTiles;

    // region Constructors
    public ReversiBoardBehavior(ReversiBoard reversiBoardControl, int boardSize,
                                AbstractReversiTurnEntity reversiEntity1, AbstractReversiTurnEntity reversiEntity2) {
        if (reversiEntity1 != null && reversiEntity2 != null)
            setTurnEntities(reversiEntity1, reversiEntity2);

        this.reversiBoardControl = reversiBoardControl;
        this.boardSize = boardSize;
        this.possibleBoardTiles = new ArrayList<>();

        reversiBoardControl.addEventHandler(
                BoardTileActivatedEvent.TILE_ACTIVATED,
                e -> getCurrentTurnEntity().tilePressed(reversiBoardControl, e)
        );
    }
    // endregion

    // region Behavior
    public void refreshBoard() {
        boardTiles = reversiBoardControl.getReversiBoardSkin().createTiles(boardSize);

        // Wait for board tile skins to load
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(100));
        pauseTransition.setOnFinished(e -> {
            int boardSize = (int) Math.floor((double)getBoardSize() / 2);

            // Set the starting tiles
            setTilePieceType(boardSize - 1, boardSize - 1, true);
            setTilePieceType(boardSize, boardSize - 1, true);
            setTilePieceType(boardSize, boardSize, true);
            setTilePieceType(boardSize - 1, boardSize);
        });
        pauseTransition.play();
    }

    private BoardTile.TilePieceType flipTilePieceType(BoardTile.TilePieceType colorTurn) {
        if (colorTurn == BoardTile.TilePieceType.WHITE)
            return BoardTile.TilePieceType.BLACK;
        else if (colorTurn == BoardTile.TilePieceType.BLACK)
            return BoardTile.TilePieceType.WHITE;

        return null;
    }

    // region Possible board tile behavior
    private enum BoardDirection { W, NW, N, NE, E, SE, S, SW }

    private void determinePossibleBoardTiles() {
        possibleBoardTiles.clear();

        // Determine all board tiles that match the current colorTurn
        ArrayList<BoardTile> colorMatchedBoardTiles = getBoardTilesByType(
                getCurrentTurnEntity().getTilePieceType());

        for (BoardTile boardTile : colorMatchedBoardTiles) {
            for (BoardDirection boardDirection : BoardDirection.values()) {
                determinePossibleBoardTiles(boardDirection, boardTile.getXCord(), boardTile.getYCord());
            }
        }
    }

    private void determinePossibleBoardTiles(BoardDirection boardDirection, int startingXCord, int startingYCord) {
        determinePossibleBoardTiles(boardDirection, startingXCord, startingYCord, false);
    }

    private void determinePossibleBoardTiles(BoardDirection boardDirection, int startingXCord, int startingYCord,
                                             boolean canBeActivated) {
        // Determine the coordinates of the current tile we are accessing
        int[] newCoordinates = translateDirToCords(boardDirection, startingXCord, startingYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        // Check if the new coordinates are inside of the board bounds
        if (newXCord >= boardSize || newXCord < 0 || newYCord >= boardSize || newYCord < 0)
            return;

        BoardTile currentTile = boardTiles[newXCord][newYCord];
        BoardTile.TilePieceType startingTilePieceType = getCurrentTurnEntity().getTilePieceType();

        // Check what kind of tile we are on and if we can add this tile to the list of possibilities
        if (currentTile.getTilePieceType() == BoardTile.TilePieceType.HIDDEN && canBeActivated) {
            possibleBoardTiles.add(currentTile);
            return;
        }
        else if (currentTile.getTilePieceType() != flipTilePieceType(startingTilePieceType))
            return;

        determinePossibleBoardTiles(boardDirection, newXCord, newYCord, true);
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

    public void visualizePossibleBoardTiles(BoardTile.TilePieceType tilePieceType) {
        for (BoardTile boardTile : possibleBoardTiles) {
            boardTile.setTilePieceType(tilePieceType);
        }
    }
    // endregion

    // region Flip board tiles from origin behavior
    private void flipBoardTilesFromOrigin(int xCord, int yCord, int turnIndex) {
        for (BoardDirection boardDirection : BoardDirection.values()) {
            flipBoardTilesFromOrigin(boardDirection, turnIndex, xCord, yCord);
        }
    }

    private boolean flipBoardTilesFromOrigin(BoardDirection boardDirection, int turnIndex,
                                           int startXCord, int startYCord) {
        int[] newCoordinates = translateDirToCords(boardDirection, startXCord, startYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        if (newXCord >= boardSize || newXCord < 0 || newYCord >= boardSize || newYCord < 0)
            return false;

        BoardTile boardTile = boardTiles[newXCord][newYCord];

        // Check if the current tile piece type is equal to the current entities tile piece type
        if (boardTile.getTilePieceType() == getCurrentTurnEntity().getTilePieceType())
            return true;
        else if (boardTile.getTilePieceType() != flipTilePieceType(getCurrentTurnEntity().getTilePieceType()))
            return false;

        boolean canBeFlipped = flipBoardTilesFromOrigin(boardDirection, turnIndex, newXCord, newYCord);

        if (canBeFlipped) {
            setTilePieceType(newXCord, newYCord, getCurrentTurnEntity().getTilePieceType());
            return true;
        }

        return false;
    }
    // endregion

    private void clearBoardTileVisuals() {
        ArrayList<BoardTile> typeMatchedBoardTiles;

        // Determine all board tiles that match the active enum
        if (getCurrentTurnEntity().getEntityType() == AbstractReversiTurnEntity.EntityType.PLAYER)
            typeMatchedBoardTiles = getBoardTilesByType(BoardTile.TilePieceType.ACTIVE);
        else
            typeMatchedBoardTiles = getBoardTilesByType(BoardTile.TilePieceType.VISIBLE);

        for (BoardTile boardTile : typeMatchedBoardTiles) {
            boardTile.setTilePieceType(BoardTile.TilePieceType.HIDDEN);
        }
    }
    // endregion

    // region Getters and setters
    public void setTurnEntities(AbstractReversiTurnEntity reversiEntity1, AbstractReversiTurnEntity reversiEntity2) {
        turnEntities = new AbstractReversiTurnEntity[2];
        currTurnEntity = 0;

        turnEntities[0] = reversiEntity1;
        turnEntities[1] = reversiEntity2;
    }

    public AbstractReversiTurnEntity getCurrentTurnEntity() {
        return turnEntities[currTurnEntity];
    }

    // region Tile piece type setters
    public void setTilePieceType(int xCord, int yCord) {
        setTilePieceType(xCord, yCord, null, false);
    }

    public void setTilePieceType(int xCord, int yCord, BoardTile.TilePieceType forcedTilePieceType) {
        setTilePieceType(xCord, yCord, forcedTilePieceType, false);
    }

    public void setTilePieceType(int xCord, int yCord, boolean denyTakeTurn) {
        setTilePieceType(xCord, yCord, null, denyTakeTurn);
    }

    public void setTilePieceType(int xCord, int yCord, BoardTile.TilePieceType forcedTilePieceType, boolean denyTakeTurn) {
        // Clear the board if this turn was not forced
        if (forcedTilePieceType == null)
            clearBoardTileVisuals();

        // Determine what tile piece type to use
        BoardTile.TilePieceType tilePieceTypeToUse = forcedTilePieceType;
        if (tilePieceTypeToUse == null)
            tilePieceTypeToUse = getCurrentTurnEntity().getTilePieceType();

        // Set the tile to the correct type
        boardTiles[xCord][yCord].setTilePieceType(tilePieceTypeToUse);

        // If a type is forced it means that the placement is not done by a player or AI
        if (forcedTilePieceType == null) {
            // Set all tiles in between two of the same types equal to this type
            flipBoardTilesFromOrigin(xCord, yCord, currTurnEntity);

            // Flip the current entity turn variable for the next turn
            currTurnEntity ^= 1;

            // Determine all possible active board tiles for the next turn
            determinePossibleBoardTiles();

            // Tell the current entity to take its turn
            if (!denyTakeTurn)
                getCurrentTurnEntity().takeTurn(reversiBoardControl);
        }
    }
    // endregion

    private ArrayList<BoardTile> getBoardTilesByType(BoardTile.TilePieceType tilePieceType) {
        ArrayList<BoardTile> typeMatchedBoardTiles = new ArrayList<>();

        for (BoardTile[] boardTilesX : boardTiles) {
            for (BoardTile boardTile : boardTilesX) {
                if (boardTile.getTilePieceType() == tilePieceType)
                    typeMatchedBoardTiles.add(boardTile);
            }
        }

        return typeMatchedBoardTiles;
    }

    public BoardTile getBoardTiles(int xCord, int yCord) {
        return boardTiles[xCord][yCord];
    }

    public int getBoardSize() {
        return boardSize;
    }

    public ArrayList<BoardTile> getPossibleBoardTiles() {
        return possibleBoardTiles;
    }
    // endregion
}
