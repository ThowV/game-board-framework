package com.thowv.javafxgridgameboard.premades.tictactoe;

import com.thowv.javafxgridgameboard.AbstractGameInstance;
import com.thowv.javafxgridgameboard.AbstractTurnEntity;
import com.thowv.javafxgridgameboard.GameBoard;
import com.thowv.javafxgridgameboard.GameBoardTileType;
import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;

public class TTToeGameInstance extends AbstractGameInstance {
    // region Constructors
    public TTToeGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo) {
        this(gameBoard, entityOne, entityTwo, 0);
    }

    public TTToeGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo,
                               int currentTurnEntity) {
        super(gameBoard, entityOne, entityTwo,
                TTToeGameInstance.class.getResource("/tictactoe-style.css").toExternalForm(),
                currentTurnEntity);
    }
    // endregion

    @Override
    public void startGame() {
        super.startGame(this);
    }

    @Override
    public void doTurn(int x, int y) {
        super.doTurn(x, y);

        GameBoardTileType winningTileType = TTToeAlgorithms.checkThreeInRow(super.getGameBoard());

        if (winningTileType != null)
            super.end(winningTileType);
        else if (super.getGameBoard().getTilesByType(GameBoardTileType.HIDDEN).size() == 0)
            super.end(new GameBoardTileType[]{ GameBoardTileType.PLAYER_1, GameBoardTileType.PLAYER_2 });
        else
            super.switchTurn(this);
    }

    @Override
    protected void onTilePressed(GameBoardTilePressedEvent e) {
        if (super.getCurrentTurnEntity().getEntityType() == AbstractTurnEntity.EntityType.PLAYER)
            super.getCurrentTurnEntity().onTilePressed(this, e);
    }
}
