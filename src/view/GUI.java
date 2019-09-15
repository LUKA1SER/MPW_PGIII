package view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.Ocean;
import model.PlacingState;

import java.util.function.UnaryOperator;


public class GUI extends Application {

    private Ocean ocean;
    private OceanPanel op;
    private PlacingState state;

    // Buttons
    public Button newFileButton;
    public Button fileButton;
    public Button saveButton;
    public Button compileButton;
    public Button fieldButton;
    public ToggleButton whaleButton;
    public ToggleButton fishButton;
    public ToggleButton shipButton;
    public ToggleButton removeButton;
    public ToggleGroup buttonGroup;

    public Button countFishButton;
    public Button turnButton;
    public Button moveButton;
    public Button putButton;
    public Button pickButton;
    public Button startButton;
    public Button pauseButton;
    public Button restartButton;

    //Border Panes
    public BorderPane outerBorder;
    public BorderPane innerBorder;

    private StackPane leftPane;
    private ScrollPane rightPane;

    //SplitPane
    SplitPane splitPane;

    //Menueleisten
    public Menu editorMenu;
    public Menu surroundingsMenu;
    public Menu whaleMenu;
    public Menu simulationsMenu;
    public Menu surroundingsMenuSave;
    public Menu surroundingsMenuLoad;
    public Menu surroundingsMenuSavePicture;


    //Untermenues
    public MenuItem newFile;
    public MenuItem openFile;
    public MenuItem compileFile;
    public MenuItem printFile;
    public MenuItem printFileSurroundings;
    public MenuItem endSimulation;
    public MenuItem saveXML;
    public MenuItem saveJAXB;
    public MenuItem saveSerialize;
    public MenuItem loadXML;
    public MenuItem loadJAXB;
    public MenuItem saveAsJPG;
    public MenuItem saveAsPNG;
    public MenuItem changeSize;
    public RadioMenuItem placeWhale;
    public RadioMenuItem placeFish;
    public RadioMenuItem placeShip;
    public RadioMenuItem deleteField;
    public ToggleGroup whaleMenuToggleGroup;
    public MenuItem countFishes;
    public MenuItem turnLeft;
    public MenuItem moveFront;
    public MenuItem pickFish;
    public MenuItem putFish;
    public MenuItem startContinue;
    public MenuItem pause;
    public MenuItem stop;

    //Menu Bar
    public MenuBar menuBar;

    //ToolBar
    public ToolBar toolBar;

    //Slider
    public Slider speedSlider;


    // Erstellen der beiden BorderPanes, die als Basis dienen
    public void createPanes() {
        innerBorder = new BorderPane();
        outerBorder = new BorderPane();
        outerBorder.setBottom(new Label("Herzlich Willkommen!"));
    }

    public void createSplitPane() {
        splitPane = new SplitPane();
        innerBorder.setCenter(splitPane);
        leftPane = new StackPane(new TextArea());
        rightPane = createScrollPane();
        splitPane.getItems().addAll(leftPane, rightPane);
    }

    public ScrollPane createScrollPane() {
        rightPane = new ScrollPane();
        op = new OceanPanel(this.ocean, this.rightPane, state);
        rightPane.setContent(op);
        return rightPane;
    }

    // Editor Menü erstellen
    public void initializeEditorMenu() {
        editorMenu = new Menu("_Editor");
        newFile = new MenuItem("Neu");
        ImageView editorNewImageView = new ImageView("resources/file.png");
        editorNewImageView.setFitHeight(15);
        editorNewImageView.setFitWidth(15);
        newFile.setGraphic(editorNewImageView);
        newFile.setAccelerator(KeyCombination.keyCombination("SHORTCUT+N"));

        openFile = new MenuItem("Öffnen");
        openFile.setAccelerator(KeyCombination.keyCombination("SHORTCUT+O"));
        ImageView editorOpenFile = new ImageView("resources/folder.png");
        editorOpenFile.setFitWidth(15);
        editorOpenFile.setFitHeight(15);
        openFile.setGraphic(editorOpenFile);

        compileFile = new MenuItem("Kompilieren");
        compileFile.setAccelerator(KeyCombination.keyCombination("SHORTCUT+K"));

        printFile = new MenuItem("Drucken");
        printFile.setAccelerator(KeyCombination.keyCombination("SHORTCUT+P"));
        ImageView editorPrintFile = new ImageView("resources/print.png");
        editorPrintFile.setFitHeight(15);
        editorPrintFile.setFitWidth(15);
        printFile.setGraphic(editorPrintFile);

        endSimulation = new MenuItem("Beenden");
        endSimulation.setAccelerator(KeyCombination.keyCombination("SHORTCUT+Q"));

        editorMenu.getItems().addAll(newFile, openFile, new SeparatorMenuItem(), compileFile, printFile, new SeparatorMenuItem(), endSimulation);
    }

