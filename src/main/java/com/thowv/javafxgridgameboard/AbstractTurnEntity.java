package com.thowv.javafxgridgameboard;

import com.thowv.javafxgridgameboard.events.GameBoardTilePressedEvent;

public abstract class AbstractTurnEntity {
    public enum EntityType { PLAYER, AI };
    private GameBoardTileType gameBoardTileType;
    private EntityType entityType;
    private String name;
    private String color;
    private int points;

    /**
     * Create an entity with a type and name.
     * @param entityType The type enum of this entity
     * @param name The name of this entity
     */
    public AbstractTurnEntity(EntityType entityType, String name) {
        this(entityType, name, "");
    }

    /**
     * Create an entity with a type, name and color.
     * @param entityType The type enum of this entity
     * @param name The name of this entity
     * @param color The color of this entity
     */
    public AbstractTurnEntity(EntityType entityType, String name, String color) {
        this.entityType = entityType;
        this.name = name;
        this.color = color;
        this.points = 0;
    }

    /**
     * Tell the entity to take a turn with information from the game instance.
     * @param gameInstance The game instance used for calculations and operations
     */
    public abstract void takeTurn(AbstractGameInstance gameInstance);

    /**
     * Fires when a tile on the game board is pressed.
     * @param gameInstance The game instance used for calculations and operations
     * @param e The event that fired this method.
     */
    public void onTilePressed(AbstractGameInstance gameInstance, GameBoardTilePressedEvent e) { }

    // region Getters and Setters

    /**
     * @return Get the entity type enum
     */
    public EntityType getEntityType() {
        return entityType;
    }

    /**
     * @return Get the tile type used for taking turns.
     */
    public GameBoardTileType getGameBoardTileType() {
        return gameBoardTileType;
    }

    /**
     * Set the tile type of this entity used for taking its turns.
     * @param gameBoardTileType The tile type this entity should use
     */
    public void setGameBoardTileType(GameBoardTileType gameBoardTileType) {
        this.gameBoardTileType = gameBoardTileType;
    }

    /**
     * @return The name of this entity
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name this entity should have
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The color this entity has
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color The color this entity should use
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return The points this entity has
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points The points this entity should have
     */
    public void setPoints(int points) {
        this.points = points;
    }
    // endregion
}
