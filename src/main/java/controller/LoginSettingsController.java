package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class LoginSettingsController implements Initializable {

    @FXML
    private Label ipAddressLabel;

    @FXML
    private TextField ipAddressTextField;

    @FXML
    private Label portLabel;

    @FXML
    private TextField portTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String studentIpAddress = userClass.student.getIpAddress();
        String studentPort = userClass.student.getPort();
        ipAddressTextField.setText("0.0.0.0");
        portTextField.setText("9005");
        if(!studentIpAddress.equals("")) {
            ipAddressTextField.setText(studentIpAddress);
        }
        if(!studentPort.equals("")) {
            portTextField.setText(studentPort);
        }
    }

    @FXML
    void saveButtonAction(ActionEvent event) {
        String studentIpAddress = ipAddressTextField.getText();
        String studentPort = portTextField.getText();
        userClass.student.setIpAddress(studentIpAddress);
        userClass.student.setPort(studentPort);
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void cancelButtonAction(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}

