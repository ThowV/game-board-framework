package com.thowv.javafxgridgameboard.premades.tictactoe;

import com.thowv.javafxgridgameboard.*;
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

        // Check if there is a three in a row on the board
        GameBoardTileType winningTileType = TTToeAlgorithms.checkThreeInRow(super.getGameBoard());

        // Either end the game or switch the turn
        if (winningTileType != null) {
            AbstractTurnEntity winningEntity = super.getEntityByTileType(winningTileType);
            AbstractTurnEntity losingEntity = super.getEntityByTileType(AlgorithmHelper.flipTileType(
                    winningTileType));

            super.end(winningEntity, losingEntity);
        }
        else if (super.getGameBoard().getTilesByType(GameBoardTileType.HIDDEN).size() == 0)
            super.end(new AbstractTurnEntity[]{ getEntityOne(), getEntityTwo() });
        else
            super.switchTurn(this);
    }

    @Override
    protected void onTilePressed(GameBoardTilePressedEvent e) {
        if (super.getCurrentTurnEntity().getEntityType() == AbstractTurnEntity.EntityType.PLAYER)
            super.getCurrentTurnEntity().onTilePressed(this, e);
    }
}