    // Editor Menü erstellen
    public void initializeSurroundingsMenu() {
        surroundingsMenu = new Menu("_Umgebung");
        surroundingsMenuSave = new Menu("Speichern");
        saveXML = new MenuItem("XML");
        saveJAXB = new MenuItem("JAXB");
        saveSerialize = new MenuItem("Serialisieren");
        surroundingsMenuSave.getItems().addAll(saveXML, saveJAXB, saveSerialize);


        surroundingsMenuLoad = new Menu("Laden");
        loadXML = new MenuItem("XML");
        loadJAXB = new MenuItem("JAXB");
        surroundingsMenuLoad.getItems().addAll(loadXML, loadJAXB);

        surroundingsMenuSavePicture = new Menu("Als Bild speichern");
        saveAsJPG = new MenuItem("JPG");
        saveAsPNG = new MenuItem("PNG");
        surroundingsMenuSavePicture.getItems().addAll(saveAsJPG, saveAsPNG);

        printFileSurroundings = new MenuItem("Drucken");
        changeSize = new MenuItem("Grösse ändern...");

        whaleMenuToggleGroup = new ToggleGroup();
        placeWhale = new RadioMenuItem("Whale platzieren");
        placeFish = new RadioMenuItem("Fisch platzieren");
        placeShip = new RadioMenuItem("Boot platzieren");
        deleteField = new RadioMenuItem("Feld löschen");


        surroundingsMenu.getItems().addAll(surroundingsMenuSave, surroundingsMenuLoad, surroundingsMenuSavePicture, printFileSurroundings,
                changeSize,new SeparatorMenuItem(), placeWhale, placeFish, placeShip, deleteField);
    }

    // Editr
    public void initializeWhaleMenu() {
        whaleMenu = new Menu("_Wal");
        countFishes = new MenuItem("Fische im Maul...");
        turnLeft = new MenuItem("linksUm");
        turnLeft.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+L"));
        moveFront = new MenuItem("vor");
        moveFront.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+V"));
        pickFish = new MenuItem("nimm");
        pickFish.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+N"));
        putFish = new MenuItem("gib");
        putFish.setAccelerator(KeyCombination.keyCombination("SHORTCUT+SHIFT+G"));

        whaleMenu.getItems().addAll(countFishes, turnLeft, moveFront, pickFish, putFish);
    }

    public void initializeSimulationsMenu() {
        simulationsMenu = new Menu("_Simulation");
        startContinue = new MenuItem("Start/Fortsetzen");
        startContinue.setAccelerator(KeyCombination.keyCombination("SHORTCUT+F11"));
        ImageView simulationStartContinue = new ImageView("resources/play_button.png");
        simulationStartContinue.setFitWidth(15);
        simulationStartContinue.setFitHeight(15);
        startContinue.setGraphic(simulationStartContinue);

        pause = new MenuItem("Pause");
        ImageView simulationPause = new ImageView("resources/pause.png");
        simulationPause.setFitHeight(15);
        simulationPause.setFitWidth(15);
        pause.setGraphic(simulationPause);

        stop = new MenuItem("Stop");
        stop.setAccelerator(KeyCombination.keyCombination("SHORTCUT+F12"));
        ImageView simulationStop = new ImageView("resources/stop.png");
        simulationStop.setFitWidth(15);
        simulationStop.setFitHeight(15);
        stop.setGraphic(simulationStop);

        simulationsMenu.getItems().addAll(startContinue, pause, stop);
    }

    // Erstellen der Menüs
    public void initializeMenus() {
        menuBar = new MenuBar();

        initializeEditorMenu();
        initializeSurroundingsMenu();
        initializeWhaleMenu();
        initializeSimulationsMenu();

        menuBar.getMenus().addAll(editorMenu, surroundingsMenu, whaleMenu, simulationsMenu);
    }

