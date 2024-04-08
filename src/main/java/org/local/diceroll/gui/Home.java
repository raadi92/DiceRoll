package org.local.diceroll.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.local.diceroll.controller.HomeController;

/**
 *
 */
public class Home extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Home.class.getResource("/org/local/diceroll/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("DiceRoll");
        stage.setScene(scene);
        stage.show();
        HomeController controller = fxmlLoader.getController();
        controller.initializeController(stage);
    }


}
