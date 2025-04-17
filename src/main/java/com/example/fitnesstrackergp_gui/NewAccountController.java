package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class NewAccountController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField ageInput;
    @FXML
    private TextField weightInput;
    @FXML
    private ChoiceBox<String> genderInput;
}