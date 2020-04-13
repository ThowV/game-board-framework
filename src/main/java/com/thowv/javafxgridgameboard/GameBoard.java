package com.thowv.javafxgridgameboard;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Thomas
 */
public class GameBoard extends Control implements Serializable {
    private GameBoardBehavior gameBoardBehavior;

    /**
     * Create a game board clone.
     * @param gameBoard The original game board
     */
    public GameBoard(GameBoard gameBoard) {
        this(gameBoard.getSize());
    }

    /**
     * Create a game board.
     * @param size The size of the game board
     */
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
    /**
     * Reset the game board.
     */
    public void reset() {
        gameBoardBehavior.resetGameBoardTiles();
    }

    /**
     * Clear decorated states on every tile.
     */
    public void clearDecoratedTiles() {
        gameBoardBehavior.clearDecoratedGameBoardTiles();
    }

    /**
     * Count tiles of a given type.
     * @param gameBoardTileType The type that has to be counted
     * @return Amount of tiles of the given type
     */
    public int countTilesByType(GameBoardTileType gameBoardTileType) {
        return gameBoardBehavior.countTilesByType(gameBoardTileType);
    }
    // endregion

    /**
     * @return The game board skin
     */
    public GameBoardSkin getGameBoardSkin() {
        return (GameBoardSkin)getSkin();
    }

    /**
     * @return The game board behavior
     */
    public GameBoardBehavior getGameBoardBehavior() {
        return gameBoardBehavior;
    }

    /**
     * @return The size of the board
     */
    public int getSize() {
        return gameBoardBehavior.getGameBoardSize();
    }

    /**
     * Set a tile type at a given location.
     * @param xCord The x coordinate of the tile
     * @param yCord The y coordinate of the tile
     * @param gameBoardTileType The type that the tile has to be set to
     */
    public void setTileType(int xCord, int yCord, GameBoardTileType gameBoardTileType) {
        gameBoardBehavior.setGameBoardTileType(xCord, yCord, gameBoardTileType);
    }

    /**
     * Copy a multidimensional array of board tiles to the game board.
     * @param gameBoardTiles A multidimensional array of board tiles
     */
    public void copyTiles(GameBoardTile[][] gameBoardTiles) {
        gameBoardBehavior.copyGameBoardTiles(gameBoardTiles);
    }

    /**
     * Copy a list of board tiles to the game board.
     * @param gameBoardTiles A list of board tiles
     */
    public void copyTiles(ArrayList<GameBoardTile> gameBoardTiles) {
        gameBoardBehavior.copyGameBoardTiles(gameBoardTiles);
    }

    /**
     * Set the type of a given list of board tiles.
     * @param gameBoardTiles The list of board tiles
     * @param forcedType The type the tiles have to be set to
     */
    public void setTileTypes(ArrayList<GameBoardTile> gameBoardTiles, GameBoardTileType forcedType) {
        gameBoardBehavior.setGameBoardTileTypes(gameBoardTiles, forcedType);
    }

    /**
     * Get a board tile at a given location.
     * @param xCord The x coordinate of the tile
     * @param yCord The y coordinate of the tile
     * @return The tile that was requested
     */
    public GameBoardTile getTile(int xCord, int yCord) {
        return gameBoardBehavior.getGameBoardTile(xCord, yCord);
    }

    /**
     * Get a list of tiles with a given type.
     * @param gameBoardTileType The type of tile requested
     * @return A list of board tiles with the given type
     */
    public ArrayList<GameBoardTile> getTilesByType(GameBoardTileType gameBoardTileType) {
        return gameBoardBehavior.getGameBoardTilesByType(gameBoardTileType);
    }

    /**
     * Get a list of tiles with multiple given types.
     * @param gameBoardTileTypes The types of tile requested
     * @return A list of board tiles with the given types
     */
    public ArrayList<GameBoardTile> getTilesByTypes(GameBoardTileType[] gameBoardTileTypes) {
        return gameBoardBehavior.getGameBoardTilesByTypes(gameBoardTileTypes);
    }

    /**
     * @return All tiles in the board game
     */
    public GameBoardTile[][] getAllTiles() {
        return gameBoardBehavior.getAllGameBoardTiles();
    }

    /**
     * @return All tiles in the board game as a deep copy
     */
    public GameBoardTile[][] getAllTilesAsDeepCopy() {
        return gameBoardBehavior.getAllGameBoardTilesCopy();
    }
    // endregion
}
