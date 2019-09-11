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


    public OceanEventHandler(Ocean ocean, OceanPanel op) {
        this.ocean = ocean;
        this.oceanPanel = op;
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
        // hier jeweils switch-case für das Schiff/Wal/etc. rein
        System.out.println(oceanPanel.getTile(o.getX(), o.getY()));
    }

    private void mouseDragged(MouseEvent o) {}

    private void mouseReleased(MouseEvent o) {}

    // switch case für verschiedene Methoden placeShip, placeHamster
    // dann ocean.paintOcean() aufrufen

}
