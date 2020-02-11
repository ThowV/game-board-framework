package com.thowv.reversiboard.demo;

import com.thowv.reversiboard.ReversiBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        ReversiBoard reversiBoard = new ReversiBoard();

        primaryStage.setScene(new Scene(reversiBoard));
        primaryStage.setTitle("JavaFX Board Project");

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
