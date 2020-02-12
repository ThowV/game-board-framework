package com.thowv.reversiboard;

import javafx.beans.binding.Bindings;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

class BoardTileSkin extends SkinBase {
    BoardTile boardTileControl;

    BoardTileSkin(BoardTile boardTileControl) {
        super(boardTileControl);

        this.boardTileControl = boardTileControl;

        createTile();
    }

    private void createTile() {
        Pane pane = new Pane();
        pane.getStyleClass().add("tile");

        float circleSize = 10 * 0.3f;

        Circle stonePiece = new Circle();
        stonePiece.getStyleClass().add("stonePieceEmpty");
        stonePiece.radiusProperty().bind(Bindings.divide(pane.widthProperty(), circleSize));
        stonePiece.centerXProperty().bind(pane.widthProperty().divide(circleSize / 1.5f));
        stonePiece.centerYProperty().bind(pane.widthProperty().divide(circleSize / 1.5f));

        //pane.getChildren().add(outerCircle);
        pane.getChildren().add(stonePiece);

        // Gets forwarded to the BoardTile.java class. We add the created tile
        getChildren().add(pane);
    }
}
