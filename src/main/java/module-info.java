module DiceRoll {
    requires javafx.controls;
    requires javafx.fxml;

    // manage fx:controller from fxml
    opens org.local.diceroll.controller to javafx.fxml;

    // where FXMLLoader object are loaded
    exports org.local.diceroll.gui;
}