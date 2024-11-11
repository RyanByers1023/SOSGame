package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.input.AlertMessage;

public abstract class SOSGamemode {
    //for checking the board for new sequences after each turn
    protected AlertMessage alertMessage;
    protected SequenceScanner sequenceScanner;

    protected int redSequences = 0;
    protected int blueSequences = 0;


    //super constructor:
    public SOSGamemode() {
        this.alertMessage = new AlertMessage();
        this.sequenceScanner = new SequenceScanner();
    }


    public boolean SequenceMade(SOSGUI gui, int row, int col, TurnManager turnManager) {
       int sequencesMade = this.sequenceScanner.SequenceSearch(gui, row, col, turnManager);

        //handle points:
        if(turnManager.redTurn){
            redSequences += sequencesMade;
        }
        else{
            blueSequences += sequencesMade;
        }

       return sequencesMade > 0;
    }

    //the board being full is a condition that needs to be checked in both gamemodes
    public boolean BoardFull(Board board){
        return this.sequenceScanner.BoardFull(board);
    }

    //abstract methods:

    //turns handled slightly different in general vs simple
    public abstract void HandleTurn(SOSGUI gui, TurnManager turnManager, int row, int col);

    //red/blue victory determined by a point value within general games, no points in simple games
    public abstract void HandleRedVictory(SOSGUI gui);
    public abstract void HandleBlueVictory(SOSGUI gui);

    //stalemate determined by point values in general games, no points in simple games
    public abstract void HandleStalemate(SOSGUI gui);

    public abstract void HandleSequenceFound(SOSGUI gui, TurnManager turnManager);
}
