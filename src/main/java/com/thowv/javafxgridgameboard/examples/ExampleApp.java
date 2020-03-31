package com.thowv.javafxgridgameboard.examples;

import com.thowv.javafxgridgameboard.GameBoard;
import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;
import com.thowv.javafxgridgameboard.events.GameEndedEvent;
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
        reversiExample(primaryStage);
        //tictactoeExample(primaryStage);
    }

    private void reversiExample(Stage primaryStage) {
        // Create a game board with a given size
        GameBoard gameBoard = new GameBoard(8);

        gameBoard.addEventHandler(GameBoardTilePressedEvent.TILE_PRESSED_EVENT_EVENT_TYPE,
                e -> System.out.println("Tile x: " + e.getXCord() + "\tTile y: " + e.getYCord()));

        gameBoard.addEventHandler(GameEndedEvent.GAME_ENDED_EVENT_TYPE,
                e -> System.out.println("Game ended: " + Arrays.toString(e.getWinningTileType())));

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

    private void tictactoeExample(Stage primaryStage) {
        // Create a game board with a given size
        GameBoard gameBoard = new GameBoard(3);

        gameBoard.addEventHandler(GameBoardTilePressedEvent.TILE_PRESSED_EVENT_EVENT_TYPE,
                e -> System.out.println("Tile x: " + e.getXCord() + "\tTile y: " + e.getYCord()));

        gameBoard.addEventHandler(GameEndedEvent.GAME_ENDED_EVENT_TYPE,
                e -> System.out.println("Game ended: " + Arrays.toString(e.getWinningTileType())));

        // Create your stage
        primaryStage.setScene(new Scene(gameBoard));
        primaryStage.setTitle("Tic Tac Toe Example");
        primaryStage.show();

        // Create an instance with the required parameters
        TTToeGameInstance tttoeGameInstance = new TTToeGameInstance(gameBoard,
                new TTToeTurnEntityPlayer(), new TTToeTurnEntityAI());

        // Start the game!
        tttoeGameInstance.startGame();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
