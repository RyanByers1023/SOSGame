package sos.ryanbyers.gameLogic;

//TO-DO: find a way to implement this as the main game state manager. Need to be able to take input in real time
//respond to input in real time/not just respond to board clicks

//start game tick once the start button is pressed
//end game tick once the game ends (dependent on rules stored/handled within SOSGamemode)

public class GameStateManager {
    private final TurnManager turnManager;
    private final SOSGamemode gameLogicManager;

    public GameStateManager(SOSGamemode gameLogicManager) {
        this.gameLogicManager = gameLogicManager;
        this.turnManager = new TurnManager();
    }

    public void ChangeTurns(){
        turnManager.ChangeTurns();
    }

    //return whose turn it currently is
    public boolean IsRedTurn(){
        return turnManager.redTurn;
    }

    public void StartGame(){
        while(gameLogicManager.gameInProgress){
            //main game loop
        }
    }

    //force the game to end
    public void EndGame(){
        gameLogicManager.gameInProgress = false;
    }

    //make game actions that are needed this tick (handles when a computer needs to "think"/make multiple moves in one turn
    public void Tick(){

    }


}
