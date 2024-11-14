package sos.ryanbyers.gameLogic;

//TO-DO: find a way to implement this as the main game state manager. Need to be able to take input in real time
//goal of this class is to add the ability to respond to input in real time--not just respond to board clicks

//VIOLATIONS note: seems like most of the FOF violations are stemming from the SOSGUI object, gui.

public class GameStateManager {
    //manages turns between two players: red and blue
    private final TurnManager turnManager;

    //handles the rules/game logic pertaining to SOS. can be SOSSimpleGamemode or SOSGeneralGamemode
    private final SOSGamemode gameLogicManager;

    //attaches listeners to all interacterable items on the board
    private final ButtonListener buttonListener;

    //handles alert messages
    private final Alert alert;

    //default constructor -- gameLogic manager needs to be instantiated before this object is created...
    public GameStateManager(SOSGamemode gameLogicManager) {
        this.gameLogicManager = gameLogicManager;
        this.turnManager = new TurnManager();
        this.alert = new Alert();
    }

    //this code executes when the start button is pressed on the gui
    public void StartGame(SOSGUI gui){
        if (!GamemodeSelected(gui)){  //cannot start the game without a gamemode selected
            return;  
        }

        //start a new game:
        ResetGUI(gui);
        this.turnManager.StartNewGame();

        //get the game rules:
        this.gameLogicManager = GetGamemode(gui);

        while(this.gameLogicManager.gameInProgress){
            //it is the computer's turn
            if(IsComputerTurn(gui)){
                HandleComputerTurn(gui, this.turnManager);
                continue;
            }
            //it is a human player's turn
            else{
            if(!PieceSelected(gui, turnManager)){
                //this may result in an infinite loop...
                continue;
            }

            MarkCell(gui, cellPos);

            //handle the current turn according to the appropriate gamemode rules:
            gameLogicManager.HandleTurn(gui, turnManager, cellPos);
            }
        }
    }
    
    public void ChangeTurns(){
        turnManager.ChangeTurns();
    }

    private boolean IsRedTurn(){
        return turnManager.redTurn;
    }

    private boolean IsBlueTurn(){
        return !turnManager.redTurn;
    }

    private void MarkCell(SOSGUI gui, cellPos){
        gui.ModifyButtonNormal(button, turnManager);

        //disable the button on the board:
        //VIOLATION: refactor:
        gui.board.DisableCell(cellPos);
    }

    private boolean PieceSelected(SOSGUI gui, TurnManager turnManager) {
        //who is trying to place a piece down?:
        boolean pieceSelected = isRedTurn ?
                //VIOLATION: FOF -- refactor
                (gui.buttons.redO.isSelected() || gui.buttons.redS.isSelected()) :
                //VIOLATION: FOF -- refactor
                (gui.buttons.blueO.isSelected() || gui.buttons.blueS.isSelected());

        //was a piece even selected?:
        if (!pieceSelected) {
            //VIOLATION: FOF -- refactor
            if(IsRedTurn() && !gui.buttons.redPlayerIsComputer.isSelected()){ //handle blue
                alertMessage.AlertPieceNotSelected(turnManager);
                return false;
            }
            //VIOLATION: FOF -- refactor
            else if(!IsRedTurn() && !gui.buttons.bluePlayerIsComputer.isSelected()){ //handle red
                alertMessage.AlertPieceNotSelected(turnManager);
                return false;
            }
        }
        return true;
    }

    private void HandleComputerTurn(){
        while(this.gameLogicManager.IsComputerTurn(gui, this.turnManager)){
            this.gameLogicManager.HandleComputerMove(gui, this.turnManager);
        }
    }

    private boolean GamemodeSelected(SOSGUI gui){
        if (!(gui.buttons.simpleGamemode.isSelected() || gui.buttons.generalGamemode.isSelected())) {
            alertMessage.AlertNoGamemodeChosen();
        }
    }

    private SOSGamemode GetGamemode(SOSGUI gui){
        this.gameLogicManager = gui.buttons.generalGamemode.isSelected()
                ? new SOSGeneralGamemode()
                : new SOSSimpleGamemode();
    }
    
    private boolean IsComputerTurn(SOSGUI gui, TurnManager turnManager) {
        //VIOLATION: FOF -- refactor
        return  gui.buttons.redPlayerIsComputer.isSelected() && turnManager.redTurn ||
                gui.buttons.bluePlayerIsComputer.isSelected() && turnManager.blueTurn;
    }

    private void ResetGUI(){
        gui.UpdateTurnIndicator(turnManager);
        gui.ResetBoard();

        //the game is starting, do not let the player change/interact with any checkboxes
        gui.DisableComputerCheckboxes();
    }

    //make game actions that are needed this tick (handles when a computer needs to "think"/make multiple moves in one turn
    //everytime something changes on the board, the below function will facilitate the passage of "time", referred to as a "tick"
    public void Tick(){

    }


}
