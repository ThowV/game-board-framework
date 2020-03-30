package com.thowv.javafxgridgameboard;

import javafx.beans.binding.Bindings;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class GameBoardTileSkin extends SkinBase<GameBoardTile> {
    private Circle gameBoardTileInner;

    protected GameBoardTileSkin(GameBoardTile gameBoardTileControl) {
        super(gameBoardTileControl);

        Pane pane = new Pane();
        pane.getStyleClass().add("game-board-tile");

        float circleSize = 10 * 0.3f;

        gameBoardTileInner = new Circle();

        gameBoardTileInner.radiusProperty().bind(Bindings.divide(pane.widthProperty(), circleSize));
        gameBoardTileInner.centerXProperty().bind(pane.widthProperty().divide(circleSize / 1.5f));
        gameBoardTileInner.centerYProperty().bind(pane.widthProperty().divide(circleSize / 1.5f));

        gameBoardTileInner.setOnMouseClicked(e -> gameBoardTileControl.getGameBoardTileBehavior().onBoardTileClick());
        setGameBoardTileType(gameBoardTileControl.getGameBoardTileBehavior().getGameBoardTileType());

        pane.getChildren().add(gameBoardTileInner);

        // Gets forwarded to the BoardTile.java class. We add the created tile
        getChildren().add(pane);
    }

    public void setGameBoardTileType(GameBoardTileType gameBoardTileType) {
        gameBoardTileInner.getStyleClass().clear();

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
