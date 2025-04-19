package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ViewHistoryController {

    @FXML
    private TextField usernameField;

    @FXML
    private TextArea outputArea;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;

    @FXML
    private RadioButton otherRadio;


    private final ProfileManager profileManager = new ProfileManager();
    private final MealLogger mealLogger = new MealLogger();
    private final ViewGoals viewGoals = new ViewGoals();

    @FXML
    private void handleProfileHistory() {
        String username = usernameField.getText().trim();
        Gender gender = getSelectedGender();

        if (username.isEmpty() || gender == null) {
            showAlert("Please enter a username and select a gender.");
            clearForm();
            return;
        }

        List<String> matches = FileHandler.readAllMatches("src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv", username, gender.toChar());
        if (matches.isEmpty()) {
            showProfileNotFoundAlert();
            clearForm();
            return;
        }

        UserProfile profile = UserProfile.fromCSV(matches.get(0));
        outputArea.setText(profile.toString());
        clearForm();
    }

    @FXML
    private void handleGoalHistory() {
        String username = usernameField.getText().trim();
        Gender gender = getSelectedGender();

        if (username.isEmpty() || gender == null) {
            showAlert("Please enter a username and select a gender.");
            clearForm();
            return;
        }

        List<String> matches = FileHandler.readAllMatches("src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv", username, gender.toChar());
        if (matches.isEmpty()) {
            showProfileNotFoundAlert();
            clearForm();
            return;
        }

        String goals = viewGoals.getGoalsSummary(username);
        outputArea.setText((goals != null && !goals.isEmpty()) ? goals : "No goals found.");
        clearForm();
    }

    private Gender getSelectedGender() {
        if (maleRadio.isSelected()) return Gender.MALE;
        if (femaleRadio.isSelected()) return Gender.FEMALE;
        if (otherRadio.isSelected()) return Gender.OTHER;
        return null;
    }

    @FXML
    private void handleMealHistory() {
        String username = usernameField.getText().trim();
        Gender gender = getSelectedGender();

        if (username.isEmpty() || gender == null) {
            showAlert("Please enter a username and select a gender.");
            clearForm();
            return;
        }

        List<String> matches = FileHandler.readAllMatches("src/main/resources/com/example/fitnesstrackergp_gui/profiles.csv", username, gender.toChar());
        if (matches.isEmpty()) {
            showProfileNotFoundAlert();
            clearForm();
            return;
        }

        openAdciSelectionWindow();
        clearForm(); // clear after opening
    }


    private void openAdciSelectionWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/fitnesstrackergp_gui/adciSelection.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Select Average Daily Calorie Intake Period");

            AdciSelectionController controller = loader.getController();
            controller.setViewHistoryController(this);

            stage.show();
        } catch (Exception e) {
            showAlert("Error opening time period selection window.");
        }
    }

    public void updateMealHistory(String period) {
        String username = usernameField.getText().trim();
        if (!profileManager.profileExists(username)) {
            showProfileNotFoundAlert();
            return;
        }

        String meals = mealLogger.getMealHistory(username, period);
        if (meals != null && !meals.isEmpty()) {
            outputArea.setText(meals);
        } else {
            outputArea.setText("No meals history found.");
        }
    }

    private void clearForm() {
        usernameField.clear();
        genderGroup.selectToggle(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
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
        alert.getButtonTypes().setAll(createButton,cancelButton);
        Optional <ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get()==createButton) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("createProfile.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.setTitle("Create New Account");
                stage.show();
            }
            catch (IOException e) {
                System.out.println("Something went wrong.");
            }
        }

    }
}
