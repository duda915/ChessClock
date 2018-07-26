package com.mdud.chessclock;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

public class Controller  {

    private Model model;
    private Timeline timer;
    private StringProperty startStopButton;
    private StringConverter<Number> stringConverter;

    @FXML
    private TextField p1IncrementTextField;

    @FXML
    private TextField p2IncrementTextField;

    @FXML
    private TextField timerP1;

    @FXML
    private TextField timerP2;

    @FXML
    private GridPane rootPane;

    public Controller(){
        model = new Model();
        timer = new Timeline(new KeyFrame(Duration.millis(100), e -> model.activePlayerTick()));
        timer.setCycleCount(Animation.INDEFINITE);

        startStopButton = new SimpleStringProperty("Start");

        stringConverter = new NumberStringConverter();

    }

    @FXML
    public void initialize() {
        p1IncrementTextField.textProperty().bindBidirectional(model.getFirstPlayerClock().incrementTimeProperty(),
                stringConverter);
        p2IncrementTextField.textProperty().bindBidirectional(model.getSecondPlayerClock().incrementTimeProperty(),
                stringConverter);


        timerP1.textProperty().bindBidirectional(model.getFirstPlayerClock().formattedTimeProperty());
        timerP2.textProperty().bindBidirectional(model.getSecondPlayerClock().formattedTimeProperty());


        rootPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> onSpacePressed(e));
    }



    public void handleStartButton() {
        if(timer.getStatus() == Animation.Status.STOPPED) {
            timer.play();
            startStopButton.setValue("Stop");
        }
        else {
            timer.stop();
            startStopButton.setValue("Start");
        }

        rootPane.requestFocus();
    }

    public void onSpacePressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.SPACE) {
            model.togglePlayer();
        }
    }

    public Model getModel() {
        return model;
    }

    public String getStartStopButton() {
        return startStopButton.get();
    }

    public StringProperty startStopButtonProperty() {
        return startStopButton;
    }
}
