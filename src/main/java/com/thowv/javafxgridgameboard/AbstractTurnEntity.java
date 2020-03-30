package com.thowv.javafxgridgameboard;

import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;

public abstract class AbstractTurnEntity {
    public enum EntityType { PLAYER, AI };
    private GameBoardTileType gameBoardTileType;
    private EntityType entityType;

    public AbstractTurnEntity(EntityType entityType) {
        this.entityType = entityType;
    }

    public abstract void takeTurn(AbstractGameInstance gameIstance);

    public void onTilePressed(AbstractGameInstance gameInstance, GameBoardTilePressedEvent e) { }

    public EntityType getEntityType() {
        return entityType;
    }

    public GameBoardTileType getGameBoardTileType() {
        return gameBoardTileType;
    }

    public void setGameBoardTileType(GameBoardTileType gameBoardTileType) {
        this.gameBoardTileType = gameBoardTileType;
    }
}