package com.thowv.reversiboard.demo;

import com.thowv.reversiboard.AbstractReversiTurnEntity;
import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;

public class DemoEntity extends AbstractReversiTurnEntity {
    public DemoEntity(BoardTile.TilePieceType tilePieceType) {
        super(EntityType.PLAYER, tilePieceType);
    }

    @Override
    public void takeTurn(ReversiBoard reversiBoard) {
        System.out.println("Turn for: " + this);
    }
}
