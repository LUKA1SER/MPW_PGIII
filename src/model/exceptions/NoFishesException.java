package model.exceptions;

public class NoFishesException extends WhaleException {
    @Override
    public String toString() {
        return "Keine Fische auf dem Feld!";
    }
}
