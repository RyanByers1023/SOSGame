package sos.ryanbyers.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Pair;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public abstract class Board {
    //grid that is displayed within the ui to user
    public GridPane grid;

    //for direct, easy access to grid elements
    public ArrayList<ArrayList<Region>> componentGrid;

    //used to set the size of the cells
    public Pair<Integer, Integer> cellSize;

    //used to track which cells are still unused (for AI scanner and BoardIsFull())
    //.x = column
    //.y = row
    public Set<Vec2> unoccupiedCells;

    public Board(int gridSize) {
        grid = new GridPane();
        componentGrid = new ArrayList<>();

        //change this value to make cells bigger or smaller
        cellSize = new Pair<> (40, 40);

        unoccupiedCells = new HashSet<>();

        InitializeGrid(gridSize);
    }

    public void InitializeGrid(int gridSize) {
        //build the column:
        for (int col = 0; col < gridSize; ++col) {
            ArrayList<Region> cellCol = new ArrayList<>();
            //build the row:
            for (int row = 0; row < gridSize; ++row) {
                Region cell = InstantiateCell();

                //style the cell
                cell.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                cell.setPrefSize(cellSize.getKey(), cellSize.getValue());

                //add the cell to the row
                cellCol.add(cell);

                //add the cell to the ui grid
                grid.add(cell, col, row);

                //add the cell to unoccupied cells
                unoccupiedCells.add(new Vec2(col, row));
            }
            //add the whole row to componentGrid
            this.componentGrid.add(cellCol);
        }
    }

    //for easy access to buttons
    public Region GetCell(Vec2 cellPos){
        return this.componentGrid.get(cellPos.x).get(cellPos.y);
    }

    public Pair<Integer, Integer> GetCellSize(){
        return this.cellSize;
    }

    public void DisableCell(Vec2 cellPos){
        GetCell(cellPos).setDisable(true);
        unoccupiedCells.remove(cellPos);
    }

    public abstract Region InstantiateCell();
}
