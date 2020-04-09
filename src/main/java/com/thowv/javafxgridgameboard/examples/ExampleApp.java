package com.thowv.javafxgridgameboard.examples;

import com.thowv.javafxgridgameboard.AbstractGameInstance;
import com.thowv.javafxgridgameboard.AbstractTurnEntity;
import com.thowv.javafxgridgameboard.GameBoard;
import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;
import com.thowv.javafxgridgameboard.listeners.GameEndListener;
import com.thowv.javafxgridgameboard.listeners.GameStartListener;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiGameInstance;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiTurnEntityAI;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiTurnEntityPlayer;
import com.thowv.javafxgridgameboard.premades.tictactoe.TTToeGameInstance;
import com.thowv.javafxgridgameboard.premades.tictactoe.TTToeTurnEntityAI;
import com.thowv.javafxgridgameboard.premades.tictactoe.TTToeTurnEntityPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Arrays;

public class ExampleApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        AbstractGameInstance gameInstance;

        gameInstance = reversiExample(primaryStage);
        //gameInstance = tictactoeExample(primaryStage);

        // Subscribe to events
        gameInstance.onGameStart(() -> System.out.println("Game started!"));
        gameInstance.onGameEnd(new GameEndListener() {
            @Override
            public void onGameEnd(AbstractTurnEntity winningTurnEntity, AbstractTurnEntity losingTurnEntity) {
                System.out.println("Game ended with a winner " + winningTurnEntity
                        + "\nAnd a loser... " + losingTurnEntity);
            }

            @Override
            public void onGameEnd(AbstractTurnEntity[] tieTurnEntities) {
                System.out.println("Game ended with a tie!");
            }
        });
        gameInstance.onTurnSwitch((e1, e2) -> System.out.println("Turn switched from " + e1 + " to " + e2));

        // Start the game!
        gameInstance.start();
    }

    private AbstractGameInstance reversiExample(Stage primaryStage) {
        // Create a game board with a given size
        GameBoard gameBoard = new GameBoard(8);

        // Create your stage
        primaryStage.setScene(new Scene(gameBoard));
        primaryStage.setTitle("Reversi Example");
        primaryStage.show();

        // Create an instance with the required parameters
        return new ReversiGameInstance(gameBoard,
                new ReversiTurnEntityPlayer(), new ReversiTurnEntityAI());
    }

    private AbstractGameInstance tictactoeExample(Stage primaryStage) {
        // Create a game board with a given size
        GameBoard gameBoard = new GameBoard(3);

        // Create your stage
        primaryStage.setScene(new Scene(gameBoard));
        primaryStage.setTitle("Tic Tac Toe Example");
        primaryStage.show();

        // Create an instance with the required parameters
        return new TTToeGameInstance(gameBoard,
                new TTToeTurnEntityPlayer(), new TTToeTurnEntityAI());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
