package sos.ryanbyers.gameLogic;

import javafx.scene.control.Button;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.SOSGUI;
import sos.ryanbyers.gui.Vec2;
import sos.ryanbyers.input.AlertMessage;
import sos.ryanbyers.players.AIBoardScan;
import sos.ryanbyers.players.BoardHeuristics;

public abstract class SOSGamemode {
    //for checking the board for new sequences after each turn
    protected AlertMessage alertMessage;
    protected SequenceScanner sequenceScanner;

    protected int redSequences = 0;
    protected int blueSequences = 0;

    private BoardHeuristics boardHeuristics;
    private AIBoardScan aiBoardScanner;

    public boolean gameInProgress = false;

    //super constructor:
    public SOSGamemode() {
        this.alertMessage = new AlertMessage();
        this.sequenceScanner = new SequenceScanner();

        this.aiBoardScanner = new AIBoardScan();
        this.boardHeuristics = new BoardHeuristics();
    }


    public boolean SequenceMade(SOSGUI gui, Vec2 cellPos, TurnManager turnManager) {
       int sequencesMade = this.sequenceScanner.SequenceSearch(gui, cellPos, turnManager);

        //handle points:
        if(turnManager.redTurn){
            redSequences += sequencesMade;
        }
        else{
            blueSequences += sequencesMade;
        }

       return sequencesMade > 0;
    }

    public void HandleComputerMove(SOSGUI gui, TurnManager turnManager) {
        gui.board.DisableUserInput();

        //generate a heuristic value for each potential space on the board
        boardHeuristics = aiBoardScanner.PerformBoardScan(gui);

        boardHeuristics.DetermineBestPlacement();

        //store the x, y coodinates of the computer's placement:
        Vec2 bestCellPos = boardHeuristics.bestCell.cellCoordinates;

        //get access to the button object attached to the board
        Button button = (Button) gui.board.GetCell(bestCellPos);

        //make the computer's choice on the board
        gui.ModifyButtonComputer(button, turnManager, boardHeuristics.bestCell);
        gui.board.DisableCell(bestCellPos);

        gui.board.EnableUserInput();

        HandleTurn(gui, turnManager, bestCellPos);
    }

    //the board being full is a condition that needs to be checked in both gamemodes
    public boolean BoardFull(Board board){
        return board.BoardFull();
    }

    public boolean IsComputerTurn(SOSGUI gui, TurnManager turnManager) {
        return  gui.buttons.redPlayerIsComputer.isSelected() && turnManager.redTurn ||
                gui.buttons.bluePlayerIsComputer.isSelected() && turnManager.blueTurn;
    }

    //abstract methods:

    //turns handled slightly different in general vs simple
    public abstract void HandleTurn(SOSGUI gui, TurnManager turnManager, Vec2 cellPos);

    //red/blue victory determined by a point value within general games, no points in simple games
    public abstract void HandleRedVictory(SOSGUI gui);
    public abstract void HandleBlueVictory(SOSGUI gui);

    //stalemate determined by point values in general games, no points in simple games
    public abstract void HandleStalemate(SOSGUI gui);

    public abstract void HandleSequenceFound(SOSGUI gui, TurnManager turnManager);
}
