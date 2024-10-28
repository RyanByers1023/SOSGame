package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSGeneralGamemode extends SOSGamemode {
    private int redSequences = 0;
    private int blueSequences = 0;

    public SOSGeneralGamemode(Board board, TurnManager turnManager) { super(board, turnManager); }

    //run after each turn:
    @Override
    public void HandleTurn(){
        //do not change turns when a sequence is made by either player (general game rule) or the game has ended
        if(SequenceMade()){
            HandleSequenceFound();
            return;
        }
        //if none of the above are applicable, just change the turn to the other player
        turnManager.ChangeTurns();
    }

    @Override
    public boolean StalemateCondition(){
        if(BoardFull() && redSequences == blueSequences){
            alert.NotifyGeneralStalemate(redSequences);
            return true;
        }
        return false;

    }

    @Override
    public boolean RedVictoryCondition(){
        if(BoardFull() && redSequences > blueSequences){
            alert.NotifyGeneralRedVictory(redSequences, blueSequences);
            return true;
        }
        return false;
    }

    @Override
    public boolean BlueVictoryCondition(){
        if(BoardFull() && redSequences < blueSequences){
            alert.NotifyGeneralBlueVictory(redSequences, blueSequences);
            return true;
        }
        return false;
    }

    @Override
    public void HandleSequenceFound(){
        if(turnManager.redTurn){
            redSequences++;
        }
        else{
            blueSequences++;
        }
    }

    @Override
    public void ResetGame(){
        redSequences = 0;
        blueSequences = 0;

    }
}
