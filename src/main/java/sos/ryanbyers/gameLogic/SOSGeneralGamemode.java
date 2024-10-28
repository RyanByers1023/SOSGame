package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSGeneralGamemode extends SOSGamemode {
    private int redSequences = 0;
    private int blueSequences = 0;

    public SOSGeneralGamemode(TurnManager turnManager) { super(turnManager); }

    //run after each turn:
    @Override
    public void HandleTurn(Board board, TurnManager turnManager){
        //do not change turns when a sequence is made by either player (general game rule) or the game has ended
        if(SequenceMade()){
            HandleSequenceFound();
            return;
        }
        //if none of the above are applicable, just change the turn to the other player
        turnManager.ChangeTurns();
    }

    //handle when a stalemate is reached:
    @Override
    public void HandleStalemate(){
        alert.NotifyGeneralStalemate();
    }

    //handle when a red victory occurs
    @Override
    public void HandleRedVictory(){
        alert.NotifyGeneralRedVictory();
    }

    //handle the case in hwihc  the blue player wins
    @Override
    public void HandleBlueVictory(){
        alert.NotifyGeneralBlueVictory();
    }

    @Override
    public void HandleSequenceFound(TurnManager turnManager){
        if(turnManager.redTurn){
            redSequences++;
        }
        else{
            blueSequences++;
        }
    }
}
