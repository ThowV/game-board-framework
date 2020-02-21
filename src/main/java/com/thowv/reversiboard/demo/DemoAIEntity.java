package com.thowv.reversiboard.demo;

import com.thowv.reversiboard.AbstractReversiTurnEntity;
import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class DemoAIEntity extends AbstractReversiTurnEntity {
    public DemoAIEntity(BoardTile.TilePieceType tilePieceType) {
        super(EntityType.AI, tilePieceType);
    }

    @Override
    public void takeTurn(ReversiBoard reversiBoard) {
        if (reversiBoard.getPossibleBoardTiles().size() != 0)
            reversiBoard.visualizePossibleBoardTiles(BoardTile.TilePieceType.VISIBLE);
        else {
            reversiBoard.passTurn();
            return;
        }

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(2000));
        pauseTransition.setOnFinished(e -> {
            ArrayList<BoardTile> possibleBoardTiles = reversiBoard.getPossibleBoardTiles();

            Random random = new Random();
            int upperBound = possibleBoardTiles.size();
            int randomNum = random.nextInt(upperBound);

            BoardTile boardTile = possibleBoardTiles.get(randomNum);

            reversiBoard.takeTurn(boardTile.getXCord(), boardTile.getYCord());
        });
        pauseTransition.play();
    }

    @Override
    public void tilePressed(ReversiBoard reversiBoard, BoardTileActivatedEvent e) { }
}
