package model;

import java.util.Observable;

public class PlacingState extends Observable {

    public final static int WHALE = 0;
    public final static int FISH = 1;
    public final static int SHIP = 2;
    public final static int CLEAR = 3;

    private int selected;
}
