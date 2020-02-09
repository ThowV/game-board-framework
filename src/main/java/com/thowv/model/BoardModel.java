package com.thowv.model;

import com.thowv.view.TileView;

public class BoardModel {
    private TileView[][] tiles;

    public BoardModel(int boardSize) {
        tiles = new TileView[boardSize][boardSize];
    }

    public boolean checkIfTileExists(int x, int y) {
        return x < tiles.length && y < tiles[0].length;
    }

    public boolean checkIfTileEmpty(int x, int y) {
        return tiles[x][y] != null;
    }

    public TileView getTile(int x, int y) {
        return tiles[x][y];
    }

    public void setTile(int x, int y, TileView tileView) {
        tiles[x][y] = tileView;
    }
}
