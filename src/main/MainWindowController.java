package main;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainWindowController {

    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose, firstPage, secondPage, thirdPage;
    @FXML private AnchorPane fieldFrame, addressFrame, settingsFrame;
    @FXML private Label inputLabel, subnetsLabel, settingsLabel;

    private double x, y;

    public void init(Stage stage) {

        addressFrame.setVisible(false);
        settingsFrame.setVisible(false);
        inputLabel.setVisible(false);
        subnetsLabel.setVisible(false);
        settingsLabel.setVisible(false);

        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX()-x);
            stage.setY(mouseEvent.getScreenY()-y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));

        firstPage.setOnMouseEntered(mouseEvent -> inputLabel.setVisible(true));
        firstPage.setOnMouseExited(mouseEvent -> inputLabel.setVisible(false));
        secondPage.setOnMouseEntered(mouseEvent -> subnetsLabel.setVisible(true));
        secondPage.setOnMouseExited(mouseEvent -> subnetsLabel.setVisible(false));
        thirdPage.setOnMouseEntered(mouseEvent -> settingsLabel.setVisible(true));
        thirdPage.setOnMouseExited(mouseEvent -> settingsLabel.setVisible(false));

        firstPage.setOnMouseClicked(mouseEvent -> {
            fieldFrame.setVisible(true);
            addressFrame.setVisible(false);
            settingsFrame.setVisible(false);
        });

        secondPage.setOnMouseClicked(mouseEvent -> {
            fieldFrame.setVisible(false);
            addressFrame.setVisible(true);
            settingsFrame.setVisible(false);
        });

        thirdPage.setOnMouseClicked(mouseEvent -> {
            fieldFrame.setVisible(false);
            addressFrame.setVisible(false);
            settingsFrame.setVisible(true);
        });
    }
}
