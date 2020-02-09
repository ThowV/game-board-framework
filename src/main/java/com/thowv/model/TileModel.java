package com.thowv.model;

import com.thowv.controller.TileController;

public class TileModel {
    private TileController tileController;
    private int tilePosX;
    private int tilePosY;

    public TileModel(TileController tileController, int tilePosX, int tilePosY) {
        this.tileController = tileController;
        this.tilePosX = tilePosX;
        this.tilePosY = tilePosY;
    }

    public int getTilePosX() { return tilePosX; }

    public int getTilePosY() { return tilePosY; }
}
