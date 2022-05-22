package Controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Vector;

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
    private Button addBtn, reset, submit;

    @FXML
    private RadioButton flsmMode, vlsmMode;

    @FXML
    private TextField IPAddress;

    @FXML
    private ChoiceBox<String> cidr, hostsNumber, subnetsNumber;

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

    private String[] cidrC = {"25", "26", "27", "28", "29", "30", "31", "32"};

    private String[] cidrB = {"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};

    private String[] cidrA = {"9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};

    private String[] classC = {"1", "2", "4", "8", "16", "32", "64", "128", "256"};

    private String[] classB = {"1", "2", "4", "8", "16", "32", "64", "128", "256", "1024", "2048", "4096", "8192",
            "16384", "32768", "65536"};

    private String[] classA = {"1", "2", "4", "8", "16", "32", "64", "128", "256", "1024", "2048", "4096", "8192",
            "16384", "32768", "65536", "131072", "262144", "524288", "1048576", "2097152", "4194304", "8388608", "16777216"};

    private double x, y;

    Table tObject;

    ObservableList<Table> list;

    private boolean isClassA = false, isClassB = false, isClassC = false, verify = false;

    private int ID, address, address2, address3, firstHostRange, lastHostRange, broadcast, count;

    private String firstPart, secondPart, thirdPart, fourthPart, first1, first2, first3;

    private boolean submitVerify;

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

        cidr.getItems().addAll(cidrC);
        cidr.setValue("0");

        hostsNumber.getItems().addAll(classC);
        hostsNumber.setValue("0");

        subnetsNumber.getItems().addAll(classC);
        subnetsNumber.setValue("0");

        IPAddress.setText("0.0.0.0");

        c1.setCellValueFactory(new PropertyValueFactory<Table, String>("c1"));
        c2.setCellValueFactory(new PropertyValueFactory<Table, String>("c2"));
        c3.setCellValueFactory(new PropertyValueFactory<Table, String>("c3"));
        c4.setCellValueFactory(new PropertyValueFactory<Table, String>("c4"));

        // FINE SETTINGS INIZIALI ------------------------------------------------------------------------------------------------------------

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

        IPAddress.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode() == KeyCode.ENTER) {
                    controller();

                    if(verify == true)
                        adapter();
                }
            }
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
        });

        reset.setOnMouseClicked(mouseEvent -> {
                list.clear();
                submitVerify = false;
        });

        submit.setOnMouseClicked(mouseEvent -> {
                controller();
                adapter();

                if(verify == true) {
                    calculator();
                    submitVerify = true;

                    fieldFrame.setVisible(false);
                    addressFrame.setVisible(true);
                    settingsFrame.setVisible(false);
                }
        });

        // PARTE DEL CONTROLLO AUTOMATICO
        /*cidr.setOnAction(actionEvent -> {
            switch(cidr.getValue()) {
                case 1:
                    if(Integer.parseInt(IPAddress.getText().substring(0, IPAddress.getText().indexOf('.'))) >= 192) {

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

    private void adapter() {

        first3 = IPAddress.getText().substring( 0, IPAddress.getText().lastIndexOf('.'));
        first2 = first3.substring( 0, first3.lastIndexOf('.'));
        first1 = first2.substring( 0, first2.lastIndexOf('.'));

        firstPart = first1;
        secondPart = first2.substring(firstPart.length() + 1);
        thirdPart = first3.substring(first2.length() + 1);
        fourthPart = IPAddress.getText().substring(first3.length() + 1);

        if(isClassC == true) {
            IPAddress.setText(firstPart + "." + secondPart + "." + thirdPart + ".0");
        } else if(isClassB == true) {
            IPAddress.setText(firstPart + "." + secondPart + ".0.0");
        } else {
            IPAddress.setText(firstPart + ".0.0.0");
        }

        System.out.println("\n\nAdaptation Done: true");
        System.out.println("--Completed--");
    }

    private void calculator() {
        ID = 0;
        address = 0;
        int subnetCounter = 0;

        firstPart = IPAddress.getText().substring( 0, IPAddress.getText().lastIndexOf('.'));
        secondPart = IPAddress.getText().substring( 0, firstPart.lastIndexOf('.'));
        thirdPart = IPAddress.getText().substring( 0, secondPart.lastIndexOf('.'));

        System.out.println("\n\nCALCULATOR STATUS");

        if(isClassC == true) {
            // firstPart = IPAddress.getText().substring( 0, 6 + IPAddress.getText().indexOf('.'));
            do {
                ID++;

                System.out.println("test");
                firstHostRange = address + 1;
                lastHostRange = (address + Integer.parseInt(hostsNumber.getValue())) - 2;

                broadcast = lastHostRange + 1;
                tObject = new Table (ID, firstPart + "." + address, firstPart + "." + firstHostRange + " - " + firstPart + "." + lastHostRange, firstPart + "." + broadcast);

                list = table.getItems();
                list.add(tObject);
                table.setItems(list);

                address += Integer.parseInt(hostsNumber.getValue());
                System.out.println(address);

                subnetCounter++;
            } while(address <= 255 && subnetCounter != Integer.parseInt(subnetsNumber.getValue()));
        }
        else if(isClassB == true) {
            // firstPart = IPAddress.getText().substring(0, 2 + IPAddress.getText().indexOf('.'));

            do {
                if(Integer.parseInt(hostsNumber.getValue()) >= 256) {
                    ID++;
                    firstHostRange = address + 1;

                    lastHostRange = (address + (Integer.parseInt(hostsNumber.getValue())) / 256) - 1;

                    broadcast = (address + (Integer.parseInt(hostsNumber.getValue())) / 256) - 1;

                    tObject = new Table(ID, secondPart + "." + address +".0", secondPart + "." + address +".1" + " - " + secondPart + "." + lastHostRange + ".254", secondPart + "." + broadcast + ".255");

                    list = table.getItems();
                    list.add(tObject);
                    table.setItems(list);

                    address += Integer.parseInt(hostsNumber.getValue()) / 256;
                }
                else {
                    address2 = 0;
                    do {
                        ID++;

                        firstHostRange = address2 + 1;

                        lastHostRange = (address2 + Integer.parseInt(hostsNumber.getValue())) - 2;

                        broadcast = lastHostRange + 1;

                        tObject = new Table(ID, secondPart + "." + address + "." + address2, secondPart + "." + address  + "." + firstHostRange + " - " + secondPart + "." + address + "." + lastHostRange, secondPart + "." + address + "." + broadcast);

                        list = table.getItems();
                        list.add(tObject);
                        table.setItems(list);

                        address2 += Integer.parseInt(hostsNumber.getValue());
                    } while (address2 <= 255);
                    address++;
                }
                tObject = new Table(ID, secondPart + "." + address + "." + address2, secondPart + "." + firstHostRange  + "." + address2 + " - " + secondPart + "." + lastHostRange + "." + address2, secondPart + "." + broadcast + "." + address2);
                subnetCounter++;
            } while(address <= 255 && subnetCounter != Integer.parseInt(subnetsNumber.getValue()));
        }
        else {
            do {
                if(Integer.parseInt(hostsNumber.getValue()) >= 65536) {
                    ID++;
                    firstHostRange = address + 1;

                    lastHostRange = (address + (Integer.parseInt(hostsNumber.getValue())) / 65536) - 1;

                    broadcast = (address + (Integer.parseInt(hostsNumber.getValue())) / 65536) - 1;

                    tObject = new Table(ID, thirdPart + "." + address +".0.0", thirdPart + "." + address +".0.1" + " - " + thirdPart + "." + lastHostRange + ".255.254", thirdPart + "." + broadcast + ".255.255");

                    list = table.getItems();
                    list.add(tObject);
                    table.setItems(list);

                    address += Integer.parseInt(hostsNumber.getValue()) / 65536;
                }
                else if(Integer.parseInt(hostsNumber.getValue()) >= 256) {
                            address2 = 0;
                            do {
                                ID++;

                                firstHostRange = address2 + 1;

                                lastHostRange = (address2 + (Integer.parseInt(hostsNumber.getValue())) / 256) - 1;

                                broadcast = (address2 + (Integer.parseInt(hostsNumber.getValue())) / 256) - 1;

                                tObject = new Table(ID, thirdPart + "." + address + "." + address2 + ".0", thirdPart + "." + address + "." + address2 + ".1" + " - " + thirdPart + "." + address + "." + lastHostRange + ".254", thirdPart + "." + address + "." + broadcast + ".255");

                                list = table.getItems();
                                list.add(tObject);
                                table.setItems(list);

                                address2 += Integer.parseInt(hostsNumber.getValue()) / 256;
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

                            lastHostRange = Integer.parseInt(hostsNumber.getValue()) + address2 - 2;

                            broadcast = lastHostRange + 1;

                            tObject = new Table(ID, thirdPart + "." + address + "." + address3 + "." + address2, thirdPart + "." + address + "." + address3 + "." + firstHostRange + " - " + thirdPart + "." + address + "." + address3 + "." + lastHostRange, thirdPart + "." + address + "." + address3 + "." + broadcast);

                            list = table.getItems();
                            list.add(tObject);
                            table.setItems(list);

                            address2 += Integer.parseInt(hostsNumber.getValue());
                        } while (address2 <= 255);
                        address3++;
                    } while(address3 <= 255);
                    address++;
                }
                } while(address <= 255);

                // tObject = new Table(ID, thirdPart + "." + address + "." + address2, thirdPart + "." + firstHostRange  + "." + address2 + " - " + thirdPart + "." + lastHostRange + "." + address2, thirdPart + "." + broadcast + "." + address2);

        }
        System.out.println("\n\nCalculation Done: true");
        System.out.println("--Completed--");
    }

    private void controller() {

        verify = false;
        count = 0;

        IPAddress.setText(IPAddress.getText().replaceAll("[^\\d.]", ""));

        System.out.println("\n\nCONTROLLER STATUS");

        if (Integer.parseInt(IPAddress.getText().substring(0, IPAddress.getText().indexOf('.'))) >= 192) {
            System.out.println("Class: C");

            cidr.getItems().setAll(cidrC);
            if(Integer.parseInt(cidr.getValue()) < 24)
                cidr.setValue("24");

            hostsNumber.getItems().setAll(classC);
            if(Integer.parseInt(hostsNumber.getValue()) > 256)
                hostsNumber.setValue("256");

            subnetsNumber.getItems().setAll(classC);
            if(Integer.parseInt(subnetsNumber.getValue()) > 256)
            subnetsNumber.setValue("256");

            isClassC = true;
            isClassB = false;
            isClassA = false;
        } else if (Integer.parseInt(IPAddress.getText().substring(0, IPAddress.getText().indexOf('.'))) >= 128) {
            System.out.println("Class: B");

            cidr.getItems().setAll(cidrB);
            if(Integer.parseInt(cidr.getValue()) < 16)
                cidr.setValue("16");

            hostsNumber.getItems().setAll(classB);
            if(Integer.parseInt(hostsNumber.getValue()) > 65536)
                hostsNumber.setValue("65536");

            subnetsNumber.getItems().setAll(classB);
            if(Integer.parseInt(subnetsNumber.getValue()) > 65536)
                subnetsNumber.setValue("65536");

            isClassC = false;
            isClassB = true;
            isClassA = false;
        } else if (Integer.parseInt(IPAddress.getText().substring(0, IPAddress.getText().indexOf('.'))) >= 1) {
            System.out.println("Class: A");

            cidr.getItems().setAll(cidrA);
            if(Integer.parseInt(cidr.getValue()) < 8)
            cidr.setValue("8");

            hostsNumber.getItems().setAll(classA);
            if(Integer.parseInt(hostsNumber.getValue()) > 16777216)
                hostsNumber.setValue("16777216");

            subnetsNumber.getItems().setAll(classA);
            if(Integer.parseInt(subnetsNumber.getValue()) > 16777216)
                subnetsNumber.setValue("16777216");

            isClassC = false;
            isClassB = false;
            isClassA = true;
        }

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
                                            verify = true;
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
                        } while (verify == false);
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
}
