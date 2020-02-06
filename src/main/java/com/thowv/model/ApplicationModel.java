package com.thowv.model;

import com.thowv.controller.ApplicationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationModel {
    private ApplicationController applicationController;
    public static Stage stage;

    public ApplicationModel(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    public void startGame() {
        applicationController.replaceBoardNode(BoardModel.createBoard(10, 10));
    }

    public void stopGame() {
        applicationController.clearBoardNode();
    }

    public static void createStage(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ApplicationModel.class.getResource("/view/ApplicationView.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("JavaFX Board Project");

        ApplicationModel.stage = stage;
    }
}
