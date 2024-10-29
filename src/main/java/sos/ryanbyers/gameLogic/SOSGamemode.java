package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;
import sos.ryanbyers.input.Alert;

public abstract class SOSGamemode {
    //for checking the board for new sequences after each turn
    protected Alert alert;

    //super constructor:
    public SOSGamemode() {
        this.alert = new Alert();
    }

    //super methods:
    //a sequence is a sequence, no difference between gamemodes
    public void SequenceMade(Board board, SequenceScanner sequenceScanner){
        return sequenceScanner.SequenceSearch(board);
    }

    //the board being full is a condition that can occur both in general and simple games
    public boolean BoardFull(Board board, SequenceScanner sequenceScanner){
        return sequenceScanner.BoardFull(board);
    }

    //abstract methods:

    //turns handled slightly different in general vs simple
    public abstract void HandleTurn(Board board, TurnManager turnManager);

    //red/blue victory determined by a point value within general games, no points in simple games
    public abstract boolean HandleRedVictory(SOSGUI gui);
    public abstract boolean HandleBlueVictory(SOSGUI gui);

    //stalemate determined by point values in general games, no points in simple games
    public abstract boolean HandleStalemate(SOSGUI gui);

    public abstract void HandleSequenceFound(TurnManager turnManager);
}
