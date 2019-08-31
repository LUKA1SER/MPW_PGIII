package model.exceptions;

public class OutOfBoundsException extends WhaleException{
    @Override
    public String toString() {
        return "Wal ist ausserhalb des Spielfeldes";
    }
}
