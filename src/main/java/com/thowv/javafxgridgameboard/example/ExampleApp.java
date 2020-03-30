package com.thowv.javafxgridgameboard.example;

import com.thowv.javafxgridgameboard.GameBoard;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiGameInstance;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiTurnEntityAI;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiTurnEntityPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExampleApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create a game board with a given size
        GameBoard gameBoard = new GameBoard(8);

        // Create your stage
        primaryStage.setScene(new Scene(gameBoard));
        primaryStage.setTitle("Reversi Example");
        primaryStage.show();

        // Create an instance with the required parameters
        ReversiGameInstance reversiGameInstance = new ReversiGameInstance(gameBoard,
                new ReversiTurnEntityPlayer(), new ReversiTurnEntityAI());

        // Start the game!
        reversiGameInstance.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
