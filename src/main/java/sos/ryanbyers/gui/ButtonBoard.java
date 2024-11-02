package sos.ryanbyers.gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Region;


public class ButtonBoard extends Board {
    public ButtonBoard(int gridSize) { super(gridSize); }

    @Override
    public Region InstantiateCell(){
        return new Button();
    }
}
