package sos.ryanbyers.gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Board {
    public GridPane grid;
    private Map<String, Button> buttonMap;

    //can create a board of labels or buttons
    public enum ComponentType {
        LABEL,
        BUTTON
    }

    public Board(int gridSize, ComponentType type) {
        grid = new GridPane();
        grid.setPadding(new Insets(10));

        buttonMap = new HashMap<>();

        InitializeGrid(gridSize, type);
    }

    public void InitializeGrid(int gridSize, ComponentType type) {
        Function<Integer, Region> componentFactory;

        if(type == ComponentType.LABEL){
            componentFactory = index -> new Label("");
        }
        else{
            componentFactory = index -> new Button();
        }

        //create sos grid cells with selected type:
        for(int row = 0; row < gridSize; ++row) {
            for(int col = 0; col < gridSize; ++col) {
                Region component = componentFactory.apply(row * gridSize + col);
                component.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                component.setPrefSize(40, 40);
                if(type == ComponentType.BUTTON){
                    String key = row + "," + col;
                    buttonMap.put(key, (Button) component);
                }
                grid.add(component, col, row);
            }
        }
    }

    //for easy access to buttons
    public Button getButton(int row, int col){
        return buttonMap.get(row + "," + col);
    }

    //for listener
    public Collection<Button> getAllButtons(){
        return buttonMap.values();
    }
}
