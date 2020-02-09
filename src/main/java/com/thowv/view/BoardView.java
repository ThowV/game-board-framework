package com.thowv.view;

import com.thowv.controller.BoardController;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.geometry.Pos;
import javafx.scene.control.Control;
import javafx.scene.layout.*;

public class BoardView extends HBox {
    /**
     * Creates a board view
     * Sizing inspiration taken from https://stackoverflow.com/questions/44979700/square-gridpane-of-square-cells
     * @param boardSize amount of tiles on each axis
     */
    public BoardView(int boardSize) {
        GridPane gridPane = new GridPane();
        VBox verticalCenterVBox = new VBox();

        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        // Alignment
        setAlignment(Pos.CENTER); // This class extends HBox
        verticalCenterVBox.setAlignment(Pos.CENTER);

        // Sizing: horizontalCenterBox (this) and verticalCenterVBox
        setMinSize(boardSize * 10, boardSize * 10);
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(gridPane, Priority.ALWAYS);

        // Sizing: verticalCenterVBox
        NumberBinding numberBinding = Bindings.min(widthProperty(), heightProperty());

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
        getChildren().add(verticalCenterVBox);

        // Add tiles
        updateTiles(gridPane, boardSize);

        new BoardController().instantiate(this, boardSize);
    }

    public void updateTiles(GridPane gridPane, int boardSize) {
        // Add tiles to the board
        for (int x = 0; x < boardSize; x++) {
            for (int y = 0; y < boardSize; y++) {
                TileView tile = new TileView(x, y);

                GridPane.setColumnIndex(tile, x);
                GridPane.setRowIndex(tile, y);

                gridPane.getChildren().add(tile);
            }
        }
    }
}
