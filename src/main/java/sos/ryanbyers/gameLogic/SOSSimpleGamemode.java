package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.gui.SequenceCoordinates;
import sos.ryanbyers.gui.Vec2;

public class SOSSimpleGamemode extends SOSGamemode {
    public SOSSimpleGamemode() {
        super();
    }

    //run when a player places a piece onto the board: handle when a piece is placed on the board
    @Override
    public void HandleTurn(SOSGUI gui, TurnManager turnManager, Vec2 cellPos){
        if(SequenceMade(gui, cellPos, turnManager)){
            gameInProgress = false;
            HandleSequenceFound(gui, turnManager);
            gui.EnableComputerCheckboxes();
            return;
        }
        if(BoardFull(gui.board)){
            gameInProgress = false;
            HandleStalemate(gui);
            gui.EnableComputerCheckboxes();
            return;
        }

        turnManager.ChangeTurns();
        gui.UpdateTurnIndicator((turnManager));
    }

    //handle when a stalemate is reached:
    @Override
    public void HandleStalemate(SOSGUI gui){
        alertMessage.NotifySimpleStalemate();
        //stop user input
        gui.ResetBoard();
        gui.labels.turnIndicator.setText("Stalemate! -- Game Over!");
    }

    //handle when a red victory occurs
    @Override
    public void HandleRedVictory(SOSGUI gui){
        alertMessage.NotifySimpleRedVictory();
        //stop user input
        gui.ResetBoard();
        gui.labels.turnIndicator.setText("Congrats, Red! -- Game Over!");
    }

    //handle when the blue player wins
    @Override
    public void HandleBlueVictory(SOSGUI gui){
        alertMessage.NotifySimpleBlueVictory();
        //stop user input
        gui.ResetBoard();
        gui.labels.turnIndicator.setText("Congrats, Blue! -- Game Over!");
    }

    //handle the case in which a sequence is found to have been made on the board
    @Override
    public void HandleSequenceFound(SOSGUI gui, TurnManager turnManager){
        if(turnManager.redTurn){
            HandleRedVictory(gui);
        }
        else{
            HandleBlueVictory(gui);
        }
    }
}
