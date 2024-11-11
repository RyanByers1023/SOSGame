package sos.ryanbyers.gui;

public class SequenceCoordinates {
    public Vec2 coordinatesStart;
    public Vec2 coordinatesEnd;

    public SequenceCoordinates(Vec2 coordinatesStart, Vec2 coordinatesEnd) {
        this.coordinatesStart = coordinatesStart;
        this.coordinatesEnd = coordinatesEnd;
    }

    //override for set used within SequenceScanner -- ensures that start and end coordinates can be interchanged and still be considered the same sequence
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //default equivalency operator
        if (o == null || getClass() != o.getClass()) return false; //is of same class?
        SequenceCoordinates that = (SequenceCoordinates) o;
        //check for reversed sequences:
        return (coordinatesStart.equals(that.coordinatesStart) && coordinatesEnd.equals(that.coordinatesEnd)) ||
                (coordinatesStart.equals(that.coordinatesEnd) && coordinatesEnd.equals(that.coordinatesStart));
    }

    //symmetrical hash code for reversed equivalency
    @Override
    public int hashCode() {
        return coordinatesStart.hashCode() + coordinatesEnd.hashCode();
    }
}