    //Erstellen der Buttons
    public void createButtons() {
        buttonGroup = new ToggleGroup();

        newFileButton = new Button();
        newFileButton.setTooltip(new Tooltip("Neue Datei erstellen"));

        fileButton = new Button();
        fileButton.setTooltip(new Tooltip("Datei öffnen"));

        saveButton = new Button();
        saveButton.setTooltip(new Tooltip("Speichern"));

        compileButton = new Button();
        compileButton.setTooltip(new Tooltip("Kompilieren"));

        fieldButton = new Button();
        fieldButton.setTooltip(new Tooltip("Groesse des Feldes aendern"));
        fieldButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               createAlertField();
            }
        });

        whaleButton = new ToggleButton();
        whaleButton.setTooltip(new Tooltip("Wal platzieren"));

        fishButton = new ToggleButton();
        fishButton.setTooltip(new Tooltip("Fisch platzieren"));

        shipButton = new ToggleButton();
        shipButton.setTooltip(new Tooltip("Schiff platzieren"));

        removeButton = new ToggleButton();
        removeButton.setTooltip(new Tooltip("Feld entfernen"));

        countFishButton = new Button();
        countFishButton.setTooltip(new Tooltip("Anzahl Fische im Maul ausgeben"));

        turnButton = new Button();
        turnButton.setTooltip(new Tooltip("Whale um 90° nach links drehen"));

        moveButton = new Button();
        moveButton.setTooltip(new Tooltip("Whale um ein Feld nach vorne bewegen"));

        pickButton = new Button();
        pickButton.setTooltip(new Tooltip("Fisch aufnehmen"));

        putButton = new Button();
        putButton.setTooltip(new Tooltip("Fisch ablegen"));

        startButton = new Button();
        startButton.setTooltip(new Tooltip("Programm starten"));

        pauseButton = new Button();
        pauseButton.setTooltip(new Tooltip("Programm pausieren"));

        restartButton = new Button();
        restartButton.setTooltip(new Tooltip("Programm anhalten"));
    }

    private void createAlertField() {
        // Vorlage: https://stackoverflow.com/questions/31556373/javafx-dialog-with-2-input-fields
        Dialog<Pair<Integer, Integer>> dialog = new Dialog<>();
        dialog.setTitle("Grösse ändern");

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane buttonPane = new GridPane();
        buttonPane.setHgap(10);
        buttonPane.setVgap(10);
        buttonPane.setPadding(new Insets(20, 150, 10, 10));

        // Nur eingabe von Zahlen erlauben nach: https://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
        TextField height = new TextField();
        height.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    height.setText(newValue.replaceAll("[^\\d]*", ""));
                }
            }
        });
        TextField width = new TextField();
        width.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    width.setText(newValue.replaceAll("[^\\d]*", ""));
                }
            }
        });
        buttonPane.add(height, 2, 0);
        buttonPane.add(new Label("Höhe: "), 1, 0);
        buttonPane.add(width, 2,1);
        buttonPane.add(new Label("Breite: "), 1, 1);
        dialog.getDialogPane().setContent(buttonPane);
        dialog.showAndWait();


        String newStringWidth = width.getText();
        String newStringHeigth = height.getText();

        int newHeigth = Integer.parseInt(newStringHeigth);
        int newWidth = Integer.parseInt(newStringWidth);
        ocean.setOceanSize(newWidth, newHeigth);
    }

    public void setToggleGroups() {
        whaleButton.setToggleGroup(buttonGroup);
        fishButton.setToggleGroup(buttonGroup);
        shipButton.setToggleGroup(buttonGroup);
        removeButton.setToggleGroup(buttonGroup);

        whaleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                placeWhale.setSelected(true);
                state.setSelected(PlacingState.WHALE);
            }
        });

        fishButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                placeFish.setSelected(true);
                state.setSelected(PlacingState.FISH);
            }
        });

        shipButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                placeShip.setSelected(true);
                state.setSelected(PlacingState.SHIP);
                }
        });

        removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteField.setSelected(true);
                state.setSelected(state.CLEAR);
            }
        });

        placeWhale.setToggleGroup(whaleMenuToggleGroup);
        placeFish.setToggleGroup(whaleMenuToggleGroup);
        placeShip.setToggleGroup(whaleMenuToggleGroup);
        deleteField.setToggleGroup(whaleMenuToggleGroup);

        placeWhale.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                whaleButton.setSelected(true);
            }
        });

        placeFish.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                fishButton.setSelected(true);
            }
        });

        placeShip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                shipButton.setSelected(true);
            }
        });

        deleteField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                removeButton.setSelected(true);
            }
        });
    }

    //Erstellen der toolBar für die Buttons
    public void createToolbar() {
        speedSlider = new Slider(0, 1, 0.01);
        speedSlider.setTooltip(new Tooltip("Geschwindigkeit einstellen"));
        toolBar = new ToolBar(newFileButton, fileButton, new Separator(), saveButton, compileButton, new Separator(), fieldButton, whaleButton, fishButton,
                shipButton, removeButton, new Separator(), countFishButton, turnButton, moveButton, pickButton, putButton, new Separator(),
                startButton, pauseButton, restartButton, speedSlider);

    }


    //erstellen der ImageViews für die Bilder und hinzufügen zu den Buttons
    public void createImageViews() {
        ImageView newFileButtonImageView = new ImageView("/resources/file.png");
        newFileButton.setGraphic(newFileButtonImageView);

        ImageView fileButtonImageView = new ImageView("/resources/folder.png");
        fileButton.setGraphic(fileButtonImageView);

        ImageView saveButtonImageView = new ImageView("/resources/save.png");
        saveButton.setGraphic(saveButtonImageView);

        ImageView compileButtonImageView = new ImageView("/resources/compile.png");
        compileButton.setGraphic(compileButtonImageView);

        ImageView fieldButtonImageView = new ImageView("/resources/water.png");
        fieldButton.setGraphic(fieldButtonImageView);

        ImageView whaleButtonImageView = new ImageView("resources/whale/whale32.png");
        whaleButton.setGraphic(whaleButtonImageView);

        ImageView fishButtonImageView = new ImageView("resources/fish/fish32.png");
        fishButton.setGraphic(fishButtonImageView);

        ImageView boatButtonImageView = new ImageView("resources/ship.png");
        shipButton.setGraphic(boatButtonImageView);

        ImageView removeButtonImageView = new ImageView("resources/cancel.png");
        removeButton.setGraphic(removeButtonImageView);

        ImageView countFishButton = new ImageView("resources/whale/whalefish.png");
        this.countFishButton.setGraphic(countFishButton);

        ImageView turnButtonImageView = new ImageView("resources/whale/whaleturn.png");
        turnButton.setGraphic(turnButtonImageView);

        ImageView moveButtonImageView = new ImageView("resources/whale/whalemove.png");
        moveButton.setGraphic(moveButtonImageView);

        ImageView pickButtonImageView = new ImageView("resources/whale/whalepick.png");
        pickButton.setGraphic(pickButtonImageView);

        ImageView putButtonImageView = new ImageView("resources/whale/whaleput.png");
        putButton.setGraphic(putButtonImageView);

        ImageView startButtonImageView = new ImageView("resources/play_button.png");
        startButton.setGraphic(startButtonImageView);

        ImageView pauseButtonImageView = new ImageView("resources/pause.png");
        pauseButton.setGraphic(pauseButtonImageView);

        ImageView restartButtonImageView = new ImageView("resources/stop.png");
        restartButton.setGraphic(restartButtonImageView);


    }


    public void buildScene() {
        createToolbar();
        outerBorder.setCenter(innerBorder);
        outerBorder.setTop(menuBar);
        innerBorder.setTop(toolBar);

    }




    @Override
    public void start(Stage primaryStage) throws Exception {
        init();
        createPanes();
        createSplitPane();
        initializeMenus();
        createButtons();
        setToggleGroups();
        createImageViews();
        createToolbar();
        buildScene();
        System.out.println(state.getSelected());
        primaryStage.getIcons().add(new Image("/resources/whale/whale32.png"));
        primaryStage.setTitle("Ozean Mini-Programmierwelt");
        primaryStage.setScene(new Scene(outerBorder, 1200, 600));
        primaryStage.show();

    }

    public void init() throws Exception {
        super.init();
        this.ocean = new Ocean();
        state = new PlacingState();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
