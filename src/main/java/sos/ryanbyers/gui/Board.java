package sos.ryanbyers.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.function.Function;
import java.util.ArrayList;

public class Board {
    public GridPane grid;
    public ArrayList<ArrayList<Region>> componentGrid;

    //can create a board of labels or buttons
    public enum ComponentType {
        LABEL,
        BUTTON
    }

    public Board(int gridSize, ComponentType type) {
        grid = new GridPane();
        grid.setPadding(new Insets(10));
        componentGrid = new ArrayList<>();

        InitializeGrid(gridSize, type);
    }

    public void InitializeGrid(int gridSize, ComponentType type) {
        Function<Integer, Region> componentFactory;

        //prep the correct type of board component based on function input
        if(type == ComponentType.LABEL){
            componentFactory = index -> new Label("");
        }
        else{
            componentFactory = index -> new Button();
        }

        //create sos grid cells with selected type:
        for(int row = 0; row < gridSize; ++row) {
            //build grid row by row
            ArrayList<Region> buttonRow = new ArrayList<>();
            for(int col = 0; col < gridSize; ++col) {
                //instantiate the component
                Region component = componentFactory.apply(row * gridSize + col);
                //style the component
                component.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                component.setPrefSize(40, 40);

                //add the component to the board
                grid.add(component, col, row);
                //add the component to the buttonRow array for later easy access
                buttonRow.add(component);
            }
            //add the whole row to button Grid
            componentGrid.add(buttonRow);
        }
    }

    //for easy access to buttons
    public Region getCell(int row, int col){
        return componentGrid.get(row).get(col);
    }
}
