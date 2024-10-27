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

        ButtonListener buttonListener = new ButtonListener(gui.buttons, gui.labels, gui.board, gui.vBoxes.sosGridBox);

        SOSGamemode gameLogicManager;

        //get gamemode:
        gui.buttons.startButton.setOnAction(event -> HandleStartButton(gui.buttons, gui.vBoxes.sosGridBox, gui.board));

        gameLogicManager = gui.buttons.generalGamemode.isSelected() ? new SOSGeneralGamemode(gui.board) : new SOSSimpleGamemode(gui.board);


    }

    private void HandleStartButton(ButtonHolder buttons, VBox sosGridBox, Board board){
        if(!(buttons.simpleGamemode.isSelected() || buttons.generalGamemode.isSelected())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No game mode selected.");
            alert.setHeaderText(null);
            alert.setContentText("Please select a game mode.");
            alert.showAndWait();
        }
        else{
            //remove previous board
            sosGridBox.getChildren().remove(board.grid);
            board = new Board(buttons.boardSizeSpinner.getValue(), Board.ComponentType.BUTTON);
            //add new board
            sosGridBox.getChildren().add(board.grid);
            //reinitialize listeners:
            Listen();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

