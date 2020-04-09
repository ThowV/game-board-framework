package com.thowv.javafxgridgameboard.premades.tictactoe;

import com.thowv.javafxgridgameboard.AbstractGameInstance;
import com.thowv.javafxgridgameboard.GameBoardTile;
import com.thowv.javafxgridgameboard.GameBoardTileType;
import com.thowv.javafxgridgameboard.premades.AbstractTurnEntityPlayer;

import java.util.ArrayList;

public class TTToeTurnEntityPlayer extends AbstractTurnEntityPlayer {
    public TTToeTurnEntityPlayer(String name) {
        super(name);
    }

    @Override
    public void takeTurn(AbstractGameInstance gameInstance) {
        ArrayList<GameBoardTile> possibleGameBoardTiles = gameInstance.getGameBoard().getTilesByType(
                GameBoardTileType.HIDDEN);

        gameInstance.getGameBoard().setTileTypes(possibleGameBoardTiles,
                GameBoardTileType.INTERACTABLE);
    }
}
