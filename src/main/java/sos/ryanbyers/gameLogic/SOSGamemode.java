package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public abstract class SOSGamemode {
    protected TurnManager turnManager;
    protected SequenceScanner sequenceScanner;
    protected Board board;

    public SOSGamemode(Board board) {
        this.board = board;
        turnManager = new TurnManager();
        sequenceScanner = new SequenceScanner(board);
    }

    public abstract void HandleTurn();

    protected boolean SequenceMade(){
        return sequenceScanner.SequenceCreated();
    }

    public boolean BoardFull(){
        return sequenceScanner.BoardFull();
    }

    public abstract boolean WinConditionMet();

    public abstract boolean StalemateConditionMet();

    public abstract void DisplayWinner();

    public abstract boolean RedHasWon();
}
