package sos.ryanbyers.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;

public class LabelBoard extends Board {
    public LabelBoard(int gridSize) { super(gridSize); }

    @Override
    public Region InstantiateCell(){
        return new Label();
    }
}
