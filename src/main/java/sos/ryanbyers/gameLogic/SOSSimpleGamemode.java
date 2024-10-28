package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSSimpleGamemode extends SOSGamemode {
    public SOSSimpleGamemode(Board board , TurnManager turnManager) {
        super(board, turnManager);
    }
    private boolean sequenceFound = false;

    //runs each turn: (run when a piece is placed)
    @Override
    public void HandleTurn(){
        if(SequenceMade()){
            HandleSequenceFound();
            return;
        }
        if(BoardFull()){
            StalemateCondition();
            return;
        }
        turnManager.ChangeTurns();
    }

    @Override
    public boolean StalemateCondition(){
        if(BoardFull()){
            alert.NotifySimpleStalemate();
            return true;
        }
        return false;
    }

    //run after a sequence has been made:
    @Override
    public boolean RedVictoryCondition(){
        if(SequenceMade() && turnManager.redTurn){
            alert.NotifySimpleRedVictory();
            return true;
        }
        return false;
    }

    //run after a sequence has been made:
    @Override
    public boolean BlueVictoryCondition(){
        if(sequenceFound && turnManager.blueTurn){
            alert.NotifySimpleBlueVictory();
            return true;
        }
        return false;
    }

    //run when a sequence is found
    @Override
    public void HandleSequenceFound(){
        sequenceFound = true;
        if(turnManager.redTurn){
            RedVictoryCondition();
        }
        else{
            BlueVictoryCondition();
        }
    }

    @Override
    public void ResetGame(){

    }
}
