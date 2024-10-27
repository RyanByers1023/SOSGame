package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public abstract class SOSGamemode {
    //for determining who goes first/changing turns thereafter
    protected TurnManager turnManager;
    //for checking the board for new sequences after each turn
    protected SequenceScanner sequenceScanner;
    //the SOS board itself, containing all buttons and cells
    protected Board board;

    //super constructor:
    public SOSGamemode(Board board) {
        this.board = board;
        turnManager = new TurnManager();
        sequenceScanner = new SequenceScanner(board);
    }

    //super methods:
    //a sequence is a sequence, no difference in how they are made
    protected boolean SequenceMade(){
        return sequenceScanner.SequenceCreated();
    }

    //the board being full is a condition that can occur both in general and simple games
    public boolean BoardFull(){
        return sequenceScanner.BoardFull();
    }

    //abstract methods:

    //turns handled slightly different in general vs simple
    public abstract void HandleTurn();
       
    //end game condition is different for general vs simple game
    public abstract boolean GameEndCondition();

    //red/blue victory determined by a point value within general games, no points in simple games
    public abstract boolean RedVictoryCondition();

    public abstract boolean BlueVictoryCondition();

    //stalemate determied by point values in general games, no points in simple games
    public abstract boolean StalemateCondition();

    //the winner in a general game will have their points displayed, simple games cannot display this as they do not have any points
    public abstract void DisplayWinner();
}
