package org.local.diceroll.manager;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.local.diceroll.dice.DicePool;

import java.util.HashMap;

public class DicePoolManager {
    private VBox container;
    private HashMap<Node, DicePool> pools;

    public DicePoolManager(VBox container) {
        this.container = container;
        this.pools = new HashMap<>();
    }

    public void setPool(Node node, String pool) {
        this.pools.put(node, new DicePool(pool));
    }

    public DicePool getPool(Node node) {
        return this.pools.get(node);
    }

    public void initPool(Node node, String pool) {
        this.container.getChildren().add(node);
        this.pools.put(node, new DicePool(pool));
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

    public void updateDicePool(Node node, String text) {
        DicePool pool = getPool(node);
        if (!pool.equalsPool(text)) {
            setPool(node, text);
        }
    }

    public String throwDicePool(Node node) {
        return getPool(node).roll().toString();
    }
}
