package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Ocean;
import model.PlacingState;
import view.OceanPanel;

import static model.PlacingState.*;

public class OceanEventHandler implements EventHandler<MouseEvent> {
    private Ocean ocean;
    private OceanPanel oceanPanel;
    private PlacingState state;


    public OceanEventHandler(Ocean ocean, OceanPanel op, PlacingState state) {
        this.ocean = ocean;
        this.oceanPanel = op;
        this.state = state;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            this.mousePressed(event);
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            this.mouseDragged(event);
        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED)
            this.mouseReleased(event);
    }


    private void mousePressed(MouseEvent o) {
        Ocean.Tile tile = oceanPanel.getTile(o.getX(), o.getY());
        System.out.println(state.getSelected());
        switch(state.getSelected()) {
            case CLEAR: ocean.clearTile(tile.getCol(), tile.getRow());
                        break;
            case WHALE: ocean.placeWhale(tile.getCol(), tile.getRow());
                        break;
            case SHIP: ocean.placeShip(tile.getCol(), tile.getRow());
                        break;
            case FISH: ocean.placeFish(tile.getCol(), tile.getRow());
                        break;
            default: break;
        }


        System.out.println(tile.toString());
    }

    private void mouseDragged(MouseEvent o) {
        Ocean.Tile tile = oceanPanel.getTile(o.getX(), o.getY());
        if (state.getSelected() == WHALE) {
            ocean.placeWhale(tile.getCol(), tile.getRow());
        } else {
            return;
        }
    }

    private void mouseReleased(MouseEvent o) {}

    // switch case für verschiedene Methoden placeShip, placeHamster
    // dann ocean.paintOcean() aufrufen

}
