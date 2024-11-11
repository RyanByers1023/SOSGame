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
    //all directions (up, down, left, right...) that are to be scanned for sequences surrounding the placed piece
    private final int[][] directionsToScan = {
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
    //row and col store the position (x, y) of the cell of the last piece placement
    public int SequenceSearch(SOSGUI gui, int row, int col, TurnManager turnManager) {
        //the piece is an 'O', or it is not an 'O' (in which case, it must be an 'S')
        boolean pieceIsO = GetPieceType(gui, turnManager);

        return PerformScan(gui, row, col, turnManager, pieceIsO);
    }

    private int PerformScan(SOSGUI gui, int row, int col, TurnManager turnManager, boolean pieceIsO) {
        //general game usage: count # of points made during this move (also a sequence was made if points > 0, so the turn shouldnt change)
        //simple game usage: if points > 0, a sequence was made and the game can now end
        int points = 0;

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
        return points;
    }

    //figure out what the current piece is (is it an O or is it not an O?): (relevant to scanning process)
    private boolean GetPieceType(SOSGUI gui, TurnManager turnManager){
        if(turnManager.redTurn){
            return gui.buttons.redO.isSelected();
        }
        return gui.buttons.blueO.isSelected();
    }

    private SequenceCoordinates CheckDirection(Board board, int row, int col, int rowDir, int colDir, boolean pieceIsO) {
        //if the piece placed is 'O', an 'S' needs to be on both sides of the 'O'
        if (pieceIsO) {
            return HandleOCheck(board, row, col, rowDir, colDir);
        }
        //if the piece placed is 'S', an 'OS' (after the 'S') or an 'SO' (before the 'S') needs to be found
        else {
            return HandleSCheck(board, row, col, rowDir, colDir);
        }
    }

    private SequenceCoordinates HandleOCheck(Board board, int row, int col, int rowDir, int colDir){
        int oppositeRowDir = -rowDir;
        int oppositeColDir = -colDir;

        //calculate the positions for both surrounding cells:
        int firstRow = row + rowDir;
        int firstCol = col + colDir;
        int secondRow = row + oppositeRowDir;
        int secondCol = col + oppositeColDir;

        //check if these values lie within the board's bounds:
        if (IsInBounds(board, firstRow, firstCol) && IsInBounds(board, secondRow, secondCol)) {
            Region firstCell = board.getCell(firstRow, firstCol);
            Region secondCell = board.getCell(secondRow, secondCol);

            //looking for two surrounding Ss, check to see if these fulfil this parameter
            if (Objects.equals(firstCell.getId(), "S") && Objects.equals(secondCell.getId(), "S")) {
                //SOS sequence found, return its coordinates
                return new SequenceCoordinates(secondRow, secondCol, firstRow, firstCol);
            }
        }

        //sequence not found
        return null;
    }

    private SequenceCoordinates HandleSCheck(Board board, int row, int col, int rowDir, int colDir){
        int nextRow = row + rowDir;
        int nextCol = col + colDir;

        if (IsInBounds(board, nextRow, nextCol)) {
            Region nextCell = board.getCell(nextRow, nextCol);
            //an 'O' was found, now check if that 'O' is followed by an 'S':
            if (Objects.equals(nextCell.getId(), "O")) {
                int afterNextRow = nextRow + rowDir;
                int afterNextCol = nextCol + colDir;

                //ensure the position that is to be checked for a potential 'S' is within the bounds of the board:
                if (IsInBounds(board, afterNextRow, afterNextCol)) {
                    Region afterNextCell = board.getCell(afterNextRow, afterNextCol);

                    //this 'S' (if found) completes the sequence:
                    if (Objects.equals(afterNextCell.getId(), "S")) {
                        //SOS sequence found, return its coordinates
                        return new SequenceCoordinates(row, col, afterNextRow, afterNextCol);
                    }
                }
            }
        }

        //sequence not found
        return null;
    }

    private boolean IsInBounds(Board board, int row, int col) {
        //check if both row value and col value fall within the boundaries of the grid
        return  row >= 0 &&
                row < board.componentGrid.size() &&
                col >= 0 &&
                col < board.componentGrid.size();
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
