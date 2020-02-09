package com.thowv.controller;

import com.thowv.model.BoardModel;
import com.thowv.view.BoardView;

public class BoardController {
    private BoardView boardView;

    public void instantiate(BoardView boardView, int boardSize) {
        this.boardView = boardView;

        new BoardModel(boardSize);
    }
}
