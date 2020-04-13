package com.thowv.javafxgridgameboard;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;

import java.io.Serializable;

public class GameBoardSkin extends SkinBase<GameBoard> implements Serializable {
    private GridPane boardGridPane;

    /**
     * Create the game board by creating a grid with constraints using javafx.
     * @param gameBoardControl The control of game board
     */
    protected GameBoardSkin(GameBoard gameBoardControl) {
        super(gameBoardControl);
        int gameBoardSize = gameBoardControl.getSize();

        HBox horizontalCenterHBox = new HBox();
        VBox verticalCenterVBox = new VBox();

        boardGridPane = new GridPane();
        boardGridPane.getStyleClass().add("game-board");

        // Alignment
        horizontalCenterHBox.setAlignment(Pos.CENTER); // This class extends HBox
        verticalCenterVBox.setAlignment(Pos.CENTER);

        // Sizing: horizontalCenterBox (this) and verticalCenterVBox
        horizontalCenterHBox.setMinSize(gameBoardSize * 30, gameBoardSize * 30);
        HBox.setHgrow(horizontalCenterHBox, Priority.ALWAYS);
        VBox.setVgrow(boardGridPane, Priority.ALWAYS);

        // Sizing: verticalCenterVBox
        NumberBinding numberBinding = Bindings.min(
                horizontalCenterHBox.widthProperty(), horizontalCenterHBox.heightProperty());

        verticalCenterVBox.prefWidthProperty().bind(numberBinding);
        verticalCenterVBox.prefHeightProperty().bind(numberBinding);
        verticalCenterVBox.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        verticalCenterVBox.setFillWidth(true);

        // Sizing: boardGridPane
        for (int x = 0; x < gameBoardSize; x++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(Control.USE_PREF_SIZE,
                    Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
            columnConstraints.setHgrow(Priority.SOMETIMES);
            boardGridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int y = 0; y < gameBoardSize; y++) {
            RowConstraints rowConstraints = new RowConstraints(Control.USE_PREF_SIZE,
                    Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);

            rowConstraints.setVgrow(Priority.SOMETIMES);
            boardGridPane.getRowConstraints().add(rowConstraints);
        }

        // Connect objects
        verticalCenterVBox.getChildren().add(boardGridPane);
        horizontalCenterHBox.getChildren().add(verticalCenterVBox);

        // Gets forwarded to the ReversiBoard.java class. We add the created board
        getChildren().add(horizontalCenterHBox);

        // Create and send over the game board tiles
        gameBoardControl.getGameBoardBehavior().setGameBoardTiles(createGameBoardTiles(gameBoardSize));
    }

    /**
     * Populate the game board skin with tiles.
     * @param gameBoardSize The size of the game board
     * @return A multidimensional array of board tiles
     */
    public GameBoardTile[][] createGameBoardTiles(int gameBoardSize) {
        GameBoardTile[][] gameBoardTiles = new GameBoardTile[gameBoardSize][gameBoardSize];
        boardGridPane.getChildren().clear();

        // Add game board tiles to the game board
        for (int x = 0; x < gameBoardSize; x++) {
            for (int y = 0; y < gameBoardSize; y++) {
                GameBoardTile tile = new GameBoardTile(x, y);

                GridPane.setColumnIndex(tile, x);
                GridPane.setRowIndex(tile, y);
                boardGridPane.getChildren().add(tile);

                gameBoardTiles[x][y] = tile;
            }
        }

        return gameBoardTiles;
    }
}
