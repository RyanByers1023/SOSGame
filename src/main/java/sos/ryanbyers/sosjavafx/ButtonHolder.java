package sos.ryanbyers.sosjavafx;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToggleGroup;

public class ButtonHolder {
    public RadioButton simpleGamemode;
    public RadioButton generalGamemode;

    public RadioButton blueS;
    public RadioButton blueO;

    public RadioButton redS;
    public RadioButton redO;

    public Button startButton;

    public Spinner<Integer> boardSizeSpinner;

    public ButtonHolder(){
        //gamemode radio buttons
        simpleGamemode = new RadioButton("Simple game");
        simpleGamemode.setId("simpleGamemode");

        generalGamemode = new RadioButton("General game");
        generalGamemode.setId("generalGamemode");

        //set correct group (gamemode group)
        ToggleGroup gamemodeToggle = new ToggleGroup();
        simpleGamemode.setToggleGroup(gamemodeToggle);
        generalGamemode.setToggleGroup(gamemodeToggle);

        //Blue player piece choice radio buttons
        blueS = new RadioButton("S");
        blueS.setId("blueS");

        blueO = new RadioButton("O");
        blueO.setId("blueO");

        //set correct group (bluePiece group)
        ToggleGroup bluePieceToggle = new ToggleGroup();
        blueS.setToggleGroup(bluePieceToggle);
        blueO.setToggleGroup(bluePieceToggle);

        //Red player piece choice radio buttons
        redS = new RadioButton("S");
        redS.setId("redS");

        redO = new RadioButton("O");
        redO.setId("redO");

        //set correct group (redPiece group)
        ToggleGroup redPieceToggle = new ToggleGroup();
        redS.setToggleGroup(redPieceToggle);
        redO.setToggleGroup(redPieceToggle);

        startButton = new Button("Start Game");
        startButton.setId("startButton");

        boardSizeSpinner = new Spinner<>(3, 30, 5); //min: 3, max: 30, default: 5
        boardSizeSpinner.setId("boardSizeSpinner");
        boardSizeSpinner.setPrefWidth(52); //this width can accomodate integers < 100
    }
}
