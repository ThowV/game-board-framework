package com.thowv.javafxgridgameboard;

import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;

public abstract class AbstractTurnEntity {
    public enum EntityType { PLAYER, AI };
    private GameBoardTileType gameBoardTileType;
    private EntityType entityType;
    private String name;
    private String color;
    private int points;

    public AbstractTurnEntity(EntityType entityType, String name) {
        this(entityType, name, "");
    }

    public AbstractTurnEntity(EntityType entityType, String name, String color) {
        this.entityType = entityType;
        this.name = name;
        this.color = color;
        this.points = 0;
    }

    public abstract void takeTurn(AbstractGameInstance gameInstance);

    public void onTilePressed(AbstractGameInstance gameInstance, GameBoardTilePressedEvent e) { }

    // region Getters and Setters
    public EntityType getEntityType() {
        return entityType;
    }

    public GameBoardTileType getGameBoardTileType() {
        return gameBoardTileType;
    }

    public void setGameBoardTileType(GameBoardTileType gameBoardTileType) {
        this.gameBoardTileType = gameBoardTileType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
    // endregion
}
