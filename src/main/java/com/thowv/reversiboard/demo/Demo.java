package com.thowv.reversiboard.demo;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import com.thowv.reversiboard.ReversiBoard;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) {
        ReversiBoard reversiBoard = startReversiOption1(primaryStage);

        reversiBoard.addEventHandler(BoardTileActivatedEvent.TILE_ACTIVATED,
                e1 -> System.out.println("Tile x: " + e1.getXCord() + "\tTile y: " + e1.getYCord()));

        System.out.println(reversiBoard.getSize());
        System.out.println(reversiBoard.getTile(0, 0).getTilePieceType());
    }

    private ReversiBoard startReversiOption1(Stage primaryStage) {
        ReversiBoard reversiBoard = new ReversiBoard(
                new DemoPlayerEntity(BoardTile.TilePieceType.WHITE),
                new DemoAIEntity(BoardTile.TilePieceType.BLACK)
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
                new DemoPlayerEntity(BoardTile.TilePieceType.WHITE),
                new DemoAIEntity(BoardTile.TilePieceType.BLACK)
        );

        reversiBoard.start();

        return reversiBoard;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
