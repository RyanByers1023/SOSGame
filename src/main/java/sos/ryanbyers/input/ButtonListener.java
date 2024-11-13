package sos.ryanbyers.input;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import sos.ryanbyers.gameLogic.*;
import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.gui.Vec2;

import java.util.List;

public class ButtonListener {
    private final AlertMessage alertMessage;
    private SOSGamemode gameLogicManager;

    public ButtonListener(TurnManager turnManager, SOSGUI gui) {
        this.alertMessage = new AlertMessage();

        AttachStartButtonListener(turnManager, gui);
        HandleComputerToggleButtons(gui);
    }

    private void AttachCellListeners(SOSGUI gui, TurnManager turnManager) {
        for (List<Region> row : gui.board.componentGrid) {
            for (Region component : row) {
                if (component instanceof Button button) { //button was clicked on the board, need to handle...
                    button.setOnAction(event -> HandleCellClick(gui, turnManager, button));
                }
            }
        }
    }

    public Vec2 HandleCellClick(SOSGUI gui, TurnManager turnManager, Button button) {

        //get location of button click (to search for sequences more efficiently):
        //get the row:
        int row = GridPane.getRowIndex(button);

        //get the column:
        int col = GridPane.getColumnIndex(button);

        //store in a Vec2 for easier readability:
        Vec2 cellPos = new Vec2(col, row);

        return cellPos;
    }

    private boolean PieceSelected(SOSGUI gui, TurnManager turnManager) {
        //who placed the latest piece?:
        boolean isRedTurn = turnManager.redTurn;
        boolean pieceSelected = isRedTurn ?
                (gui.buttons.redO.isSelected() || gui.buttons.redS.isSelected()) :
                (gui.buttons.blueO.isSelected() || gui.buttons.blueS.isSelected());

        //was a piece even selected?:
        if (!pieceSelected) {
            if(isRedTurn && !gui.buttons.redPlayerIsComputer.isSelected()){
                alertMessage.AlertPieceNotSelected(turnManager);
                return false;
            }
            else if(!isRedTurn && !gui.buttons.bluePlayerIsComputer.isSelected()){
                alertMessage.AlertPieceNotSelected(turnManager);
                return false;
            }
        }
        return true;
    }

    private void AttachStartButtonListener(TurnManager turnManager, SOSGUI gui) {
        gui.buttons.startButton.setOnAction(event -> HandleStartButtonClick(turnManager, gui));
    }

    private void HandleStartButtonClick(SOSGUI gui) {
        GameStateManager.StartGame(SOSGUI gui);

        //TO-DO: there is currently no way to remove this from this function, refactor this code to enable this:
        AttachCellListeners(gui, turnManager);
    }

    private void HandleAutomatedGame(SOSGUI gui, TurnManager turnManager){
        while(gameLogicManager.gameInProgress){
            gameLogicManager.HandleComputerMove(gui, turnManager);
        }
    }

    private boolean BothPlayersAreComputers(SOSGUI gui){
        return gui.buttons.redPlayerIsComputer.isSelected() && gui.buttons.bluePlayerIsComputer.isSelected();
    }

    private void AttachComputerCheckboxListeners(){
        gui.buttons.bluePlayerIsComputer.selectedProperty().addListener(observable, oldValue, isSelected);
        gui.buttons.redPlayerIsComputer.selectedProperty().addListener((observable, oldValue, isSelected);
    }
}
