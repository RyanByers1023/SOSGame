package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;
import sos.ryanbyers.input.Alert;

public abstract class SOSGamemode {
    //for determining who goes first/changing turns thereafter
    public TurnManager turnManager;

    //for checking the board for new sequences after each turn
    public SequenceScanner sequenceScanner;

    //the SOS board itself, containing all buttons and cells
    protected Board board;
    protected Alert alert;

    //super constructor:
    public SOSGamemode(Board board, TurnManager turnManager) {
        this.board = board;
        this.turnManager = turnManager;
        this.alert = new Alert();
        sequenceScanner = new SequenceScanner(board);
    }

    //super methods:
    //a sequence is a sequence, no difference between gamemodes
    protected boolean SequenceMade(){
        return sequenceScanner.SequenceSearch();
    }

    //the board being full is a condition that can occur both in general and simple games
    public boolean BoardFull(){
        return sequenceScanner.BoardFull();
    }

    //abstract methods:

    //turns handled slightly different in general vs simple
    public abstract void HandleTurn();

    //red/blue victory determined by a point value within general games, no points in simple games
    public abstract boolean RedVictoryCondition();
    public abstract boolean BlueVictoryCondition();

    //stalemate determined by point values in general games, no points in simple games
    public abstract boolean StalemateCondition();

    public abstract void HandleSequenceFound();

    public abstract void ResetGame();
}
