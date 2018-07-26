package com.mdud.chessclock;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Model {
    public enum ActivePlayer {
        PLAYER1,
        PLAYER2
    };

    private Clock firstPlayerClock;
    private Clock secondPlayerClock;

    private ActivePlayer activePlayer;

    public Model() {
        activePlayer = ActivePlayer.PLAYER1;
        firstPlayerClock = new Clock(50, 20);
        secondPlayerClock = new Clock(50, 20);
    }

    public Clock getFirstPlayerClock() {
        return firstPlayerClock;
    }

    public Clock getSecondPlayerClock() {
        return secondPlayerClock;
    }

    public void togglePlayer() {
        if(activePlayer == ActivePlayer.PLAYER1) {
            activePlayer = ActivePlayer.PLAYER2;
            firstPlayerClock.incrementTimer();
        }
        else {
            activePlayer = ActivePlayer.PLAYER1;
            secondPlayerClock.incrementTimer();
        }
    }

    public void activePlayerTick() {
        if(firstPlayerClock.isLoser() || secondPlayerClock.isLoser())
            return;

        if(activePlayer == ActivePlayer.PLAYER1)
            firstPlayerClock.timerTick();
        else
            secondPlayerClock.timerTick();
    }

    public void clearMatch() {
        firstPlayerClock.clearLoserFlag();
        secondPlayerClock.clearLoserFlag();
    }

    public ActivePlayer getActivePlayer() {
        return activePlayer;
    }
}
