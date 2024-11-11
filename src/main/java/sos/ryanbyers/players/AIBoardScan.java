package sos.ryanbyers.players;

public class AIBoardScan{
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
}

