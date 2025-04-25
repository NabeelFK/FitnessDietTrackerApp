package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;

public class SetGoalsController implements Initializable {
    private String goalsFile;
    private String profilesFile;

    public SetGoalsController() {
        this.goalsFile = ArgsManager.getInstance().getGoalsCSV();
        this.profilesFile = ArgsManager.getInstance().getProfileCSV();
    }

    @FXML
    private TextField usernameInput;

    @FXML
    private TextField ageInput;

    @FXML
    private TextField weightInput;

    @FXML
    private ChoiceBox<String> genderInput;

    private final String[] genders = new String[]{"Male", "Female", "Other"};

    @FXML
    private TextField idealWeightInput;

    @FXML
    private TextField idealExerciseInput;

    @FXML
    private TextField idealSleepInput;

    @FXML
    private Button submitButton;

    String inputtedUsername;
    String username;
    int age = 0;
    Float weight = 0f;
    String inputtedGender;
    String gender;
    Float idealWeight = 0f;
    int idealExercise = 0;
    int idealSleep = 0;

    /**
     * Initializes the GUI components for the SetGoalsController.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resources The resources used to localize the root object, or null if the resources are not available.
     */
    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources) {
        genderInput.getItems().addAll(genders);
        genderInput.setValue("Male"); // Default selection to Male
    }

    /**
     * Collects and validates the user's input for their fitness goals, and saves the data in csv file if valid.
     * If any input is invalid, error messages are displayed.
     */
    @FXML
    private void handleSubmit() {
        inputtedUsername = usernameInput.getText();
        gender = genderInput.getValue();

        if (!isUsernameExists(inputtedUsername)) {
            showProfileNotFoundAlert();
            return;
        }

        if (username != null && age != 0 && weight != 0 && gender != null && idealWeight != 0 &&
                idealExercise != 0 && idealSleep != 0) {
            showConfirmation("Success!", "Your profile has been updated.");
            return;
        }

        if (ProfileManager.isValidUsername(inputtedUsername) && !inputtedUsername.isEmpty()) {
            username = inputtedUsername;
        } else {
            showError("Error. Invalid username.", "Please enter a valid username." +
                    "\n *Alphabetical characters only.");
        }
        try {
            age = Integer.parseInt(ageInput.getText());
        } catch (NumberFormatException e) {
            showError("Error. Invalid age.", "Please enter a valid age.");
        }
        try {
            weight = Float.parseFloat(weightInput.getText());
        } catch (NumberFormatException e) {
            showError("Error. Invalid weight.", "Please enter a valid weight (up to one decimal space).");
        }
        try {
            idealWeight = Float.parseFloat(idealWeightInput.getText());
        } catch (NumberFormatException e) {
            showError("Error. Invalid ideal weight.", "Please enter a valid weight (up to one decimal space).");
        }
        try {
            idealExercise = Integer.parseInt(idealExerciseInput.getText());
        } catch (NumberFormatException e) {
            showError("Error. Invalid exercise time.", "Please enter a valid exercise time (in minutes).");
        }
        try {
            idealSleep = Integer.parseInt(idealSleepInput.getText());
        } catch (NumberFormatException e) {
            showError("Error. Invalid sleep time.", "Please enter a valid sleep time (in hours).");
        }

        if (gender == null) {
            showError("Gender null.", "Please select a gender.");
            return;
        }

        if (username != null && age != 0) {
            // Save the user's goals to the CSV file
            saveGoals(inputtedUsername, age, weight, gender, idealWeight, idealExercise, idealSleep);
        }
        resetForm();
    }

    /**
     * Resets the form after response has been submitted.
     */
    private void resetForm() {
        usernameInput.clear();
        ageInput.clear();
        weightInput.clear();
        idealWeightInput.clear();
        idealExerciseInput.clear();
        idealSleepInput.clear();
        genderInput.setValue("Male");
    }

    /**
     * Checks if the given username exists in the CSV file.
     * If the username is found, it returns true. If the username is not found or an error occurs while
     * reading the file, it returns false.
     *
     * @param username The username to search for in the CSV file.
     * @return true if the username is found in the file, false otherwise.
     */
    private boolean isUsernameExists(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(profilesFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Saves the user's fitness goals to a CSV file.
     * If the data is successfully saved, a confirmation message is displayed.
     * In case of an error during the file operation, an error message is displayed.
     *
     * @param username      The username of the user whose goals are being saved.
     * @param age           The age of the user.
     * @param weight        The current weight of the user.
     * @param gender        The gender of the user (Male, Female, Other).
     * @param idealWeight   The ideal weight goal for the user.
     * @param idealExercise The ideal number of exercise hours per day for the user.
     * @param idealSleep    The ideal number of sleep hours per day for the user.
     */
    private void saveGoals(String username, int age, float weight, String gender, float idealWeight, int idealExercise, int idealSleep) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(goalsFile, true))) {
            writer.write(username + "," + age + "," + weight + "," + gender + "," + idealWeight + "," + idealExercise + "," + idealSleep);
            writer.newLine();
            showConfirmation("Goals Saved", "Your fitness goals have been successfully saved!");
        } catch (IOException e) {
            showError("Error", "An error occurred while saving your data.");
        }
    }

    /**
     * Displays an error alert with the specified title and message.
     *
     * @param title   The title of the error alert.
     * @param message The detailed error message to display in the alert.
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays an informational confirmation alert with the given title and message.
     *
     * @param title   The title of the confirmation alert.
     * @param message The message to display in the alert.
     */
    private void showConfirmation(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
