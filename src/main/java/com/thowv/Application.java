package com.thowv;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("2.3-javafx-board");
        StackPane layoutPane = new StackPane();

        Label label = new Label();
        label.setText("Application window");
        layoutPane.getChildren().add(label);

        Scene scene = new Scene(layoutPane, 300, 450);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
