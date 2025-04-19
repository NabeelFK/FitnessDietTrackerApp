package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDate;
import java.util.Optional;


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
        String gender = "N/A";
        LocalDate todaysDate = LocalDate.now();

        //if user does not input anything in the username field, username warning will occur
        if (username.isBlank()) {
            Alert invalidAlert = new Alert(Alert.AlertType.WARNING);
            invalidAlert.setTitle("Error: No Username Provided");
            invalidAlert.setHeaderText("Please make sure to fill in Username field.");
            invalidAlert.setContentText("If you have not previously created an account, you will be unable to save history.");
            invalidAlert.showAndWait();
            return;

        }
        InputStream toRead = getClass().getResourceAsStream("/com/example/fitnesstrackergp_gui/profiles.csv");
        try {
            assert toRead != null;
            try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(toRead))){
                String lines;
                boolean usernameFound = false;
                while ((lines = lineReader.readLine()) != null) {
                    if (lines.contains(username)){
                        usernameFound = true;
                        break;
                    }
                }
            if (!usernameFound){
                //if there is no matching username associated with the inputted name, age and weight, a warning error will pop up
                showProfileNotFoundAlert();
                return;
            }

        if (age.isBlank()){
            //if no age is inputted, the correct user cannot be accessed so an error will pop up
            Alert invalidAlertAge = new Alert(Alert.AlertType.WARNING);
            invalidAlertAge.setTitle("Error: No Age Provided");
            invalidAlertAge.setHeaderText("Please make sure to fill in Age field.");
            invalidAlertAge.setContentText("Ensure you are inputting the correct up to date age with your profile.");
            invalidAlertAge.showAndWait();
            return;
        }
        if (weight.isBlank()){
            //if no weight is inputted, the correct user cannot be accessed so an error will pop up
            Alert invalidAlertWeight = new Alert(Alert.AlertType.WARNING);
            invalidAlertWeight.setTitle("Error: No Weight Provided");
            invalidAlertWeight.setHeaderText("Please make sure to fill in Weight field.");
            invalidAlertWeight.setContentText("Ensure you are inputting the correct up to date weight with your profile.");
            invalidAlertWeight.showAndWait();
            return;
        }
        //if no gender is selected, the correct user cannot be accessed so an error will pop up
        if (!genderOtherButton.isSelected() && !genderMaleButton.isSelected() && !genderFemaleButton.isSelected()){
            Alert invalidAlertMeal = new Alert(Alert.AlertType.WARNING);
            invalidAlertMeal.setTitle("Error: No Gender Provided");
            invalidAlertMeal.setHeaderText("Please make sure to fill in Gender field.");
            invalidAlertMeal.setContentText("Ensure you are selecting the correct gender associated with your profile.");
            invalidAlertMeal.showAndWait();
            return;
        }

        if (genderFemaleButton.isSelected()){
            gender = "F";
        }
        if (genderMaleButton.isSelected()){
            gender = "M";
        }
        if (genderOtherButton.isSelected()){
            gender = "M";
        }

        }
        //establish meal fields as strings to set up error conditions
        String breakfast = breakfastField.getText().trim();
        String lunch = lunchField.getText().trim();
        String dinner = dinnerField.getText().trim();

        //at least one meal must be inputted in order for it to save to the users file, if none is inputted an error will pop up
        if (breakfast.isBlank() || lunch.isBlank() || dinner.isBlank()){
            Alert invalidAlertMeal = new Alert(Alert.AlertType.WARNING);
            invalidAlertMeal.setTitle("Error: No Meal Provided");
            invalidAlertMeal.setHeaderText("Please make sure to fill at least one meal field");
            invalidAlertMeal.setContentText("If you do not fill in any meals, no history can be added to your account.");
            invalidAlertMeal.showAndWait();
            return;
        }
        //if everything is inputted correctly then it will add the data to the file
        if (!username.isBlank() && !age.isBlank() && !weight.isBlank() && (gender.contains("M") || gender.contains("F")) && (!breakfast.isBlank() || !lunch.isBlank() || !dinner.isBlank())) {

            try {
                String mealFormat = "\n" + username + "," + gender + "," + todaysDate + "," + breakfast + "," + lunch + "," + dinner + "\n";
                String fileName = "src/main/resources/com/example/fitnesstrackergp_gui/meals.csv";
                FileWriter writer = new FileWriter(fileName, true);
                writer.write(mealFormat);
                writer.close();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //if user types everything in correctly an information pop up will let the user know their new added data has saved successfully
    Alert mealSave = new Alert(Alert.AlertType.INFORMATION);
        mealSave.setTitle("Success!");
        mealSave.setHeaderText("Meals have been saved and added to user history.");
        mealSave.showAndWait();


    }

    private void showProfileNotFoundAlert() {
        //create an alert
        Alert alert = new Alert(Alert.AlertType.WARNING);
        //set the title and content
        alert.setTitle("Profile not found");
        alert.setHeaderText("That profile doesn't exist");
        alert.setContentText("Would you like to create a new account?");
        //add buttons for the user to click
        ButtonType createButton = new ButtonType("Create New Account");
        ButtonType cancelButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        //adding them to the alert
        alert.getButtonTypes().setAll(createButton, cancelButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == createButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createProfile.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Create New Account");
                stage.show();
            } catch (IOException e) {
                System.out.println("Something went wrong.");
            }
        }
    }
}