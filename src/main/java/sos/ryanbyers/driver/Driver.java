package sos.ryanbyers.driver;

import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import sos.ryanbyers.gameLogic.SOSGamemode;
import sos.ryanbyers.gameLogic.SOSGeneralGamemode;
import sos.ryanbyers.gameLogic.SOSSimpleGamemode;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.ButtonHolder;
import sos.ryanbyers.gui.SOSGUI;

import javafx.application.Application;
import javafx.stage.Stage;
import sos.ryanbyers.input.ButtonListener;

import java.awt.*;

public class Driver extends Application {
    @Override
    public void start(Stage primaryStage) {
        SOSGUI gui = new SOSGUI(primaryStage, 1280, 720);

        SOSGamemode gameLogicManager;

        //when user presses start:
        gameLogicManager = gui.buttons.generalGamemode.isSelected() ? new SOSGeneralGamemode(gui.board) : new SOSSimpleGamemode(gui.board);


    }
}

    public static void main(String[] args) {
        launch(args);
    }
}

