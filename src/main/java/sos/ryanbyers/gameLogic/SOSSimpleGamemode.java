package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSSimpleGamemode extends SOSGamemode {
    public SOSSimpleGamemode(Board board) {
        super(board);
    }

    @Override
    public boolean WinConditionMet(){
        //add notice with regard to who won the game
        return SequenceMade();
    }

    @Override
    public boolean StalemateConditionMet(){
        return BoardFull() && !WinConditionMet();
    }

    @Override
    public boolean RedHasWon(){
        return WinConditionMet() && turnManager.redTurn;
    }

    @Override
    public void DisplayWinner(){
        if(StalemateConditionMet()){

        }
        else if(RedHasWon()){

        }
        else{

        }
        //display who made a sequence (blue or red?)
        //OR: display that a stalemate has been reached
    }
}
