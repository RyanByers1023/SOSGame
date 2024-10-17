package sos.ryanbyers.gui;

import javafx.scene.control.Label;

public class LabelHolder {
    public Label gamemode;
    public Label bluePlayer;
    public Label redPlayer;
    public Label currentTurn;
    public Label turnIndicator;
    public Label boardSize;

    public LabelHolder(){
        gamemode = new Label("Game Mode:");
        bluePlayer = new Label("Blue Player:");
        redPlayer = new Label("Red Player:");
        currentTurn = new Label("Current Turn:");
        turnIndicator = new Label("Game has not yet begun..."); //change this label to indicate current turn as game progresses
        boardSize = new Label("Board Size:");
    }

    public void ChangeTurn(boolean isBlueTurn){
        if(isBlueTurn){
            turnIndicator.setText("Blue");
        }
        else{
            turnIndicator.setText("Red");
        }
    }

}
