package com.thowv.view;

import com.thowv.controller.TileController;
import javafx.beans.binding.Bindings;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TileView extends Pane {
    private Circle innerCircle;
    private Circle outerCircle;

    public TileView(int tilePosX, int tilePosY) {
        super();

        updateTile();

        new TileController().initialize(this, tilePosX, tilePosY);
    }

    private void updateTile() {
        setStyle("-fx-background-color: #fcba03");

        float circleSize = 10 * 0.3f;

        innerCircle = new Circle();
        innerCircle.radiusProperty().bind(Bindings.divide(widthProperty(), circleSize));
        innerCircle.centerXProperty().bind(widthProperty().divide(circleSize / 1.5f));
        innerCircle.centerYProperty().bind(widthProperty().divide(circleSize / 1.5f));
        innerCircle.setFill(Color.rgb(0, 0, 0, 1));
        innerCircle.setStroke(Color.rgb(0, 0, 1));
        innerCircle.setStrokeWidth(5);

        outerCircle = new Circle();
        outerCircle.radiusProperty().bind(Bindings.divide(widthProperty(), circleSize));
        outerCircle.centerXProperty().bind(widthProperty().divide(circleSize / 1.5f));
        outerCircle.centerYProperty().bind(widthProperty().divide(circleSize / 1.5f));
        outerCircle.setFill(Color.rgb(0, 0, 0, 0));
        outerCircle.setStroke(Color.rgb(52, 183, 235));
        outerCircle.setStrokeWidth(5);

        getChildren().add(outerCircle);
        getChildren().add(innerCircle);
    }

    public Circle getInnerCircle() { return innerCircle; }

    public Circle getOuterCircle() { return outerCircle; }
}
