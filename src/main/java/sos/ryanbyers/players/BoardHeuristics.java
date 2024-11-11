package sos.ryanbyers.players;

import javafx.scene.control.Cell;
import javafx.scene.layout.Region;
import sos.ryanbyers.gui.Board;

import javax.swing.*;
import java.util.ArrayList;

//contains a version of the board that contains heuristics
//loop through the boardHeursticContainer array and choose the cell that contains the highest heuristic value
//store in bestCell (this is where the piece will be placed, the type of piece to be placed is also stored within bestCell)
public class BoardHeuristics {
    public ArrayList<CellHeuristics> boardHeuristicContainer;
    public CellHeuristics bestCell;

    public BoardHeuristics(Board board) {
        //loop through current board state and perform scan to populate boardHeuristicContainer with CellHeuristics
        boardHeuristicContainer = new ArrayList<>();

        GenerateHeuristics(board);
    }

    private void GenerateHeuristics(Board board){
        for(ArrayList<Region> component : board.componentGrid){
            //generate heurstics
        }
    }
}
