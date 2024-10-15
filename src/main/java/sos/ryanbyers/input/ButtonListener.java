package sos.ryanbyers.input;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.ButtonHolder;
import sos.ryanbyers.gui.LabelHolder;
import sos.ryanbyers.gameLogic.TurnManager;

public class ButtonListener {
    private ButtonHolder buttons;
    private LabelHolder labels;
    private Board board;
    private VBox sosGridBox;
    private TurnManager turnManager;

    public ButtonListener(ButtonHolder buttons, LabelHolder labels, Board board, VBox sosGridBox) {
        this.buttons = buttons;
        this.labels = labels;
        this.board = board;
        this.sosGridBox = sosGridBox;
        turnManager = new TurnManager();
        Listen();
    }

    private void Listen() {
        buttons.startButton.setOnAction(event -> {
            HandleStartButton();
        });

        for(Button button : board.getAllButtons()){
            button.setOnAction(event -> {
                //set appropriate team color for piece:
                String textColor = turnManager.blueTurn ? "blue" : "red";
                button.setStyle("-fx-text-fill: " + textColor + "; -fx-font-weight: bold;");

                if(turnManager.redTurn){
                    if(!(buttons.redO.isSelected() || buttons.redS.isSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Red, choose a piece to place on the board.");
                        alert.setHeaderText(null);
                        alert.setContentText("Please select a piece, red.");
                        alert.showAndWait();
                    }
                    else{
                        button.setText(buttons.redO.isSelected() ? "O" : "S");
                        button.setDisable(true);
                        turnManager.ChangeTurns();
                        labels.turnIndicator.setText((turnManager.blueTurn) ? "Blue" : "Red");
                    }

                }
                else{
                    if(!(buttons.blueO.isSelected() || buttons.blueS.isSelected())){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Blue, choose a piece to place on the board.");
                        alert.setHeaderText(null);
                        alert.setContentText("Please select a piece, blue.");
                        alert.showAndWait();
                    }
                    else{
                        button.setText(buttons.blueO.isSelected() ? "O" : "S");
                        button.setDisable(true);
                        turnManager.ChangeTurns();
                        labels.turnIndicator.setText((turnManager.blueTurn) ? "Blue" : "Red");
                    }

                }
            });
        }
    }

    private void HandleStartButton(){
        if(!(buttons.simpleGamemode.isSelected() || buttons.generalGamemode.isSelected())){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No game mode selected.");
            alert.setHeaderText(null);
            alert.setContentText("Please select a game mode.");
            alert.showAndWait();
        }
        else{
            //remove previous board
            sosGridBox.getChildren().remove(board.grid);
            board = new Board(buttons.boardSizeSpinner.getValue(), Board.ComponentType.BUTTON);
            //add new board
            sosGridBox.getChildren().add(board.grid);
            //reinitialize listeners:
            Listen();
            turnManager.AnnounceFirstTurn();
            labels.turnIndicator.setText((turnManager.blueTurn) ? "Blue" : "Red");
        }
    }
}
