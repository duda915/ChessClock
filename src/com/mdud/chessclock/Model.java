package com.mdud.chessclock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Model {
    public enum ActivePlayer {
        PLAYER1,
        PLAYER2
    };

    private Clock firstPlayerClock;
    private Clock secondPlayerClock;
    private Timeline timer;

    private boolean startState = false;

    private ActivePlayer activePlayer;

    public Model() {
        activePlayer = ActivePlayer.PLAYER1;
        firstPlayerClock = new Clock(50, 2);
        secondPlayerClock = new Clock(50, 2);

        timer = new Timeline(new KeyFrame(Duration.millis(100), e -> activePlayerTick()));
        timer.setCycleCount(Animation.INDEFINITE);
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
            if(startState)
                firstPlayerClock.incrementTimer();
        }
        else {
            activePlayer = ActivePlayer.PLAYER1;
            if(startState)
                secondPlayerClock.incrementTimer();
        }
    }

    public void toggleTimer() {
        if(timer.getStatus() == Animation.Status.STOPPED) {
            startState = true;
            timer.play();

        }
        else {
            startState = false;
            timer.stop();

        }
    }

    private void activePlayerTick() {
        if(firstPlayerClock.isLoser() || secondPlayerClock.isLoser()) {
            startState = false;
            return;
        }

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

    public Timeline getTimer() {
        return timer;
    }
}
