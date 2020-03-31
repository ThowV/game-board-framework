package com.thowv.javafxgridgameboard;

public class AlgorithmHelper {
    public enum GameBoardDirection { W, NW, N, NE, E, SE, S, SW }

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

    public static GameBoardTileType flipTileType(GameBoardTileType gameBoardTileType) {
        if (gameBoardTileType == GameBoardTileType.PLAYER_1)
            return GameBoardTileType.PLAYER_2;
        else if (gameBoardTileType == GameBoardTileType.PLAYER_2)
            return GameBoardTileType.PLAYER_1;

        return null;
    }
}
