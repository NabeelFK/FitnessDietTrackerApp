package com.example.fitnesstrackergp_gui;

import javafx.event.ActionEvent;
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
    @FXML
    private Button submitButton;

    String Username;
    int Age;
    Float Weight;
    Gender Gender;

    public void handleSubmitBtn(ActionEvent actionEvent) {
        try {
            Age = Integer.parseInt(ageInput.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }
    }
}