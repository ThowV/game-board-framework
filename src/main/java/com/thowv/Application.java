package com.thowv;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Application.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("JavaFX Board Project");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
