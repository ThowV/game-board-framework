package com.thowv.javafxgridgameboard;

import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;
import com.thowv.javafxgridgameboard.listeners.GameEndListener;
import com.thowv.javafxgridgameboard.listeners.GameStartListener;
import com.thowv.javafxgridgameboard.listeners.TurnSwitchListener;
import javafx.application.Platform;

public abstract class AbstractGameInstance {
    private GameBoard gameBoard;
    private AbstractTurnEntity[] turnEntities;
    private int currentTurnEntity;
    private GameBoardEventManager eventManager;

    public AbstractGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo,
                                   String stylesheet, int currentTurnEntity) {
        this.gameBoard = gameBoard;

        // Set style
        if (stylesheet != null)
            gameBoard.getStylesheets().add(stylesheet);

        // Set turn entities
        entityOne.setGameBoardTileType(GameBoardTileType.PLAYER_1);
        entityTwo.setGameBoardTileType(GameBoardTileType.PLAYER_2);

        this.turnEntities = new AbstractTurnEntity[]{entityOne, entityTwo};
        this.currentTurnEntity = currentTurnEntity;

        // Do event handling
        gameBoard.addEventHandler(GameBoardTilePressedEvent.TILE_PRESSED_EVENT_EVENT_TYPE,
                this::onTilePressed);

        this.eventManager = new GameBoardEventManager();
    }

    protected void switchCurrentTurnEntity() {
        AbstractTurnEntity previousTurnEntity = getCurrentTurnEntity();
        currentTurnEntity ^= 1;
        AbstractTurnEntity currentTurnEntity = getCurrentTurnEntity();

        eventManager.notifyOnTurnSwitch(currentTurnEntity, previousTurnEntity);
    }

    public void start() {
        Platform.runLater(this::startGame);
    }

    protected abstract void startGame();

    protected void startGame(AbstractGameInstance gameInstance) {
        // Initiate the first turn
        eventManager.notifyOnGameStart(getCurrentTurnEntity());
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

    public void calculateEntityPoints() {
        // Count points for each entity by counting the tiles they possess
        for (AbstractTurnEntity turnEntity : turnEntities)
            turnEntity.setPoints(gameBoard.countTilesByType(turnEntity.getGameBoardTileType()));
    }

    public void end(AbstractTurnEntity winningTurnEntity, AbstractTurnEntity losingTurnEntity) {
        eventManager.notifyOnGameEnd(winningTurnEntity, losingTurnEntity);
    }

    public void end(AbstractTurnEntity[] tieTurnEntities) {
        eventManager.notifyOnGameEnd(tieTurnEntities);
    }

    protected void onTilePressed(GameBoardTilePressedEvent e) { }

    // region Events
    public void onGameStart(GameStartListener listener) {
        eventManager.onGameStart(listener);
    }

    public void onGameEnd(GameEndListener listener) {
        eventManager.onGameEnd(listener);
    }

    public void onTurnSwitch(TurnSwitchListener listener) {
        eventManager.onTurnSwitch(listener);
    }
    // endregion

    // region Getters and setters
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public AbstractTurnEntity[] getTurnEntities() {
        return turnEntities;
    }

    public AbstractTurnEntity getEntityOne() {
        return turnEntities[0];
    }

    public AbstractTurnEntity getEntityTwo() {
        return turnEntities[1];
    }

    public AbstractTurnEntity getEntityByTileType(GameBoardTileType tileType) {
        for (AbstractTurnEntity turnEntity : turnEntities)
            if (turnEntity.getGameBoardTileType() == tileType)
                return turnEntity;

        return null;
    }

    public AbstractTurnEntity getCurrentTurnEntity() {
        return turnEntities[currentTurnEntity];
    }
    // endregion
}
