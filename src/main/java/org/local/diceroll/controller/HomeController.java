package org.local.diceroll.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.local.diceroll.gui.Home;
import org.local.diceroll.manager.DicePoolManager;
import org.local.diceroll.util.FileManagerUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
        if (getDicePoolCounter() < DICE_POOL_MAX) {
            addNewDicePool();
        }
    }

    public void initializeController(Stage stage) {
        initializePoolManager();
        btnSave.setOnAction(event -> setupSaveButton(stage));
        btnLoad.setOnAction(event -> setupOpenButton(stage));
    }

    private void setupSaveButton(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as...");

        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            logger.info("Selected File: " + selectedFile.getAbsolutePath());
            String exportedPools = this.poolManager.exportToFile();
            FileManagerUtil.writeStringIntoFile(exportedPools, selectedFile);
        } else {
            logger.info("No file selected.");
        }
    }

    private void setupOpenButton(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open");

        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            try {
                logger.info("Selected File: " + selectedFile.getAbsolutePath());
                clearChildrenFromPool();

                List<String> dicePools = FileManagerUtil.loadFromFile(selectedFile);
                for (String dicePool : dicePools) {
                    addNewDicePool(dicePool);
                }
            } catch (IOException ex) {
                logger.severe(ex.getLocalizedMessage());
            }
        } else {
            logger.info("No file selected.");
        }
    }

    private void initializePoolManager() {
        this.poolManager = new DicePoolManager();
    }

    public void addNewDicePool() throws IOException {
        addNewDicePool(null);
    }

    public void addNewDicePool(String pool) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/org/local/diceroll/dicepool.fxml"));
        Node dicePoolNode = fxmlLoader.load();
        addNodeToPoolContainer(dicePoolNode);
        poolManager.initPool(dicePoolNode, pool);
        initializeDicePoolController(fxmlLoader.getController(), pool, dicePoolNode);
    }

    private void initializeDicePoolController(DicePoolController controller, String pool, Node dicePoolNode) {
        controller.initDicePoolController(dicePoolNode, poolManager, this);
        controller.populateDicePool(pool);
    }

    private void addNodeToPoolContainer(Node dicePoolNode) {
        this.vboxPoolContainer.getChildren().add(dicePoolNode);
    }

    public int getDicePoolCounter() {
        return this.vboxPoolContainer.getChildren().size();
    }

    public void removeChildFromPool(Node node) {
        this.vboxPoolContainer.getChildren().remove(node);
    }

    public void clearChildrenFromPool(){
        this.vboxPoolContainer.getChildren().clear();
    }

}