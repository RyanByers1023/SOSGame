package sos.ryanbyers.input;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import sos.ryanbyers.gameLogic.*;
import sos.ryanbyers.gui.SOSGUI;

import java.util.List;

public class ButtonListener {
    private final AlertMessage alertMessage;
    private SOSGamemode gameLogicManager;

    public ButtonListener(TurnManager turnManager, SOSGUI gui) {
        this.alertMessage = new AlertMessage();

        AttachStartButtonListener(turnManager, gui);
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

    private void HandleCellClick(SOSGUI gui, TurnManager turnManager, Button button) {
        //get location of button click (to search for sequences more efficiently):
        //cast to node
        Node node = (Node) button;
        //get the row
        int row = GridPane.getRowIndex(node);
        //get the column
        int col = GridPane.getColumnIndex(node);

        //who placed it?:
        boolean isRedTurn = turnManager.redTurn;
        boolean pieceSelected = isRedTurn ?
                (gui.buttons.redO.isSelected() || gui.buttons.redS.isSelected()) :
                (gui.buttons.blueO.isSelected() || gui.buttons.blueS.isSelected());

        //was a piece even selected?:
        if (!pieceSelected) {
            alertMessage.AlertPieceNotSelected(turnManager);
            return;
        }

        //a piece was selected, modify the button in place to reflect this:
        gui.ModifyButton(button, turnManager);

        gameLogicManager.HandleTurn(gui, turnManager, row, col);
    }

    private void AttachStartButtonListener(TurnManager turnManager, SOSGUI gui) {
        gui.buttons.startButton.setOnAction(event -> HandleStartButtonClick(turnManager, gui));
    }

    private void HandleStartButtonClick(TurnManager turnManager, SOSGUI gui) {
        if (!(gui.buttons.simpleGamemode.isSelected() || gui.buttons.generalGamemode.isSelected())) {
            alertMessage.AlertNoGamemodeChosen();
            return;
        }

        this.gameLogicManager = gui.buttons.generalGamemode.isSelected()
                ? new SOSGeneralGamemode()
                : new SOSSimpleGamemode();

        gui.ResetBoard();
        AttachCellListeners(gui, turnManager);
        turnManager.StartNewGame();
        gui.UpdateTurnIndicator(turnManager);
    }
}
