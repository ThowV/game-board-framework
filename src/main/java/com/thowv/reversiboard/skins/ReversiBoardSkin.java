package com.thowv.reversiboard.skins;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;

public class ReversiBoardSkin extends SkinBase<ReversiBoard> {
    private BoardTile[][] boardTiles;
    private GridPane boardGridPane;
    private int boardSize;

    public ReversiBoardSkin(ReversiBoard reversiBoardControl) {
        super(reversiBoardControl);

        boardSize = reversiBoardControl.getSize();
        createBoard();
    }

    private void createBoard() {
        HBox horizontalCenterHBox = new HBox();
        VBox verticalCenterVBox = new VBox();

        boardGridPane = new GridPane();
        boardGridPane.getStyleClass().add("board");

        refreshBoardTiles();

        // Alignment
        horizontalCenterHBox.setAlignment(Pos.CENTER); // This class extends HBox
        verticalCenterVBox.setAlignment(Pos.CENTER);

        // Sizing: horizontalCenterBox (this) and verticalCenterVBox
        horizontalCenterHBox.setMinSize(boardSize * 30, boardSize * 30);
        HBox.setHgrow(horizontalCenterHBox, Priority.ALWAYS);
        VBox.setVgrow(boardGridPane, Priority.ALWAYS);

        // Sizing: verticalCenterVBox
        NumberBinding numberBinding = Bindings.min(horizontalCenterHBox.widthProperty(), horizontalCenterHBox.heightProperty());

        verticalCenterVBox.prefWidthProperty().bind(numberBinding);
        verticalCenterVBox.prefHeightProperty().bind(numberBinding);
        verticalCenterVBox.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        verticalCenterVBox.setFillWidth(true);

        // Sizing: boardGridPane
        for (int x = 0; x < boardSize; x++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(Control.USE_PREF_SIZE,
                    Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
            columnConstraints.setHgrow(Priority.SOMETIMES);
            boardGridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int y = 0; y < boardSize; y++) {
            RowConstraints rowConstraints = new RowConstraints(Control.USE_PREF_SIZE,
                    Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);

            rowConstraints.setVgrow(Priority.SOMETIMES);
            boardGridPane.getRowConstraints().add(rowConstraints);
        }

        // Connect objects
        verticalCenterVBox.getChildren().add(boardGridPane);
        horizontalCenterHBox.getChildren().add(verticalCenterVBox);

        // Add tiles
        createTiles();

        // Gets forwarded to the ReversiBoard.java class. We add the created board
        getChildren().add(horizontalCenterHBox);
    }

    private void createTiles() {
        // Add tiles to the reversi board
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                BoardTile tile = new BoardTile(x, y);

                GridPane.setColumnIndex(tile, x);
                GridPane.setRowIndex(tile, y);

                boardGridPane.getChildren().add(tile);
                boardTiles[x][y] = tile;
            }
        }
    }

    public BoardTile getBoardTile(int xCord, int yCord) {
        return boardTiles[xCord][yCord];
    }

    public void refreshBoardTiles() {
        boardGridPane.getChildren().clear();
        boardTiles = new BoardTile[boardSize][boardSize];
        createTiles();
    }
}
