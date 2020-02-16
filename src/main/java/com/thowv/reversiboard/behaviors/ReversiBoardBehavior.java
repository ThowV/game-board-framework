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
    private BoardTile[][] boardTileReferences;

    // region Constructors
    public ReversiBoardBehavior(ReversiBoard reversiBoardControl, int boardSize,
                                AbstractReversiTurnEntity reversiEntity1, AbstractReversiTurnEntity reversiEntity2) {
        if (reversiEntity1 != null && reversiEntity2 != null)
            setTurnEntities(reversiEntity1, reversiEntity2);

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

        // Wait for board tile skins to load
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(100));
        pauseTransition.setOnFinished(e -> {
            setTilePieceType(3, 3);
            setTilePieceType(4, 3);
            setTilePieceType(4, 4);
            setTilePieceType(3, 4);
        });
        pauseTransition.play();
    }

    private BoardTile.TilePieceType flipTilePieceType(BoardTile.TilePieceType colorTurn) {
        if (colorTurn == BoardTile.TilePieceType.WHITE)
            colorTurn = BoardTile.TilePieceType.BLACK;
        else
            colorTurn = BoardTile.TilePieceType.WHITE;

        return colorTurn;
    }

    // region Determine active board tiles behavior
    private enum BoardDirection { W, NW, N, NE, E, SE, S, SW }

    private void determineActiveBoardTiles(int turnIndex) {
        // Determine all board tiles that match the current colorTurn
        ArrayList<BoardTile> colorMatchedBoardTiles = getBoardTilesByType(
                turnEntities[turnIndex].getTilePieceType());

        for (BoardTile boardTile : colorMatchedBoardTiles) {
            for (BoardDirection boardDirection : BoardDirection.values()) {
                determineActiveBoardTiles(boardDirection, turnIndex, boardTile.getXCord(), boardTile.getYCord());
            }
        }
    }

    private void determineActiveBoardTiles(BoardDirection boardDirection, int turnIndex,
                                           int startingXCord, int startingYCord) {
        determineActiveBoardTiles(boardDirection, turnIndex, startingXCord, startingYCord, false);
    }

    private void determineActiveBoardTiles(BoardDirection boardDirection, int turnIndex,
                                           int startingXCord, int startingYCord, boolean canBeActivated) {
        int[] newCoordinates = translateDirToCords(boardDirection, startingXCord, startingYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        if (newXCord >= boardSize || newXCord < 0 || newYCord >= boardSize || newYCord < 0)
            return;

        BoardTile.TilePieceType currentTilePieceType = boardTileReferences[newXCord][newYCord].getTilePieceType();
        BoardTile.TilePieceType startingTilePieceType = turnEntities[turnIndex].getTilePieceType();

        if (currentTilePieceType == startingTilePieceType || currentTilePieceType == BoardTile.TilePieceType.ACTIVE)
            return;
        else if (currentTilePieceType == BoardTile.TilePieceType.INACTIVE && !canBeActivated)
            return;
        else if (currentTilePieceType == flipTilePieceType(startingTilePieceType))
            canBeActivated = true;
        else if (currentTilePieceType == BoardTile.TilePieceType.INACTIVE) {
            setTilePieceType(newXCord, newYCord, BoardTile.TilePieceType.ACTIVE);
            return;
        }

        determineActiveBoardTiles(boardDirection, turnIndex, newXCord, newYCord, canBeActivated);
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

        BoardTile boardTile = boardTileReferences[newCoordinates[0]][newCoordinates[1]];
        BoardTile.TilePieceType boardTilePieceType = boardTile.getTilePieceType();

        if (boardTilePieceType == BoardTile.TilePieceType.ACTIVE
                || boardTilePieceType == BoardTile.TilePieceType.INACTIVE)
            return false;
        else if (boardTilePieceType == turnEntities[turnIndex].getTilePieceType())
            return true;

        boolean canBeFlipped = flipBoardTilesFromOrigin(boardDirection, turnIndex, newXCord, newYCord);

        if (canBeFlipped) {
            setTilePieceType(newXCord, newYCord, turnEntities[turnIndex].getTilePieceType());
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
    public void setTurnEntities(AbstractReversiTurnEntity reversiEntity1, AbstractReversiTurnEntity reversiEntity2) {
        turnEntities = new AbstractReversiTurnEntity[2];
        currTurnEntity = 0;

        turnEntities[0] = reversiEntity1;
        turnEntities[1] = reversiEntity2;

        System.out.println(Arrays.toString(turnEntities));
    }

    // region Tile piece type setters
    public void setTilePieceType(int xCord, int yCord) {
        setTilePieceType(xCord, yCord, null);
    }

    public void setTilePieceType(int xCord, int yCord, BoardTile.TilePieceType forcedTilePieceType) {
        // Clear the board if this turn was not forced
        if (forcedTilePieceType == null)
            clearActiveBoardTiles();

        // Determine what tile piece type to use
        BoardTile.TilePieceType tilePieceTypeToUse = forcedTilePieceType;
        if (tilePieceTypeToUse == null)
            tilePieceTypeToUse = turnEntities[currTurnEntity].getTilePieceType();

        // Set the tile to the correct type
        boardTileReferences[xCord][yCord].setTilePieceType(tilePieceTypeToUse);

        if (forcedTilePieceType == null) {
            // Set all tiles in between two of the same types equal to this type
            flipBoardTilesFromOrigin(xCord, yCord, currTurnEntity);

            // Flip the current entity turn variable for the next turn
            currTurnEntity ^= 1;

            // Determine all possible active board tiles for the next turn
            determineActiveBoardTiles(currTurnEntity);

            // Tell the current entity to take its turn
            turnEntities[currTurnEntity].takeTurn(reversiBoardControl);
        }
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
