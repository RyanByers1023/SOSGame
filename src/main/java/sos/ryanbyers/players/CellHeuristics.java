package sos.ryanbyers.players;

import sos.ryanbyers.gui.Vec2;

public class CellHeuristics implements Comparable<CellHeuristics> {
    public boolean pieceIsO;
    public int heuristicValue;
    public Vec2 cellCoordinates;

    CellHeuristics(Vec2 cellCoordinates, int heuristicValue, boolean pieceIsO) {
        this.cellCoordinates = cellCoordinates;
        this.heuristicValue = heuristicValue;
        this.pieceIsO = pieceIsO;
    }

    //override compareTo to simulate '>' for easy heuristic value comparison
    @Override
    public int compareTo(CellHeuristics o) {
        if(o == null){
            return 0;
        }
        return Integer.compare(this.heuristicValue, o.heuristicValue);
    }
}
