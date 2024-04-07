package org.local.diceroll.controller;

import javafx.scene.Node;
import org.local.diceroll.manager.DicePoolManager;

public class DicepoolController {

    private Node node;
    private DicePoolManager poolManager;

    protected void initDicepoolController(Node node, DicePoolManager poolManager){
        this.node = node;
        this.poolManager = poolManager;
    }

    public void onCloseButtonClick() {
        this.poolManager.clearPool(node);
    }

}
