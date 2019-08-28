package model;

// @Author: Lukas Kaiser

public class Ocean {

    private int[][] oceanTiles;

    private final static int START_ROWS = 15; // Zeilen
    private final static int START_COLS = 10; // Spalten

    private final static int NORTH = 0;
    private final static int WEST = 1;
    private final static int SOUTH = 2;
    private final static int EAST = 3;

    private final static int SHIP = -1;
    private final static int NORMAL_FIELD = 0;

    private Whale whale;
    private int whaleRow;
    private int whaleCol;
    private int whaleDirection;
    private int fishesInMouth;

    public Ocean (int START_ROWS, int START_COLS){
        oceanTiles = new int[START_COLS][START_ROWS];
        whale = new Whale(this);
        whaleRow = 0;
        whaleCol = 0;
        whaleDirection = EAST;
        fishesInMouth = 0;

        // alle Felder des Arrays auf 0 (normales Feld) setzen
        for (int i = 0; i < oceanTiles.length ; i++) {
            for (int j = 0; j < oceanTiles[0].length ; j++) {
                oceanTiles[i][j] = NORMAL_FIELD;
            }
        }
    }

    // Wal vor bewegen

    public void move() {

    }

    // Wal drehen (90° nach links)
    public void turn() {
        this.whaleDirection = (this.whaleDirection + 1) % 4;
    }

    // Fisch aufnehmen
    public void pickFish() {
        if (fishOnTile()) {
            removeFish(whaleRow, whaleCol);
            this.fishesInMouth += 1;
        }
    }

    // Fisch ablegen
    public void putFish() throws NoFishesInMouthException {
        if (this.fishesInMouth > 0) {
            placeFish(whaleRow, whaleCol);
            this.fishesInMouth -= 1;
        } else {
            throw new NoFishesInMouthException();
        }
    }

    // Maul Leer?
    public boolean isMouthEmpty() {
        return true;
    }

    // Schiff da? (Ist ein Schiff auf dem folgenden Feld?)
    public boolean shipInFront() {
        return true;
    }

    // Fisch da? (Ist ein Schiff auf dem aktuellen Feld?)
    public boolean fishOnTile() {
        return true;
    }


    // Ozean größe anpassen

    // Ozean Wal platzieren
    public void placeWhale(int row, int col) {
        this.whaleRow = row;
        this.whaleCol = col;
    }

    // Ozean Schiff setzen
    public void placeShip(int row, int col) {
        oceanTiles[row][col] = SHIP;
    }

    // Ozean Fisch setzen
    public void placeFish(int row, int col) {
        oceanTiles[row][col] += 1;
    }

    // Ozean einen Fisch weniger auf dem Feld
    public void removeFish(int row, int col) throws NoFishesException {
        // Wenn kein Fisch auf dem Feld ist, Exception werfen
        if (oceanTiles[row][col] == 0) {
            throw new NoFishesException();
        }

        oceanTiles[row][col] -= 1;
    }

    // Ozean Feld leeren
    public void clearTile(int row, int col) {
        oceanTiles[row][col] = NORMAL_FIELD;
    }

    // ganzen Ozean leeren
    public void clearAllTiles(int row, int col) {
        for (int i = 0; i < oceanTiles.length; i++) {
            for (int j = 0; j < oceanTiles[0].length ; j++) {
                oceanTiles[i][j] = NORMAL_FIELD;
            }
        }
    }

    // Mauer auf dem Feld (Ist eine Mauer auf dem Feld?)

    // Fisch auf dem Feld (Ist ein Fisch auf dem Feld?)


}

