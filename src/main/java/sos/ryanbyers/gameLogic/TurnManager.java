package sos.ryanbyers.gameLogic;

import javafx.scene.control.Alert;
import java.util.Random;
import sos.ryanbyers.alert;

public class TurnManager {
    public boolean blueTurn;
    public boolean redTurn;
    private final Random random;
    private final Alert alert;

    public TurnManager(){
        random = new Random();
        alert = new Alert();

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
