package sos.ryanbyers.input;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.ButtonHolder;
import sos.ryanbyers.gui.LabelHolder;
import sos.ryanbyers.gameLogic.TurnManager;

import java.util.List;

//TO-DO: remove turnManager as a dependency. find a way to only pass turnManager as a parameter to the methods that require it.
public class ButtonListener {
    private ButtonHolder buttons;
    private LabelHolder labels;
    private Board board;
    private VBox sosGridBox;

    public ButtonListener(ButtonHolder buttons, LabelHolder labels, Board board, VBox sosGridBox, TurnManager turnManager) {
        this.buttons = buttons;
        this.labels = labels;
        this.board = board;
        this.sosGridBox = sosGridBox;
        Listen();
    }

    private void Listen() {
        for(List<Region> row : board.componentGrid){
            for(Region component : row){
                if (component instanceof Button button) {
                    button.setOnAction(event -> {
                        if (InputValid(button)) {
                            ModifyButton(button);
                        }
                    });
                }
            }
        }
    }

    private boolean InputValid(Button button){
        //it is the red players turn:
        if(this.turnManager.redTurn){
            //red has not selected a piece:
            if(!(buttons.redO.isSelected() || buttons.redS.isSelected())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Red, choose a piece to place on the board.");
                alert.setHeaderText(null);
                alert.setContentText("Please select a piece, red.");
                alert.showAndWait();
                return false;
            }
        }
        //it is the blue players turn:
        else{
            //blue has not selected a piece:
            if(!(buttons.blueO.isSelected() || buttons.blueS.isSelected())){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Blue, choose a piece to place on the board.");
                alert.setHeaderText(null);
                alert.setContentText("Please select a piece, blue.");
                alert.showAndWait();
                return false;
            }
        }
        return true;
    }

    private void ModifyButton(Button button){
        //set appropriate team color for piece depending on whose turn it is:
        String textColor = turnManager.blueTurn ? "blue" : "red";
        button.setStyle("-fx-text-fill: " + textColor + "; -fx-font-weight: bold;");

        //red's turn
        if(turnManager.redTurn){
            button.setText(buttons.redO.isSelected() ? "O" : "S");
            button.setId(buttons.blueO.isSelected() ? "O" : "S");
        }
        //blue's turn
        else{
            button.setText(buttons.blueO.isSelected() ? "O" : "S");
            button.setId(buttons.blueO.isSelected() ? "O" : "S");
        }

        button.setDisable(true);
        turnManager.ChangeTurns();
        labels.turnIndicator.setText((turnManager.blueTurn) ? "Blue" : "Red");
    }
}
