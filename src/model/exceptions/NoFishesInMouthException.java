package model.exceptions;

public class NoFishesInMouthException extends WhaleException {
    @Override
    public String toString() {
        return "Keine Fische im Maul!";
    }
}
