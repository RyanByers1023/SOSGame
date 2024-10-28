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
    private SOSGUI gui;
    private Alert alert;
    private SOSGamemode gameLogicManager;

    public ButtonListener(SOSGUI gui, TurnManager turnManager) {
        this.gui = gui;
        this.alert = new Alert();

        AttachStartButtonListener(turnManager);
    }

    private void AttachCellListeners(TurnManager turnManager) {
        for(List<Region> row : gui.board.componentGrid){
            for(Region component : row){
                if (component instanceof Button button) {
                    button.setOnAction(event -> {
                        //celar previous event handlers
                        button.setOnAction(null);
                        System.out.println("Button clicked at position: " + button.getText());

                        boolean isRedTurn = turnManager.redTurn;
                        boolean pieceSelected = isRedTurn ?
                                (gui.buttons.redO.isSelected() || gui.buttons.redS.isSelected()) :
                                (gui.buttons.blueO.isSelected() || gui.buttons.blueS.isSelected());

                        if (!pieceSelected) {
                            alert.AlertPieceNotSelected(turnManager);
                            return;
                        }

                        gui.ModifyButton(button, turnManager);

                        if (gameLogicManager.sequenceScanner.SequenceSearch()) {
                            System.out.println("Sequence formed.");
                            gameLogicManager.HandleSequenceFound();
                        }

                        CheckGameEndConditions();
                        gameLogicManager.HandleTurn();
                        gui.UpdateTurnIndicator(turnManager);
                    });
                }
            }
        }
    }

    private void AttachStartButtonListener(TurnManager turnManager) {
        gui.buttons.startButton.setOnAction(event -> {
            if (!(gui.buttons.simpleGamemode.isSelected() || gui.buttons.generalGamemode.isSelected())) {
                alert.AlertNoGamemodeChosen();
                return;
            }

            gameLogicManager = gui.buttons.generalGamemode.isSelected()
                    ? new SOSGeneralGamemode(gui.board, turnManager)
                    : new SOSSimpleGamemode(gui.board, turnManager);

            gui.ResetBoard();

            AttachCellListeners(turnManager);

            turnManager.StartNewGame();
            gui.UpdateTurnIndicator(turnManager);

        });
    }

    private void CheckGameEndConditions() {
        if (gameLogicManager.StalemateCondition() ||
                gameLogicManager.RedVictoryCondition() ||
                gameLogicManager.BlueVictoryCondition()) {
            DisableCellListeners();
            gui.ResetBoard();
            gameLogicManager.ResetGame();
        }
    }

    private void DisableCellListeners() {
        for (List<Region> row : gui.board.componentGrid) {
            for (Region component : row) {
                if (component instanceof Button button) {
                    button.setOnAction(null); // Remove listener
                }
            }
        }
    }

}
