package com.thowv.javafxgridgameboard;

public class AlgorithmHelper {
    public enum GameBoardDirection { W, NW, N, NE, E, SE, S, SW }

    /**
     * Translate coordinates to new coordinates using a given directions.
     * @param direction The direction enum used for translating the coordinates
     * @param xCord The starting x coordinate
     * @param yCord The starting y coordinate
     * @return The set of new coordinates
     */
    public static int[] translateDirToCords(GameBoardDirection direction, int xCord, int yCord) {
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

    /**
     * Flip a direction enum into the opposite direction.
     * @param direction The direction enum to be flipped
     * @return The new direction
     */
    public static GameBoardDirection flipGameBoardDirection(GameBoardDirection direction) {
        switch (direction) {
            case W:
                return GameBoardDirection.E;
            case NW:
                return GameBoardDirection.SE;
            case N:
                return GameBoardDirection.S;
            case NE:
                return GameBoardDirection.SW;
            case E:
                return GameBoardDirection.W;
            case SE:
                return GameBoardDirection.NW;
            case S:
                return GameBoardDirection.N;
            case SW:
                return GameBoardDirection.NE;
        }

        return direction;
    }

    /**
     * Flip a tile type enum to the opposite type.
     * @param gameBoardTileType The tile type to be flipped
     * @return The new tile type
     */
    public static GameBoardTileType flipTileType(GameBoardTileType gameBoardTileType) {
        if (gameBoardTileType == GameBoardTileType.PLAYER_1)
            return GameBoardTileType.PLAYER_2;
        else if (gameBoardTileType == GameBoardTileType.PLAYER_2)
            return GameBoardTileType.PLAYER_1;

        return null;
    }
}
