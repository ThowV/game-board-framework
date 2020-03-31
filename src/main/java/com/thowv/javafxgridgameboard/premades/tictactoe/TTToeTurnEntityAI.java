package com.thowv.javafxgridgameboard.premades.tictactoe;

import com.thowv.javafxgridgameboard.AbstractGameInstance;
import com.thowv.javafxgridgameboard.GameBoardTile;
import com.thowv.javafxgridgameboard.GameBoardTileType;
import com.thowv.javafxgridgameboard.premades.AbstractTurnEntityRandomAI;

import java.util.ArrayList;

public class TTToeTurnEntityAI extends AbstractTurnEntityRandomAI {
    @Override
    public void takeTurn(AbstractGameInstance gameInstance) {
        ArrayList<GameBoardTile> possibleGameBoardTiles = gameInstance.getGameBoard().getTilesByType(
                GameBoardTileType.HIDDEN);

        gameInstance.getGameBoard().setTileTypes(possibleGameBoardTiles,
                GameBoardTileType.VISIBLE);

        super.takeRandomTurn(gameInstance, possibleGameBoardTiles);
    }
}
