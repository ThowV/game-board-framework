package com.thowv.javafxgridgameboard.premades.tictactoe;

import com.thowv.javafxgridgameboard.AlgorithmHelper;
import com.thowv.javafxgridgameboard.GameBoardTile;
import com.thowv.javafxgridgameboard.GameBoardTileType;

public class TTToeAlgorithms {
    private static final TTToeStartPosition[] startPositions = new TTToeStartPosition[] {
            new TTToeStartPosition(0, 0,
                    new AlgorithmHelper.GameBoardDirection[]{
                            AlgorithmHelper.GameBoardDirection.E,
                            AlgorithmHelper.GameBoardDirection.S
                    }),
            new TTToeStartPosition(1, 1,
                    new AlgorithmHelper.GameBoardDirection[]{
                            AlgorithmHelper.GameBoardDirection.N,
                            AlgorithmHelper.GameBoardDirection.NE,
                            AlgorithmHelper.GameBoardDirection.E,
                            AlgorithmHelper.GameBoardDirection.SE
                    },
                    true),
            new TTToeStartPosition(2, 2,
                    new AlgorithmHelper.GameBoardDirection[]{
                            AlgorithmHelper.GameBoardDirection.W,
                            AlgorithmHelper.GameBoardDirection.N
                    })
    };

    public static GameBoardTileType checkThreeInRow(GameBoardTile[][] gameBoardTiles) {
        for (TTToeStartPosition startPosition : startPositions) {
            GameBoardTileType result = checkThreeInRow(gameBoardTiles, startPosition);
            if (result != null)
                return result;
        }

        return null;
    }

    private static GameBoardTileType checkThreeInRow(GameBoardTile[][] gameBoardTiles, TTToeStartPosition startPosition) {
        GameBoardTile startingTile = gameBoardTiles[startPosition.getXCord()][startPosition.getYCord()];
        GameBoardTileType startingTileType = startingTile.getGameBoardTileType();

        // Check if the game board tile of the start position is owned by a player
        if (!startingTile.isOwnedByPlayer())
            return null;

        // Loop through all given directions
        for (AlgorithmHelper.GameBoardDirection direction : startPosition.getDirections()) {
            int matchedTilesCount = 1; // 1 Since the starting tile counts as well

            int normalDirXCord = startPosition.getXCord();
            int normalDirYCord = startPosition.getYCord();
            int flippedDirXCord = startPosition.getXCord();
            int flippedDirYCord = startPosition.getYCord();

            while (matchedTilesCount < 3) {
                int toBeAdded = 0;

                int[] newCoordinates = AlgorithmHelper.translateDirToCords(
                        direction, normalDirXCord, normalDirYCord);

                toBeAdded += checkForMatch(gameBoardTiles, startingTileType, newCoordinates);

                int[] newFlippedCoordinates = AlgorithmHelper.translateDirToCords(
                        AlgorithmHelper.flipGameBoardDirection(direction),
                        flippedDirXCord, flippedDirYCord);

                toBeAdded += checkForMatch(gameBoardTiles, startingTileType, newFlippedCoordinates);

                if (toBeAdded == 0)
                    break;
                else {
                    // We have to keep working with these coordinates so re-assign them
                    normalDirXCord = newCoordinates[0];
                    normalDirYCord = newCoordinates[1];
                    flippedDirXCord = newFlippedCoordinates[0];
                    flippedDirYCord = newFlippedCoordinates[1];
                }

                matchedTilesCount += toBeAdded;
            }

            // Check if we found three in a row
            if (matchedTilesCount == 3)
                return startingTileType;
        }

        return null;
    }

    private static int checkForMatch(GameBoardTile[][] gameBoardTiles, GameBoardTileType startingTileType, int[] coordinates) {
        int xCord = coordinates[0];
        int yCord = coordinates[1];

        // Check if the new coordinates are inside of the board bounds
        if (xCord >= gameBoardTiles.length || xCord < 0 || yCord >= gameBoardTiles.length || yCord < 0)
            return 0;

        // Check if the current tile type matches the type this loop started with
        if (gameBoardTiles[xCord][yCord].getGameBoardTileType() == startingTileType)
            return 1;

        return 0;
    }
}
