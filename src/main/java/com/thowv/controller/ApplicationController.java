package com.thowv.controller;

import com.thowv.model.ApplicationModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {
    private ApplicationModel applicationModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        applicationModel = new ApplicationModel(this);
    }

    @FXML
    Label statusLabel;
    @FXML
    Button gameStateButton;

    private enum tempGameState { GameStarted, GameStopped};
    private tempGameState currentGameState = tempGameState.GameStopped;

    public void startButtonPressed() {
        if (currentGameState == tempGameState.GameStopped) {
            applicationModel.startGame();
            currentGameState = tempGameState.GameStarted;

            System.out.println("Game started");
            statusLabel.setText("Game started");
            gameStateButton.setText("Stop");
        }
        else if (currentGameState == tempGameState.GameStarted) {
            applicationModel.stopGame();
            currentGameState = tempGameState.GameStopped;

            System.out.println("Game stopped");
            statusLabel.setText("Game stopped");
            gameStateButton.setText("Start");
        }
    }

    @FXML
    VBox boardVBox;

    public void replaceBoardNode(Node node) {
        boardVBox.getChildren().add(node);
    }

    public void clearBoardNode() {
        boardVBox.getChildren().clear();
    }
}
