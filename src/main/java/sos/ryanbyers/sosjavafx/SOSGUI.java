package sos.ryanbyers.sosjavafx;

import javafx.application.Application;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.function.Function;

public class SOSGUI extends Application  {
    private ButtonHolder buttons;
    private LabelHolder labels;
    private HBoxHolder hBoxes;
    private VBoxHolder vBoxes;

    @Override
    public void start(Stage primaryStage) {
        InitializeUIElements();
        DisplayWindow(primaryStage, vBoxes.mainBox, 720, 1280);
    }

    private void InitializeUIElements(){
        buttons = new ButtonHolder();
        labels = new LabelHolder();
        hBoxes = new HBoxHolder();
        vBoxes = new VBoxHolder();

        //insert labels, buttons, hBoxes, vBoxes into boxes where necessary:
        FillHBoxes();
        FillVBoxes();
    }

    private void FillHBoxes(){
        //add gamemode selection + board size buttons/input fields:
        hBoxes.gameOptionsBox.getChildren().addAll(labels.gamemode, buttons.simpleGamemode, buttons.generalGamemode, labels.boardSize);

        //create spinner to allow for board size selection:
        //note: spinner doesn't allow for unique user input, invalid input tests are unneccessary
        Spinner<Integer> boardSizeSpinner = new Spinner<>(3, 30, 5); //min: 3, max: 30, default: 5
        boardSizeSpinner.setPrefWidth(52); //this width can accomodate integers < 100
        hBoxes.gameOptionsBox.getChildren().add(boardSizeSpinner);

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

        //initialize unplayable grid for display purposes (fill with empty labels)
        int DEFAULT_GRID_SIZE = 5;
        GridPane placeholderGrid = InitializeGrid(DEFAULT_GRID_SIZE, index -> new Label(""));
        vBoxes.sosGridBox.getChildren().add(placeholderGrid);

        vBoxes.mainBox.getChildren().add(hBoxes.startBox);
    }

    private static <T extends Region> GridPane InitializeGrid(int gridSize, Function<Integer, T> componentFactory){
        //create sos grid cells (may be changed later but i'll set them now for initial display purposes):
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        
        for(int row = 0; row < gridSize; ++row) {
            for(int col = 0; col < gridSize; ++col) {
                T component = componentFactory.apply(row * gridSize + col);
                component.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                component.setPrefSize(40, 40);
                grid.add(component, col, row);
            }
        }
        return grid;
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