package com.thowv.javafxgridgameboard.premades;

import com.thowv.javafxgridgameboard.AbstractGameInstance;
import com.thowv.javafxgridgameboard.AbstractTurnEntity;
import com.thowv.javafxgridgameboard.GameBoardTile;
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractTurnEntityRandomAI extends AbstractTurnEntity {
    private int turnSpeed;

    public AbstractTurnEntityRandomAI() {
        this(2000);
    }

    public AbstractTurnEntityRandomAI(int turnSpeed) {
        super(EntityType.AI);
        this.turnSpeed = turnSpeed;
    }

    @Override
    public abstract void takeTurn(AbstractGameInstance gameInstance);

    public void takeRandomTurn(AbstractGameInstance gameInstance, ArrayList<GameBoardTile> possibleGameBoardTiles) {
        PauseTransition pauseTransition = new PauseTransition(Duration.millis(turnSpeed));
        pauseTransition.setOnFinished(e -> {
            Random random = new Random();
            int upperBound = possibleGameBoardTiles.size();
            int randomNum = random.nextInt(upperBound);

            GameBoardTile gameBoardTile = possibleGameBoardTiles.get(randomNum);

            gameInstance.doTurn(gameBoardTile.getXCord(), gameBoardTile.getYCord());
        });
        pauseTransition.play();
    }

    public int getTurnSpeed() {
        return turnSpeed;
    }
}
