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
        for (int row = 0; row < gridSize; ++row) {
            //build grid row by row
            ArrayList<Region> cellRow = new ArrayList<>();
            for (int col = 0; col < gridSize; ++col) {
                Region cell = InstantiateCell();

                //style the cell
                cell.setStyle("-fx-border-color: black; -fx-border-width: 1px; -fx-background-color: white;");
                cell.setPrefSize(cellSize.getKey(), cellSize.getValue());

                //add the cell to the row
                cellRow.add(cell);

                //add the cell to the ui grid
                grid.add(cell, col, row);
            }
            //add the whole row to componentGrid
            this.componentGrid.add(cellRow);
        }
    }

    //for easy access to buttons
    public Region getCell(int row, int col){
        return this.componentGrid.get(row).get(col);
    }

    public Pair<Integer, Integer> GetCellSize(){
        return this.cellSize;
    }

    public abstract Region InstantiateCell();
}
