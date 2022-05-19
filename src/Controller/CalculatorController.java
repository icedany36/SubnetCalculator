package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController {

    @FXML
    private Pane titlePane;

    @FXML
    private ImageView btnMinimize, btnClose, firstPage, secondPage, thirdPage;

    @FXML
    private AnchorPane fieldFrame, addressFrame, settingsFrame;

    @FXML
    private Label inputLabel, subnetsLabel, settingsLabel;

    @FXML
    private Button addBtn;

    @FXML
    private RadioButton flsmMode, vlsmMode;

    @FXML
    private TextField IPAddress;

    @FXML
    private ChoiceBox<String> cidr;

    private String[] cidrList = {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};

    private double x, y;

    /*public void enable(MouseEvent e) {
        if(e.getTarget() == vlsmMode)
            addButton.setEnabled(true);
        else if(e.getTarget() == flsmMode)
            addButton.setEnabled(false);
    }*/


    public void initialize(URL url, ResourceBundle resourceBundle) {

        // btnMinimize.setOnMouseClicked(mouseEvent -> MainWindow.test());

        // btnClose.setOnMouseClicked(mouseEvent -> System.exit(0));


    }

    public void init(Stage stage) {

        addressFrame.setVisible(false);
        settingsFrame.setVisible(false);
        inputLabel.setVisible(false);
        subnetsLabel.setVisible(false);
        settingsLabel.setVisible(false);
        addBtn.setVisible(false);

        cidr.getItems().addAll(cidrList);
        cidr.setValue("24");

        thirdPage.setOnMouseExited(mouseEvent -> settingsLabel.setVisible(false));
        thirdPage.setOnMouseEntered(mouseEvent -> settingsLabel.setVisible(true));

        secondPage.setOnMouseExited(mouseEvent -> subnetsLabel.setVisible(false));
        secondPage.setOnMouseEntered(mouseEvent -> subnetsLabel.setVisible(true));

        firstPage.setOnMouseExited(mouseEvent -> inputLabel.setVisible(false));
        firstPage.setOnMouseEntered(mouseEvent -> inputLabel.setVisible(true));

        vlsmMode.setOnMouseClicked(mouseEvent -> {
            addBtn.setVisible(true);
        });

        flsmMode.setOnMouseClicked(mouseEvent -> {
            addBtn.setVisible(false);
        });

        /*flsmMode.setOnMouseClicked(mouseEvent -> addBtn.setEnabled(false));*/

        firstPage.setOnMouseClicked(mouseEvent -> {
            fieldFrame.setVisible(true);
            addressFrame.setVisible(false);
            settingsFrame.setVisible(false);
        });

        thirdPage.setOnMouseClicked(mouseEvent -> {
            fieldFrame.setVisible(false);
            addressFrame.setVisible(false);
            settingsFrame.setVisible(true);
        });

        secondPage.setOnMouseClicked(mouseEvent -> {
            fieldFrame.setVisible(false);
            addressFrame.setVisible(true);
            settingsFrame.setVisible(false);

            calculation();
        });

        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        titlePane.setOnMouseDragged(mouseEvent -> {
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> stage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> stage.setIconified(true));
    }

    private void calculation() {

        if(!IPAddress.getText().equals("")) {

            /*(cidr) {
                case "8":
                    break;
            }*/
        }
    }
}
