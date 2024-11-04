package sos.ryanbyers.players;

//will hold the logic for the moves of the computer player
public class ComputerPlayerMove{
    public void MakeAMove(Board board, SOSGamemode gameLogic){
        if(gameLogic.type() == SOSSimpleGamemode){
            HandleSimpleMove(board);
        }
        else{
            HandleGeneralMove(board)
        }
    }

    private void HandleSimpleMove(Board board){
        //use the current state of the board to determine what move would result in the best outcome for the computer player
    }

    private void HandleGeneralMove(Board board){
        //use the current state of the board to determine what move would result in the best outcome for the computer player
    }