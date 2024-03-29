package view;


import controller.OceanEventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import model.Ocean;
import model.PlacingState;

import java.util.Observable;
import java.util.Observer;


public class OceanPanel extends Region implements Observer {

    private Ocean ocean;
    private ScrollPane parent;
    private Canvas canvas;
    private GraphicsContext gc;
    PlacingState state;

    final static int CELLSIZE = 34;
    private int x = 10;
    private int y = 10;

    private Image shipImage;
    private Image[] fishes;

    private Image whaleLeft;
    private Image whaleRight;
    private Image whaleUp;
    private Image whaleDown;
    private Image[] whales;

    public OceanPanel(Ocean ocean, ScrollPane sc, PlacingState state) {
        this.ocean = ocean;
        this.parent = sc;
        this.state = state;
        ocean.addObserver(this);
        loadImages();
        loadWhaleImages();
        loadFishImages();
        paintOcean();
        state.addObserver(this);

        OceanEventHandler oeh = new OceanEventHandler(this.ocean, this, this.state);
        this.setOnMousePressed(oeh);
        this.setOnMouseDragged(oeh);
        this.setOnMouseReleased(oeh);
    }

    // zeichnen des Feldes
    public void paintOcean() {
        canvas = new Canvas((this.getFieldWidth() + 200), (this.getFieldHeight() + 200));
        this.setPrefSize(this.getFieldWidth(), this.getFieldHeight());
        gc = canvas.getGraphicsContext2D();
        this.getChildren().addAll(this.getCanvas());
        this.center(parent.getViewportBounds(), this);
        parent.viewportBoundsProperty().addListener((observable, oldValue, newValue) -> {
            this.center(newValue, this);
        });


        int r = 0;
        int c = 0;
        // Für jedes Feld zunächst einen Blauen untergrund und ein Raster zeichnen
        for (r = 0; r < ocean.getNoOfRows(); r++) {
            for (c = 0; c < ocean.getNoOfCols(); c++) {
                gc.setFill(Color.LIGHTBLUE);
                gc.fillRect(x + c * this.CELLSIZE, y + r * this.CELLSIZE, 34, 34);

                // an der Position des Wals das Wal-Bild zeichnen
                if (r == this.ocean.getWhaleRow() && c == this.ocean.getWhaleCol()) {
                    gc.drawImage(returnWhaleImage(), x + c * this.CELLSIZE, y + r * this.CELLSIZE);

                // an der Position eines Schiffes ein Schiff zeichnen
                } else if (ocean.getFieldValue(r, c) == -1) {
                    gc.drawImage(shipImage, x + c * this.CELLSIZE, y + r * this.CELLSIZE);

                // Fische zeichnen
                } else if (ocean.getFieldValue(r, c) > 0) {
                    // Wenn mehr als 12 Fische auf dem Feld sind, werden max. 12 Fische angezeigt
                    if (ocean.getFieldValue(r, c) > 12) {
                        for (int j = 1; j <= 12; j++) {
                            gc.drawImage(fishes[j - 1], x + c * this.CELLSIZE, y + r * this.CELLSIZE);
                        }
                    } else {
                        // wenn <= 12 Fische auf dem Feld sind, entsprechend viele Fische anzeigen lassen
                        for (int i = 1; i <= this.ocean.getFieldValue(r, c); i++) {
                            gc.drawImage(fishes[i - 1], x + c * this.CELLSIZE, y + r * this.CELLSIZE);
                        }
                    }
                }
                gc.setStroke(Color.BLACK);
                gc.strokeRect(x + c * this.CELLSIZE, y + r * this.CELLSIZE, 34, 34);
            }
        }
    }


    // Feld berechnen, auf das geklickt wurde
    public Ocean.Tile getTile (double xValue, double yValue) {
        int x = ((int) (xValue/ 34));
        int y = ((int) (yValue / 34));
        return new Ocean.Tile(x, y);
    }

    // Laden der Bilder
    public void loadImages() {
        shipImage = new Image("/resources/ship.png");
    }

    public void loadFishImages() {
        fishes = new Image[12];

        for (int i = 0; i < 12; i++) {
            fishes[i] = new Image("resources/fish/fish" + (i + 1) + ".png");
        }


    }

    public void loadWhaleImages() {
        whaleLeft = new Image("/resources/whale/whaleLeft.png");
        whaleRight = new Image("/resources/whale/whaleRight.png");
        whaleUp = new Image("/resources/whale/whaleUp.png");
        whaleDown = new Image("/resources/whale/whaleDown.png");
    }

    public Image returnWhaleImage() {
        switch (this.ocean.getWhaleDirection()) {
            case 0:
                return whaleUp;
            case 1:
                return whaleLeft;
            case 2:
                return whaleDown;
            case 3:
                return whaleRight;
        }

        return whaleRight;
    }

    // Zentrieren des Feldes innerhalb der ScrollPane
    // https://stackoverflow.com/questions/30687994/how-to-center-the-content-of-a-javafx-8-scrollpane
    public void center(Bounds viewPortBounds, Node centeredNode) {
        double width = viewPortBounds.getWidth();
        double height = viewPortBounds.getHeight();
        if (width > this.getFieldWidth()) {
            centeredNode.setTranslateX((width - this.getFieldWidth()) / 2);
        } else {
            centeredNode.setTranslateX(0);
        }
        if (height > this.getFieldHeight()) {
            centeredNode.setTranslateY((height - this.getFieldHeight()) / 2);
        } else {
            centeredNode.setTranslateY(0);
        }
    }


    public int getFieldHeight() {
        return this.ocean.getOceanHeight() * this.CELLSIZE;
    }

    public int getFieldWidth() {
        return this.ocean.getOceanWidth() * this.CELLSIZE;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void update(Observable o, Object arg) {
        gc.clearRect(0,0, this.canvas.getWidth(), this.canvas.getHeight());
        this.paintOcean();
    }
}
