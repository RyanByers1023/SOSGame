packages sos.ryanbyers.players;

public class AIBoardScan{
    //the only difference between a general ai and a simple ai is the fact that a general ai must get as many seqeunces as possible
    //a simple ai must only get the first sequence.

    private static int GENERAL_OFFENSE_WEIGHT = 10; //may help nudge towards a win better than a defensive move
    private static int GENERAL_DEFENSE_WEIGHT = 3; //helps prevent the opponent from building sequences, but doesnt directly result in points
    private static int SIMPLE_OFFENSE_WEIGHT = Integer.MAX_VALUE; //will result in a direct win
    private static int SIMPLE_DEFENSE_WEIGHT = 3; //better than nothing, prevents an opponent's win (but will this ever be a better choice within a simple game?)


    //perform scan of board:
    //  find all cells on the board that would result in n sequences being made, with n = rows * col (num of cells on the board)
    //  find all cells on the board that would result in n - 1 sequences being made
    //place the correct piece type in the cell location that would result in the most seqeunces being made
    //.
    //.
    //.
    //find all cells on the board that would result in one sequence being made
    //if there are more 'O's than 'S's on the board:
    //  place an 'S' in a random, valid cell
    //else:
    //  place an 'O' in a random, valid cell
    //otherwise the board is full and the game is over

    public int 
}

