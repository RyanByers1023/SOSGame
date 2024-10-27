package sos.ryanbyers.gameLogic;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import sos.ryanbyers.gui.Board;

import java.util.Objects;

public class SequenceScanner {
    private Board board;

    public SequenceScanner(Board board) {
        this.board = board;
    }

    //run after every move made
    public boolean SequenceCreated(){
        for (int row = 0; row < board.componentGrid.size(); row++) {
            for (int col = 0; col < board.componentGrid.size(); col++) {
                // If an "O" is present, check for all possible valid "SOS" sequences
                Region button = board.getCell(row, col);
                //"O" must be in the middle of a valid SOS sequence, best to only start a search from there:
                if (Objects.equals(button.getId(), "O")) {
                    //check 1 unit in all directions from position of cell for an "S"
                    if (checkDirection(row, col, -1, 0) || //up
                            checkDirection(row, col, 1, 0) ||  //down
                            checkDirection(row, col, 0, -1) || //left
                            checkDirection(row, col, 0, 1) ||  //right
                            checkDirection(row, col, -1, -1) || //up-Left Diagonal
                            checkDirection(row, col, -1, 1) ||  //up-Right Diagonal
                            checkDirection(row, col, 1, -1) ||  //down-Left Diagonal
                            checkDirection(row, col, 1, 1)) {   //down-Right Diagonal
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //found an "SO" or an "OS". need to check if there exists another "S" to complete the sequence:
    private boolean checkDirection(int row, int col, int rowDir, int colDir) {
        //calculate positions of the surrounding 'S' cells for "SOS"
        int startRow = row + rowDir;
        int startCol = col + colDir;
        int endRow = row - rowDir;
        int endCol = col - colDir;

        //indices must be within bounds to form an "SOS"
        if (isInBounds(startRow, startCol) && isInBounds(endRow, endCol)) {
            Region startS = board.getCell(startRow, startCol); //before the "O"
            Region endS = board.getCell(endRow, endCol); //after the "O"
            return Objects.equals(startS.getId(), "S") && Objects.equals(endS.getId(), "S");
        }
        return false;
    }

    private boolean isInBounds(int row, int col) {
        return row >= 0 && row < board.componentGrid.size() && col >= 0 && col < board.componentGrid.size();
    }

    public boolean BoardFull(){
        for (int row = 0; row < board.componentGrid.size(); row++) {
            for (int col = 0; col < board.componentGrid.size(); col++) {
                Region button = board.getCell(row, col);
                //check for at least one unoccupied cell
                if (!Objects.equals(button.getId(), "O") && !Objects.equals(button.getId(), "S")) {
                    return false;
                }
            }
        }
        return true;
    }

}
