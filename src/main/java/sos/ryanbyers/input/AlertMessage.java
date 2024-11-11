package sos.ryanbyers.input;

import sos.ryanbyers.gameLogic.TurnManager;

public class AlertMessage {
    public void AlertNoGamemodeChosen(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle("No game mode selected.");
        alert.setHeaderText(null);
        alert.setContentText("Please select a game mode.");
        alert.showAndWait();
    }

    public void AlertPieceNotSelected(TurnManager turnManager){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
        alert.setTitle("Select a piece!");
        alert.setHeaderText(null);
        if(turnManager.redTurn){
            alert.setContentText("Red, select a piece before making a move.");
        }
        else{
            alert.setContentText("Blue, select a piece before making a move.");
        }
        alert.showAndWait();
    }

    public void NotifySimpleRedVictory(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Red has won!");
        alert.setHeaderText(null);
        alert.setContentText("Congrats to red! They have successfully created a sequence and have won the game.");
        alert.showAndWait();
    }

    public void NotifySimpleBlueVictory(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Blue has won!");
        alert.setHeaderText(null);
        alert.setContentText("Congrats to blue! They have successfully created a sequence and have won the game.");
        alert.showAndWait();
    }

    public void NotifySimpleStalemate(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Stalemate!");
        alert.setHeaderText(null);
        alert.setContentText("The board is full, and no one made a sequence. It's a draw!");
        alert.showAndWait();
    }

    public void NotifyGeneralRedVictory(int redSequences, int blueSequences){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Red has won!");
        alert.setHeaderText(null);
        alert.setContentText("Congrats to red! They made " + redSequences + " sequences while blue only made " + blueSequences + " sequences.");
        alert.showAndWait();
    }

    public void NotifyGeneralBlueVictory(int redSequences, int blueSequences){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Blue has won!");
        alert.setHeaderText(null);
        alert.setContentText("Congrats to blue! They made " + blueSequences + " sequences while red only made " + redSequences + " sequences.");
        alert.showAndWait();
    }

    public void NotifyGeneralStalemate(int sequences){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Stalemate!");
        alert.setHeaderText(null);
        alert.setContentText("The board is full, and both player red and player blue made the same number of sequences (" + sequences + "). It's a draw!");
        alert.showAndWait();
    }

    public void NotifyRedGoesFirst(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Red goes first!");
        alert.setHeaderText(null);
        alert.setContentText("Make your first move, red...");
        alert.showAndWait();
    }

    public void NotifyBlueGoesFirst(){
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle("Blue goes first!");
        alert.setHeaderText(null);
        alert.setContentText("Make your first move, blue...");
        alert.showAndWait();
    }
}