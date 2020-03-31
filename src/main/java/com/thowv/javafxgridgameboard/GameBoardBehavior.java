package com.thowv.javafxgridgameboard;

import java.util.ArrayList;

public class GameBoardBehavior {
    private GameBoard gameBoardControl;
    private int gameBoardSize;
    private GameBoardTile[][] gameBoardTiles;

    public GameBoardBehavior(GameBoard gameBoardControl, int gameBoardSize) {
        this.gameBoardControl = gameBoardControl;
        this.gameBoardSize = gameBoardSize;
    }

    // region Behavior
    public void resetGameBoardTiles() {
        gameBoardTiles = gameBoardControl.getGameBoardSkin().createGameBoardTiles(gameBoardSize);
    }

    public void clearDecoratedGameBoardTiles() {
        ArrayList<GameBoardTile> matchedGameBoardTiles = getGameBoardTilesByTypes(
                new GameBoardTileType[] { GameBoardTileType.VISIBLE, GameBoardTileType.INTERACTABLE });

        // Hide all type matched game board tiles
        for (GameBoardTile gameBoardTile : matchedGameBoardTiles) {
            gameBoardTile.setGameBoardTileType(GameBoardTileType.HIDDEN);
        }
    }
    // endregion

    // region Getters and setters
    public int getGameBoardSize() {
        return gameBoardSize;
    }

    public void setGameBoardTileType(int xCord, int yCord, GameBoardTileType gameBoardTileType) {
        gameBoardTiles[xCord][yCord].setGameBoardTileType(gameBoardTileType);
    }

    public void setGameBoardTileTypes(ArrayList<GameBoardTile> gameBoardTiles, GameBoardTileType gameBoardTileType) {
        for (GameBoardTile gameBoardTile : gameBoardTiles) {
            gameBoardTile.setGameBoardTileType(gameBoardTileType);
        }
    }

    public void setGameBoardTiles(GameBoardTile[][] gameBoardTiles) {
        this.gameBoardTiles = gameBoardTiles;
    }

    public GameBoardTile getGameBoardTile(int xCord, int yCord) {
        return gameBoardTiles[xCord][yCord];
    }

    public ArrayList<GameBoardTile> getGameBoardTilesByType(GameBoardTileType gameBoardTileType) {
        return getGameBoardTilesByTypes(new GameBoardTileType[] { gameBoardTileType });
    }

    public ArrayList<GameBoardTile> getGameBoardTilesByTypes(GameBoardTileType[] gameBoardTileTypes) {
        ArrayList<GameBoardTile> typeMatchedBoardTiles = new ArrayList<>();

        for (GameBoardTile[] gameBoardTilesX : gameBoardTiles) {
            for (GameBoardTile gameBoardTile : gameBoardTilesX) {
                for (GameBoardTileType gameBoardTileType : gameBoardTileTypes) {
                    if (gameBoardTile.getGameBoardTileType() == gameBoardTileType)
                        typeMatchedBoardTiles.add(gameBoardTile);
                }
            }
        }

        return typeMatchedBoardTiles;
    }
    // endregion
}
