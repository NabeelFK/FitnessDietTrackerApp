package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
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

        String meals = mealLogger.getMealHistory(username);
        if (meals != null && !meals.isEmpty()) {
            outputArea.setText(meals);
        } else {
            outputArea.setText("No meal history found.");
        }
    }

    public void showProfileNotFoundAlert() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Profile not found");
        alert.setHeaderText("That profile doesn't exist");
        alert.setContentText("Would you like to create a new account?");
        ButtonType createButton = new ButtonType("Create New Account");
        ButtonType cancelButton = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
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
