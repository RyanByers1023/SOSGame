package sos.ryanbyers.gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;
import sos.ryanbyers.gameLogic.TurnManager;
import sos.ryanbyers.players.CellHeuristics;

public class SOSGUI  {
    public ButtonHolder buttons;
    public LabelHolder labels;
    public HBoxHolder hBoxes;
    public VBoxHolder vBoxes;

    public Board board;
    Pane overlayPane;
    StackPane mainBoardPane;

    public SOSGUI(Stage primaryStage, int windowWidth, int windowHeight) {
        InitializeUIElements();

        DisplayWindow(primaryStage, windowWidth, windowHeight);
    }

    private void InitializeUIElements(){
        buttons = new ButtonHolder();
        labels = new LabelHolder();
        hBoxes = new HBoxHolder();
        vBoxes = new VBoxHolder();

        overlayPane = new Pane();
        overlayPane.setPickOnBounds(false);
        overlayPane.setManaged(false);

        mainBoardPane = new StackPane();

        int DEFAULT_BOARD_SIZE = 5;
        CreateLabelBoard(DEFAULT_BOARD_SIZE);

        //insert labels, buttons, hBoxes, vBoxes into boxes where necessary:
        FillHBoxes();
        FillVBoxes();
    }

    private void CreateButtonBoard(int gridSize){
        this.board = new ButtonBoard(gridSize);
    }

    private void CreateLabelBoard(int gridSize){
        this.board = new LabelBoard(gridSize);
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

        vBoxes.redPlayerChoicesBox.getChildren().addAll(labels.redPlayer, buttons.redS, buttons.redO, buttons.redPlayerIsComputer);
        vBoxes.bluePlayerChoicesBox.getChildren().addAll(labels.bluePlayer, buttons.blueS, buttons.blueO, buttons.bluePlayerIsComputer);

        vBoxes.mainBox.getChildren().add(hBoxes.gameSpaceBox);

        vBoxes.mainBox.getChildren().add(hBoxes.playerTurnBox);

        vBoxes.sosGridBox.getChildren().add(board.grid);

        vBoxes.mainBox.getChildren().add(hBoxes.startBox);
    }

    //runs when a valid cell on the SOS board is pressed
    public void ModifyButtonNormal(Button button, TurnManager turnManager){
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

        labels.turnIndicator.setText((turnManager.blueTurn) ? "Blue" : "Red");
    }

    public void ModifyButtonComputer(Button button, TurnManager turnManager, CellHeuristics cellInfo){
        //set appropriate team color for piece depending on whose turn it is:
        String textColor = turnManager.blueTurn ? "blue" : "red";
        button.setStyle("-fx-text-fill: " + textColor + "; -fx-font-weight: bold;");

        if(cellInfo.pieceIsO){
            button.setText("O");
            button.setId("O");
        }
        else{
            button.setText("S");
            button.setId("S");
        }

        labels.turnIndicator.setText((turnManager.blueTurn) ? "Blue" : "Red");
    }

    public void ResetBoard() {
        mainBoardPane.getChildren().clear();

        int gridSize = buttons.boardSizeSpinner.getValue();

        CreateButtonBoard(gridSize);

        overlayPane = new Pane();
        overlayPane.setPickOnBounds(false);

        mainBoardPane.getChildren().addAll(board.grid, overlayPane);

        vBoxes.sosGridBox.getChildren().clear();
        vBoxes.sosGridBox.getChildren().add(mainBoardPane);
    }

    public void UpdateTurnIndicator(TurnManager turnManager){
        labels.turnIndicator.setText(turnManager.blueTurn ? "Blue" : "Red");
    }
    public void DrawSequenceLine(SequenceCoordinates sequence, boolean isRedPlayer) {
        Pair<Integer, Integer> cellSize = board.GetCellSize();

        double startX = sequence.coordinatesStart.x * cellSize.getKey() + (double) cellSize.getKey() / 2;
        double startY = sequence.coordinatesStart.y * cellSize.getKey() + (double) cellSize.getKey() / 2;

        double endX = sequence.coordinatesEnd.x * cellSize.getKey() + (double) cellSize.getKey() / 2;
        double endY = sequence.coordinatesEnd.y * cellSize.getKey() + (double) cellSize.getKey() / 2;

        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(isRedPlayer ? Color.RED : Color.BLUE);
        line.setStrokeWidth(1);
        line.setManaged(false);

        overlayPane.getChildren().add(line);
    }

    public void DisableComputerCheckboxes(){
        this.buttons.bluePlayerIsComputer.setDisable(true);
        this.buttons.redPlayerIsComputer.setDisable(true);
    }

    public void EnableComputerCheckboxes(){
        this.buttons.bluePlayerIsComputer.setDisable(false);
        this.buttons.redPlayerIsComputer.setDisable(false);
    }

    public void DisableBlueRadioButtons(){
        buttons.blueS.setDisable(isSelected);
        buttons.blueO.setDisable(isSelected);

        buttons.blueS.setSelected(false);
        buttons.blueO.setSelected(false);
    }
    
    public void DisableRedRadioButtons(){
        buttons.redS.setDisable(isSelected);
        buttons.redO.setDisable(isSelected);

        buttons.redS.setSelected(false);
        buttons.redO.setSelected(false);
    }

    private void DisplayWindow(Stage primaryStage, int windowWidth, int windowHeight){
        Scene scene = new Scene(vBoxes.mainBox, windowWidth, windowHeight);

        //set and show stage
        primaryStage.setTitle("SOS Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}