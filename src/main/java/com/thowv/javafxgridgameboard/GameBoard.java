package com.thowv.javafxgridgameboard;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.ArrayList;

public class GameBoard extends Control {
    private GameBoardBehavior gameBoardBehavior;

    public GameBoard(GameBoard gameBoard) {
        this(gameBoard.getSize());
        //this.gameBoardBehavior = new GameBoardBehavior(gameBoard, gameBoard.getSize());
    }

    public GameBoard(int size) {
        this.gameBoardBehavior = new GameBoardBehavior(this, size);
    }

    // region Overrides
    @Override
    public String getUserAgentStylesheet() {
        return GameBoard.class.getResource("/default-style.css").toExternalForm();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GameBoardSkin(this);
    }
    // endregion

    // region Behavior
    public void reset() {
        gameBoardBehavior.resetGameBoardTiles();
    }

    public void clearDecoratedTiles() {
        gameBoardBehavior.clearDecoratedGameBoardTiles();
    }

    public int countTilesByType(GameBoardTileType gameBoardTileType) {
        return gameBoardBehavior.countTilesByType(gameBoardTileType);
    }
    // endregion

    public GameBoardSkin getGameBoardSkin() {
        return (GameBoardSkin)getSkin();
    }

    public GameBoardBehavior getGameBoardBehavior() {
        return gameBoardBehavior;
    }

    public int getSize() {
        return gameBoardBehavior.getGameBoardSize();
    }

    public void setTileType(int xCord, int yCord, GameBoardTileType gameBoardTileType) {
        gameBoardBehavior.setGameBoardTileType(xCord, yCord, gameBoardTileType);
    }

    public void setTileTypes(ArrayList<GameBoardTile> gameBoardTiles, GameBoardTileType gameBoardTileType) {
        gameBoardBehavior.setGameBoardTileTypes(gameBoardTiles, gameBoardTileType);
    }

    public GameBoardTile getTile(int xCord, int yCord) {
        return gameBoardBehavior.getGameBoardTile(xCord, yCord);
    }

    public ArrayList<GameBoardTile> getTilesByType(GameBoardTileType gameBoardTileType) {
        return gameBoardBehavior.getGameBoardTilesByType(gameBoardTileType);
    }

    public ArrayList<GameBoardTile> getTilesByTypes(GameBoardTileType[] gameBoardTileTypes) {
        return gameBoardBehavior.getGameBoardTilesByTypes(gameBoardTileTypes);
    }
    // endregion
}
