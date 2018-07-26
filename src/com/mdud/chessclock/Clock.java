package com.mdud.chessclock;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import javax.swing.event.ChangeListener;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    //remainingTime in format 00.0s -> 600 == 60.00s
    private DoubleProperty remainingTime;
    private DoubleProperty incrementTime;

    private boolean isLoser = false;

    public Clock(double remainingTime, int incrementTime) {
        this.remainingTime = new SimpleDoubleProperty(remainingTime);
        this.incrementTime = new SimpleDoubleProperty(incrementTime);

    }


    public void timerTick() {
        remainingTime.setValue(remainingTime.getValue()- 0.1);
        if(remainingTime.getValue() <= 0)
            isLoser = true;

    }

    public void incrementTimer() {
        remainingTime.setValue(remainingTime.getValue() + incrementTime.getValue());

    }

    public double getRemainingTime() {
        return remainingTime.get();
    }

    public DoubleProperty remainingTimeProperty() {
        return remainingTime;
    }

    public double getIncrementTime() {
        return incrementTime.get();
    }

    public DoubleProperty incrementTimeProperty() {
        return incrementTime;
    }

    public boolean isLoser() {
        return isLoser;
    }

    public void clearLoserFlag() {
        isLoser = false;
    }
}
