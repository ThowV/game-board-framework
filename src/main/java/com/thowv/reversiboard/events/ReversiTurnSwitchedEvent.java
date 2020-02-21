package com.thowv.reversiboard.events;

import com.thowv.reversiboard.AbstractReversiTurnEntity;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class ReversiTurnSwitchedEvent extends Event {
    public enum TurnAction { TURN_TAKEN, TURN_PASSED }

    public static final EventType<ReversiTurnSwitchedEvent> TURN_SWITCHED =
            new EventType<>(Event.ANY, "TURN_SWITCHED");
    private AbstractReversiTurnEntity pastTurnEntity;
    private TurnAction turnAction;
    private AbstractReversiTurnEntity nextTurnEntity;

    public ReversiTurnSwitchedEvent(Object source, EventTarget target,
                                    AbstractReversiTurnEntity pastTurnEntity, TurnAction turnAction,
                                    AbstractReversiTurnEntity nextTurnEntity) {
        super(source, target, TURN_SWITCHED);

        this.pastTurnEntity = pastTurnEntity;
        this.turnAction = turnAction;
        this.nextTurnEntity = nextTurnEntity;
    }

    public AbstractReversiTurnEntity getPastTurnEntity() {
        return pastTurnEntity;
    }

    public TurnAction getTurnAction() {
        return turnAction;
    }

    public AbstractReversiTurnEntity getNextTurnEntity() {
        return nextTurnEntity;
    }
}
