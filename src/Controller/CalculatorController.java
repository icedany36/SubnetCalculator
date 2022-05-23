package Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

public class CalculatorController implements EventHandler<KeyEvent> {

    @FXML
    private Pane titlePane;

    @FXML
    private ImageView btnMinimize, btnClose, firstPage, secondPage, thirdPage;

    @FXML
    private AnchorPane fieldFrame, addressFrame, settingsFrame;

    @FXML
    private Label inputLabel, subnetsLabel, settingsLabel;

    @FXML
    private Button addBtn, reset, submit;

    @FXML
    private RadioButton flsmMode, vlsmMode;

    @FXML
    private TextField IPAddress;

    @FXML
    private ComboBox<String> cidr, hostsNumber, subnetsNumber;

    @FXML
    private TableView<Table> table;

    @FXML
    private TableColumn<Table, String> c1;

    @FXML
    private TableColumn<Table, String> c2;

    @FXML
    private TableColumn<Table, String> c3;

    @FXML
    private TableColumn<Table, String> c4;

    private String[] cidrC = {"24", "25", "26", "27", "28", "29", "30"};

    private String[] cidrB = {"16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};

    private String[] cidrA = {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"};

    private String[] hostsClassC = {"2", "6", "14", "30", "62", "126", "254"};

    private String[] hostsClassB = {"2", "6", "14", "30", "62", "126", "254", "1022", "2046", "4094", "8190",
            "16382", "32766", "65534"};

    private String[] hostsClassA = {"2", "6", "14", "30", "62", "126", "254", "1022", "2046", "4094", "8190",
            "16382", "32766", "65534", "131070", "262142", "524286", "1048574", "2097150", "4194302", "8388606", "16777214"};

    private String[] subnetsClassC = {"1", "2", "4", "8", "16", "32", "64"};

    private String[] subnetsClassB = {"1", "2", "4", "8", "16", "32", "64", "128", "256", "1024", "2048", "4096", "8192", "16384"};

    private String[] subnetsClassA = {"1", "2", "4", "8", "16", "32", "64", "128", "256", "1024", "2048", "4096", "8192",
            "16384", "32768", "65536", "131072", "262144", "524288", "1048576", "2097152", "4194304"};

    private double x, y;

    Table tObject;

    KeyCombination short1 = new KeyCodeCombination(KeyCode.DIGIT1, KeyCodeCombination.CONTROL_DOWN);
    KeyCombination short2 = new KeyCodeCombination(KeyCode.DIGIT2, KeyCodeCombination.CONTROL_DOWN);
    KeyCombination short3 = new KeyCodeCombination(KeyCode.DIGIT3, KeyCodeCombination.CONTROL_DOWN);
    KeyCombination shortCalc = new KeyCodeCombination(KeyCode.C, KeyCodeCombination.SHIFT_DOWN);
    KeyCombination shortReset = new KeyCodeCombination(KeyCode.R, KeyCodeCombination.SHIFT_DOWN);

    ObservableList<Table> list;

    private boolean isClassA = false, isClassB = false, isClassC = false, controllerVerifier = false, adapterVerifier = false, firstTime = true, submitVerifier;

    private int ID, address, address2, address3, firstHostRange, lastHostRange, broadcast, count, numberOfIP, currentPage = 1;

    private String firstPart, secondPart, thirdPart, fourthPart, first1, first2, first3, lastIP = "", lastMaskBits, lastHostsNumber, lastSubnetsNumber;

    /* public void enable(MouseEvent e) {
        if(e.getTarget() == vlsmMode)
            addButton.setEnabled(true);
        else if(e.getTarget() == flsmMode)
            addButton.setEnabled(false);
    } */


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
        inputLabel.setVisible(true);
        reset.setVisible(false);

        cidr.getItems().setAll(cidrA);
        cidr.setValue("8");

        hostsNumber.getItems().setAll(hostsClassA);
        hostsNumber.setValue("16777214");

        subnetsNumber.getItems().setAll(subnetsClassA);
        subnetsNumber.setValue("1");

        IPAddress.setText("10.0.0.0");

        c1.setCellValueFactory(new PropertyValueFactory<Table, String>("c1"));
        c2.setCellValueFactory(new PropertyValueFactory<Table, String>("c2"));
        c3.setCellValueFactory(new PropertyValueFactory<Table, String>("c3"));
        c4.setCellValueFactory(new PropertyValueFactory<Table, String>("c4"));

        // cidr.setStyle(" -fx-background-color: #FFFFFF");

        // FINE SETTINGS INIZIALI ------------------------------------------------------------------------------------------------------------


        thirdPage.setOnMouseExited(mouseEvent -> labelController());
        thirdPage.setOnMouseEntered(mouseEvent -> {
            inputLabel.setVisible(false);
            subnetsLabel.setVisible(false);
            settingsLabel.setVisible(true);
        });

        secondPage.setOnMouseExited(mouseEvent ->
            labelController()
        );
        secondPage.setOnMouseEntered(mouseEvent -> {
            inputLabel.setVisible(false);
            subnetsLabel.setVisible(true);
            settingsLabel.setVisible(false);
        });

        stage.getScene().setOnKeyPressed(keyEvent -> {
            if(short1.match(keyEvent)) {
                currentPage = 1;

                settingsLabel.setVisible(false);
                inputLabel.setVisible(true);
                subnetsLabel.setVisible(false);

                fieldFrame.setVisible(true);
                addressFrame.setVisible(false);
                settingsFrame.setVisible(false);
            } else if(short2.match(keyEvent)) {
                currentPage = 2;

                settingsLabel.setVisible(false);
                inputLabel.setVisible(false);
                subnetsLabel.setVisible(true);

                fieldFrame.setVisible(false);
                addressFrame.setVisible(true);
                settingsFrame.setVisible(false);

            } else if(short3.match(keyEvent)) {
                currentPage = 3;

                settingsLabel.setVisible(true);
                inputLabel.setVisible(false);
                subnetsLabel.setVisible(false);

                fieldFrame.setVisible(false);
                addressFrame.setVisible(false);
                settingsFrame.setVisible(true);
            } else if(shortCalc.match(keyEvent)) {
                controller();

                if(!lastIP.contains(IPAddress.getText()) || lastHostsNumber.contains(hostsNumber.getValue()) || lastSubnetsNumber.contains(subnetsNumber.getValue()) || lastMaskBits.contains(cidr.getValue()) && controllerVerifier == true) {
                    adapter();
                    calculator();

                    lastIP = IPAddress.getText();
                    submitVerifier = true;
                    firstTime = false;

                    fieldFrame.setVisible(false);
                    addressFrame.setVisible(true);
                    settingsFrame.setVisible(false);
                }
            }
        });

        firstPage.setOnMouseExited(mouseEvent -> labelController());
        firstPage.setOnMouseEntered(mouseEvent -> {
            inputLabel.setVisible(true);
            subnetsLabel.setVisible(false);
            settingsLabel.setVisible(false);
        });

        submit.setOnMouseEntered(mouseEvent -> submit.setStyle("-fx-background-color: rgba(255, 255, 255, .100);"));
        submit.setOnMouseExited(mouseEvent -> submit.setStyle(null));

        reset.setOnMouseEntered(mouseEvent -> reset.setStyle("-fx-background-color: rgba(255, 255, 255, .100);"));
        reset.setOnMouseExited(mouseEvent -> reset.setStyle(null));

        addBtn.setOnMouseEntered(mouseEvent -> addBtn.setStyle("-fx-background-color: rgba(255, 255, 255, .100);"));
        addBtn.setOnMouseExited(mouseEvent -> addBtn.setStyle(null));

        vlsmMode.setOnMouseClicked(mouseEvent -> {
            addBtn.setVisible(true);
            reset.setVisible(true);
        });

        flsmMode.setOnMouseClicked(mouseEvent -> {
            addBtn.setVisible(false);
            reset.setVisible(false);
        });

        IPAddress.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER) {
                    controller();

                    if(controllerVerifier == true && lastIP != IPAddress.getText())
                            adapter();
                }
            }
        });

        /*flsmMode.setOnMouseClicked(mouseEvent -> addBtn.setEnabled(false));*/


        firstPage.setOnMouseClicked(mouseEvent -> {
            currentPage = 1;

            settingsLabel.setVisible(false);
            inputLabel.setVisible(true);
            subnetsLabel.setVisible(false);

            fieldFrame.setVisible(true);
            addressFrame.setVisible(false);
            settingsFrame.setVisible(false);
        });

        thirdPage.setOnMouseClicked(mouseEvent -> {
            currentPage = 3;

            settingsLabel.setVisible(true);
            inputLabel.setVisible(false);
            subnetsLabel.setVisible(false);

            fieldFrame.setVisible(false);
            addressFrame.setVisible(false);
            settingsFrame.setVisible(true);
        });

        secondPage.setOnMouseClicked(mouseEvent -> {
            currentPage = 2;

            settingsLabel.setVisible(false);
            inputLabel.setVisible(false);
            subnetsLabel.setVisible(true);

            fieldFrame.setVisible(false);
            addressFrame.setVisible(true);
            settingsFrame.setVisible(false);
        });

        reset.setOnMouseClicked(mouseEvent -> {
            if(submitVerifier == true) {
                list.clear();
                submitVerifier = false;
            }
        });

        submit.setOnMouseClicked(mouseEvent -> {
                controller();

                if(!lastIP.contains(IPAddress.getText()) || lastHostsNumber.contains(hostsNumber.getValue()) || lastSubnetsNumber.contains(subnetsNumber.getValue()) || lastMaskBits.contains(cidr.getValue()) && controllerVerifier == true) {
                    adapter();
                    calculator();

                    lastIP = IPAddress.getText();
                    submitVerifier = true;
                    firstTime = false;

                    fieldFrame.setVisible(false);
                    addressFrame.setVisible(true);
                    settingsFrame.setVisible(false);
                }
        });

        /*cidr.setOnMouseClicked(mouseEvent -> { //TODO
            controller();

            if(controllerVerifier == true) {
                adapter();

                /* switch(Integer.parseInt(cidr.getValue())) {
                    case 8:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 9:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 10:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 11:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 12:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 13:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 14:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 15:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 16:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 17:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 18:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 19:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 20:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 21:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 22:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 23:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 24:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 25:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 26:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 27:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 28:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 29:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 30:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 31:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;

                    case 32:
                        subnetsNumber.setValue(String.valueOf((Math.pow(2,16))));
                        break;
                }
            }
        }); */

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

    private void labelController() {
        switch(currentPage) {
            case 1:
                inputLabel.setVisible(true);
                subnetsLabel.setVisible(false);
                settingsLabel.setVisible(false);
                break;
            case 2:
                inputLabel.setVisible(false);
                subnetsLabel.setVisible(true);
                settingsLabel.setVisible(false);
                break;
            case 3:
                inputLabel.setVisible(false);
                subnetsLabel.setVisible(false);
                settingsLabel.setVisible(true);
                break;
        }
    }

    private void adapter() {

        first3 = IPAddress.getText().substring( 0, IPAddress.getText().lastIndexOf('.'));
        first2 = first3.substring( 0, first3.lastIndexOf('.'));
        first1 = first2.substring( 0, first2.lastIndexOf('.'));

        firstPart = first1;
        secondPart = first2.substring(firstPart.length() + 1);
        thirdPart = first3.substring(first2.length() + 1);
        fourthPart = IPAddress.getText().substring(first3.length() + 1);

        System.out.println("\n\nADAPTER STATUS");

        if (Integer.parseInt(IPAddress.getText().substring(0, IPAddress.getText().indexOf('.'))) >= 192) {
            System.out.println("Class: C");

            lastMaskBits = cidr.getValue();
            cidr.getItems().setAll(cidrC);
            cidr.setValue(lastMaskBits);

            if(Integer.parseInt(cidr.getValue()) < 24)
                cidr.setValue("24");

            lastHostsNumber = hostsNumber.getValue();
            hostsNumber.getItems().setAll(hostsClassC);
            hostsNumber.setValue(lastHostsNumber);

            if(Integer.parseInt(hostsNumber.getValue()) > 256)
                hostsNumber.setValue("254");

            lastSubnetsNumber = subnetsNumber.getValue();
            subnetsNumber.getItems().setAll(subnetsClassC);
            subnetsNumber.setValue(lastSubnetsNumber);

            if(Integer.parseInt(subnetsNumber.getValue()) > 256)
                subnetsNumber.setValue("256");

            isClassC = true;
            isClassB = false;
            isClassA = false;
        } else if (Integer.parseInt(IPAddress.getText().substring(0, IPAddress.getText().indexOf('.'))) >= 128) {
            System.out.println("Class: B");

            lastMaskBits = cidr.getValue();
            cidr.getItems().setAll(cidrB);
            cidr.setValue(lastMaskBits);

            if(Integer.parseInt(cidr.getValue()) < 16)
                cidr.setValue("16");

            lastHostsNumber = hostsNumber.getValue();
            hostsNumber.getItems().setAll(hostsClassB);
            hostsNumber.setValue(lastHostsNumber);

            if(Integer.parseInt(hostsNumber.getValue()) > 65536)
                hostsNumber.setValue("65536");

            lastSubnetsNumber = subnetsNumber.getValue();
            subnetsNumber.getItems().setAll(subnetsClassB);
            subnetsNumber.setValue(lastSubnetsNumber);

            if(Integer.parseInt(subnetsNumber.getValue()) > 65536)
                subnetsNumber.setValue("65536");

            isClassC = false;
            isClassB = true;
            isClassA = false;
        } else if (Integer.parseInt(IPAddress.getText().substring(0, IPAddress.getText().indexOf('.'))) >= 0) {
            System.out.println("Class: A");

            lastMaskBits = cidr.getValue();

            cidr.getItems().setAll(cidrA);

            cidr.setValue(lastMaskBits);

            if(Integer.parseInt(cidr.getValue()) < 8)
                cidr.setValue("8");

            lastHostsNumber = hostsNumber.getValue();

            hostsNumber.getItems().setAll(hostsClassA);

            hostsNumber.setValue(lastHostsNumber);

            if(Integer.parseInt(hostsNumber.getValue()) > 16777216)
                hostsNumber.setValue("16777216");

            lastSubnetsNumber = subnetsNumber.getValue();

            subnetsNumber.getItems().setAll(subnetsClassA);

            subnetsNumber.setValue(lastSubnetsNumber);

            if(Integer.parseInt(subnetsNumber.getValue()) > 16777216)
                subnetsNumber.setValue("16777216");

            isClassC = false;
            isClassB = false;
            isClassA = true;

        }

        if(isClassC == true) {
            IPAddress.setText(firstPart + "." + secondPart + "." + thirdPart + ".0");
        } else if(isClassB == true) {
            IPAddress.setText(firstPart + "." + secondPart + ".0.0");
        } else {
            IPAddress.setText(firstPart + ".0.0.0");
        }

        System.out.println("Adaptation Done: true");
        System.out.println("--Completed--");
    }

    private void calculator() {
        if(submitVerifier == true) {
            list.clear();
            submitVerifier = false;
        }
        ID = 0;
        address = 0;
        int subnetCounter = 0;

        firstPart = IPAddress.getText().substring( 0, IPAddress.getText().lastIndexOf('.'));
        secondPart = IPAddress.getText().substring( 0, firstPart.lastIndexOf('.'));
        thirdPart = IPAddress.getText().substring( 0, secondPart.lastIndexOf('.'));

        System.out.println("\n\nCALCULATOR STATUS");

        numberOfIP = Integer.parseInt(hostsNumber.getValue()) + 2;

        if(isClassC == true) {
            // Class C ------------------------------------------------------------------------------------------
            // firstPart = IPAddress.getText().substring( 0, 6 + IPAddress.getText().indexOf('.'));
            do {
                ID++;

                System.out.println("test");
                firstHostRange = address + 1;
                lastHostRange = (address + numberOfIP) - 2;

                broadcast = lastHostRange + 1;
                tObject = new Table (ID, firstPart + "." + address, firstPart + "." + firstHostRange + " - " + firstPart + "." + lastHostRange, firstPart + "." + broadcast);

                list = table.getItems();
                list.add(tObject);
                table.setItems(list);

                address += numberOfIP;
                System.out.println(address);

                subnetCounter++;
            } while(address <= 255 && subnetCounter != Integer.parseInt(subnetsNumber.getValue()));
        }
        else if(isClassB == true) {
            // Class B ------------------------------------------------------------------------------
            // firstPart = IPAddress.getText().substring(0, 2 + IPAddress.getText().indexOf('.'));

            do {
                if(numberOfIP >= 256) {
                    ID++;
                    firstHostRange = address + 1;

                    lastHostRange = (address + (numberOfIP) / 256) - 1;

                    broadcast = (address + (numberOfIP) / 256) - 1;

                    tObject = new Table(ID, secondPart + "." + address +".0", secondPart + "." + address +".1" + " - " + secondPart + "." + lastHostRange + ".254", secondPart + "." + broadcast + ".255");

                    list = table.getItems();
                    list.add(tObject);
                    table.setItems(list);

                    address += numberOfIP / 256;
                }
                else {
                    address2 = 0;
                    do {
                        ID++;

                        firstHostRange = address2 + 1;

                        lastHostRange = (address2 + numberOfIP) - 2;

                        broadcast = lastHostRange + 1;

                        tObject = new Table(ID, secondPart + "." + address + "." + address2, secondPart + "." + address  + "." + firstHostRange + " - " + secondPart + "." + address + "." + lastHostRange, secondPart + "." + address + "." + broadcast);

                        list = table.getItems();
                        list.add(tObject);
                        table.setItems(list);

                        address2 += numberOfIP;
                    } while (address2 <= 255);
                    address++;
                }
                tObject = new Table(ID, secondPart + "." + address + "." + address2, secondPart + "." + firstHostRange  + "." + address2 + " - " + secondPart + "." + lastHostRange + "." + address2, secondPart + "." + broadcast + "." + address2);
                subnetCounter++;
            } while(address <= 255 && subnetCounter != Integer.parseInt(subnetsNumber.getValue()));
        }
        else {
            // Class A --------------------------------------------------------
            System.out.println(numberOfIP);
            do {
                if(numberOfIP >= 65536) {
                    ID++;
                    firstHostRange = address + 1;

                    lastHostRange = (address + (numberOfIP) / 65536) - 1;

                    broadcast = (address + (numberOfIP) / 65536) - 1;

                    tObject = new Table(ID, thirdPart + "." + address +".0.0", thirdPart + "." + address +".0.1" + " - " + thirdPart + "." + lastHostRange + ".255.254", thirdPart + "." + lastHostRange + ".255.255");

                    list = table.getItems();
                    list.add(tObject);
                    table.setItems(list);

                    address += numberOfIP / 65536;
                }
                else if(numberOfIP >= 256) {
                            address2 = 0;
                            do {
                                ID++;

                                firstHostRange = address2 + 1;

                                lastHostRange = (address2 + (numberOfIP) / 256) - 1;

                                broadcast = (address2 + (numberOfIP) / 256) - 1;

                                tObject = new Table(ID, thirdPart + "." + address + "." + address2 + ".0", thirdPart + "." + address + "." + address2 + ".1" + " - " + thirdPart + "." + address + "." + lastHostRange + ".254", thirdPart + "." + address + "." + broadcast + ".255");

                                list = table.getItems();
                                list.add(tObject);
                                table.setItems(list);

                                address2 += numberOfIP / 256;
                            } while (address2 <= 255);
                            address++;
                }
                else {
                    address3 = 0;
                    do {
                        address2 = 0;
                        do {
                            ID++;

                            firstHostRange = address2 + 1;

                            lastHostRange = numberOfIP + address2 - 2;

                            broadcast = lastHostRange + 1;

                            tObject = new Table(ID, thirdPart + "." + address + "." + address3 + "." + address2, thirdPart + "." + address + "." + address3 + "." + firstHostRange + " - " + thirdPart + "." + address + "." + address3 + "." + lastHostRange, thirdPart + "." + address + "." + address3 + "." + broadcast);

                            list = table.getItems();
                            list.add(tObject);
                            table.setItems(list);

                            address2 += numberOfIP;
                        } while (address2 <= 255);
                        address3++;
                    } while(address3 <= 255);
                    address++;
                }
                } while(address <= 255 && subnetCounter != Integer.parseInt(subnetsNumber.getValue()));

                // tObject = new Table(ID, thirdPart + "." + address + "." + address2, thirdPart + "." + firstHostRange  + "." + address2 + " - " + thirdPart + "." + lastHostRange + "." + address2, thirdPart + "." + broadcast + "." + address2);

        }
        System.out.println("Calculation Done: true");
        System.out.println("--Completed--");
    }

    private void controller() {

        controllerVerifier = false;
        count = 0;

        IPAddress.setText(IPAddress.getText().replaceAll("[^\\d.]", ""));

        System.out.println("\n\nCONTROLLER STATUS");

        if(IPAddress.getText().length() > 0) {
            System.out.println("Not Empty: true");

                for (int i = 0; i < IPAddress.getText().length(); i++) {
                    if (IPAddress.getText().charAt(i) == '.') {
                        if(i + 1  <= IPAddress.getText().length()) {
                            if(i + 1 != IPAddress.getText().length()) {
                                if (IPAddress.getText().charAt(i + 1) != '.')
                                    count++;
                            } else {
                                count++;
                            }
                        }
                    }
                }

                System.out.println("[Dot Counted: " + count + "]");

                if(count == 3) {
                    System.out.println("3 Dots: true");

                    first3 = IPAddress.getText().substring( 0, IPAddress.getText().lastIndexOf('.'));
                    first2 = first3.substring( 0, first3.lastIndexOf('.'));
                    first1 = first2.substring( 0, first2.lastIndexOf('.'));

                    firstPart = first1;
                    secondPart = first2.substring(firstPart.length() + 1);
                    thirdPart = first3.substring(first2.length() + 1);
                    fourthPart = IPAddress.getText().substring(first3.length() + 1);
                    if((firstPart.length() <= 3 && firstPart.length() >= 1) && (secondPart.length() <= 3 && secondPart.length() >= 1) && (thirdPart.length() <= 3 && thirdPart.length() >= 1) && (fourthPart.length() <= 3 && fourthPart.length() >= 1)) {
                        System.out.println("Bytes length = true");
                        do {
                            first3 = IPAddress.getText().substring( 0, IPAddress.getText().lastIndexOf('.'));
                            first2 = first3.substring( 0, first3.lastIndexOf('.'));
                            first1 = first2.substring( 0, first2.lastIndexOf('.'));

                            firstPart = first1;
                            secondPart = first2.substring(firstPart.length() + 1);
                            thirdPart = first3.substring(first2.length() + 1);
                            fourthPart = IPAddress.getText().substring(first3.length() + 1);

                            if (Integer.parseInt(firstPart) >= 0 && Integer.parseInt(firstPart) <= 255) {
                                System.out.println("First Byte = true");
                                if (Integer.parseInt(secondPart) >= 0 && Integer.parseInt(secondPart) <= 255) {
                                    System.out.println("Second Byte = true");
                                    if (Integer.parseInt(thirdPart) >= 0 && Integer.parseInt(thirdPart) <= 255) {
                                        System.out.println("Third Byte = true");
                                        if (Integer.parseInt(fourthPart) >= 0 && Integer.parseInt(fourthPart) <= 255) {
                                            System.out.println("Fourth Byte = true");
                                            System.out.println("Verify = true");
                                            System.out.println("--Completed--");
                                            controllerVerifier = true;
                                        } else {
                                            System.out.println("Fourth Byte = false");
                                            System.out.println("Incorrect Address");

                                            if (Integer.parseInt(fourthPart) < 0) {
                                                IPAddress.setText(firstPart + "." + secondPart + "." + thirdPart + ".0");
                                            } else {
                                                IPAddress.setText(firstPart + "." + secondPart + "." + thirdPart + ".255");
                                            }
                                        }
                                    } else {
                                        System.out.println("Third Byte = false");
                                        System.out.println("Incorrect Address");

                                        if (Integer.parseInt(thirdPart) < 0) {
                                            IPAddress.setText(firstPart + "." + secondPart + ".0." + fourthPart);
                                        } else {
                                            IPAddress.setText(firstPart + "." + secondPart + ".255." + fourthPart);
                                        }
                                    }
                                } else {
                                    System.out.println("Second Byte = false");
                                    System.out.println("Incorrect Address");

                                    if (Integer.parseInt(secondPart) < 0) {
                                        IPAddress.setText(firstPart + ".0." + thirdPart + "." + fourthPart);
                                    } else {
                                        IPAddress.setText(firstPart + ".255." + thirdPart + "." + fourthPart);
                                    }
                                }
                            } else {
                                System.out.println("First Byte = false");
                                System.out.println("Incorrect Address");

                                if (Integer.parseInt(firstPart) < 0) {
                                    IPAddress.setText("0." + secondPart + "." + thirdPart + "." + fourthPart);
                                } else {
                                    IPAddress.setText("255." + secondPart + "." + thirdPart + "." + fourthPart);
                                }
                            }
                        } while (controllerVerifier == false);
                    } else {
                        System.out.println("Bytes length = false");
                    }
                } else {
                    System.out.println("3 Dots: false");
                    System.out.println("Incorrect Address");

                    if(count == 2) {
                        IPAddress.setText(IPAddress.getText() + ".0");
                    } else if(count == 1) {
                        IPAddress.setText(IPAddress.getText() + ".0.0");
                    }
                }
        }
        else {
            System.out.println("Not Empty: false");
            System.out.println("Incorrect Address");
            IPAddress.setText("0.0.0.0");
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {

    }
}
