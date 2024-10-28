package sos.ryanbyers.gameLogic;

import javafx.scene.control.Alert;

import java.util.Random;

public class TurnManager {
    public boolean blueTurn;
    public boolean redTurn;
    private final Random random;

    public TurnManager(){
        random = new Random();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Blue goes first!");
            alert.setHeaderText(null);
            alert.setContentText("Make your first move, blue...");
            alert.showAndWait();
        }
        else{
            redTurn = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Red goes first!");
            alert.setHeaderText(null);
            alert.setContentText("Make your first move, red...");
            alert.showAndWait();
        }
    }

    public void ChangeTurns(){
        blueTurn = !blueTurn;
        redTurn = !redTurn;
    }
}
