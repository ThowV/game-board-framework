package com.thowv.javafxgridgameboard.premades.reversi;

import com.thowv.javafxgridgameboard.GameBoard;
import com.thowv.javafxgridgameboard.GameBoardTile;
import com.thowv.javafxgridgameboard.GameBoardTileType;

import java.util.ArrayList;

public class ReversiAlgorithms {
    private enum Direction { W, NW, N, NE, E, SE, S, SW }

    private static int[] translateDirToCords(Direction direction, int xCord, int yCord) {
        switch (direction) {
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

    private static GameBoardTileType flipTilePieceType(GameBoardTileType gameBoardTileType) {
        if (gameBoardTileType == GameBoardTileType.PLAYER_1)
            return GameBoardTileType.PLAYER_2;
        else if (gameBoardTileType == GameBoardTileType.PLAYER_2)
            return GameBoardTileType.PLAYER_1;

        return null;
    }

    // region Determine game board tile possibilities
    public static ArrayList<GameBoardTile> determineTilePossibilities(GameBoard gameBoard,
                                                                      GameBoardTileType currentTileType) {
        ArrayList<GameBoardTile> possibleTiles = new ArrayList<>();

        //Get all tiles that match the current game board tile type
        ArrayList<GameBoardTile> matchedTiles = gameBoard.getTilesByType(currentTileType);

        // Determine all game board tiles that match the current game board tile type
        for (GameBoardTile gameBoardTile : matchedTiles) {
            // Do this algorithm for each direction
            for (Direction direction : Direction.values()) {
                // Run algorithm
                GameBoardTile possibility = determineTilePossibilities(gameBoard, currentTileType,
                        direction, gameBoardTile.getXCord(), gameBoardTile.getYCord(),
                        false);

                if (possibility != null)
                    possibleTiles.add(possibility);
            }
        }

        return possibleTiles;
    }

    private static GameBoardTile determineTilePossibilities(GameBoard gameBoard, GameBoardTileType currentTileType,
                                                   Direction direction, int startXCord, int startYCord,
                                                   boolean canBeActivated) {
        // Determine the coordinates of the current tile we are accessing
        int[] newCoordinates = translateDirToCords(direction, startXCord, startYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        // Check if the new coordinates are inside of the board bounds
        if (newXCord >= gameBoard.getSize() || newXCord < 0 || newYCord >= gameBoard.getSize() || newYCord < 0)
            return null;

        // Get the current game board tile we are on
        GameBoardTile currentTile = gameBoard.getTile(newXCord, newYCord);

        // Check what kind of tile we are on and if we can add this tile to the list of possibilities
        if (currentTile.getGameBoardTileType() == GameBoardTileType.HIDDEN && canBeActivated) {
            return currentTile;
        }
        else if (currentTile.getGameBoardTileType() != flipTilePieceType(currentTileType))
            return null; // The tile is anything other than the opposite of the current tile type, so we stop

        return determineTilePossibilities(gameBoard, currentTileType, direction,
                newXCord, newYCord, true);
    }
    // endregion

    // region Flip game board tiles from origin
    public static void flipTilesFromOrigin(GameBoard gameBoard, GameBoardTileType currentTileType,
                                           int xCord, int yCord) {
        for (Direction direction : Direction.values()) {
            flipTilesFromOrigin(gameBoard, currentTileType, direction, xCord, yCord);
        }
    }

    private static boolean flipTilesFromOrigin(GameBoard gameBoard, GameBoardTileType currentTileType,
                                               Direction direction, int startXCord, int startYCord) {
        // Determine the coordinates of the current tile we are accessing
        int[] newCoordinates = translateDirToCords(direction, startXCord, startYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        // Check if the new coordinates are inside of the board bounds
        if (newXCord >= gameBoard.getSize() || newXCord < 0 || newYCord >= gameBoard.getSize() || newYCord < 0)
            return false;

        // Get the current game board tile we are on
        GameBoardTile currentTile = gameBoard.getTile(newXCord, newYCord);

        /*
        Check if the current game board tile piece type is equal to the current entities game board tile piece type
        If the current game board tile type is equal to the current entity game board tile it means we can flip tiles,
        the algorithm will now run in reverse order.
        If the current game board tile type is anything other than the opposite of the current entity game board tile
        type, it means there is a gap and we are not allowed to flip tiles.
         */
        if (currentTile.getGameBoardTileType() == currentTileType)
            return true;
        else if (currentTile.getGameBoardTileType() != flipTilePieceType(currentTileType))
            return false;

        boolean canBeFlipped = flipTilesFromOrigin(gameBoard, currentTileType,
                direction, newXCord, newYCord);

        if (canBeFlipped) {
            gameBoard.setTileType(newXCord, newYCord, currentTileType);
            return true;
        }

        return false;
    }
    // endregion
}
