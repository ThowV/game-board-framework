package com.thowv.view;

import com.thowv.model.ApplicationModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ApplicationView {

    public ApplicationView(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(ApplicationModel.class.getResource("/fxml/Application.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("JavaFX Board Project");

        ApplicationModel.stage = stage;
    }
}
