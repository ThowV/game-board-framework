package com.thowv;

import com.thowv.model.ApplicationModel;
import com.thowv.view.ApplicationView;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            ApplicationView applicationModel = new ApplicationView(primaryStage);
            primaryStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
