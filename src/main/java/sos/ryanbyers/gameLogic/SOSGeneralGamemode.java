package sos.ryanbyers.gameLogic;

import sos.ryanbyers.gui.SOSGUI;

public class SOSGeneralGamemode extends SOSGamemode {
    public SOSGeneralGamemode() {
        super();
    }

    //run after a piece is placed:
    @Override
    public void HandleTurn(SOSGUI gui, TurnManager turnManager, int row, int col){
        //do not change turns when a sequence is made by either player (general game rule) or the game has ended
        if(SequenceMade(gui, row, col, turnManager)){
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
        }
        //if none of the above are applicable, just change the turn to the other player
        turnManager.ChangeTurns();
        gui.UpdateTurnIndicator((turnManager));
    }

    //handle when a stalemate is reached:
    @Override
    public void HandleStalemate(SOSGUI gui){
        alert.NotifyGeneralStalemate(redSequences);
        //stop the user from inputting further... (this will be the same for the rest of the methods)
        gui.ResetBoard();
        gui.labels.turnIndicator.setText("Stalemate! -- Game Over!");
        ClearPoints();
    }

    //handle when a red victory occurs
    @Override
    public void HandleRedVictory(SOSGUI gui){
        alert.NotifyGeneralRedVictory(redSequences, blueSequences);
        //prevent user input
        gui.ResetBoard();
        gui.labels.turnIndicator.setText("Congrats, Red! -- Game Over!");
        ClearPoints();
    }

    //handle the case in which the blue player wins
    @Override
    public void HandleBlueVictory(SOSGUI gui){
        alert.NotifyGeneralBlueVictory(redSequences, blueSequences);
        //stop user input
        gui.ResetBoard();
        gui.labels.turnIndicator.setText("Congrats, Blue! -- Game Over!");
        ClearPoints();
    }

    @Override
    //was adding sequences twice in this method-- throwing off point value
    public void HandleSequenceFound(SOSGUI gui, TurnManager turnManager){
            //pass
            return;
        }
    }

    public void ClearPoints(){
        redSequences = 0;
        blueSequences = 0;
    }
}
