package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSSimpleGamemode extends SOSGamemode {
    public SOSSimpleGamemode(TurnManager turnManager) {
        super(turnManager);
    }

    //runs each turn: (run when a piece is placed)
    @Override
    public void HandleTurn(Board board){
        if(SequenceMade(board)){
            HandleSequenceFound();
            return;
        }
        if(BoardFull(board)){
            StalemateCondition();
            return;
        }
        turnManager.ChangeTurns();
    }

    @Override
    public boolean StalemateCondition(Board board){
        if(BoardFull(board)){
            alert.NotifySimpleStalemate();
            return true;
        }
        return false;
    }

    //run after a sequence has been made:
    @Override
    public boolean RedVictoryCondition(TurnManager turnManager, Board board){
        if(SequenceMade(board) && turnManager.redTurn){
            alert.NotifySimpleRedVictory();
            return true;
        }
        return false;
    }

    //run after a sequence has been made:
    @Override
    public boolean BlueVictoryCondition(TurnManager turnManager))
        if(sequenceFound && turnManager.blueTurn){
            alert.NotifySimpleBlueVictory();
            return true;
        }
        return false;
    }

    //run when a sequence is found
    @Override
    public void HandleSequenceFound(){
        if(turnManager.redTurn){
            RedVictoryCondition();
        }
        else{
            BlueVictoryCondition();
        }
    }
}
