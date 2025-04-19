package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
}
