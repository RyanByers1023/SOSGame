package sos.ryanbyers.gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.util.Pair;


import java.util.ArrayList;

public abstract class Board {
    public GridPane grid;
    public ArrayList<ArrayList<Region>> componentGrid;
    public Pair<Integer, Integer> cellSize;

    public Board(int gridSize) {
        grid = new GridPane();
        componentGrid = new ArrayList<>();
        cellSize = new Pair<> (40, 40);

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

                //add the cell to the grid
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
