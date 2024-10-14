package sos.ryanbyers.sosjavafx;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SOSGUI extends Application  {
    public ButtonHolder buttons;
    private LabelHolder labels;
    private HBoxHolder hBoxes;
    private VBoxHolder vBoxes;

    private Board board;

    private ButtonListener buttonListener;

    @Override
    public void start(Stage primaryStage) {
        InitializeUIElements();
        InitializeListeners();
        DisplayWindow(primaryStage, vBoxes.mainBox, 720, 1280);
    }

    private void InitializeUIElements(){
        buttons = new ButtonHolder();
        labels = new LabelHolder();
        hBoxes = new HBoxHolder();
        vBoxes = new VBoxHolder();

        int DEFAULT_BOARD_SIZE = 5;
        board = new Board(5, Board.ComponentType.LABEL);

        //insert labels, buttons, hBoxes, vBoxes into boxes where necessary:
        FillHBoxes();
        FillVBoxes();
    }

    private void InitializeListeners(){
        buttonListener = new ButtonListener(buttons, labels, board, vBoxes.sosGridBox);
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

    private void DisplayWindow(Stage primaryStage, VBox mainBox, int windowHeight, int windowWidth){
        Scene scene = new Scene(mainBox, windowWidth, windowHeight);

        //set and show stage
        primaryStage.setTitle("SOS Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}