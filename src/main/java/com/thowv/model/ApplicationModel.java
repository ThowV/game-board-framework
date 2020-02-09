package com.thowv.model;

import com.thowv.controller.ApplicationController;
import com.thowv.view.BoardView;
import javafx.stage.Stage;

public class ApplicationModel {
    private ApplicationController applicationController;
    public static Stage stage;

    public enum ApplicationState { GameRunning, Idle};
    private ApplicationState currentApplicationState = ApplicationState.Idle;

    public ApplicationModel(ApplicationController applicationController) {
        this.applicationController = applicationController;
    }

    public void setGameRunningState(int boardSize) {
        currentApplicationState = ApplicationState.GameRunning;
        applicationController.setGameRunningVisuals();
        applicationController.replaceBoardNode(new BoardView(boardSize));
    }

    public void setIdleState() {
        currentApplicationState = ApplicationState.Idle;
        applicationController.setIdleVisuals();
        applicationController.clearBoardNode();
    }


    public ApplicationState getCurrentApplicationState() { return currentApplicationState; }
}
