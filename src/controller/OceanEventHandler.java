package controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.Ocean;
import view.OceanPanel;

public class OceanEventHandler implements EventHandler<MouseEvent> {
    private Ocean ocean;
    OceanPanel oceanPanel;
    //PlacingState state;


    public OceanEventHandler() {
        //this.ocean = ocean;
        //this.oceanPanel = op;
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


    private void mousePressed(MouseEvent o) {}

    private void mouseDragged(MouseEvent o) {}

    private void mouseReleased(MouseEvent o) {}

    // switch case für verschiedene Methoden placeShip, placeHamster
    // dann ocean.paintOcean() aufrufen

    // Methode getTile, um richtiges Feld aus der GUI auszugeben



}
