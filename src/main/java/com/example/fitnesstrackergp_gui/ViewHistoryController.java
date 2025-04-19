package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ViewHistoryController {

    @FXML
    private TextField usernameField;

    @FXML
    private Button profileHistoryBtn;

    @FXML
    private Button goalHistoryBtn;

    @FXML
    private Button mealHistoryBtn;

    @FXML
    private TextArea outputArea;

    private final ProfileManager profileManager = new ProfileManager();
    private final MealLogger mealLogger = new MealLogger();
    private final ViewGoals viewGoals = new ViewGoals();

    @FXML
    private void handleProfileHistory() {
        String username = usernameField.getText().trim();
        if (!profileManager.profileExists(username)) {
            showProfileNotFoundAlert();
            return;
        }

        UserProfile profile = profileManager.getProfile(username);
        if (profile != null) {
            outputArea.setText(profile.toString());
        } else {
            outputArea.setText("Unable to load profile.");
        }
    }

    @FXML
    private void handleGoalHistory() {
        String username = usernameField.getText().trim();
        if (!profileManager.profileExists(username)) {
            showProfileNotFoundAlert();
            return;
        }

        String goals = viewGoals.getGoalsSummary(username);
        if (goals != null && !goals.isEmpty()) {
            outputArea.setText(goals);
        } else {
            outputArea.setText("No goals found.");
        }
    }

    @FXML
    private void handleMealHistory() {
        String username = usernameField.getText().trim();
        if (!profileManager.profileExists(username)) {
            showProfileNotFoundAlert();
            return;
        }

        openAdciSelectionWindow();
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
            showAlert("Profile not found. Please create an account.");
            return;
        }

        String meals = mealLogger.getMealHistory(username, period);
        if (meals != null && !meals.isEmpty()) {
            outputArea.setText(meals);
        } else {
            outputArea.setText("No meals history found.");
        }
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
