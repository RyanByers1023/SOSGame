package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.Board;

public class SOSSimpleGamemode extends SOSGamemode {
    public SOSSimpleGamemode(Board board) {
        super(board);
    }

    @Override
    public boolean GameEndCondition(){
        //game ends when the first sequence is made
        return SequenceMade();
    }

    @Override
    public boolean StalemateConditionMet(){
        return BoardFull() && !WinConditionMet();
    }

    @Override
    public boolean RedVictoryCondition(){
        return GameEndCondition() && turnManager.redTurn;
    }
}
