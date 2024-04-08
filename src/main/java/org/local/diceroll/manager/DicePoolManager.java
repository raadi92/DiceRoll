package org.local.diceroll.manager;

import javafx.scene.Node;
import org.local.diceroll.dice.DicePool;

import java.util.HashMap;

public class DicePoolManager {
    private final HashMap<Node, DicePool> pools;

    public DicePoolManager() {
        this.pools = new HashMap<>();
    }

    public void setPool(Node node, String pool) {
        this.pools.put(node, new DicePool(pool));
    }

    public DicePool getPool(Node node) {
        return this.pools.get(node);
    }

    public void initPool(Node node, String pool) {
        this.pools.put(node, new DicePool(pool));
    }

    public void clearPool(Node node) {
        this.pools.remove(node);
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

    public String exportToFile() {
        StringBuilder exportedString = new StringBuilder();
        boolean is1st = true;
        for (DicePool pool : this.pools.values()) {
            if (!is1st) exportedString.append("\n");
            exportedString.append(pool.getPoolString());
            is1st = false;
        }
        return exportedString.toString();
    }

}
