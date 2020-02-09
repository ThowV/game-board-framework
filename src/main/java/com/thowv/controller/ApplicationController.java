package com.thowv.controller;

import com.thowv.model.ApplicationModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    private ApplicationModel applicationModel;

    @FXML
    Label statusLabel;
    @FXML
    Button startStopButton;

    @FXML
    Label boardSizeLabel;
    @FXML
    Slider boardSizeSlider;

    @FXML
    VBox boardVBox;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        boardSizeSlider.valueProperty().addListener((observableValue, number, t1) ->
                sliderChanged(t1, boardSizeLabel, "Board size"));

        applicationModel = new ApplicationModel(this);
    }

    // region Events
    public void startStopButtonPressed(MouseEvent e) {
        if (applicationModel.getCurrentApplicationState() == ApplicationModel.ApplicationState.Idle)
            applicationModel.setGameRunningState((int)boardSizeSlider.getValue());
        else if (applicationModel.getCurrentApplicationState() == ApplicationModel.ApplicationState.GameRunning)
            applicationModel.setIdleState();
    }

    public void sliderChanged(Number sliderValue, Label matchingLabel, String prefixText) {
        matchingLabel.setText(prefixText + ": " + sliderValue.intValue());
    }
    // endregion

    public void setGameRunningVisuals() {
        statusLabel.setText("Game started");
        startStopButton.setText("Stop");
    }

    public void setIdleVisuals() {
        statusLabel.setText("Game stopped");
        startStopButton.setText("Start");
    }

    public void replaceBoardNode(Node node) {
        VBox.setVgrow(node, Priority.ALWAYS);
        boardVBox.getChildren().add(node);
    }

    public void clearBoardNode() {
        boardVBox.getChildren().clear();
    }
}
