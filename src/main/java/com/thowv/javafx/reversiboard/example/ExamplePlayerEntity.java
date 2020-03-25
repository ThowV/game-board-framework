package com.thowv.javafx.reversiboard.example;

import com.thowv.javafx.reversiboard.AbstractReversiTurnEntity;
import com.thowv.javafx.reversiboard.BoardTile;
import com.thowv.javafx.reversiboard.ReversiBoard;
import com.thowv.javafx.reversiboard.events.BoardTileActivatedEvent;

public class ExamplePlayerEntity extends AbstractReversiTurnEntity {
    public ExamplePlayerEntity(BoardTile.TilePieceType tilePieceType) {
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
