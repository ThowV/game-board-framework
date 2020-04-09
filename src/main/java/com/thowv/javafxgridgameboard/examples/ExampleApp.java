package com.thowv.javafxgridgameboard.examples;

import com.thowv.javafxgridgameboard.AbstractGameInstance;
import com.thowv.javafxgridgameboard.AbstractTurnEntity;
import com.thowv.javafxgridgameboard.GameBoard;
import com.thowv.javafxgridgameboard.listeners.GameEndListener;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiGameInstance;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiTurnEntityAI;
import com.thowv.javafxgridgameboard.premades.reversi.ReversiTurnEntityPlayer;
import com.thowv.javafxgridgameboard.premades.tictactoe.TTToeGameInstance;
import com.thowv.javafxgridgameboard.premades.tictactoe.TTToeTurnEntityAI;
import com.thowv.javafxgridgameboard.premades.tictactoe.TTToeTurnEntityPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExampleApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        AbstractGameInstance gameInstance;

        gameInstance = reversiExample(primaryStage);
        //gameInstance = tictactoeExample(primaryStage);

        // Subscribe to events
        gameInstance.onGameStart((currentTurnEntity) -> System.out.println("Game started! "
                + currentTurnEntity.getName() + " has the first move."));

        gameInstance.onGameEnd(new GameEndListener() {
            @Override
            public void onGameEnd(AbstractTurnEntity winningTurnEntity, AbstractTurnEntity losingTurnEntity) {
                System.out.println("Game ended with a winner " + winningTurnEntity.getName()
                        + "\nAnd a loser... " + losingTurnEntity.getName());
            }

            @Override
            public void onGameEnd(AbstractTurnEntity[] tieTurnEntities) {
                System.out.println("Game ended with a tie!");
            }
        });

        gameInstance.onTurnSwitch((currentTurnEntity, previousTurnEntity) -> System.out.println("Turn switched!"
                + "\n\tFrom: " + previousTurnEntity.getName() + " - Points: " + previousTurnEntity.getPoints()
                + "\n\tTo: " + currentTurnEntity.getName() + " - Points: " + currentTurnEntity.getPoints())
        );

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
                new ReversiTurnEntityPlayer("ReversiPlayer"),
                new ReversiTurnEntityAI("ReversiAI")
        );
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
                new TTToeTurnEntityPlayer("TTToePlayer"),
                new TTToeTurnEntityAI("TTToeAI")
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
