package model;

public class Whale {

    Ocean ocean;

    // alle Aktionen des Hamsters auf das Territorium (Ozean) auslagern
    Whale(Ocean ocean) {
        this.ocean = ocean;
    }

    // Wal bewegungs-Methoden rufen bewegungs-Methoden des Territoriums auf

    // Beispiel: vor() { this.ocean.vor() }

    public void move() {
        this.ocean.move();
    }

    public void turn() {
        this.ocean.turn();
    }

    public void pickFish() {
        this.ocean.pickFish();
    }

    public void putFish() {
        this.ocean.putFish();
    }

    public void isMouthEmpty() {
        this.ocean.isMouthEmpty();
    }

    public void shipInFront(int direction) {
        this.ocean.shipInFront(direction);
    }

    public void fishOnTile() {
        this.ocean.fishOnTile();
    }



}
