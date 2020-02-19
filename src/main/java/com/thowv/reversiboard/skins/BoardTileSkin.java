package com.thowv.reversiboard.skins;

import com.thowv.reversiboard.BoardTile;
import com.thowv.reversiboard.behaviors.BoardTileBehavior;
import javafx.beans.binding.Bindings;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class BoardTileSkin extends SkinBase<BoardTile> {
    private Circle tilePiece;

    // region Constructors
    public BoardTileSkin(BoardTile boardTileControl) {
        super(boardTileControl);

        BoardTileBehavior boardTileBehavior = boardTileControl.getBoardTileBehavior();

        createTile(boardTileBehavior);
    }
    // endregion

    // region Skin building
    private void createTile(BoardTileBehavior boardTileBehavior) {
        Pane pane = new Pane();
        pane.getStyleClass().add("tile");

        float circleSize = 10 * 0.3f;

        tilePiece = new Circle();

        tilePiece.radiusProperty().bind(Bindings.divide(pane.widthProperty(), circleSize));
        tilePiece.centerXProperty().bind(pane.widthProperty().divide(circleSize / 1.5f));
        tilePiece.centerYProperty().bind(pane.widthProperty().divide(circleSize / 1.5f));

        tilePiece.setOnMouseClicked(e -> boardTileBehavior.onBoardTileClick());
        setTilePieceType(boardTileBehavior.getTilePieceType());

        pane.getChildren().add(tilePiece);

        // Gets forwarded to the BoardTile.java class. We add the created tile
        getChildren().add(pane);
    }
    // endregion

    // region Getters and setters
    public void setTilePieceType(BoardTile.TilePieceType tilePieceType) {
        tilePiece.getStyleClass().clear();

        if (tilePieceType == BoardTile.TilePieceType.HIDDEN)
            tilePiece.getStyleClass().add("stonePieceHidden");
        else if (tilePieceType == BoardTile.TilePieceType.VISIBLE)
            tilePiece.getStyleClass().add("stonePieceVisible");
        else if (tilePieceType == BoardTile.TilePieceType.ACTIVE)
            tilePiece.getStyleClass().add("stonePieceActive");
        else if (tilePieceType == BoardTile.TilePieceType.BLACK)
            tilePiece.getStyleClass().add("stonePieceBlack");
        else if (tilePieceType == BoardTile.TilePieceType.WHITE)
            tilePiece.getStyleClass().add("stonePieceWhite");
    }
    // endregion
}
