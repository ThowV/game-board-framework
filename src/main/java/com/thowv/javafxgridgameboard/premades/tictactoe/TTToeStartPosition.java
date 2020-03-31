package com.thowv.javafxgridgameboard.premades.tictactoe;

import com.thowv.javafxgridgameboard.AlgorithmHelper;

public class TTToeStartPosition {
    private int xCord;
    private int yCord;
    private AlgorithmHelper.GameBoardDirection[] directions;
    private boolean multiDirectional;

    public TTToeStartPosition(int xCord, int yCord, AlgorithmHelper.GameBoardDirection[] directions) {
        this(xCord, yCord, directions, false);
    }

    public TTToeStartPosition(int xCord, int yCord, AlgorithmHelper.GameBoardDirection[] directions,
                              boolean multiDirectional) {
        this.xCord = xCord;
        this.yCord = yCord;
        this.directions = directions;
        this.multiDirectional = multiDirectional;
    }

    public int getXCord() {
        return xCord;
    }

    public int getYCord() {
        return yCord;
    }

    public AlgorithmHelper.GameBoardDirection[] getDirections() {
        return directions;
    }

    public boolean isMultiDirectional() {
        return multiDirectional;
    }
}
