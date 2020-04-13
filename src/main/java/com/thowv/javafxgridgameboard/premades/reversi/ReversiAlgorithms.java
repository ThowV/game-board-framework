package com.thowv.javafxgridgameboard.premades.reversi;

import com.thowv.javafxgridgameboard.AlgorithmHelper;
import com.thowv.javafxgridgameboard.GameBoardTile;
import com.thowv.javafxgridgameboard.GameBoardTileType;

import java.util.ArrayList;

public class ReversiAlgorithms {
    // region Determine game board tile possibilities
    /**
     * Determine the tiles that can be picked to do a move.
     * @param gameBoardTiles All tiles in the game board
     * @param tileType The tile type that matches the one of the current turn entity
     * @return All possible tiles
     */
    public static ArrayList<GameBoardTile> determineTilePossibilities(GameBoardTile[][] gameBoardTiles,
                                                                      GameBoardTileType tileType) {
        ArrayList<GameBoardTile> possibleTiles = new ArrayList<>();

        //Get all tiles that match the current game board tile type
        ArrayList<GameBoardTile> matchedTiles = getTilesByType(gameBoardTiles, tileType);

        // Determine all game board tiles that match the current game board tile type
        for (GameBoardTile gameBoardTile : matchedTiles) {
            // Do this algorithm for each direction
            for (AlgorithmHelper.GameBoardDirection direction : AlgorithmHelper.GameBoardDirection.values()) {
                // Run algorithm
                GameBoardTile possibility = determineTilePossibilities(gameBoardTiles, tileType,
                        direction, gameBoardTile.getXCord(), gameBoardTile.getYCord(),
                        false);

                if (possibility != null)
                    possibleTiles.add(possibility);
            }
        }

        return possibleTiles;
    }

    private static GameBoardTile determineTilePossibilities(GameBoardTile[][] gameBoardTiles, GameBoardTileType currentTileType,
                                                            AlgorithmHelper.GameBoardDirection direction,
                                                            int startXCord, int startYCord, boolean canBeActivated) {
        // Determine the coordinates of the current tile we are accessing
        int[] newCoordinates = AlgorithmHelper.translateDirToCords(
                direction, startXCord, startYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        // Check if the new coordinates are inside of the board bounds
        if (newXCord >= gameBoardTiles.length || newXCord < 0 || newYCord >= gameBoardTiles.length || newYCord < 0)
            return null;

        // Get the current game board tile we are on
        GameBoardTile currentTile = gameBoardTiles[newXCord][newYCord];

        // Check what kind of tile we are on and if we can add this tile to the list of possibilities
        if (currentTile.getGameBoardTileType() == GameBoardTileType.HIDDEN && canBeActivated) {
            return currentTile;
        }
        else if (currentTile.getGameBoardTileType() != AlgorithmHelper.flipTileType(currentTileType))
            return null; // The tile is anything other than the opposite of the current tile type, so we stop

        return determineTilePossibilities(gameBoardTiles, currentTileType, direction,
                newXCord, newYCord, true);
    }

    private static ArrayList<GameBoardTile> getTilesByType(GameBoardTile[][] gameBoardTiles, GameBoardTileType tileType) {
        ArrayList<GameBoardTile> matchedTiles = new ArrayList<>();

        for (GameBoardTile[] gameBoardTileArray : gameBoardTiles) {
            for (GameBoardTile gameBoardTile : gameBoardTileArray) {
                if (gameBoardTile.getGameBoardTileType() == tileType)
                    matchedTiles.add(gameBoardTile);
            }
        }

        return matchedTiles;
    }
    // endregion

    // region Flip game board tiles from origin
    /**
     * Flip tiles from a given start position in the game board array.
     * @param gameBoard All tiles in the game board
     * @param xCord The x coordinate of the start tile
     * @param yCord The y coordinate of the start tile
     */
    public static void flipTilesFromOrigin(GameBoardTile[][] gameBoard, int xCord, int yCord) {
        GameBoardTileType gameBoardTileType = gameBoard[xCord][yCord].getGameBoardTileType();

        for (AlgorithmHelper.GameBoardDirection direction : AlgorithmHelper.GameBoardDirection.values()) {
            flipTilesFromOrigin(gameBoard, gameBoardTileType, direction, xCord, yCord);
        }
    }

    private static boolean flipTilesFromOrigin(GameBoardTile[][] gameBoardTiles, GameBoardTileType tileType,
                                               AlgorithmHelper.GameBoardDirection direction,
                                               int startXCord, int startYCord) {
        // Determine the coordinates of the current tile we are accessing
        int[] newCoordinates = AlgorithmHelper.translateDirToCords(
                direction, startXCord, startYCord);
        int newXCord = newCoordinates[0];
        int newYCord = newCoordinates[1];

        // Check if the new coordinates are inside of the board bounds
        if (newXCord >= gameBoardTiles.length || newXCord < 0 || newYCord >= gameBoardTiles.length || newYCord < 0)
            return false;

        // Get the current game board tile we are on
        GameBoardTile currentTile = gameBoardTiles[newXCord][newYCord];

        /*
        Check if the current game board tile piece type is equal to the current entities game board tile piece type
        If the current game board tile type is equal to the current entity game board tile it means we can flip tiles,
        the algorithm will now run in reverse order.
        If the current game board tile type is anything other than the opposite of the current entity game board tile
        type, it means there is a gap and we are not allowed to flip tiles.
         */
        if (currentTile.getGameBoardTileType() == tileType)
            return true;
        else if (currentTile.getGameBoardTileType() != AlgorithmHelper.flipTileType(tileType))
            return false;

        boolean canBeFlipped = flipTilesFromOrigin(gameBoardTiles, tileType,
                direction, newXCord, newYCord);

        if (canBeFlipped) {
            gameBoardTiles[newXCord][newYCord].setGameBoardTileType(tileType);
            return true;
        }

        return false;
    }
    // endregion
}
