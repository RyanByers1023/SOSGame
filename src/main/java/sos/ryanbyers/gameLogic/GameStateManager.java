package sos.ryanbyers.gameLogic;

//TO-DO: find a way to implement this as the main game state manager. Need to be able to take input in real time
//respond to input in real time/not just respond to board clicks

//start game tick once the start button is pressed
//end game tick once the game ends (dependent on rules stored/handled within SOSGamemode)

public class GameStateManager {
    //manages turns between two players: red and blue
    private final TurnManager turnManager;

    //handles the rules/game logic pertaining to SOS. can be SOSSimpleGamemode or SOSGeneralGamemode
    private final SOSGamemode gameLogicManager;

    //attaches listeners to all interacterable items on the board
    private final ButtonListener buttonListener;

    //handles alert messages
    private final Alert alert;

    //default constructor
    public GameStateManager(SOSGamemode gameLogicManager) {
        this.gameLogicManager = gameLogicManager;
        this.turnManager = new TurnManager();
        this.alert = new Alert();
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

    public void StartGame(SOSGUI gui){
        while(this.gameLogicManager.gameInProgress){

            if (!GamemodeSelected(gui)){  //alert the player, and prevent the game from beginning:
                break;  
            }

            //start a new game:
            ResetGUI(gui);
            this.turnManager.StartNewGame();

            //get the game rules:
            this.gameLogicManager = GetGameLogicManager(gui);

            HandleComputerTurn(gui, this.turnManager);

            
            if(!PieceSelected(gui, turnManager)){
                return;
            }

            gui.ModifyButtonNormal(button, turnManager);

            //disable the button on the board:
            //VIOLATION: refactor:
            gui.board.DisableCell(cellPos);

            //handle the current turn according to the appropriate gamemode rules:
            gameLogicManager.HandleTurn(gui, turnManager, cellPos);
            
        }
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

    private SOSGamemode GetGameLogicManager(SOSGUI gui){
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
