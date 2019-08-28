package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class GUI extends Application {

    // Buttons
    public Button newFileButton;
    public Button fileButton;
    public Button saveButton;
    public Button compileButton;
    public Button fieldButton;
    public Button whaleButton;
    public Button fishButton;
    public Button boatButton;
    public Button removeButton;

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
    public RadioMenuItem placeBoat;
    public RadioMenuItem deleteField;
    public ToggleGroup menu;
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
        StackPane leftPane = new StackPane(new TextArea());
        StackPane rightPane = new StackPane(new Pane());
        splitPane.getItems().addAll(leftPane, rightPane);
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

        menu = new ToggleGroup();
        placeWhale = new RadioMenuItem("Whale platzieren");
        placeFish = new RadioMenuItem("Fisch platzieren");
        placeBoat = new RadioMenuItem("Boot platzieren");
        deleteField = new RadioMenuItem("Feld löschen");
        placeWhale.setToggleGroup(menu);
        placeWhale.setSelected(true);
        placeFish.setToggleGroup(menu);
        placeBoat.setToggleGroup(menu);
        deleteField.setToggleGroup(menu);

        surroundingsMenu.getItems().addAll(surroundingsMenuSave, surroundingsMenuLoad, surroundingsMenuSavePicture, printFileSurroundings,
                changeSize,new SeparatorMenuItem(), placeWhale, placeFish, placeBoat, deleteField);
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
        newFileButton = new Button();
        newFileButton.setTooltip(new Tooltip("Neue Datei erstellen"));
        fileButton = new Button();
        fileButton.setTooltip(new Tooltip("Datei öffnen"));
        saveButton = new Button();
        saveButton.setTooltip(new Tooltip("Speichern"));
        compileButton = new Button();
        compileButton.setTooltip(new Tooltip("Kompilieren"));
        fieldButton = new Button();
        fieldButton.setTooltip(new Tooltip("Neues Geländefeld setzen"));
        whaleButton = new Button();
        whaleButton.setTooltip(new Tooltip("Whale platzieren"));
        fishButton = new Button();
        fishButton.setTooltip(new Tooltip("Fisch platzieren"));
        boatButton = new Button();
        boatButton.setTooltip(new Tooltip("Boot platzieren"));
        removeButton = new Button();
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


    //Erstellen der toolBar für die Buttons
    public void createToolbar() {
        speedSlider = new Slider(0, 1, 0.01);
        speedSlider.setTooltip(new Tooltip("Geschwindigkeit einstellen"));
        toolBar = new ToolBar(newFileButton, fileButton, new Separator(), saveButton, compileButton, new Separator(), fieldButton, whaleButton, fishButton,
                boatButton, removeButton, new Separator(), countFishButton, turnButton, moveButton, pickButton, putButton, new Separator(),
                startButton, pauseButton, restartButton, speedSlider);

    }

    //erstellen der ImageViews für die Bilder und hinzufügen zu den Buttons
    public void createImageViews() {
        ImageView newFileButtonImageView = new ImageView("/resources/file.png");
        /*newFileButtonImageView.setFitHeight(15);
        newFileButtonImageView.setFitWidth(15);*/
        newFileButton.setGraphic(newFileButtonImageView);

        ImageView fileButtonImageView = new ImageView("/resources/folder.png");
        /*newFileButtonImageView.setFitWidth(10);
        newFileButtonImageView.setFitHeight(10);*/
        fileButton.setGraphic(fileButtonImageView);

        ImageView saveButtonImageView = new ImageView("/resources/save.png");
        /*saveButtonImageView.setFitHeight(15);
        saveButtonImageView.setFitWidth(15);*/
        saveButton.setGraphic(saveButtonImageView);

        ImageView compileButtonImageView = new ImageView("/resources/compile.png");
        /*compileButtonImageView.setFitWidth(15);
        compileButtonImageView.setFitHeight(15);*/
        compileButton.setGraphic(compileButtonImageView);

        ImageView fieldButtonImageView = new ImageView("/resources/water.png");
        /*fieldButtonImageView.setFitHeight(15);
        fileButtonImageView.setFitWidth(15);*/
        fieldButton.setGraphic(fieldButtonImageView);

        ImageView whaleButtonImageView = new ImageView("resources/whale32.png");
        /*whaleButtonImageView.setFitWidth(15);
        whaleButtonImageView.setFitHeight(15);*/
        whaleButton.setGraphic(whaleButtonImageView);

        ImageView fishButtonImageView = new ImageView("resources/fish32.png");
        /*fishButtonImageView.setFitHeight(15);
        fishButtonImageView.setFitWidth(15);*/
        fishButton.setGraphic(fishButtonImageView);

        ImageView boatButtonImageView = new ImageView("resources/ship.png");
        /*boatButtonImageView.setFitWidth(15);
        boatButtonImageView.setFitHeight(15);*/
        boatButton.setGraphic(boatButtonImageView);

        ImageView removeButtonImageView = new ImageView("resources/cancel.png");
        /*removeButtonImageView.setFitHeight(15);
        removeButtonImageView.setFitWidth(15);*/
        removeButton.setGraphic(removeButtonImageView);

        ImageView countFishButton = new ImageView("resources/whalefish.png");
        /*countFishButton.setFitWidth(15);
        countFishButton.setFitHeight(15);*/
        this.countFishButton.setGraphic(countFishButton);

        ImageView turnButtonImageView = new ImageView("resources/whaleturn.png");
        /*turnButtonImageView.setFitHeight(15);
        turnButtonImageView.setFitWidth(15);*/
        turnButton.setGraphic(turnButtonImageView);

        ImageView moveButtonImageView = new ImageView("resources/whalemove.png");
        /*moveButtonImageView.setFitWidth(15);
        moveButtonImageView.setFitHeight(15);*/
        moveButton.setGraphic(moveButtonImageView);

        ImageView pickButtonImageView = new ImageView("resources/whalepick.png");
        /*pickButtonImageView.setFitHeight(15);
        pickButtonImageView.setFitWidth(15);*/
        pickButton.setGraphic(pickButtonImageView);

        ImageView putButtonImageView = new ImageView("resources/whaleput.png");
        /*putButtonImageView.setFitWidth(15);
        putButtonImageView.setFitHeight(15);*/
        putButton.setGraphic(putButtonImageView);

        ImageView startButtonImageView = new ImageView("resources/play_button.png");
        /*startButtonImageView.setFitHeight(15);
        startButtonImageView.setFitWidth(15);*/
        startButton.setGraphic(startButtonImageView);

        ImageView pauseButtonImageView = new ImageView("resources/pause.png");
        /*pauseButtonImageView.setFitWidth(15);
        pauseButtonImageView.setFitHeight(15);*/
        pauseButton.setGraphic(pauseButtonImageView);

        ImageView restartButtonImageView = new ImageView("resources/stop.png");
        /*restartButtonImageView.setFitHeight(15);
        removeButtonImageView.setFitWidth(15);*/
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
        createPanes();
        createSplitPane();
        initializeMenus();
        createButtons();
        createImageViews();
        createToolbar();
        buildScene();

        primaryStage.setScene(new Scene(outerBorder, 1200, 600));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
