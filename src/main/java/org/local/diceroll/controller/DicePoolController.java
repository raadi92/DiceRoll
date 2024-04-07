package org.local.diceroll.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.local.diceroll.manager.DicePoolManager;

public class DicePoolController {

    private Node node;
    private DicePoolManager poolManager;

    @FXML
    private TextField txtDicePool;
    @FXML
    private Label lblResult;

    protected void initDicePoolController(Node node, DicePoolManager poolManager){
        this.node = node;
        this.poolManager = poolManager;
    }

    public void onCloseButtonClick() {
        this.poolManager.clearPool(node);
    }

    public void onThrowButtonClick() {
        this.poolManager.updateDicePool(node, txtDicePool.getText());
        lblResult.setText(this.poolManager.throwDicePool(node));
    }

}
