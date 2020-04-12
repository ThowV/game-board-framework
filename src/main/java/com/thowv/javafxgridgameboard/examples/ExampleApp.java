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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ExampleApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Create the game instances
        AbstractGameInstance reversiGameInstance = reversiExample();
        AbstractGameInstance tttoeGameInstance = tictactoeExample();

        // Subscribe to events that the reversi game instance fires
        subscribeToEvents(reversiGameInstance);

        // Set the stage
        VBox root = new VBox();
        root.getChildren().addAll(reversiGameInstance.getGameBoard(), tttoeGameInstance.getGameBoard());
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Reversi Example");
        primaryStage.show();

        // Start the games!
        reversiGameInstance.start();
        tttoeGameInstance.start();
    }

    private AbstractGameInstance reversiExample() {
        // Create a game board with a given size
        GameBoard gameBoard = new GameBoard(8);

        // Create an instance with the required parameters
        return new ReversiGameInstance(gameBoard,
                new ReversiTurnEntityPlayer("ReversiPlayer"),
                new ReversiTurnEntityAI("ReversiAI")
        );
    }

    private AbstractGameInstance tictactoeExample() {
        // Create a game board with a given size
        GameBoard gameBoard = new GameBoard(3);

        // Create an instance with the required parameters
        return new TTToeGameInstance(gameBoard,
                new TTToeTurnEntityPlayer("TTToePlayer"),
                new TTToeTurnEntityAI("TTToeAI")
        );
    }

    private void subscribeToEvents(AbstractGameInstance gameInstance) {
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}
