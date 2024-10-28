package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSSimpleGamemode extends SOSGamemode {
    public SOSSimpleGamemode() {
        super();
    }

    //run when a player places a piece onto the board: handle when a piece is placed on the board
    @Override
    public void HandleTurn(Board board, TurnManager turnManager){
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

    //handle when a stalemate is reached:
    @Override
    public void HandleStalemate(){
            alert.NotifySimpleStalemate();
    }

    //handle when a red victory occurs
    @Override
    public void HandleRedVictory(){
        alert.NotifySimpleRedVictory();
    }

    //handle when the blue player wins
    @Override
    public void HandleBlueVictory(){
        alert.NotifySimpleBlueVictory();
    }

    //handle the case in which a sequence is found to have been made on the board
    @Override
    public void HandleSequenceFound(TurnManager turnManager){
        if(turnManager.redTurn){
            RedVictoryCondition();
        }
        else{
            BlueVictoryCondition();
        }
    }
}
