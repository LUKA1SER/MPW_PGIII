package model;

// @Author: Lukas Kaiser

import model.exceptions.NoFishesException;
import model.exceptions.NoFishesInMouthException;
import model.exceptions.OutOfBoundsException;
import model.exceptions.ShipInFrontException;

public class Ocean {

    private int[][] oceanTiles;

    private final static int START_ROWS = 5; // Zeilen
    private final static int START_COLS = 5; // Spalten

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

    public int getFishesInMouth() {
        return this.fishesInMouth;
    }

    public Ocean (){
        oceanTiles = new int[START_ROWS][START_COLS];
        whale = new Whale(this);
        whaleRow = 1;
        whaleCol = 1;
        whaleDirection = EAST;
        fishesInMouth = 0;

        // alle Felder des Arrays auf 0 (normales Feld) setzen
        for (int i = 0; i < oceanTiles.length ; i++) {
            for (int j = 0; j < oceanTiles[0].length ; j++) {
                oceanTiles[i][j] = NORMAL_FIELD;
            }
        }
        // oceanTiles[0][1] = 1;
    }

    // Wal vor bewegen
    public void move() throws ShipInFrontException, OutOfBoundsException {
        switch(whaleDirection) {
            case NORTH: whaleRow -= 1;
                if (whaleRow < 0) {
                    throw new OutOfBoundsException();
                }
                if (oceanTiles[whaleRow][whaleCol] == -1) {
                    throw new ShipInFrontException();
                }
                break;
            case SOUTH: whaleRow += 1;
                if (whaleRow > oceanTiles.length - 1) {
                    throw new OutOfBoundsException();
                }
                if (oceanTiles[whaleRow][whaleCol] == -1) {
                    throw new ShipInFrontException();
                }
                break;
            case EAST: whaleCol += 1;
                if (whaleCol > oceanTiles[0].length - 1) {
                    throw new OutOfBoundsException();
                }
                if (oceanTiles[whaleRow][whaleCol] == -1) {
                    throw new ShipInFrontException();
                }
                break;
            case WEST: whaleCol -= 1;
                if (whaleCol < 0) {
                    throw new OutOfBoundsException();
                }
                if (oceanTiles[whaleRow][whaleCol] == -1) {
                    throw new ShipInFrontException();
                }
                break;
        }
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
        if (!isMouthEmpty()) {
            placeFish(whaleRow, whaleCol);
            this.fishesInMouth -= 1;
        } else {
            throw new NoFishesInMouthException();
        }
    }

    // Maul Leer?
    public boolean isMouthEmpty() {
        if (this.getFishesInMouth() == 0) {
            return true;
        }
        return false;
    }

    // Schiff da? (Ist ein Schiff auf dem folgenden Feld?)
    public boolean shipInFront(int direction) {
        switch (direction) {
            case 0:
                if (oceanTiles[whaleRow - 1][whaleRow] == -1) {
                    return true;
                }
                break;
            case 1:
                if (oceanTiles[whaleRow][whaleRow - 1] == -1) {
                    return true;
                }
                break;
            case 2:
                if (oceanTiles[whaleRow + 1][whaleRow] == -1) {
                    return true;
                }
                break;
            case 3:
                if (oceanTiles[whaleRow ][whaleRow + 1] == -1) {
                    return true;
                }
                break;
        }
        return false;
    }

    // Fisch da? (Ist ein Fisch auf dem aktuellen Feld?)
    public boolean fishOnTile() {
        if (oceanTiles[whaleRow][whaleCol] >= 1) {
            return true;
        }
        return false;
    }


    // Ozean größe anpassen
    public int[][] setOceanSize(int rows, int cols) {
        int[][] tmp = new int[rows][cols];

        if (tmp.length > oceanTiles.length && tmp[0].length > oceanTiles[0].length) {
            for (int i = 0; i < oceanTiles.length; i++) {
                for (int j = 0; j < oceanTiles.length; j++) {
                    tmp[i][j] = oceanTiles[i][j];
                }
            }
        } else if (tmp.length < oceanTiles.length && tmp[0].length < oceanTiles[0].length) {
            for (int i = 0; i <tmp.length ; i++) {
                for (int j = 0; j < tmp.length; j++) {
                    tmp[i][j] = oceanTiles[i][j];
                }
            }
        }

        this.oceanTiles = tmp;

        if(this.whaleRow >= rows && this.whaleCol >= cols) {
            if (this.oceanTiles[0][0] == -1) {
                this.oceanTiles[0][0] = 0;
            }
            this.whaleCol = 0;
            this.whaleRow = 0;
        }


        return oceanTiles;
    }

    // Ozean Wal platzieren
    public void placeWhale(int row, int col) {
        setWhaleCol(col);
        setWhaleRow(row);
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

    // Getter und Setter Methoden

    public int[][] getOceanTiles() {
        return oceanTiles;
    }

    public void setOceanTiles(int[][] oceanTiles) {
        this.oceanTiles = oceanTiles;
    }

    public static int getStartRows() {
        return START_ROWS;
    }

    public static int getStartCols() {
        return START_COLS;
    }

    public static int getNORTH() {
        return NORTH;
    }

    public static int getWEST() {
        return WEST;
    }

    public static int getSOUTH() {
        return SOUTH;
    }

    public static int getEAST() {
        return EAST;
    }

    public static int getSHIP() {
        return SHIP;
    }

    public static int getNormalField() {
        return NORMAL_FIELD;
    }

    public Whale getWhale() {
        return whale;
    }

    public void setWhale(Whale whale) {
        this.whale = whale;
    }

    public int getWhaleRow() {
        return whaleRow;
    }

    public void setWhaleRow(int whaleRow) {
        this.whaleRow = whaleRow;
    }

    public int getWhaleCol() {
        return whaleCol;
    }

    public void setWhaleCol(int whaleCol) {
        this.whaleCol = whaleCol;
    }

    public int getWhaleDirection() {
        return whaleDirection;
    }

    public void setWhaleDirection(int whaleDirection) {
        this.whaleDirection = whaleDirection;
    }

    public void setFishesInMouth(int fishesInMouth) {
        this.fishesInMouth = fishesInMouth;
    }

}

