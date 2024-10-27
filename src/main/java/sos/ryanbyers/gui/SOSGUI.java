package sos.ryanbyers.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sos.ryanbyers.gameLogic.TurnManager;
import sos.ryanbyers.input.ButtonListener;

import java.awt.*;

public class SOSGUI  {
    public ButtonHolder buttons;
    public LabelHolder labels;
    public HBoxHolder hBoxes;
    public VBoxHolder vBoxes;
    private InputValidator alert;
    private ButtonListener buttonListener;

    public Board board;

    public SOSGUI(Stage primaryStage, int windowWidth, int windowHeight) {
        InitializeUIElements();

        DisplayWindow(primaryStage, windowWidth, windowHeight);
    }

    private void InitializeUIElements(){
        buttons = new ButtonHolder();
        labels = new LabelHolder();
        hBoxes = new HBoxHolder();
        vBoxes = new VBoxHolder();
        alert = new InputValidator();
        buttonListener = new ButtonLister(gui);

        int DEFAULT_BOARD_SIZE = 5;
        board = new Board(5, Board.ComponentType.LABEL);

        //insert labels, buttons, hBoxes, vBoxes into boxes where necessary:
        FillHBoxes();
        FillVBoxes();
    }

    private void FillHBoxes(){
        //add gamemode selection + board size buttons/input fields:
        hBoxes.gameOptionsBox.getChildren().addAll(labels.gamemode, buttons.simpleGamemode, buttons.generalGamemode, labels.boardSize);

        hBoxes.gameOptionsBox.getChildren().add(buttons.boardSizeSpinner);

        hBoxes.gameSpaceBox.getChildren().addAll(vBoxes.redPlayerChoicesBox, vBoxes.sosGridBox, vBoxes.bluePlayerChoicesBox);

        hBoxes.playerTurnBox.getChildren().add(labels.currentTurn);
        hBoxes.playerTurnBox.getChildren().add(labels.turnIndicator);

        hBoxes.startBox.getChildren().add(buttons.startButton);
    }

    private void FillVBoxes(){
        vBoxes.mainBox.getChildren().add(hBoxes.gameOptionsBox);

        vBoxes.redPlayerChoicesBox.getChildren().addAll(labels.redPlayer, buttons.redS, buttons.redO);
        vBoxes.bluePlayerChoicesBox.getChildren().addAll(labels.bluePlayer, buttons.blueS, buttons.blueO);

        vBoxes.mainBox.getChildren().add(hBoxes.gameSpaceBox);

        vBoxes.mainBox.getChildren().add(hBoxes.playerTurnBox);

        vBoxes.sosGridBox.getChildren().add(board.grid);

        vBoxes.mainBox.getChildren().add(hBoxes.startBox);
    }

    //runs when a valid cell on the SOS board is pressed
    private void ModifyButton(Button button, TurnManager turnManager){
        //set appropriate team color for piece depending on whose turn it is:
        String textColor = turnManager.blueTurn ? "blue" : "red";
        button.setStyle("-fx-text-fill: " + textColor + "; -fx-font-weight: bold;");

        //red's turn
        if(turnManager.redTurn){
            button.setText(buttons.redO.isSelected() ? "O" : "S");
            button.setId(buttons.redO.isSelected() ? "O" : "S");
        }
        //blue's turn
        else{
            button.setText(buttons.blueO.isSelected() ? "O" : "S");
            button.setId(buttons.blueO.isSelected() ? "O" : "S");
        }

        button.setDisable(true);
        turnManager.ChangeTurns();
        labels.turnIndicator.setText((turnManager.blueTurn) ? "Blue" : "Red");
    }

    private void HandleStartButton(){
        if(!(buttons.simpleGamemode.isSelected() || buttons.generalGamemode.isSelected())){
            alert.AlertNoGamemodeChosen();
        }
        else{
        //remove previous board
        sosGridBox.getChildren().remove(board.grid);
        board = new Board(buttons.boardSizeSpinner.getValue(), Board.ComponentType.BUTTON);
        //add new board
        sosGridBox.getChildren().add(board.grid);
        //reinitialize listeners:
        buttonListener.AddCellListeners();
    }
    


    private void DisplayWindow(Stage primaryStage, int windowWidth, int windowHeight){
        Scene scene = new Scene(vBoxes.mainBox, windowWidth, windowHeight);

        //set and show stage
        primaryStage.setTitle("SOS Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}