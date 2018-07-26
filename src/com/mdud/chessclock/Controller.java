package com.mdud.chessclock;

import javafx.animation.Animation;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import java.text.Format;
import java.text.ParseException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class Controller  {

    private Model model;
    private StringProperty startStopButton;
    private StringConverter<Number> stringConverter;
    private TextFormatter<Number> doubleTextFormatter;

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

    @FXML
    private VBox firstPlayerBox;

    @FXML
    private VBox secondPlayerBox;

    public Controller(){
        model = new Model();


        startStopButton = new SimpleStringProperty("Start");

        stringConverter = new StringConverter<>() {
            @Override
            public String toString(Number object) {
                return String.format("%.1f", object.doubleValue()).replace(",", ".");
            }

            @Override
            public Number fromString(String string) {
                try {
                    return Double.parseDouble(string);
                } catch (NumberFormatException pe) {
                    return 2;
                }
            }
        };


    }

    @FXML
    public void initialize() {
        p1IncrementTextField.textProperty().bindBidirectional(model.getFirstPlayerClock().incrementTimeProperty(),
                stringConverter);
        p2IncrementTextField.textProperty().bindBidirectional(model.getSecondPlayerClock().incrementTimeProperty(),
                stringConverter);

        timerP1.textProperty().bindBidirectional(model.getFirstPlayerClock().remainingTimeProperty(),
                stringConverter);
        timerP2.textProperty().bindBidirectional(model.getSecondPlayerClock().remainingTimeProperty(),
                stringConverter);


        rootPane.addEventFilter(KeyEvent.KEY_PRESSED, e -> onSpacePressed(e));

        rootPane.requestFocus();
    }



    public void handleStartButton() {
        if(model.getTimer().getStatus() == Animation.Status.STOPPED){
            model.toggleTimer();
            startStopButton.setValue("Stop");
        } else {
            model.clearMatch();
            model.toggleTimer();
            startStopButton.setValue("Start");
        }
        rootPane.requestFocus();
    }

    public void onSpacePressed(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.SPACE) {
            model.togglePlayer();
            if(model.getActivePlayer() == Model.ActivePlayer.PLAYER1) {
                firstPlayerBox.getStyleClass().clear();
                firstPlayerBox.getStyleClass().add("activePlayer");
                timerP1.getStyleClass().clear();
                timerP1.getStyleClass().add("timerActive");

                secondPlayerBox.getStyleClass().clear();
                secondPlayerBox.getStyleClass().add("inactivePlayer");
                timerP2.getStyleClass().clear();
                timerP2.getStyleClass().add("timerInactive");
            } else {
                firstPlayerBox.getStyleClass().clear();
                firstPlayerBox.getStyleClass().add("inactivePlayer");
                timerP1.getStyleClass().clear();
                timerP1.getStyleClass().add("timerInactive");

                secondPlayerBox.getStyleClass().clear();
                secondPlayerBox.getStyleClass().add("activePlayer");
                timerP2.getStyleClass().clear();
                timerP2.getStyleClass().add("timerActive");
            }
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
