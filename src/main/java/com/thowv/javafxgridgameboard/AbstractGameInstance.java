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

    /**
     * Create a new game instance, set the style, add the turn entities and couple the event manager.
     * @param gameBoard The game board that this instance will use to play the game on
     * @param entityOne The first entity that plays the game
     * @param entityTwo The second entity that plays the game
     * @param stylesheet The style sheet of the game board
     * @param currentTurnEntity The starting turn entity
     */
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

    /**
     * Switch the turn entity.
     */
    protected void switchCurrentTurnEntity() {
        AbstractTurnEntity previousTurnEntity = getCurrentTurnEntity();
        currentTurnEntity ^= 1;
        AbstractTurnEntity currentTurnEntity = getCurrentTurnEntity();

        eventManager.notifyOnTurnSwitch(currentTurnEntity, previousTurnEntity);
    }

    /**
     * Start the game
     */
    public void start() {
        Platform.runLater(this::startGame);
    }

    protected abstract void startGame();

    /**
     * Start the game by firing the event and telling the turn entity to take its turn.
     * @param gameInstance The game instance that is being sent towards the turn entity
     */
    protected void startGame(AbstractGameInstance gameInstance) {
        // Initiate the first turn
        eventManager.notifyOnGameStart(getCurrentTurnEntity());
        getCurrentTurnEntity().takeTurn(gameInstance);
    }

    /**
     * Do a turn with a given x and y position on the game board.
     * @param x The x coordinate for the turn
     * @param y The y coordinate for the turn
     */
    public void doTurn(int x, int y) {
        // Place the appropriate game board tile type at the given coordinates
        gameBoard.setTileType(x, y, getCurrentTurnEntity().getGameBoardTileType());

        // Clear decorated VISIBLE and INTERACTABLE game board tile types
        gameBoard.clearDecoratedTiles();
    }

    /**
     * Switch the turn.
     */
    public void switchTurn() {
        switchTurn(this);
    }

    /**
     * Switch the turn and tell the next turn entity to take its turn.
     * @param gameInstance The game instance that is being sent towards the turn entity
     */
    public void switchTurn(AbstractGameInstance gameInstance) {
        // Switch to the next entity and tell it to take a turn
        switchCurrentTurnEntity();
        getCurrentTurnEntity().takeTurn(gameInstance);
    }

    /**
     * Calculate the points for each entity by counting the tiles it possesses.
     */
    public void calculateEntityPoints() {
        // Count points for each entity by counting the tiles they possess
        for (AbstractTurnEntity turnEntity : turnEntities)
            turnEntity.setPoints(gameBoard.countTilesByType(turnEntity.getGameBoardTileType()));
    }

    /**
     * End the game and fire the end event.
     * @param winningTurnEntity The turn entity that wins the game
     * @param losingTurnEntity The turn entity that loses the game
     */
    public void end(AbstractTurnEntity winningTurnEntity, AbstractTurnEntity losingTurnEntity) {
        eventManager.notifyOnGameEnd(winningTurnEntity, losingTurnEntity);
    }

    /**
     * End the game and fire the end event.
     * @param tieTurnEntities The turn entities that have a tie
     */
    public void end(AbstractTurnEntity[] tieTurnEntities) {
        eventManager.notifyOnGameEnd(tieTurnEntities);
    }

    /**
     * Gets fired when a tile on the game board is pressed.
     * @param e The event that fired this method
     */
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

    /**
     * Set the game board this instance used to play the game on.
     * @param gameBoard The game board that this instance uses
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * @return The game board this instance uses to play the game on
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * @return The turn entities playing in this game instance
     */
    public AbstractTurnEntity[] getTurnEntities() {
        return turnEntities;
    }

    /**
     * @return The first turn entity playing in this game instance
     */
    public AbstractTurnEntity getEntityOne() {
        return turnEntities[0];
    }

    /**
     * @return The second turn entity playing in this game instance
     */
    public AbstractTurnEntity getEntityTwo() {
        return turnEntities[1];
    }

    /**
     * Get the entity that used the given tile type to play the game.
     * @param tileType The tile type used for matching the turn entity
     * @return The turn entity that uses the given tile type
     */
    public AbstractTurnEntity getEntityByTileType(GameBoardTileType tileType) {
        for (AbstractTurnEntity turnEntity : turnEntities)
            if (turnEntity.getGameBoardTileType() == tileType)
                return turnEntity;

        return null;
    }

    /**
     * @return The current turn entity
     */
    public AbstractTurnEntity getCurrentTurnEntity() {
        return turnEntities[currentTurnEntity];
    }
    // endregion
}
