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
            HandleStalemate();
            return;
        }
        turnManager.ChangeTurns();
    }

    //handle when a stalemate is reached:
    @Override
    public void HandleStalemate(SOSGUI gui){
        alert.NotifySimpleStalemate();
        //stop user input
        gui.ResetBoard();
        ResetGame();
    }

    //handle when a red victory occurs
    @Override
    public void HandleRedVictory(SOSGUI gui){
        alert.NotifySimpleRedVictory();
        //stop user input
        gui.ResetBoard();
        ResetGame();

    }

    //handle when the blue player wins
    @Override
    public void HandleBlueVictory(SOSGUI gui){
        alert.NotifySimpleBlueVictory();
        //stop user input
        gui.ResetBoard();
        ResetGame();
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

    @Override
    public void ResetGame(){
        //nothing to be done here?
    }
}
