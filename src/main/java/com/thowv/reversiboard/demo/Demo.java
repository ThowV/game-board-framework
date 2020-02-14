package com.thowv.reversiboard.demo;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import com.thowv.reversiboard.ReversiBoard;
import com.thowv.reversiboard.skins.BoardTileSkin;
import com.thowv.reversiboard.skins.ReversiBoardSkin;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        ReversiBoard reversiBoard = new ReversiBoard();

        primaryStage.setScene(new Scene(reversiBoard));
        primaryStage.setTitle("JavaFX Board Project");

        primaryStage.show();

        // Showcasing behaviour
        reversiBoard.start();

        reversiBoard.addEventHandler(BoardTileActivatedEvent.TILE_ACTIVATED,
                e -> System.out.println("Tile x: " + e.getXCord() + "\tTile y: " + e.getYCord()));

        System.out.println(reversiBoard.getSize());
        System.out.println(reversiBoard.getTile(0, 0));

        //reversiBoard.setTilePieceType(BoardTile.TilePieceType.WHITE, 0, 0);
        //reversiBoard.setTilePieceType(BoardTile.TilePieceType.BLACK, 1, 1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
