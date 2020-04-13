package com.thowv.javafxgridgameboard;

import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.Serializable;

public class GameBoardTileSkin extends SkinBase<GameBoardTile> implements Serializable {
    private Pane gameBoardTileInner;

    /**
     * Create a new tile using javafx.
     * @param gameBoardTileControl The control of this tile
     */
    protected GameBoardTileSkin(GameBoardTile gameBoardTileControl) {
        super(gameBoardTileControl);

        StackPane stackPane = new StackPane();
        stackPane.getStyleClass().add("game-board-tile");

        gameBoardTileInner = new Pane();
        gameBoardTileInner.getStyleClass().add("game-board-tile-inner");
        gameBoardTileInner.setOnMouseClicked(e -> gameBoardTileControl.getGameBoardTileBehavior().onBoardTileClick());
        setGameBoardTileType(gameBoardTileControl.getGameBoardTileBehavior().getGameBoardTileType());

        stackPane.getChildren().add(gameBoardTileInner);

        // Gets forwarded to the BoardTile.java class. We add the created tile
        getChildren().add(stackPane);
    }

    /**
     * Set the tile type and switch visuals.
     * @param gameBoardTileType The type determining the visuals
     */
    public void setGameBoardTileType(GameBoardTileType gameBoardTileType) {
        if (gameBoardTileInner.getStyleClass().size() == 2)
            gameBoardTileInner.getStyleClass().remove(1);

        if (gameBoardTileType == GameBoardTileType.HIDDEN)
            gameBoardTileInner.getStyleClass().add("game-board-tile-inner-hidden");
        else if (gameBoardTileType == GameBoardTileType.VISIBLE)
            gameBoardTileInner.getStyleClass().add("game-board-tile-inner-visible");
        else if (gameBoardTileType == GameBoardTileType.INTERACTABLE)
            gameBoardTileInner.getStyleClass().add("game-board-tile-inner-interactable");
        else if (gameBoardTileType == GameBoardTileType.PLAYER_1)
            gameBoardTileInner.getStyleClass().add("game-board-tile-inner-player-1");
        else if (gameBoardTileType == GameBoardTileType.PLAYER_2)
            gameBoardTileInner.getStyleClass().add("game-board-tile-inner-player-2");
    }
}
