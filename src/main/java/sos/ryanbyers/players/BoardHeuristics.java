package sos.ryanbyers.players;

import java.util.ArrayList;
import java.util.Random;

//loop through the boardHeuristicContainer array and choose the cell that contains the highest heuristic value
//store in bestCell (this is where the piece will be placed, the type of piece to be placed is also stored within bestCell)
public class BoardHeuristics {
    public ArrayList<CellHeuristics> boardHeuristicContainer;
    public CellHeuristics bestCell;

    public BoardHeuristics() {
        //loop through current board state and perform scan to populate boardHeuristicContainer with CellHeuristics
        boardHeuristicContainer = new ArrayList<>();
        bestCell = null;
    }

    public void DetermineBestPlacement(){
        int maxHeuristicValue = Integer.MIN_VALUE;

        for (CellHeuristics cell : boardHeuristicContainer) {
            if (cell.heuristicValue > maxHeuristicValue) {
                maxHeuristicValue = cell.heuristicValue;
                bestCell = cell;
            }
        }

        //there are no best selections, so choose a piece at random:
        if(bestCell.heuristicValue == 0){
            Random random = new Random();
            bestCell.pieceIsO = random.nextBoolean();
        }
    }
}
