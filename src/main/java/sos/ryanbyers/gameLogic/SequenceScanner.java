package sos.ryanbyers.gameLogic;

import javafx.scene.layout.Region;
import javafx.util.Pair;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.gui.SequenceCoordinates;

import java.util.HashSet;
import java.util.Set;

import java.util.Objects;

public class SequenceScanner {
    //contains all of the directions in which are necessary to scan (from the perspective of the placed piece)
    private int[][] directionsToScan = {
                {-1, 0},  // above
                {-1, 1},  // above-right diagonal
                {0, 1},   // right
                {1, 0},   // below
                {1, 1},   // below-right diagonal
                {1, -1},  // below-left diagonal
                {0, -1},  // left
                {-1, -1}  // above-left diagonal
        };

    //run after every move made
    public Pair<Boolean, Integer> SequenceSearch(SOSGUI gui, int row, int col, TurnManager turnManager) {
        //general game usage: count # of points made during this move (also a sequence was made if points > 0, so the turn shouldnt change)
        //simple game usage: if points > 0, a sequence was made and the game can now end
        int points = 0;

        boolean pieceIsO = GetPieceType(turnManager);

        return PerformScan();
    }

    private Pair<Boolean, Integer> PerformScan(){
        for (int[] direction : this.directionsToScan) {
            //if a sequence is not detected, this function will return null to sequence
            SequenceCoordinates sequence = CheckDirection(gui.board, row, col, direction[0], direction[1], pieceIsO);
            if (sequence != null){
                //keep track of number of sequences made
                points++;
                //draw a line to show the sequence visually
                gui.DrawSequenceLine(sequence.startRow, sequence.startCol, sequence.endRow, sequence.endCol, turnManager.redTurn);
            }
        }
        return new Pair<>(sequenceMade, points);
    }

    //figure out what the current piece is (is it an O or is it not an O?): (relevant to scanning process)
    private boolean GetPieceType(TurnManager turnManager){
        if(turnManager.redTurn){
            pieceIsO = gui.buttons.redO.isSelected();
        }
        else{
            pieceIsO = gui.buttons.blueO.isSelected();
        }

        return pieceIsO;
    }

//TO-DO: refactor this mess:
    private SequenceCoordinates CheckDirection(Board board, int row, int col, int rowDir, int colDir, boolean pieceIsO) {
        //if the piece placed is 'O', we need to find 'S' on both sides
        if (pieceIsO) {
            int oppositeRowDir = -rowDir;
            int oppositeColDir = -colDir;

            //calculate the positions for both surrounding cells
            int firstRow = row + rowDir;
            int firstCol = col + colDir;
            int secondRow = row + oppositeRowDir;
            int secondCol = col + oppositeColDir;

            if (IsInBounds(board, firstRow, firstCol) && IsInBounds(board, secondRow, secondCol)) {
                Region firstCell = board.getCell(firstRow, firstCol);
                Region secondCell = board.getCell(secondRow, secondCol);

                if (Objects.equals(firstCell.getId(), "S") && Objects.equals(secondCell.getId(), "S")) {
                    //'O' is in the middle, so the sequence starts at `secondRow, secondCol` and ends at `firstRow, firstCol`
                    return new SequenceCoordinates(secondRow, secondCol, firstRow, firstCol);
                }
            }
        } else {
            int nextRow = row + rowDir;
            int nextCol = col + colDir;

            if (IsInBounds(board, nextRow, nextCol)) {
                Region nextCell = board.getCell(nextRow, nextCol);
                if (Objects.equals(nextCell.getId(), "O")) {
                    int afterNextRow = nextRow + rowDir;
                    int afterNextCol = nextCol + colDir;

                    if (IsInBounds(board, afterNextRow, afterNextCol)) {
                        Region afterNextCell = board.getCell(afterNextRow, afterNextCol);

                        if (Objects.equals(afterNextCell.getId(), "S")) {
                            //'S' is at the start, so the sequence starts at `row, col` and ends at `afterNextRow, afterNextCol`
                            return new SequenceCoordinates(row, col, afterNextRow, afterNextCol);
                        }
                    }
                }
            }
        }
        //no sequence found
        return null;
    }

    private boolean IsInBounds(Board board, int row, int col) {
        return row >= 0 && row < board.componentGrid.size() && col >= 0 && col < board.componentGrid.size();
    }

    public boolean BoardFull(Board board){
        for (int row = 0; row < board.componentGrid.size(); row++) {
            for (int col = 0; col < board.componentGrid.size(); col++) {
                Region button = board.getCell(row, col);
                //check for at least one unoccupied cell
                if (!button.isDisabled()) {
                    return false;
                }
            }
        }
        return true;
    }

}
