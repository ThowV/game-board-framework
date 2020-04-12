package com.thowv.javafxgridgameboard;

import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.io.Serializable;

public class GameBoardTile extends Control implements Serializable {
    private GameBoardTileBehavior gameBoardTileBehavior;

    // region Constructors
    public GameBoardTile(int xCord, int yCord) {
        this(GameBoardTileType.HIDDEN, xCord, yCord);
    }

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
    public GameBoardTileSkin getGameBoardTileSkin() {
        return (GameBoardTileSkin)getSkin();
    }

    public GameBoardTileBehavior getGameBoardTileBehavior() {
        return gameBoardTileBehavior;
    }

    public void setGameBoardTileType(GameBoardTileType gameBoardTileType) {
        gameBoardTileBehavior.setGameBoardTileType(gameBoardTileType);
    }

    public GameBoardTileType getGameBoardTileType() {
        return gameBoardTileBehavior.getGameBoardTileType();
    }

    public int getXCord() {
        return gameBoardTileBehavior.getXCord();
    }

    public int getYCord() {
        return gameBoardTileBehavior.getYCord();
    }

    public boolean isOwnedByPlayer() {
        return gameBoardTileBehavior.isOwnedByPlayer();
    }

    @Override
    public String toString() {
        return this.hashCode() + ", " + this.getXCord() + ", " + this.getYCord() + ", " + this.getGameBoardTileType();
    }
    // endregion
}
