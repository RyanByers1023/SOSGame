package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.gui.SequenceCoordinates;
import sos.ryanbyers.gui.Vec2;

public class SOSGeneralGamemode extends SOSGamemode {
    public SOSGeneralGamemode() {
        super();
    }

    //run after a piece is placed:
    @Override
    public void HandleTurn(SOSGUI gui, TurnManager turnManager, Vec2 cellPos){
        //do not change turns when a sequence is made by either player (general game rule) or the game has ended
        if(SequenceMade(gui, cellPos, turnManager)){
            HandleSequenceFound(gui, turnManager);
        }
        if(BoardFull(gui.board)){
            if(redSequences > blueSequences){
                HandleRedVictory(gui);
            }
            else if(blueSequences > redSequences){
                HandleBlueVictory(gui);
            }
            else{
                HandleStalemate(gui);
            }
            gameInProgress = false;
            gui.EnableComputerCheckboxes();
        }
        //if none of the above are applicable, just change the turn to the other player
        turnManager.ChangeTurns();
        gui.UpdateTurnIndicator(turnManager);
    }

    //handle when a stalemate is reached:
    @Override
    public void HandleStalemate(SOSGUI gui){
        alertMessage.NotifyGeneralStalemate(redSequences);
        gui.labels.turnIndicator.setText("Stalemate! -- Game Over!");
        ClearPoints();
    }

    //handle when a red victory occurs
    @Override
    public void HandleRedVictory(SOSGUI gui){
        alertMessage.NotifyGeneralRedVictory(redSequences, blueSequences);
        gui.labels.turnIndicator.setText("Congrats, Red! -- Game Over!");
        ClearPoints();
    }

    //handle the case in which the blue player wins
    @Override
    public void HandleBlueVictory(SOSGUI gui){
        alertMessage.NotifyGeneralBlueVictory(redSequences, blueSequences);
        gui.labels.turnIndicator.setText("Congrats, Blue! -- Game Over!");
        ClearPoints();
    }

    @Override
    //was adding sequences twice in this method-- throwing off point value
    public void HandleSequenceFound(SOSGUI gui, TurnManager turnManager){
        return;
    }

    public void ClearPoints(){
        this.redSequences = 0;
        this.blueSequences = 0;
    }
}
