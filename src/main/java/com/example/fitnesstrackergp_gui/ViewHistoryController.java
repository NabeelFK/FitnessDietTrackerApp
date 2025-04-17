package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
            showAlert("Profile not found. Please create an account.");
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
            showAlert("Profile not found. Please create an account.");
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
            showAlert("Profile not found. Please create an account.");
            return;
        }

        String meals = mealLogger.getMealHistory(username);
        if (meals != null && !meals.isEmpty()) {
            outputArea.setText(meals);
        } else {
            outputArea.setText("No meal history found.");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
