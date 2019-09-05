package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import model.Ocean;


public class OceanPanel extends Region {

    private Ocean ocean;
    private ScrollPane parent;
    private Canvas canvas;
    GraphicsContext gc;

    final static int CELLSIZE = 34;
    private int x = 10;
    private int y = 10;

    private Image waterField;
    private Image whaleImage;
    private Image shipImage;
    private Image oneFish;
    private Image[] fishes;
    private Image[] whales;

    public OceanPanel(Ocean ocean, ScrollPane sc) {
        this.ocean = ocean;
        this.parent = sc;
        paintOcean();
    }

    // zeichnen des Feldes
    public void paintOcean() {
        canvas = new Canvas(this.ocean.getOceanWidth() * CELLSIZE, this.ocean.getOceanHeight() * CELLSIZE);
        gc = canvas.getGraphicsContext2D();
        this.getChildren().addAll(canvas);
        this.setPrefSize(ocean.getOceanWidth(), ocean.getOceanHeight());
        loadImages();

        int r = 0;
        int c = 0;
        for (r = 0; r < ocean.getNoOfRows(); r++) {
            for (c = 0; c < ocean.getNoOfCols(); c++) {
                gc.setFill(Color.LIGHTBLUE);
                gc.fillRect(x + c * this.CELLSIZE, y + r * this.CELLSIZE, 34, 34);
                if (r == this.ocean.getWhaleRow() && c == this.ocean.getWhaleCol()) {
                    gc.drawImage(whaleImage, x + c * CELLSIZE, y + r * CELLSIZE);
                } else if (ocean.getField(r, c) == -1) {
                    gc.setFill(Color.RED);
                    gc.fillRect(x + c * this.CELLSIZE, y + r * this.CELLSIZE, 34, 34);
                } else if (ocean.getField(r, c) > 0) {
                    gc.drawImage(oneFish, x + c * CELLSIZE, y + r * CELLSIZE, 32, 32);
                }
                gc.setStroke(Color.BLACK);
                gc.strokeRect(x + c * this.CELLSIZE, y + r * this.CELLSIZE, 34, 34);
            }
        }
    }

    // Laden der Bilder
    public void loadImages() {
        // Bild fuer einen Fisch auf dem Feld
        oneFish = new Image("/resources/fish32.png");
        whaleImage = new Image("resources/whale32.png");

    }

    public void loadWhaleImage() {
        switch(this.ocean.getWhaleDirection()) {
        }
    }

    // Zentrieren des Feldes innerhalb der ScrollPane
    public void center() {

    }


    public Canvas getCanvas() {
        return canvas;
    }
}
