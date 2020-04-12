package com.thowv.javafxgridgameboard;

import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;
import javafx.application.Platform;

import java.io.Serializable;

public class GameBoardTileBehavior implements Serializable {
    private GameBoardTile gameBoardTileControl;
    private GameBoardTileType gameBoardTileType;
    private boolean ownedByPlayer = false;
    private int xCord;
    private int yCord;

    // region Constructors
    public GameBoardTileBehavior(GameBoardTile gameBoardTileControl, GameBoardTileType gameBoardTileType,
                             int xCord, int yCord) {
        this.gameBoardTileControl = gameBoardTileControl;
        this.gameBoardTileType = gameBoardTileType;
        this.xCord = xCord;
        this.yCord = yCord;
    }
    // endregion

    // region Events
    public void onBoardTileClick() {
        if (gameBoardTileType == GameBoardTileType.INTERACTABLE)
            gameBoardTileControl.fireEvent(
                    new GameBoardTilePressedEvent(this, gameBoardTileControl)
            );
    }
    // endregion

    // region Getters and setters
    public void setGameBoardTileType(GameBoardTileType gameBoardTileType) {
        this.gameBoardTileType = gameBoardTileType;

        gameBoardTileControl.getGameBoardTileSkin().setGameBoardTileType(gameBoardTileType);

        ownedByPlayer = gameBoardTileType == GameBoardTileType.PLAYER_1 || gameBoardTileType == GameBoardTileType.PLAYER_2;
    }

    public GameBoardTileType getGameBoardTileType() {
        return gameBoardTileType;
    }

    public boolean isOwnedByPlayer() {
        return ownedByPlayer;
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }
    // endregion
}
