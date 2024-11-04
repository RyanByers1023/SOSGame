package sos.ryanbyers.players;

public class ComputerPlayer extends Player {
    private ComputerPlayerMove() computerPlayerChoiceHandler;

    public ComputerPlayer(){
        super();

        computerPlayerChoiceHandler = new ComputerPlayerMove();
    }

    public MakeAMove(Board board, SOSGamemode gameLogicHandler){
        computerPlayerChoiceHandler.MakeAMove(board, );
    }


}
