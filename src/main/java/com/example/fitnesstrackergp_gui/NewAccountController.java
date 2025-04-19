package com.example.fitnesstrackergp_gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewAccountController implements Initializable {
    private static final String fileName = "src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv";

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField ageInput;

    @FXML
    private TextField weightInput;

    @FXML
    private TextField heightInput;

    @FXML
    private ChoiceBox<String> genderInput;

    private final String[] genders = new String[]{"Male", "Female", "Other"};

    @FXML
    private Button submitButton;

    String attemptedUsername;
    String Username;
    int Age = 0;
    Float Weight = 0f;
    int Height = 0;
    String genderString;
    Gender gender;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderInput.getItems().addAll(genders);
        genderInput.setOnAction(this::setGender);
    }

    //Sets gender based on if/what menu item selected. If none is selected, an error message pops up.
    private void setGender(ActionEvent actionEvent) {
        genderString = genderInput.getValue();
        if  (genderInput.getValue().equals("Male")) {
            gender = Gender.MALE;
        }
        else if (genderInput.getValue().equals("Female")) {
            gender = Gender.FEMALE;
        }
        else if  (genderInput.getValue().equals("Other")) {
            gender = Gender.OTHER;
        }
    }

    public void handleSubmitBtn() {
        //Checks user input for username. If the username isn't alphabetical, an error message is displayed.
        attemptedUsername = usernameInput.getText();
        if (ProfileManager.isValidUsername(attemptedUsername) && !attemptedUsername.isEmpty()) {
            Username = attemptedUsername;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid username");
            alert.setContentText("Please enter a valid username (please only enter alphabetic characters).");
            alert.showAndWait();
        }

        //Tries parsing integer from user input. If invalid input Age does not get set and error message is displayed.
        try {
            Age = Integer.parseInt(ageInput.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Age");
            alert.setContentText("Please enter a valid integer for your age, ie. no decimal places.");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Age");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        //Tries parsing float from user input. If invalid, Weight does not get set and error message is displayed.
        try {
            Weight = Float.parseFloat(weightInput.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Weight");
            alert.setContentText("Please enter a valid number for your age, up to one decimal place.");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Weight");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        //Tries parsing integer from user input. If invalid input Height does not get set and error message is displayed.
        try {
            Height = Integer.parseInt(heightInput.getText());
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid height");
            alert.setContentText("Please enter a valid integer for your height, ie. no decimal places.");
            alert.showAndWait();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid height");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

        //Shows error message if no option is selected in the gender selection menu.
        if (gender == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Gender");
            alert.setContentText("Please enter a select a gender from the menu.");
            alert.showAndWait();
        }

        if (Username != null && Age != 0 && Weight != 0 && Height != 0 && gender != null) {
            UserProfile profile = new UserProfile(Username, gender, Age, Weight, Height);
            FileHandler.writeToFile(fileName, profile.toCSV() + "\n");

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success!");
            alert.setHeaderText("Profile created.");
            alert.setContentText("You have successfully created your account.");
            alert.showAndWait();
        }
        else  {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Something went wrong!");
            alert.setHeaderText("Profile could not be created.");
            alert.setContentText("Please check to make sure you have entered correct information.");
            alert.showAndWait();
        }
    }

}