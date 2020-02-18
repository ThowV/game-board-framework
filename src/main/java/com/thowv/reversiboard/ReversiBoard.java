package com.thowv.reversiboard;

import com.thowv.reversiboard.behaviors.ReversiBoardBehavior;
import com.thowv.reversiboard.skins.ReversiBoardSkin;
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.util.ArrayList;

public class ReversiBoard extends Control {
    private ReversiBoardBehavior reversiBoardBehavior;

    // region Constructors
    public ReversiBoard() {
        this(8);
    }

    public ReversiBoard(int size) {
        this(size, null, null);
    }

    public ReversiBoard(AbstractReversiTurnEntity reversiTurnEntity1, AbstractReversiTurnEntity reversiTurnEntity2) {
        this(8, reversiTurnEntity1, reversiTurnEntity2);
    }

    public ReversiBoard(int size,
                        AbstractReversiTurnEntity reversiTurnEntity1, AbstractReversiTurnEntity reversiTurnEntity2) {
        this.reversiBoardBehavior = new ReversiBoardBehavior(this, size,
                reversiTurnEntity1, reversiTurnEntity2);
    }
    // endregion

    // region Overrides
    @Override
    public String getUserAgentStylesheet() {
        return ReversiBoard.class.getResource("/control-style.css").toExternalForm();
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ReversiBoardSkin(this);
    }
    // endregion

    // region Control behavior
    public void start() {
        reversiBoardBehavior.refreshPopulateBoard();
    }

    public void activateBoardTile(int xCord, int yCord) {
        reversiBoardBehavior.setTilePieceType(xCord, yCord);
    }
    // endregion

    // region Getters and setters
    public void setTurnEntities(AbstractReversiTurnEntity reversiTurnEntity1, AbstractReversiTurnEntity reversiTurnEntity2) {
        reversiBoardBehavior.setTurnEntities(reversiTurnEntity1, reversiTurnEntity2);
    }

    public ReversiBoardSkin getReversiBoardSkin() {
        return (ReversiBoardSkin)getSkin();
    }

    public int getSize() {
        return reversiBoardBehavior.getBoardSize();
    }

    public BoardTile getTile(int xCord, int yCord) {
        return reversiBoardBehavior.getBoardTileReference(xCord, yCord);
    }

    public ArrayList<BoardTile> getPossibleBoardTiles() {
        return reversiBoardBehavior.getPossibleBoardTiles();
    }
    // endregion
}
