package main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML private Pane titlePane;
    @FXML private ImageView btnMinimize, btnClose, firstPage, secondPage, thirdPage;
    @FXML private AnchorPane fieldFrame, addressFrame, settingsFrame;
    @FXML private Label inputLabel, subnetsLabel, settingsLabel;


    private double x, y;

    public void init() {
        addressFrame.setVisible(false);
        settingsFrame.setVisible(false);
        inputLabel.setVisible(false);
        subnetsLabel.setVisible(false);
        settingsLabel.setVisible(false);
    }

    @FXML
    public void drag(Stage stage) {
        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });
    }

    public void showLabel1() {
        firstPage.setOnMouseEntered(mouseEvent -> inputLabel.setVisible(true));
    }

    public void hideLabel1() {
        firstPage.setOnMouseExited(mouseEvent -> inputLabel.setVisible(false));
    }

    public void showLabel2() {
        secondPage.setOnMouseEntered(mouseEvent -> subnetsLabel.setVisible(true));
    }

    public void hideLabel2() {
        secondPage.setOnMouseExited(mouseEvent -> subnetsLabel.setVisible(false));
    }

    public void showLabel3() {
        thirdPage.setOnMouseEntered(mouseEvent -> settingsLabel.setVisible(true));
    }

    public void hideLabel3() {
        thirdPage.setOnMouseExited(mouseEvent -> settingsLabel.setVisible(false));
    }

    public void openFirstPage(ActionEvent e) {
        firstPage.setOnMouseClicked(mouseEvent -> {
            fieldFrame.setVisible(true);
            addressFrame.setVisible(false);
            settingsFrame.setVisible(false);
        });
    }

        public void openSecondPage(MouseEvent e){
                fieldFrame.setVisible(false);
                addressFrame.setVisible(true);
                settingsFrame.setVisible(false);
        }


        public void openThirdPage(ActionEvent e){
            thirdPage.setOnMouseClicked(mouseEvent -> {
                fieldFrame.setVisible(false);
                addressFrame.setVisible(false);
                settingsFrame.setVisible(true);
            });
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnMinimize.setOnMouseClicked(mouseEvent -> MainWindow.test());

        btnClose.setOnMouseClicked(mouseEvent -> System.exit(0));
    }
}
