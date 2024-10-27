package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSGeneralGamemode extends SOSGamemode {
    private int redSequences = 0;
    private int blueSequences = 0;

    public SOSGeneralGamemode(Board board) { super(board); }

    @Override
    public void HandleTurn(){
        //check if the game has begun yet:
        if(!turnManager.GameStarted()){
            turnManager.StartNewGame();
            return;
        }
        //do not change turns when a sequence is made by either player (general game rule) or the game has ended
        if(SequenceMade() || WinConditionMet()){
            return;
        }
        //if none of the above are applicable, just change the turn to the other player
        turnManager.ChangeTurns();

    }

    @Override
    public boolean GameEndCondition(){
        //condition to end the current game = the board has no more free spaces
        return BoardFull();
    }

    @Override
    public boolean StalemateCondition(){
        //WinConditionMet + redSequences == blueSequences
        return BoardFull() && redSequences == blueSequences;
    }

    @Override
    public boolean RedVictoryCondition(){
        return redSequences > blueSequences;
    }

    @Override boolean BlueVictoryCondition(){
        return blueSequences > redSequences;
    }
}
