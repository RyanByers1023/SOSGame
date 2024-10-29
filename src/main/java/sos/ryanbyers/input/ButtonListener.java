package sos.ryanbyers.input;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import sos.ryanbyers.gameLogic.SOSGamemode;
import sos.ryanbyers.gameLogic.SOSGeneralGamemode;
import sos.ryanbyers.gameLogic.SOSSimpleGamemode;
import sos.ryanbyers.gameLogic.TurnManager;
import sos.ryanbyers.gui.SOSGUI;

import java.util.List;

public class ButtonListener {
    private Alert alert;

    public ButtonListener(TurnManager turnManager, SOSGamemode gameLogicManager, SOSGUI gui) {
        this.alert = new Alert();

        AttachStartButtonListener(turnManager, gui, gameLogicManager);
    }

    private void AttachCellListeners(TurnManager turnManager, SOSGamemode gameLogicManager, SOSGUI gui) {
        for(List<Region> row : gui.board.componentGrid){
            for(Region component : row){
                if (component instanceof Button button) {
                    button.setOnAction(event -> {
                        boolean isRedTurn = turnManager.redTurn;
                        boolean pieceSelected = isRedTurn ?
                                (gui.buttons.redO.isSelected() || gui.buttons.redS.isSelected()) :
                                (gui.buttons.blueO.isSelected() || gui.buttons.blueS.isSelected());

                        if (!pieceSelected) {
                            alert.AlertPieceNotSelected(turnManager);
                            return;
                        }

                        gui.ModifyButton(button, turnManager);

                        gameLogicManager.HandleTurn();
                        gui.UpdateTurnIndicator(turnManager);
                    });
                }
            }
        }
    }

    private void AttachStartButtonListener(TurnManager turnManager, SOSGamemode gameLogicManager, SOSGUI gui) {
        gui.buttons.startButton.setOnAction(event -> {
            if (!(gui.buttons.simpleGamemode.isSelected() || gui.buttons.generalGamemode.isSelected())) {
                alert.AlertNoGamemodeChosen();
                return;
            }

            gameLogicManager = gui.buttons.generalGamemode.isSelected()
                    ? new SOSGeneralGamemode()
                    : new SOSSimpleGamemode();

            gui.ResetBoard();

            AttachCellListeners(turnManager, gameLogicManager, gui);

            turnManager.StartNewGame();
            gui.UpdateTurnIndicator(turnManager);

        });
    }

    private void CheckGameEndConditions(SOSGUI gui, SOSGamemode gameLogicManager) {
        if (gameLogicManager.StalemateCondition() ||
                gameLogicManager.RedVictoryCondition() ||
                gameLogicManager.BlueVictoryCondition()) {
            
        }
    }

    private void DisableCellListeners(SOSGUI gui) {
        for (List<Region> row : gui.board.componentGrid) {
            for (Region component : row) {
                if (component instanceof Button button) {
                    button.setOnAction(null); // Remove listener
                }
            }
        }
    }

}
