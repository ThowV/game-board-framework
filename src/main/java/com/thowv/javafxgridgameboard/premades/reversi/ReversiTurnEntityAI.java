package com.thowv.javafxgridgameboard.premades.reversi;

import com.thowv.javafxgridgameboard.*;
import com.thowv.javafxgridgameboard.premades.AbstractTurnEntityRandomAI;

import java.util.ArrayList;

public class ReversiTurnEntityAI extends AbstractTurnEntityRandomAI {
    public ReversiTurnEntityAI(String name) {
        super(name);
    }

    @Override
    public void takeTurn(AbstractGameInstance gameInstance) {
        takeTurn((ReversiGameInstance)gameInstance);
    }

    private void takeTurn(ReversiGameInstance gameInstance) {
        ArrayList<GameBoardTile> possibleGameBoardTiles = ReversiAlgorithms.determineTilePossibilities(
                gameInstance.getGameBoard(), getGameBoardTileType());

        if (possibleGameBoardTiles.size() != 0) {
            gameInstance.getGameBoard().setTileTypes(possibleGameBoardTiles,
                    GameBoardTileType.VISIBLE);
            super.takeRandomTurn(gameInstance, possibleGameBoardTiles);
        }
        else
            gameInstance.passTurn();
    }
}
