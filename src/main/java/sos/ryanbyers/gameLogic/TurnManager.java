package sos.ryanbyers.gameLogic;

import java.util.Random;
import sos.ryanbyers.input.AlertMessage;

public class TurnManager {
    public boolean blueTurn;
    public boolean redTurn;
    private final Random random;
    private final AlertMessage alert;

    public TurnManager(){
        random = new Random();
        alert = new AlertMessage();

        blueTurn = random.nextBoolean();
        redTurn = !blueTurn;
    }

    public void StartNewGame(){
        blueTurn = random.nextBoolean();
        redTurn = !blueTurn;
        AnnounceFirstTurn();
    }

    private void AnnounceFirstTurn(){
        if(blueTurn){
            redTurn = false;
            alert.NotifyBlueGoesFirst();
        }
        else{
            redTurn = true;
            alert.NotifyRedGoesFirst();
        }
    }

    public void ChangeTurns(){
        blueTurn = !blueTurn;
        redTurn = !redTurn;
    }
}
