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
    private IntegerProperty incrementTime;
    private StringProperty formattedTime;

    private boolean isLoser = false;

    public Clock(double remainingTime, int incrementTime) {
        this.remainingTime = new SimpleDoubleProperty(remainingTime);
        this.incrementTime = new SimpleIntegerProperty(incrementTime);
        formattedTime = new SimpleStringProperty();
        parseClockTime();

        this.remainingTime.addListener(listener -> parseClockTime());

    }

    private void parseClockTime() {
        double timeDouble = remainingTime.getValue();
        int min = (int) timeDouble/1000;
        int sec = (int) (timeDouble % 1000)/10;
        int tens = (int) (timeDouble % 10);

        formattedTime.setValue(min + ":" + sec + "." + tens);
    }

    public void timerTick() {
        remainingTime.setValue(remainingTime.getValue()-1);
        if(remainingTime.getValue() == 0)
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

    public int getIncrementTime() {
        return incrementTime.get();
    }

    public IntegerProperty incrementTimeProperty() {
        return incrementTime;
    }

    public String getFormattedTime() {
        return formattedTime.get();
    }

    public StringProperty formattedTimeProperty() {
        return formattedTime;
    }

    public boolean isLoser() {
        return isLoser;
    }

    public void clearLoserFlag() {
        isLoser = false;
    }
}
