package com.thowv.javafxgridgameboard.premades.reversi;

import com.thowv.javafxgridgameboard.*;
import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;
import com.thowv.javafxgridgameboard.events.GameEndedEvent;

public class ReversiGameInstance extends AbstractGameInstance {
    byte passAmount = 0;

    // region Constructors
    public ReversiGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo) {
        this(gameBoard, entityOne, entityTwo, 0);
    }

    public ReversiGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo,
                               int currentTurnEntity) {
        super(gameBoard, entityOne, entityTwo,
                ReversiGameInstance.class.getResource("/reversi-style.css").toExternalForm(),
                currentTurnEntity);

        super.getGameBoard().addEventHandler(GameBoardTilePressedEvent.TILE_PRESSED_EVENT_EVENT_TYPE,
                this::onTilePressed);
    }
    // endregion

    @Override
    public void startGame() {
        int halfSize = (int)Math.floor((double)super.getGameBoard().getSize() / 2);

        // Set the starting tiles
        super.getGameBoard().setTileType(halfSize - 1, halfSize - 1, GameBoardTileType.PLAYER_1);
        super.getGameBoard().setTileType(halfSize, halfSize - 1, GameBoardTileType.PLAYER_2);
        super.getGameBoard().setTileType(halfSize, halfSize, GameBoardTileType.PLAYER_1);
        super.getGameBoard().setTileType(halfSize - 1, halfSize, GameBoardTileType.PLAYER_2);

        // Initiate the first turn
        super.getCurrentTurnEntity().takeTurn(this);
    }

    @Override
    public void doTurn(int x, int y) {
        // Place the appropriate game board tile type at the given coordinates
        super.getGameBoard().setTileType(x, y,
                super.getCurrentTurnEntity().getGameBoardTileType());

        // Flip all possible game board tile types
        ReversiAlgorithms.flipTilesFromOrigin(super.getGameBoard(),
                super.getCurrentTurnEntity().getGameBoardTileType(), x, y);

        // Clear decorated VISIBLE and INTERACTABLE game board tile types
        super.getGameBoard().clearDecoratedTiles();

        nextTurn();
    }

    @Override
    public void endGame() {
        int entityOneTileAmount = super.getGameBoard().getTilesByType(GameBoardTileType.PLAYER_1).size();
        int entityTwoTileAmount = super.getGameBoard().getTilesByType(GameBoardTileType.PLAYER_2).size();

        GameBoardTileType winningTileType = null; // Null stands for tie

        // Determine the winner
        if (entityOneTileAmount > entityTwoTileAmount)
            winningTileType = super.getEntityOne().getGameBoardTileType();
        else if (entityTwoTileAmount > entityOneTileAmount)
            winningTileType = super.getEntityTwo().getGameBoardTileType();

        super.getGameBoard().fireEvent(
                new GameEndedEvent(this, super.getGameBoard(), winningTileType)
        );
    }

    public void passTurn() {
        passAmount += 1;

        if (passAmount == 2)
            endGame();
        else
            nextTurn();
    }

    private void nextTurn() {
        // Switch to the next entity and tell it to take a turn
        super.switchCurrentTurnEntity();
        super.getCurrentTurnEntity().takeTurn(this);
    }

    private void onTilePressed(GameBoardTilePressedEvent e) {
        if (super.getCurrentTurnEntity().getEntityType() == AbstractTurnEntity.EntityType.PLAYER)
            super.getCurrentTurnEntity().onTilePressed(this, e);
    }
}
