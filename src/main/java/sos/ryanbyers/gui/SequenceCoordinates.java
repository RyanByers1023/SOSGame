package sos.ryanbyers.gui;

import javafx.util.Pair;

public class SequenceCoordinates {
    public Pair<Integer, Integer> coordinatesStart;
    public Pair<Integer, Integer> coordinatesEnd;

    public SequenceCoordinates(int startRow, int startCol, int endRow, int endCol) {
        coordinatesStart = new Pair<>(startRow, startCol);
        coordinatesEnd = new Pair<>(endRow, endCol);
    }

    //override for set used within SequenceScanner -- ensures that start and end coordinates can be interchanged and still be considered the same sequence
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //default equivalency op
        if (o == null || getClass() != o.getClass()) return false; //is of same class?
        SequenceCoordinates that = (SequenceCoordinates) o;
        return (coordinatesStart.equals(that.coordinatesStart) && coordinatesEnd.equals(that.coordinatesEnd)) ||
                (coordinatesStart.equals(that.coordinatesEnd) && coordinatesEnd.equals(that.coordinatesStart)); //check for reversed sequences
    }

    //symmetrical hash code for reversed equivalency
    @Override
    public int hashCode() {
        return coordinatesStart.hashCode() + coordinatesEnd.hashCode();
    }
}
