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

    private void HandleCellClick(SOSGUI gui, TurnManager turnManager, Button button) {
        //it is the computer's turn to play, ignore input:
        //TO-DO: advance turn automatically w/o needing to click when the computer has consecutive turns:
        //soln: detach game logic progression from board clicks:
        //have a class that manages the game state upon clicking the start button
        //this object will persist throughout the lifetime of the game
        //it will manage the turns, game logic, etc.
        //will perform this via a while loop that terminates only once the game has ended
        while(gameLogicManager.IsComputerTurn(gui, turnManager)){
            gameLogicManager.HandleComputerMove(gui, turnManager);
        }

        //get location of button click (to search for sequences more efficiently):
        //get the row:
        int row = GridPane.getRowIndex(button);

        //get the column:
        int col = GridPane.getColumnIndex(button);

        //store in a Vec2 for easier readability:
        Vec2 cellPos = new Vec2(col, row);

        if(!PieceSelected(gui, turnManager)){
            return;
        }

        gui.ModifyButtonNormal(button, turnManager);

        //disable the button on the board:
        gui.board.DisableCell(cellPos);

        //handle the current turn according to the appropriate gamemode rules:
        gameLogicManager.HandleTurn(gui, turnManager, cellPos);
    }

    private boolean IsComputerTurn(SOSGUI gui, TurnManager turnManager) {
        return  gui.buttons.redPlayerIsComputer.isSelected() && turnManager.redTurn ||
                gui.buttons.bluePlayerIsComputer.isSelected() && turnManager.blueTurn;
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

    private void HandleStartButtonClick(TurnManager turnManager, SOSGUI gui) {
        if (!(gui.buttons.simpleGamemode.isSelected() || gui.buttons.generalGamemode.isSelected())) {
            alertMessage.AlertNoGamemodeChosen();
            return;
        }

        this.gameLogicManager = gui.buttons.generalGamemode.isSelected()
                ? new SOSGeneralGamemode()
                : new SOSSimpleGamemode();

        gui.ResetBoard();

        gameLogicManager.gameInProgress = true;

        AttachCellListeners(gui, turnManager);

        turnManager.StartNewGame();

        gui.UpdateTurnIndicator(turnManager);

        gui.DisableComputerCheckboxes();

        if(BothPlayersAreComputers(gui)) {
            HandleAutomatedGame(gui, turnManager);
        }

        if(IsComputerTurn(gui, turnManager)){
            gameLogicManager.HandleComputerMove(gui, turnManager);
        }
    }

    private void HandleAutomatedGame(SOSGUI gui, TurnManager turnManager){
        while(gameLogicManager.gameInProgress){
            gameLogicManager.HandleComputerMove(gui, turnManager);
        }
    }

    private boolean BothPlayersAreComputers(SOSGUI gui){
        return gui.buttons.redPlayerIsComputer.isSelected() && gui.buttons.bluePlayerIsComputer.isSelected();
    }

    private void HandleComputerToggleButtons(SOSGUI gui){
        //add listeners to disable/enable radio buttons based on checkbox state
        gui.buttons.bluePlayerIsComputer.selectedProperty().addListener((observable, oldValue, isSelected) -> {
            //disable user input for radio buttons:
            gui.buttons.blueS.setDisable(isSelected);
            gui.buttons.blueO.setDisable(isSelected);

            //uncheck the radio buttons:
            gui.buttons.blueS.setSelected(false);
            gui.buttons.blueO.setSelected(false);
        });

        gui.buttons.redPlayerIsComputer.selectedProperty().addListener((observable, oldValue, isSelected) -> {
            //disable user input for radio buttons:
            gui.buttons.redS.setDisable(isSelected);
            gui.buttons.redO.setDisable(isSelected);

            //uncheck the radio buttons:
            gui.buttons.redS.setSelected(false);
            gui.buttons.redO.setSelected(false);
        });
    }
}
