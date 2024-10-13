package sos.ryanbyers.sosjavafx;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

public class VBoxHolder {
    public VBox mainBox;
    public VBox bluePlayerChoicesBox;
    public VBox redPlayerChoicesBox;
    public VBox sosGridBox;

    public VBoxHolder() {
        //set up VBox that will act as container for all other boxes
        mainBox = new VBox(10);
        mainBox.setPadding(new Insets(15));
        mainBox.setStyle("-fx-border-color: black; -fx-border-width: 5px; -fx-border-style: solid;");

        //store the blue player piece selection here
        bluePlayerChoicesBox = new VBox(10);
        bluePlayerChoicesBox.setPadding(new Insets(15));

        //store the red player piece selection here
        redPlayerChoicesBox = new VBox(10);
        redPlayerChoicesBox.setPadding(new Insets(15));

        //store the grid here
        sosGridBox = new VBox(10);
    }
}
