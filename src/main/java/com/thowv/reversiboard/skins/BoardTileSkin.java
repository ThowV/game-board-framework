package com.thowv.reversiboard.skins;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.events.BoardTileActivatedEvent;
import javafx.beans.binding.Bindings;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class BoardTileSkin extends SkinBase<BoardTile> {
    private BoardTile boardTileControl;
    private Circle tilePiece;

    public BoardTileSkin(BoardTile boardTileControl) {
        super(boardTileControl);

        this.boardTileControl = boardTileControl;

        createTile();
        registerEvents();
    }

    private void createTile() {
        Pane pane = new Pane();
        pane.getStyleClass().add("tile");

        float circleSize = 10 * 0.3f;

        tilePiece = new Circle();
        tilePiece.radiusProperty().bind(Bindings.divide(pane.widthProperty(), circleSize));
        tilePiece.centerXProperty().bind(pane.widthProperty().divide(circleSize / 1.5f));
        tilePiece.centerYProperty().bind(pane.widthProperty().divide(circleSize / 1.5f));
        setTilePieceType(BoardTile.TilePieceType.NONE);

        pane.getChildren().add(tilePiece);

        // Gets forwarded to the BoardTile.java class. We add the created tile
        getChildren().add(pane);
    }

    private void registerEvents() {
        tilePiece.setOnMouseClicked(e -> raiseTileActivatedEvent());
    }

    private void raiseTileActivatedEvent() {
        boardTileControl.fireEvent(new BoardTileActivatedEvent(this, boardTileControl));
    }

    public void setTilePieceType(BoardTile.TilePieceType tilePieceType) {
        tilePiece.getStyleClass().clear();

        if (tilePieceType == BoardTile.TilePieceType.NONE)
            tilePiece.getStyleClass().add("stonePieceEmpty");
        else if (tilePieceType == BoardTile.TilePieceType.BLACK)
            tilePiece.getStyleClass().add("stonePieceBlack");
        else if (tilePieceType == BoardTile.TilePieceType.WHITE)
            tilePiece.getStyleClass().add("stonePieceWhite");
    }
}
