package com.thowv.javafxgridgameboard;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.io.Serializable;

public class GameBoardTile extends Control implements Serializable {
    private GameBoardTileBehavior gameBoardTileBehavior;

    // region Constructors
    /**
     * Create a tile board clone.
     * @param gameBoardTile The original board tile
     */
    public GameBoardTile(GameBoardTile gameBoardTile) {
        this(gameBoardTile.getGameBoardTileType(),
                gameBoardTile.getXCord(), gameBoardTile.getYCord());
    }

    /**
     * Create a board tile with coordinates.
     * @param xCord The x coordinate of the tile
     * @param yCord The y coordinate of the tile
     */
    public GameBoardTile(int xCord, int yCord) {
        this(GameBoardTileType.HIDDEN, xCord, yCord);
    }

    /**
     * Create a board tile with a type and coordinates.
     * @param gameBoardTileType The type enum that this tile holds
     * @param xCord The x coordinate of the tile
     * @param yCord The y coordinate of the tile
     */
    public GameBoardTile(GameBoardTileType gameBoardTileType, int xCord, int yCord) {
        this.gameBoardTileBehavior = new GameBoardTileBehavior(this, gameBoardTileType, xCord, yCord);
    }
    // endregion

    // region Overrides
    @Override
    protected Skin<?> createDefaultSkin() {
        return new GameBoardTileSkin(this);
    }
    // endregion

    // region Getters and setters

    /**
     * @return The game board tile skin
     */
    public GameBoardTileSkin getGameBoardTileSkin() {
        return (GameBoardTileSkin)getSkin();
    }

    /**
     * @return The game board tile behavior
     */
    public GameBoardTileBehavior getGameBoardTileBehavior() {
        return gameBoardTileBehavior;
    }

    /**
     * @param gameBoardTileType The type enum this tile should take on
     */
    public void setGameBoardTileType(GameBoardTileType gameBoardTileType) {
        gameBoardTileBehavior.setGameBoardTileType(gameBoardTileType);
    }

    /**
     * @return The type that this tile holds
     */
    public GameBoardTileType getGameBoardTileType() {
        return gameBoardTileBehavior.getGameBoardTileType();
    }

    /**
     * @return The x coordinate of this tile
     */
    public int getXCord() {
        return gameBoardTileBehavior.getXCord();
    }

    /**
     * @return The y coordinate of this tile
     */
    public int getYCord() {
        return gameBoardTileBehavior.getYCord();
    }

    /**
     * @return A boolean telling if the tile is owned by a player type
     */
    public boolean isOwnedByPlayer() {
        return gameBoardTileBehavior.isOwnedByPlayer();
    }

    @Override
    public String toString() {
        return this.hashCode() + ", " + this.getXCord() + ", " + this.getYCord() + ", " + this.getGameBoardTileType();
    }
    // endregion
}
