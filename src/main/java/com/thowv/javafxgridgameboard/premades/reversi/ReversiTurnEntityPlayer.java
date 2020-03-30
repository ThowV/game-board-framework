package com.thowv.javafxgridgameboard.premades.reversi;

import com.thowv.javafxgridgameboard.*;
import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;

import java.util.ArrayList;

public class ReversiTurnEntityPlayer extends AbstractTurnEntity {
    public ReversiTurnEntityPlayer() {
        super(EntityType.PLAYER);
    }

    @Override
    public void takeTurn(AbstractGameInstance gameInstance) {
        takeTurn((ReversiGameInstance)gameInstance);
    }

    private void takeTurn(ReversiGameInstance gameInstance) {
        ArrayList<GameBoardTile> possibleGameBoardTiles = ReversiAlgorithms.determineTilePossibilities(
                gameInstance.getGameBoard(), getGameBoardTileType());

        gameInstance.getGameBoard().setTileTypes(possibleGameBoardTiles, GameBoardTileType.INTERACTABLE);
    }

    @Override
    public void onTilePressed(AbstractGameInstance gameInstance, GameBoardTilePressedEvent e) {
        onTilePressed((ReversiGameInstance)gameInstance, e.getXCord(), e.getYCord());
    }

    private void onTilePressed(ReversiGameInstance gameInstance, int xCord, int yCord) {
        gameInstance.doTurn(xCord, yCord);
    }
}
