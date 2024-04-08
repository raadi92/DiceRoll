package org.local.diceroll.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.local.diceroll.manager.DicePoolManager;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class HomeController {
    private static final int DICE_POOL_MAX = 6;
    Logger logger = Logger.getLogger(getClass().getName());

    @FXML
    private VBox vboxPoolContainer;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnLoad;
    private DicePoolManager poolManager;

    public void onAddButtonClick() throws IOException {
        if (poolManager == null) {
            initializePoolManager(vboxPoolContainer);
        }

        if (poolManager.getDicePoolCounter() < DICE_POOL_MAX) {
            poolManager.addNewDicePool("1d6");
        }
    }

    public void initializeController(Stage stage) {
        initializePoolManager(vboxPoolContainer);
        btnSave.setOnAction(event -> setupSaveButton(stage));
        btnLoad.setOnAction(event -> setupOpenButton(stage));
    }

    private void setupSaveButton(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");

        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            logger.info("Selected File: " + selectedFile.getAbsolutePath());
            this.poolManager.exportToFile(selectedFile);
        } else {
            logger.info("No file selected.");
        }
    }

    private void setupOpenButton(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            logger.info("Selected File: " + selectedFile.getAbsolutePath());
            this.poolManager.loadFromFile(selectedFile);
        } else {
            logger.info("No file selected.");
        }
    }

    private void initializePoolManager(VBox container) {
        this.poolManager = new DicePoolManager(container);
    }
}