package sos.ryanbyers.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class HBoxHolder {
    public HBox gameOptionsBox;
    public HBox gameSpaceBox;
    public HBox playerTurnBox;
    public HBox startBox;

    public HBoxHolder() {
        //hbox for game mode selection radio buttons
        gameOptionsBox = new HBox(15);
        gameOptionsBox.setPadding(new Insets(15));
        gameOptionsBox.setAlignment(Pos.CENTER);

        //holds the grid box and red and blue player piece choices boxes
        gameSpaceBox = new HBox(10);
        gameSpaceBox.setAlignment(Pos.CENTER);

        //holds text that tells the user which player's turn it is
        playerTurnBox = new HBox(10);
        playerTurnBox.setAlignment(Pos.CENTER);

        //holds the button to start the game
        startBox = new HBox(10);
        startBox.setAlignment(Pos.CENTER);
    }
}
