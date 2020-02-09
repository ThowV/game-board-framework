package com.thowv.controller;

import com.thowv.model.TileModel;
import com.thowv.view.TileView;
import javafx.animation.FadeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class TileController {
    TileView tileView;
    FadeTransition fadeTransition;

    public void initialize(TileView tileView, int tilePosX, int tilePosY) {
        this.tileView = tileView;

        initializeAnimations();

        tileView.getInnerCircle().setOnMouseEntered(e -> onMouseEntered());
        tileView.getInnerCircle().setOnMouseClicked(e -> onMouseClicked());
        tileView.getInnerCircle().setOnMouseExited(e -> onMouseExited());

        new TileModel(this, tilePosX, tilePosY);
    }

    private void initializeAnimations() {
        fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setNode(tileView.getOuterCircle());
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.5);
        fadeTransition.setCycleCount(50);
        fadeTransition.setAutoReverse(true);
    }

    private void onMouseEntered() {
        tileView.getInnerCircle().setStroke(Color.rgb(0, 0, 0, 0)); // setVisible causes scaling issue
        tileView.getOuterCircle().setStroke(Color.rgb(52, 183, 235, 1)); // setVisible causes scaling issue
        fadeTransition.play();
    }

    private void onMouseClicked() {
        System.out.println("Mouse clicked on tile");
    }

    private void onMouseExited() {
        tileView.getInnerCircle().setStroke(Color.rgb(0, 0, 0, 1)); // setVisible causes scaling issue
        tileView.getOuterCircle().setStroke(Color.rgb(52, 183, 235, 0)); // setVisible causes scaling issue
        fadeTransition.stop();
    }
}
