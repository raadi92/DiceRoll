package org.local.diceroll.manager;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.util.HashMap;

public class DicePoolManager {
    private VBox container;
    private HashMap<Node, String> pools;

    public DicePoolManager(VBox container) {
        this.container = container;
        this.pools = new HashMap<>();
    }

    public void setPool(Node node, String pool) {
        this.pools.put(node, pool);
    }

    public String getPool(Node node) {
        return this.pools.get(node);
    }

    public void initPool(Node node, String pool) {
        this.container.getChildren().add(node);
        this.pools.put(node, pool);
    }

    public void initPool(Node node) {
        initPool(node, null);
    }

    public void clearPool(Node node) {
        this.container.getChildren().remove(node);
        this.pools.remove(node);
    }

    public int getDicePoolCounter() {
        return this.container.getChildren().size();
    }
}
