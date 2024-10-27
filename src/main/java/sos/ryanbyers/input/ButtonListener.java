package sos.ryanbyers.input;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import sos.ryanbyers.gui.Board;
import sos.ryanbyers.gui.ButtonHolder;
import sos.ryanbyers.gui.LabelHolder;
import sos.ryanbyers.gameLogic.TurnManager;

import java.util.List;

public class ButtonListener {
    private ButtonHolder buttons;
    private LabelHolder labels;
    private Board board;
    private VBox sosGridBox;
    private SOSGUI gui;

    public ButtonListener(SOSGUI gui) {
        this.buttons = gui.buttons;
        this.labels = gui.labels;
        this.board = gui.board;
        this.sosGridBox = gui.vBoxes.sosGridBox;
        this.gui = gui;
        AttachCellListeners();
        AttachStartButtonListener();
    }

    private void AttachCellListeners() {
        for(List<Region> row : board.componentGrid){
            for(Region component : row){
                if (component instanceof Button button) {
                    button.setOnAction(event -> {
                        if (InputValid(button)) {
                            gui.ModifyButton(button);
                        }
                    });
                }
            }
        }
    }

    private void AttachStartButtonListener(){

    }
}
