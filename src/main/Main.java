package main;

import Controller.CalculatorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Parent root = FXMLLoader.load(getClass().getResource("../views/calculator.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/calculator.fxml"));

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("values/style.css");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("Subnet Calculator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("../img/icon.png")));
        ((CalculatorController)loader.getController()).init(stage);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
