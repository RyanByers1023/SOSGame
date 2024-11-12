package sos.ryanbyers.gameLogic;

import javafx.scene.layout.Region;
import javafx.util.Pair;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.gui.SequenceCoordinates;
import sos.ryanbyers.gui.Vec2;

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

    //TO-DO: pass pieceIsO by parameter (makes AIBoardScan much easier)
    public int SequenceSearch(SOSGUI gui, Vec2 cellPos, TurnManager turnManager) {
        //the piece is an 'O', or it is not an 'O' (in which case, it must be an 'S')
        //this is needed because the scanner looks for different sequence fragments depending on the piece it is searching from
        boolean redTurn = turnManager.redTurn;
        boolean pieceIsO = GetPieceType(gui, turnManager);

        return PerformScan(gui, cellPos, pieceIsO, redTurn, true);
    }

    //this function is also used by the computer opponent. realMove is set to false when this occurs
    public int PerformScan(SOSGUI gui, Vec2 cellPos, boolean pieceIsO, boolean redTurn, boolean realMove) {
        //general game usage: count # of sequences made during this move. dont change turn if at least one was made
        //simple game usage: if sequencesMade > 0, a sequence was made and the game can now end
        int sequencesMade = 0;

        //track unique sequences to prevent duplicate counting
        Set<SequenceCoordinates> uniqueSequences = new HashSet<>();

        for (int[] direction : this.directionsToScan) {
            //if a sequence is not detected, this function will return null to sequence
            SequenceCoordinates sequence = CheckDirection(gui.board, cellPos, new Vec2(direction[0], direction[1]), pieceIsO);
            if (sequence != null && uniqueSequences.add(sequence)){
                //keep track of number of sequences made
                sequencesMade++;
                //draw a line to show the sequence visually
                if(realMove){
                    gui.DrawSequenceLine(sequence, redTurn);
                }
            }
        }
        return sequencesMade;
    }

    //figure out what the current piece is (is it an O or is it not an O?): (relevant to scanning process)
    private boolean GetPieceType(SOSGUI gui, TurnManager turnManager){
        if(turnManager.redTurn){
            return gui.buttons.redO.isSelected();
        }
        return gui.buttons.blueO.isSelected();
    }

    private SequenceCoordinates CheckDirection(Board board, Vec2 cellPos, Vec2 cellDirection, boolean pieceIsO) {
        //if the piece placed is 'O', an 'S' needs to be on both sides of the 'O'
        if (pieceIsO) {
            return HandleOCheck(board, cellPos, cellDirection);
        }
        //if the piece placed is 'S', an 'OS' (after the 'S') or an 'SO' (before the 'S') needs to be found
        else {
            return HandleSCheck(board, cellPos, cellDirection);
        }
    }

    private SequenceCoordinates HandleOCheck(Board board, Vec2 cellPos, Vec2 cellDirection){
        int oppositeRowDir = -cellDirection.y;
        int oppositeColDir = -cellDirection.x;

        //calculate the positions for both surrounding cells:
        int firstRow = cellPos.y + cellDirection.y;
        int firstCol = cellPos.x + cellDirection.x;
        Vec2 firstCellPos = new Vec2(firstCol, firstRow);

        int secondRow = cellPos.y + oppositeRowDir;
        int secondCol = cellPos.x + oppositeColDir;
        Vec2 secondCellPos = new Vec2(secondCol, secondRow);

        //check if these values lie within the board's bounds:
        if (IsInBounds(board, firstCellPos) && IsInBounds(board, secondCellPos)) {
            Region firstCell = board.GetCell(firstCellPos);
            Region secondCell = board.GetCell(secondCellPos);

            //looking for two surrounding Ss, check to see if these fulfil this parameter
            if (Objects.equals(firstCell.getId(), "S") && Objects.equals(secondCell.getId(), "S")) {
                //SOS sequence found, return its coordinates
                return new SequenceCoordinates(firstCellPos, secondCellPos);
            }
        }

        //sequence not found
        return null;
    }

    private SequenceCoordinates HandleSCheck(Board board, Vec2 cellPos, Vec2 cellDirection){
        int nextCol = cellPos.x + cellDirection.x;
        int nextRow = cellPos.y + cellDirection.y;
        Vec2 nextCellPos = new Vec2(nextCol, nextRow);

        if (IsInBounds(board, nextCellPos)) {
            Region nextCell = board.GetCell(nextCellPos);
            //an 'O' was found, now check if that 'O' is followed by an 'S':
            if (Objects.equals(nextCell.getId(), "O")) {
                int afterNextCol = nextCol + cellDirection.x;
                int afterNextRow = nextRow + cellDirection.y;
                Vec2 afterNextCellPos = new Vec2(afterNextCol, afterNextRow);

                //ensure the position that is to be checked for a potential 'S' is within the bounds of the board:
                if (IsInBounds(board, afterNextCellPos)) {
                    Region afterNextCell = board.GetCell(afterNextCellPos);

                    //this 'S' (if found) completes the sequence:
                    if (Objects.equals(afterNextCell.getId(), "S")) {
                        //SOS sequence found, return its coordinates
                        return new SequenceCoordinates(cellPos, afterNextCellPos);
                    }
                }
            }
        }

        //sequence not found
        return null;
    }

    private boolean IsInBounds(Board board, Vec2 cellPos) {
        //check if both row value and col value fall within the boundaries of the grid
        return  cellPos.y >= 0 &&
                cellPos.y < board.componentGrid.size() &&
                cellPos.x >= 0 &&
                cellPos.x < board.componentGrid.size();
    }
}
