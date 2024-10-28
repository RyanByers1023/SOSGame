package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;
import sos.ryanbyers.input.Alert;

public abstract class SOSGamemode {
    //for checking the board for new sequences after each turn
    public SequenceScanner sequenceScanner;
    protected Alert alert;

    //super constructor:
    public SOSGamemode() {
        this.alert = new Alert();
        sequenceScanner = new SequenceScanner();
    }

    //super methods:
    //a sequence is a sequence, no difference between gamemodes
    public boolean SequenceMade(board){
        return sequenceScanner.SequenceSearch(board);
    }

    //the board being full is a condition that can occur both in general and simple games
    public boolean BoardFull(board){
        return sequenceScanner.BoardFull(board);
    }

    //abstract methods:

    //turns handled slightly different in general vs simple
    public abstract void HandleTurn(TurnManager turnManager);

    //red/blue victory determined by a point value within general games, no points in simple games
    public abstract boolean RedVictoryCondition(Board board, TurnManager turnManager);
    public abstract boolean BlueVictoryCondition(Board board, TurnManager turnManager);

    //stalemate determined by point values in general games, no points in simple games
    public abstract boolean StalemateCondition(Board board, TurnManager turnManager);

    public abstract void HandleSequenceFound();
}
