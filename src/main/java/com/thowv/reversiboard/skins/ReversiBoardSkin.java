package com.thowv.reversiboard.skins;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;

public class ReversiBoardSkin extends SkinBase {
    public ReversiBoardSkin(ReversiBoard reversiBoardControl) {
        super(reversiBoardControl);

        createBoard(reversiBoardControl.getBoardSize());
    }

    private void createBoard(int boardSize) {
        HBox horizontalCenterHBox = new HBox();
        GridPane gridPane = new GridPane();
        VBox verticalCenterVBox = new VBox();

        gridPane.getStyleClass().add("board");

        // Alignment
        horizontalCenterHBox.setAlignment(Pos.CENTER); // This class extends HBox
        verticalCenterVBox.setAlignment(Pos.CENTER);

        // Sizing: horizontalCenterBox (this) and verticalCenterVBox
        horizontalCenterHBox.setMinSize(boardSize * 10, boardSize * 10);
        HBox.setHgrow(horizontalCenterHBox, Priority.ALWAYS);
        VBox.setVgrow(gridPane, Priority.ALWAYS);

        // Sizing: verticalCenterVBox
        NumberBinding numberBinding = Bindings.min(horizontalCenterHBox.widthProperty(), horizontalCenterHBox.heightProperty());

        verticalCenterVBox.prefWidthProperty().bind(numberBinding);
        verticalCenterVBox.prefHeightProperty().bind(numberBinding);
        verticalCenterVBox.setMaxSize(Control.USE_PREF_SIZE, Control.USE_PREF_SIZE);
        verticalCenterVBox.setFillWidth(true);

        // Sizing: gridPane
        for (int x = 0; x < boardSize; x++) {
            ColumnConstraints columnConstraints = new ColumnConstraints(Control.USE_PREF_SIZE,
                    Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);
            columnConstraints.setHgrow(Priority.SOMETIMES);
            gridPane.getColumnConstraints().add(columnConstraints);
        }

        for (int y = 0; y < boardSize; y++) {
            RowConstraints rowConstraints = new RowConstraints(Control.USE_PREF_SIZE,
                    Control.USE_COMPUTED_SIZE, Double.MAX_VALUE);

            rowConstraints.setVgrow(Priority.SOMETIMES);
            gridPane.getRowConstraints().add(rowConstraints);
        }

        // Connect objects
        verticalCenterVBox.getChildren().add(gridPane);
        horizontalCenterHBox.getChildren().add(verticalCenterVBox);

        // Add tiles
        createTiles(gridPane, boardSize);

        // Gets forwarded to the ReversiBoard.java class. We add the created board
        getChildren().add(horizontalCenterHBox);
    }

    private void createTiles(GridPane gridPane, int boardSize) {
        // Add tiles to the reversi board
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                BoardTile tile = new BoardTile(x, y);

                GridPane.setColumnIndex(tile, x);
                GridPane.setRowIndex(tile, y);

                gridPane.getChildren().add(tile);
            }
        }
    }
}
