import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        Scene scene = new Scene(root, Color.GRAY);

        // Image icon = new Image("icon.png");
        // stage.getIcons().add(icon);
        stage.setTitle("Subnet Calculator");
        // stage.setWidth(1680);
        // stage.setHeight(1050);
        // stage.setResizable(false);

        stage.setScene(scene);
        stage.show();
    }
}