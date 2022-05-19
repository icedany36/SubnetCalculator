package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../main/MainWindowInterface.fxml"));

        stage.setScene(new Scene(root));
        // scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setResizable(false);
        stage.setTitle("Calculator");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));
        // ((MainWindowController)loader.getController()).init(stage);
        stage.show();


    }

    public static void main(String[] args) {

        launch(args);
    }

    public static void test() {
    }
}
