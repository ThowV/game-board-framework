package com.thowv.model;

import com.thowv.model.board.Chip;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class BoardModel {
    private Scene boardScene;
    private int boardSizeX;
    private int boardSizeY;
    private Chip[][] chips;

    public BoardModel(Scene boardScene, int boardSizeX, int boardSizeY) {
        this.boardScene = boardScene;
        this.boardSizeX = boardSizeX;
        this.boardSizeY = boardSizeY;

        chips = new Chip[boardSizeX][boardSizeY];
    }

    public static GridPane createBoard(int sizeX, int sizeY) {
        GridPane boardGridPane = null;

        try {
            boardGridPane = FXMLLoader.load(BoardModel.class.getResource("/view/BoardView.fxml"));
        }
        catch (IOException e) { e.printStackTrace(); }

        return boardGridPane;
    }
}
