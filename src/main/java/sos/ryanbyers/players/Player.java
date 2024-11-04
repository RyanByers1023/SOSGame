package sos.ryanbyers.players;

public abstract class Player{
    public int points;
    public boolean isTurn;

    public Player(){
        this.points = 0;
        this.isTurn = false;
    }
}