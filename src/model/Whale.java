package model;

public class Whale {

    private Ocean ocean;

    // alle Aktionen des Hamsters auf das Territorium (Ozean) auslagern
    Whale(Ocean ocean) {
        this.ocean = ocean;
    }

    // Wal bewegungs-Methoden rufen bewegungs-Methoden des Territoriums auf
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

    public void shipInFront() {
        this.ocean.shipInFront();
    }

    public void fishOnTile() {
        this.ocean.fishOnTile();
    }



}
