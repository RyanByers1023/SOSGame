package sos.ryanbyers.players;

import sos.ryanbyers.gameLogic.SequenceScanner;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.gui.Vec2;

public class AIBoardScan{
    private SequenceScanner sequenceScanner;

    public AIBoardScan(){
        sequenceScanner = new SequenceScanner();
    }
    //perform a full scan of the board
    //generate a list of potential placements for the ai to make
    //select from the list the best placement and make that placement

    //how?:
    //scan entire board
    //for each cell, assign the best piece to place there ('S' or 'O') and a heuristic value:
    //the heuristic value is determined by the number of sequences placing a piece there would make
    //the best piece to place is determined by the number of potential sequences that would be made if that piece was placed there
    //so, if an 'S' creates 1 sequence, and an 'O' generates 3, the cell will be marked as 'O' (piece to place) - 3 (heuristic value)
    //if either pieces both make the same number of sequences, roll a die to determine which one is placed at that spot
    //if all spaces have the same heuristic value, pick a random spot.
    //repeat until game ends (board full/first sequence made)

    //how to scan individual cells?:
    //use SequenceScanner, and insert the value and the piece type that is to be tested for value
    //for each cell, both 'S' and 'O' will be tested
    //whichever piece yields a higher sequencesMade value is determined to be the desired piece to place at that cell
    //sequencesMade is assigned as the heuristic value for that cell

    //how to determine where to put a piece after scanning the board?:
    //scan the list of CellHeuristics
    //determine which cell has the highest heuristic value and place a piece there.
    //if multiple cells have the same heuristic value, choose a random cell in the set of the best cell choices

    public BoardHeuristics PerformBoardScan(SOSGUI gui){
        BoardHeuristics boardHeuristics = new BoardHeuristics();
        for(int col = 0; col < gui.board.componentGrid.size(); ++col){
            CellHeuristics currentCellInfo = null;
            for(int row = 0; row < gui.board.componentGrid.size(); ++row){
                //perform a scan from the current cell:
                Vec2 currentCellPos = new Vec2(col, row);

                //but make sure it is an unoccupied space:
                if(!gui.board.unoccupiedCells.contains(currentCellPos)){
                    continue;
                }

                //obtain # of sequences created for placing an 'O' at this cellPos
                boolean placeO = true;
                int oPlacementSequences = sequenceScanner.PerformScan(gui, currentCellPos, placeO, true, false);

                //obtain # of sequences created for placing an 'S' at this cellPos
                placeO = false;
                int sPlacementSequences = sequenceScanner.PerformScan(gui, currentCellPos, placeO, true, false);

                //determine the best piece for this cell:
                boolean bestPieceIsO = oPlacementSequences >= sPlacementSequences;
                int maxSequences = Math.max(oPlacementSequences, sPlacementSequences);

                //store this cell and all the info needed for the AI to make a decision on whether it will be a good choice within currentCellInfo
                currentCellInfo = new CellHeuristics(currentCellPos, maxSequences, bestPieceIsO);

                //add the current cell to the cell container:
                boardHeuristics.boardHeuristicContainer.add(currentCellInfo);
            }
        }
        return boardHeuristics;
    }
}

