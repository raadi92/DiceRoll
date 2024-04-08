package org.local.diceroll.manager;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import org.local.diceroll.controller.DicePoolController;
import org.local.diceroll.dice.DicePool;
import org.local.diceroll.gui.Home;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Logger;

public class DicePoolManager {
    private VBox container;
    private HashMap<Node, DicePool> pools;
    private Logger logger = Logger.getLogger(getClass().getName());

    public DicePoolManager(VBox container) {
        this.container = container;
        this.pools = new HashMap<>();
    }

    public void setPool(Node node, String pool) {
        this.pools.put(node, new DicePool(pool));
    }

    public void setPool(int index, String pool) throws IOException {
        ObservableList<Node> nodeList = this.container.getChildren();
        if (nodeList == null ||  index >= nodeList.size() ||
                nodeList.get(index) == null) {
            addNewDicePool(pool);
        } else {
            this.pools.put(nodeList.get(index), new DicePool(pool));
        }
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

    public void exportToFile(File selectedFile) {
        try (FileWriter writer = new FileWriter(selectedFile)) {

            boolean is1st = true;
            for (DicePool pool : this.pools.values()) {
                writer.write(is1st ? pool.getPoolString() : "\n" + pool.getPoolString());
                is1st = false;
            }
            logger.info("File saved: " + selectedFile.getAbsolutePath());

        } catch (IOException e) {
            logger.info("An error occurred while writing the string to the file: " + e.getMessage());
        }

    }

    public void loadFromFile(File selectedFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {

            String line;
            int i=0;
            while ((line = reader.readLine()) != null) {
                logger.info(String.format("reading line: %s", line));
                setPool(i, line);
                i++;
            }

        } catch (IOException e) {
            logger.info("An error occurred while writing the string to the file: " + e.getMessage());
        }

    }

    public void addNewDicePool() throws IOException {
        addNewDicePool(null);
    }

    public void addNewDicePool(String pool) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/org/local/diceroll/dicepool.fxml"));
        Node dicePoolNode = fxmlLoader.load();
        DicePoolController controller = fxmlLoader.getController();
        controller.initDicePoolController(dicePoolNode, this);
        initPool(dicePoolNode, pool);
        controller.populateDicePool(pool);
    }
}
