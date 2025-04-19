package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.*;


public class LogMealsController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField weightField;

    @FXML
    private RadioButton genderMaleButton;

    @FXML
    private RadioButton genderFemaleButton;

    @FXML
    private RadioButton genderOtherButton;

    @FXML
    private TextField breakfastField;

    @FXML
    private TextField lunchField;

    @FXML
    private TextField dinnerField;

    @FXML
    private Button submitButton;


    @FXML
    public void initialize() {
        //set gender buttons as a part of the same group , so you can only select one
        ToggleGroup genderButtonGroup = new ToggleGroup();
        genderFemaleButton.setToggleGroup(genderButtonGroup);
        genderMaleButton.setToggleGroup(genderButtonGroup);
        genderOtherButton.setToggleGroup(genderButtonGroup);

    }

    @FXML
    //handle everything that needs to be cleared or checked when the submit button is pressed, if any of the fields are empty the corresponding error will show up
    private void handleSubmitButton() {
        String username = usernameField.getText().trim();
        String age = ageField.getText().trim();
        String weight = weightField.getText().trim();
        //if user does not input anything in the username field, username warning will occur
        if (username.isBlank()) {
            Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
            invalidAlert.setTitle("Error: No Username Provided");
            invalidAlert.setHeaderText("Please make sure to fill in Username field.");
            invalidAlert.setContentText("If you have not previously created an account, you will be unable to save history.");
            invalidAlert.showAndWait();

        }
        InputStream toRead = getClass().getResourceAsStream("/com/example/fitnesstrackergp_gui.csv");
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(toRead))){
            String lines;
            boolean usernameFound = false;
            while ((lines = lineReader.readLine()) != null) {
                if (lines.contains(username)){
                    usernameFound = true;
                    break;
                }
            }
        if (usernameFound == false){
            Alert usernameNotFound = new Alert(Alert.AlertType.WARNING);
            usernameNotFound.setTitle("Username Not Found");
            usernameNotFound.setHeaderText("Please enter an existing user or create a new profile.");
            usernameNotFound.showAndWait();
        }

    } catch (IOException e) {
            throw new RuntimeException(e);
        }
    Alert mealSave = new Alert(Alert.AlertType.INFORMATION);
        mealSave.setTitle("Success!");
        mealSave.setHeaderText("Meals have been saved and added to user history.");
        mealSave.showAndWait();

    }
}