package sos.ryanbyers.sosjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SOSGUI extends Application  {

    //buttons, labels, etc. map naming convention:
    //keys are camelCase w/ special characters removed -- General Gamemode: --> generalGamemode, Gamemode --> gameMode, etc.
    private Map<String, RadioButton> buttons;
    private Map<String, Label> labels; 
    private Map<String, HBox> hBoxes;
    private Map<String, VBox> vBoxes;

    @Override
    public void start(Stage primaryStage) {
        InitializeUIElements();

        //initialize unplayable grid for display purposes (fill with empty labels)
        int DEFAULT_GRID_SIZE = 5;
        GridPane placeholderGrid = IntializeGrid(DEFAULT_GRID_SIZE, index -> new Label(""));

        vBoxes.get("sosGridBox").getChildren().add(placeholderGrid);
        hBoxes.get("gameSpaceBox").getChildren().add(vBoxes.get("sosGridBox"));

        DisplayWindow(scene, primaryStage, 640, 360);     
    }

    private void InitializeUIElements(){
        buttons = InitializeButtons(); //buttonName, referenced button
        labels = InitializeLabels(); //labelName, referenced label

        hBoxes = InitializeHBoxes(buttons, labels); //hbox, referenced hbox
        vBoxes = InitializeVBoxes(buttons, labels); //vbox, referenced vbox

        //insert labels, buttons, hBoxes, vBoxes into boxes where necessary:
        FillHBoxes(buttons, labels, hBoxes, vBoxes);
        FillVBoxes(buttons, labels, hBoxes, vBoxes);

        //create spinner to allow for board size selection:
        //note: spinner doesn't allow for unique user input, invalid input tests are unneccessary
        Spinner<Integer> boardSizeSpinner = new Spinner<>(3, 30, 5); //min: 3, max: 30, default: 5
        boardSizeSpinner.setPrefWidth(52); //this width can accomodate integers < 100 

        hBoxes.get("gameOptionsBox").getChildren().add(boardSizeSpinner);
    }

    private static Map<String, RadioButton> InitializeButtons(){
        //gamemode radio buttons
        ToggleGroup gamemodeToggle = new ToggleGroup();
        RadioButton simpleGamemode = new RadioButton("Simple game");
        RadioButton generalGamemode = new RadioButton("General game");

        //set correct group (gamemode group)
        simpleGamemode.setToggleGroup(gamemodeToggle);
        generalGamemode.setToggleGroup(gamemodeToggle);

        //Blue player piece choice radio buttons
        ToggleGroup bluePieceToggle = new ToggleGroup();
        RadioButton blueS = new RadioButton("S");
        RadioButton blueO = new RadioButton("O");

        blueS.setToggleGroup(bluePieceToggle);
        blueO.setToggleGroup(bluePieceToggle);

        //Red player piece choice radio buttons 
        ToggleGroup redPieceToggle = new ToggleGroup();
        RadioButton redS = new RadioButton("S");
        RadioButton redO = new RadioButton("O");

        redS.setToggleGroup(redPieceToggle);
        redO.setToggleGroup(redPieceToggle);

        //place all buttons into a map:
        Map<String, RadioButton> buttons = new HashMap<>();
        buttons.put("simpleGamemode", simpleGamemode);
        buttons.put("generalGamemode", generalGamemode);
        buttons.put("blueS", blueS);
        buttons.put("blueO", blueO);
        buttons.put("redS", redS);
        buttons.put("redO", redO);

        return buttons;
    }

    private static Map<String, Label> InitializeLabels(){
        Label gamemode = new Label("Game Mode: ");
        Label bluePlayer = new Label("Blue Player: ");
        Label redPlayer = new Label("Red Player: ");
        Label currentTurn = new Label("Current Turn: ");
        Label currentTurnBlue = new Label("Blue");
        Label currentTurnRed = new Label("Red");
        Label boardSize = new Label("Board Size: ");

        Map<String, Label> labels = new HashMap<>();
        labels.put("gamemode", gamemode);
        labels.put("bluePlayer", bluePlayer);
        labels.put("redPlayer", redPlayer);
        labels.put("currentTurn", currentTurn);
        labels.put("currentTurnBlue", currentTurnBlue);
        labels.put("currentTurnRed", currentTurnRed);
        labels.put("boardSize", boardSize);

        return labels;
    }

    private static Map<String, HBox> InitializeHBoxes(Map<String, RadioButton> buttons, Map<String, Label> labels){
        //hbox for game mode selection radio buttons
        HBox gameOptionsBox = new HBox(15);
        gameOptionsBox.setPadding(new Insets(15));
        gameOptionsBox.setAlignment(Pos.CENTER);

        //holds the grid box and red and blue player piece choices boxes
        HBox gameSpaceBox = new HBox(10);
        gameSpaceBox.setAlignment(Pos.CENTER);

        Map<String, HBox> hBoxes = new HashMap<>();
        hBoxes.put("gameOptionsBox", gameOptionsBox);
        hBoxes.put("gameSpaceBox", gameSpaceBox);

        return HBoxes;
    }

    private static Map<String, VBox> InitializeVBoxes(Map<String, Button> buttons, Map<String, Labels> labels, Map<String, HBox> hBoxes){
        //set up VBox that will act as container for all other HBoxes
        VBox mainBox = new VBox(10);
        mainBox.setPadding(new Insets(15));
        mainBox.setStyle("-fx-border-color: black; -fx-border-width: 10px; -fx-border-style: solid;");  

        VBox bluePlayerChoicesBox = new VBox(10);
        bluePlayerChoicesBox.setPadding(new Insets(15));

        VBox redPlayerChoicesBox = new VBox(10);
        redPlayerChoicesBox.setPadding(new Insets(15));

        VBox sosGridBox = new VBox(10);

        Map<String, VBox> vBoxes = new HashMap<>();
        vBoxes.put("mainBox", mainBox);
        vBoxes.put("bluePlayerChoicesBox", bluePlayerChoicesBox);
        vBoxes.put("redPlayerChoicesBox", redPlayerChoicesBox);
        vBoxes.put("sosGridBox", sosGridBox);

        return vBoxes;
    }

    private void FillHBoxes(Map<String, Button> buttons, Map<String, Label> labels, Map<String, VBox> vBoxes){
        vBox.get("mainBox").getChildren().add(hBoxes.get("gameOptionsBox"));

        //add gamemode selection + board size buttons/input fields:
        hBox.get("gameOptionsBox").getChildren().addAll(labels.get("gamemode"), buttons.get("simpleGamemode"), buttons.get("generalGamemode"), labels.get("boardSize"));

        hBox.get("gameSpaceBox").getChildren().add(vBox.get("redPlayerChoicesBox"));
    }

    private void FillVBoxes(Map<String, Button> buttons, Map<String, Label> labels, Map<String, HBox> hBoxes, Map<String, VBox> vBox){
        vBoxes.get("redPlayerChoicesBox").getChildren().addAll(labels.get("redPlayer"), buttons.get("redS"), buttons.get("redO"));
        vBoxes.get("bluePlayerChoicesBox").getChildren().addAll(labels.get("bluePlayer"), buttons.get("blueS"), buttons.get("blueO"));
        vBoxes.get("gameSpaceBox").getChildren().add(bluePlayerChoicesBox);
        vBoxes.get("mainBox").getChildren().add(vBoxes.get("gameSpaceBox");
    }

    private static gridPane InitializeGrid(int gridSize, Function<Integer, T> componentFactory){
        //create sos grid cells (may be changed later but i'll set them now for initial display purposes):
        GridPane grid = new Gridpane();
        grid.setPadding(new Insets(10));
        
        for(int row = 0; row < gridSize; ++row) {
            for(int col = 0; col < gridSize; ++col) {
                T component = componentFactory.apply(row * gridSize + col);
                Label cell = new Label(""); //leave empty for now as this is just a display, replace real grid with buttons
                cell.setPrefSize(40, 40);
                cell.setAlignment(Pos.CENTER);
                cell.setStyle("-fx-border-color: black; -fx-background-color: white;");

                grid.add(cell, col, row);
            }
        }

        return grid;
    }

    private void DisplayWindow(Scene scene, Stage primaryStage, int windowHeight, int windowWidth){
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