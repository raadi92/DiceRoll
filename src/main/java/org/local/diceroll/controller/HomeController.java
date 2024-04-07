package org.local.diceroll.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.local.diceroll.gui.Home;
import org.local.diceroll.manager.DicePoolManager;

import java.io.IOException;

public class HomeController {
    private static final int DICE_POOL_MAX = 6;
    @FXML
    private VBox vboxPoolContainer;
    private DicePoolManager poolManager;

    public void onAddButtonClick() throws IOException {
        if (poolManager == null) {
            initializePoolManager(vboxPoolContainer);
        }

        if (poolManager.getDicePoolCounter() < DICE_POOL_MAX) {
            addNewDicePool();
        }
    }

    private void addNewDicePool() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/org/local/diceroll/dicepool.fxml"));
        Node dicePoolNode = fxmlLoader.load();
        DicePoolController controller = fxmlLoader.getController();
        controller.initDicePoolController(dicePoolNode, poolManager);
        poolManager.initPool(dicePoolNode);
    }

    private void initializePoolManager(VBox container) {
        this.poolManager = new DicePoolManager(container);
    }
}