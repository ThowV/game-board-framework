package com.thowv.reversiboard.demo;

import com.thowv.reversiboard.AbstractReversiTurnEntity;
import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.ReversiBoard;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;

public class DemoPlayerEntity extends AbstractReversiTurnEntity {
    public DemoPlayerEntity(BoardTile.TilePieceType tilePieceType) {
        super(EntityType.PLAYER, tilePieceType);
    }

    @Override
    public void takeTurn(ReversiBoard reversiBoard) {
        reversiBoard.visualizePossibleBoardTiles(BoardTile.TilePieceType.ACTIVE);
    }

    @Override
    public void tilePressed(ReversiBoard reversiBoard, BoardTileActivatedEvent e) {
        reversiBoard.activateBoardTile(e.getXCord(), e.getYCord());
    }
}
