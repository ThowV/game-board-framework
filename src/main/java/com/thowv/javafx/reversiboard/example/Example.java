package com.thowv.javafx.reversiboard.example;

import com.thowv.javafx.reversiboard.BoardTile;
import com.thowv.javafx.reversiboard.ReversiBoard;
import com.thowv.javafx.reversiboard.events.BoardTileActivatedEvent;
import com.thowv.javafx.reversiboard.events.ReversiGameEndedEvent;
import com.thowv.javafx.reversiboard.events.ReversiTurnSwitchedEvent;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Example extends Application {
    @Override
    public void start(Stage primaryStage) {
        ReversiBoard reversiBoard = startReversiOption1(primaryStage);

        reversiBoard.addEventHandler(BoardTileActivatedEvent.TILE_ACTIVATED,
                e -> System.out.println("Tile x: " + e.getXCord() + "\tTile y: " + e.getYCord()));

        reversiBoard.addEventHandler(ReversiGameEndedEvent.GAME_ENDED,
                e -> System.out.println("Game ended, the winner is: " + e.getWinningTurnEntity()));

        reversiBoard.addEventHandler(ReversiTurnSwitchedEvent.TURN_SWITCHED,
                e -> System.out.println("Turn switched \n\tfrom " + e.getPastTurnEntity()
                        + " \n\tto " + e.getNextTurnEntity()
                        + " \n\twith action " + e.getTurnAction()));

        System.out.println(reversiBoard.getSize());
        System.out.println(reversiBoard.getTile(0, 0).getTilePieceType());
    }

    private ReversiBoard startReversiOption1(Stage primaryStage) {
        ReversiBoard reversiBoard = new ReversiBoard(
                new ExamplePlayerEntity(BoardTile.TilePieceType.WHITE),
                new ExampleAIEntity(BoardTile.TilePieceType.BLACK)
        );

        primaryStage.setScene(new Scene(reversiBoard));
        primaryStage.setTitle("JavaFX Reversi Board Project");
        primaryStage.show();

        reversiBoard.start();

        return reversiBoard;
    }

    private ReversiBoard startReversiOption2(Stage primaryStage) {
        ReversiBoard reversiBoard = new ReversiBoard();

        primaryStage.setScene(new Scene(reversiBoard));
        primaryStage.setTitle("JavaFX Reversi Board Project");
        primaryStage.show();

        reversiBoard.setTurnEntities(
                new ExamplePlayerEntity(BoardTile.TilePieceType.WHITE),
                new ExampleAIEntity(BoardTile.TilePieceType.BLACK)
        );

        reversiBoard.start();

        return reversiBoard;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
