package sos.ryanbyers.gameLogic;

import javafx.util.Pair;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.input.Alert;

public abstract class SOSGamemode {
    //for checking the board for new sequences after each turn
    protected Alert alert;
    protected SequenceScanner sequenceScanner;

    protected int redSequences = 0;
    protected int blueSequences = 0;


    //super constructor:
    public SOSGamemode() {
        this.alert = new Alert();
        this.sequenceScanner = new SequenceScanner();
    }


    public boolean SequenceMade(SOSGUI gui, int row, int col, TurnManager turnManager) {
        Pair<Boolean, Integer> result = this.sequenceScanner.SequenceSearch(gui, row, col, turnManager);

        //determine whether or not a sequence was made
        boolean sequenceMade = result.getKey();

        //handle points:
        if(sequenceMade) {
            int points = result.getValue();
            if(turnManager.redTurn){
                redSequences += points;
            }
            else{
                blueSequences += points;
            }
        }

        return sequenceMade;
    }

    //the board being full is a condition that can occur both in general and simple games
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
