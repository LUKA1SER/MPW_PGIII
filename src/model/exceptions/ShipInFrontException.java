package model.exceptions;

public class ShipInFrontException extends WhaleException {
    @Override
    public String toString() {
        return "Schiff ist im Weg!";
    }
}
