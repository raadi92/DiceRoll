package org.local.diceroll.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.local.diceroll.manager.DicePoolManager;

public class DicePoolController {

    private Node node;
    private DicePoolManager poolManager;
    private HomeController homeController;

    @FXML
    private TextField txtDicePool;
    @FXML
    private Label lblResult;

    public void initDicePoolController(Node node, DicePoolManager poolManager, HomeController homeController){
        this.node = node;
        this.poolManager = poolManager;
        this.homeController = homeController;
    }

    public void onCloseButtonClick() {
        this.poolManager.clearPool(node);
        this.homeController.removeChildFromPool(node);
    }

    public void onThrowButtonClick() {
        this.poolManager.updateDicePool(node, txtDicePool.getText());
        lblResult.setText(this.poolManager.throwDicePool(node));
    }

    public void populateDicePool(String text) {
        txtDicePool.setText(text);
    }

}
