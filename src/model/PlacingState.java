package model;

import java.util.Observable;

public class PlacingState extends Observable {

    public final static int WHALE = 0;
    public final static int FISH = 1;
    public final static int SHIP = 2;
    public final static int CLEAR = 3;

    private int selected;


    public int getSelected() {
        return this.selected;
    }

    public void setSelected(int value) {
        if (0 > value || value > 3) {
            return;
        }
        this.selected = value;
    }
}
