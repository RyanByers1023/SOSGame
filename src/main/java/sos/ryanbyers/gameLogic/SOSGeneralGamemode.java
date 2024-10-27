package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSGeneralGamemode extends SOSGamemode {
    private int redSequences = 0;
    private int blueSequences = 0;

    public SOSGeneralGamemode(Board board) { super(board); }

    @Override
    public void HandleTurn(){
        //neither players turn?: game has not begun yet
        if(!turnManager.GameStarted()){
            turnManager.StartNewGame();
            return;
        }
        //do not change turns when a sequence is made by either player or the game has ended
        if(SequenceMade() || WinConditionMet()){
            return;
        }
        turnManager.ChangeTurns();

    }

    @Override
    public boolean WinConditionMet(){
        if (SequenceMade()){
            if(BoardFull()){ //game must now end
                DisplayWinner();
                return true;
            }
            //iterate correct score based on whose turn it was
            else{
                if(turnManager.blueTurn){
                    blueSequences++;
                }
                else{
                    redSequences++;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean StalemateConditionMet(){
        return BoardFull() && redSequences == blueSequences;
    }

    @Override
    public boolean RedHasWon(){
        return redSequences > blueSequences;
    }

    @Override
    public void DisplayWinner(){
        //draw
        if(StalemateConditionMet()){

        }
        //red victory
        else if(RedHasWon()){

        }
        //blue victory
        else{

        }
        //display # of sequences made by each player
        //display who won (blue or red?)
        //OR: display that a stalemate has been reached
    }
}
