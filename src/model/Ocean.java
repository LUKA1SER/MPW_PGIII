package model;

// @Author: Lukas Kaiser

import javafx.beans.InvalidationListener;
import model.exceptions.NoFishesException;
import model.exceptions.NoFishesInMouthException;
import model.exceptions.OutOfBoundsException;
import model.exceptions.ShipInFrontException;
import view.OceanPanel;

import java.util.Observable;

public class Ocean extends Observable {

    private int[][] oceanTiles;

    // funktioniert nur bis groesse 78
    private final static int START_ROWS = 10; // Zeilen
    private final static int START_COLS = 10; // Spalten

    private static int rows;
    private static int cols;

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

    private OceanPanel oceanPanel;

    public int getFishesInMouth() {
        return this.fishesInMouth;
    }

    public Ocean (){
        oceanTiles = new int[START_ROWS][START_COLS];
        whale = new Whale(this);
        setWhaleTopLeft();
        whaleDirection = EAST;
        fishesInMouth = 0;



        // alle Felder des Arrays auf 0 (normales Feld) setzen
        for (int i = 0; i < oceanTiles.length ; i++) {
            for (int j = 0; j < oceanTiles[0].length ; j++) {
                oceanTiles[i][j] = NORMAL_FIELD;
            }
        }


    }

    // Wal vor bewegen und bei Einschränkungen durch Spielfeldrand oder Schiff Exception werfen
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

    // Prüfen, ob der Wal Fische im Maul hat
    public boolean isMouthEmpty() {
        if (this.getFishesInMouth() == 0) {
            return true;
        }
        return false;
    }

    // prüfen, ob ein Schiff auf dem folgenden Feld in Blickrichtung des Wals ist
    public boolean shipInFront() {
        switch (whaleDirection) {
            case NORTH:
                if (oceanTiles[whaleRow -  1][whaleCol] == -1) {
                    return true;
                }
                break;
            case SOUTH:
                if (oceanTiles[whaleRow + 1][whaleCol] == -1) {
                    return true;
                }
                break;
            case EAST:
                if (oceanTiles[whaleRow][whaleCol + 1] == -1) {
                    return true;
                }
                break;
            case WEST:
                if (oceanTiles[whaleRow][whaleCol - 1] == -1) {
                    return true;
                }
                break;
        }
        return false;
    }

    // Sind auf dem aktuellen Feld >= 1 Fisch?
    public boolean fishOnTile() {
        if (oceanTiles[whaleRow][whaleCol] >= 1) {
            return true;
        }
        return false;
    }

    // Ozean größe anpassen
    public void setOceanSize(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        int[][] tmp = new int[rows][cols];
        // TODO: richtige Übernahme der Felder für jede Größe des Arrays implementireren
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
        } else {
            this.oceanTiles = tmp;
        }



        this.oceanTiles = tmp;

        if(this.whaleRow >= rows || this.whaleCol >= cols) {
            if (this.oceanTiles[0][0] == -1) {
                this.oceanTiles[0][0] = 0;
            }
            this.setWhaleTopLeft();
        }

        setChanged();
        notifyObservers();

    }

    // Ozean Wal platzieren
    public void placeWhale(int row, int col) {
        setWhaleCol(col);
        setWhaleRow(row);
        setChanged();
        notifyObservers();
    }

    public void setWhaleTopLeft() {
        this.placeWhale(0,0);
    }

    // Schiff setzen
    public void placeShip(int row, int col) {
        oceanTiles[row][col] = SHIP;
        setChanged();
        notifyObservers();
    }



    // Ozean Fisch setzen
    public void placeFish(int row, int col) {
        oceanTiles[row][col] += 1;
        setChanged();
        notifyObservers();
    }

    // Ozean einen Fisch weniger auf dem Feld
    public void removeFish(int row, int col) throws NoFishesException {
        // Wenn kein Fisch auf dem Feld ist, Exception werfen
        if (oceanTiles[row][col] == NORMAL_FIELD) {
            throw new NoFishesException();
        }
        oceanTiles[row][col] -= 1;
    }

    // Ozean Feld leeren
    public void clearTile(int row, int col) {
        oceanTiles[row][col] = NORMAL_FIELD;
        setChanged();
        notifyObservers();
    }

    // ganzen Ozean leeren
    public void clearAllTiles(int row, int col) {
        for (int i = 0; i < oceanTiles.length; i++) {
            for (int j = 0; j < oceanTiles[0].length ; j++) {
                oceanTiles[i][j] = NORMAL_FIELD;
            }
        }
    }

    // Breite des Ozeans für das OceanPanel
    public int getOceanWidth () {
        return this.oceanTiles[0].length;
    }

    // Höhe des Ozeans für das OceanPanel
    public int getOceanHeight() {
        return this.oceanTiles.length;
    }

    @Override
    public void notifyObservers() {

        super.notifyObservers();
    }

    // Getter und Setter Methoden

    public int[][] getOceanTiles() {
        return oceanTiles;
    }

    public void setOceanTiles(int[][] oceanTiles) {
        this.oceanTiles = oceanTiles;
    }

    public void setField(int r, int c, int[][] array, int value) {
        array[r][c] = value;
    }

    public int getNoOfRows() {
        return this.oceanTiles.length;
    }

    public int getNoOfCols() {
        return this.oceanTiles[0].length;
    }

    public int getFieldValue(int r, int c) {
        return this.oceanTiles[r][c];
    }

    public int getNORTH() {
        return NORTH;
    }

    public int getWEST() {
        return WEST;
    }

    public int getSOUTH() {
        return SOUTH;
    }

    public int getEAST() {
        return EAST;
    }

    public int getSHIP() {
        return SHIP;
    }

    public int getNormalField() {
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

    public static class Tile {
        int row;
        int col;

        public Tile(int r, int c) {
            this.row = r;
            this.col = c;
        }

        public int getCol() {
            return col;
        }

        public int getRow() {
            return row;
        }

        @Override
        public String toString() {
            return this.row + " " + this.col;
        }
    }

}

