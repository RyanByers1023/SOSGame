package sos.ryanbyers.driver;

import sos.ryanbyers.gameLogic.GameStateManager;
import sos.ryanbyers.gameLogic.TurnManager;
import sos.ryanbyers.gui.SOSGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import sos.ryanbyers.input.ButtonListener;

public class Driver extends Application {
    private ButtonListener buttonListener;
    GameStateManager gameStateManager;

    @Override
    public void start(Stage primaryStage) {
        //initialize user interface
        SOSGUI gui = new SOSGUI(primaryStage, 1280, 720);
        //gui is the only object allowed to instantiate/have direct access to (stored as attribute) the board

        TurnManager turnManager = new TurnManager();

        //set up listeners:
        //attach listeners to board (from gui) and to the start button (from gui)
        buttonListener = new ButtonListener(turnManager, gui);
    }

    public static void main(String[] args) { launch(args); }
}




