package com.thowv.javafx.reversiboard.events;

import com.thowv.javafx.reversiboard.AbstractReversiTurnEntity;
import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.event.EventType;

public class ReversiGameEndedEvent extends Event {
    public static final EventType<ReversiGameEndedEvent> GAME_ENDED =
            new EventType<>(Event.ANY, "GAME_ENDED");
    private AbstractReversiTurnEntity winningTurnEntity;

    public ReversiGameEndedEvent(Object source, EventTarget target, AbstractReversiTurnEntity winningTurnEntity) {
        super(source, target, GAME_ENDED);

        this.winningTurnEntity = winningTurnEntity;
    }

    public AbstractReversiTurnEntity getWinningTurnEntity() {
        return winningTurnEntity;
    }
}
