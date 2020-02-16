package com.thowv.reversiboard.demo;

import com.thowv.reversiboard.AbstractReversiTurnEntity;
import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import com.thowv.reversiboard.ReversiBoard;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        ReversiBoard reversiBoard = new ReversiBoard();

        primaryStage.setScene(new Scene(reversiBoard));
        primaryStage.setTitle("JavaFX Board Project");

        primaryStage.show();

        // Showcasing behaviour
        reversiBoard.setTurnEntities(new DemoEntity(BoardTile.TilePieceType.WHITE),
                new DemoEntity(BoardTile.TilePieceType.BLACK));
        reversiBoard.start();

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(100));
        pauseTransition.setOnFinished(e -> {
            reversiBoard.addEventHandler(BoardTileActivatedEvent.TILE_ACTIVATED,
                    e1 -> System.out.println("Tile x: " + e1.getXCord() + "\tTile y: " + e1.getYCord()));

            System.out.println(reversiBoard.getSize());
            System.out.println(reversiBoard.getTile(0, 0).getTilePieceType());
        });
        pauseTransition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
