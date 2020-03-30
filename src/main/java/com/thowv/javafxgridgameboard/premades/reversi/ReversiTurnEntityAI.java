package com.thowv.javafxgridgameboard.premades.reversi;

import com.thowv.javafxgridgameboard.*;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class ReversiTurnEntityAI extends AbstractTurnEntity {
    public ReversiTurnEntityAI() {
        super(EntityType.AI);
    }

    @Override
    public void takeTurn(AbstractGameInstance gameInstance) {
        takeTurn((ReversiGameInstance)gameInstance);
    }

    private void takeTurn(ReversiGameInstance gameInstance) {
        ArrayList<GameBoardTile> possibleGameBoardTiles = ReversiAlgorithms.determineTilePossibilities(
                gameInstance.getGameBoard(), getGameBoardTileType());

        if (possibleGameBoardTiles.size() != 0)
            gameInstance.getGameBoard().setTileTypes(possibleGameBoardTiles, GameBoardTileType.VISIBLE);
        else {
            gameInstance.passTurn();
            return;
        }

        PauseTransition pauseTransition = new PauseTransition(Duration.millis(2000));
        pauseTransition.setOnFinished(e -> {
            Random random = new Random();
            int upperBound = possibleGameBoardTiles.size();
            int randomNum = random.nextInt(upperBound);

            GameBoardTile gameBoardTile = possibleGameBoardTiles.get(randomNum);

            gameInstance.doTurn(gameBoardTile.getXCord(), gameBoardTile.getYCord());
        });
        pauseTransition.play();
    }
}
