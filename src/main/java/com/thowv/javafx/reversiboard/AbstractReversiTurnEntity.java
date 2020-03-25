package com.thowv.javafx.reversiboard;

import com.thowv.javafx.reversiboard.events.BoardTileActivatedEvent;

public abstract class AbstractReversiTurnEntity {
    public enum EntityType { PLAYER, AI };
    private EntityType entityType;
    private BoardTile.TilePieceType tilePieceType;

    public AbstractReversiTurnEntity(EntityType entityType, BoardTile.TilePieceType tilePieceType) {
        this.entityType = entityType;
        this.tilePieceType = tilePieceType;
    }

    public abstract void takeTurn(ReversiBoard reversiBoard);

    public abstract void tilePressed(ReversiBoard reversiBoard, BoardTileActivatedEvent e);

    public EntityType getEntityType() {
        return entityType;
    }

    public BoardTile.TilePieceType getTilePieceType() {
        return tilePieceType;
    }
}
