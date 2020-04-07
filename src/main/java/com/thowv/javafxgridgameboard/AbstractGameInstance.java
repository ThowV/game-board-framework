package com.thowv.javafxgridgameboard;

import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;
import com.thowv.javafxgridgameboard.events.GameEndedEvent;
import javafx.application.Platform;

public abstract class AbstractGameInstance {
    private GameBoard gameBoard;
    private AbstractTurnEntity[] turnEntities;
    private int currentTurnEntity;

    public AbstractGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo,
                                   String stylesheet, int currentTurnEntity) {
        this.gameBoard = gameBoard;

        if (stylesheet != null)
            gameBoard.getStylesheets().add(stylesheet);

        entityOne.setGameBoardTileType(GameBoardTileType.PLAYER_1);
        entityTwo.setGameBoardTileType(GameBoardTileType.PLAYER_2);

        this.turnEntities = new AbstractTurnEntity[]{entityOne, entityTwo};
        this.currentTurnEntity = currentTurnEntity;

        gameBoard.addEventHandler(GameBoardTilePressedEvent.TILE_PRESSED_EVENT_EVENT_TYPE,
                this::onTilePressed);
    }

    protected void switchCurrentTurnEntity() {
        currentTurnEntity ^= 1;
    }

    public void start() {
        Platform.runLater(this::startGame);
    }

    protected abstract void startGame();

    protected void startGame(AbstractGameInstance gameInstance) {
        // Initiate the first turn
        getCurrentTurnEntity().takeTurn(gameInstance);
    }

    public void doTurn(int x, int y) {
        // Place the appropriate game board tile type at the given coordinates
        gameBoard.setTileType(x, y, getCurrentTurnEntity().getGameBoardTileType());

        // Clear decorated VISIBLE and INTERACTABLE game board tile types
        gameBoard.clearDecoratedTiles();
    }

    public void switchTurn() {
        switchTurn(this);
    }

    public void switchTurn(AbstractGameInstance gameInstance) {
        // Switch to the next entity and tell it to take a turn
        switchCurrentTurnEntity();
        getCurrentTurnEntity().takeTurn(gameInstance);
    }

    public void end(GameBoardTileType winningTileType) {
        end(new GameBoardTileType[] { winningTileType });
    }

    public void end(GameBoardTileType[] winningTileTypes) {
        // Fire the game ended event
        gameBoard.fireEvent(
                new GameEndedEvent(this, gameBoard, winningTileTypes)
        );
    }

    protected void onTilePressed(GameBoardTilePressedEvent e) { }

    // region Getters and setters
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public AbstractTurnEntity getEntityOne() {
        return turnEntities[0];
    }

    public AbstractTurnEntity getEntityTwo() {
        return turnEntities[1];
    }

    public AbstractTurnEntity getCurrentTurnEntity() {
        return turnEntities[currentTurnEntity];
    }
    // endregion
}
