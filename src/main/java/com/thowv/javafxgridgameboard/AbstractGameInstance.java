package com.thowv.javafxgridgameboard;

public abstract class AbstractGameInstance {
    private GameBoard gameBoard;
    private AbstractTurnEntity[] turnEntities;
    private int currentTurnEntity;

    protected AbstractGameInstance(GameBoard gameBoard, AbstractTurnEntity entityOne, AbstractTurnEntity entityTwo,
                                   String stylesheet, int currentTurnEntity) {
        this.gameBoard = gameBoard;

        if (stylesheet != null)
            gameBoard.getStylesheets().add(stylesheet);

        entityOne.setGameBoardTileType(GameBoardTileType.PLAYER_1);
        entityTwo.setGameBoardTileType(GameBoardTileType.PLAYER_2);

        this.turnEntities = new AbstractTurnEntity[]{entityOne, entityTwo};
        this.currentTurnEntity = currentTurnEntity;
    }

    // region Default methods
    protected void switchCurrentTurnEntity() {
        currentTurnEntity ^= 1;
    }
    // endregion

    // region Abstract  methods
    public abstract void startGame();
    public abstract void doTurn(int x, int y);
    public abstract void endGame();
    // endregion

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
