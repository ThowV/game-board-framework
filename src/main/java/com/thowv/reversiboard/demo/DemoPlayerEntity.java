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
        if (reversiBoard.getPossibleBoardTiles().size() != 0)
            reversiBoard.visualizePossibleBoardTiles(BoardTile.TilePieceType.ACTIVE);
        else
            reversiBoard.passTurn();
    }

    @Override
    public void tilePressed(ReversiBoard reversiBoard, BoardTileActivatedEvent e) {
        reversiBoard.takeTurn(e.getXCord(), e.getYCord());
    }
}
