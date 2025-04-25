package com.example.fitnesstrackergp_gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

public class MenuController {

    @FXML
    private Button newAccountButton;

    @FXML
    private Button setGoalsButton;

    @FXML
    private Button logMealsButton;

    @FXML
    private Button viewHistoryButton;

    @FXML
    private Button exitButton;

    public void handleNewAccBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NewAccountApp.class.getResource("createProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 450);
        Stage stage = new Stage();
        stage.setTitle("Create New Account");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleSetGoalsBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("setGoals.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Set Goals");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @FXML
    public void handleLogMealsBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("logMeals.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Log Meals");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @FXML
    public void handleViewHistoryBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("viewHistory.fxml"));
        Stage stage = new Stage();
        stage.setTitle("View History");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

    @FXML
    public void handleDownloadCSVBtn(ActionEvent actionEvent) {

        String userHome = System.getProperty("user.home");
        File downloadDir = new File(userHome + "/Downloads/");

        try {
            copyToDownloads("meals.csv", downloadDir);
            copyToDownloads("profiles.csv", downloadDir);
            copyToDownloads("goals.csv", downloadDir);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("CSV files downloaded to your Downloads folder!");
            alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Failed to download CSV files.");
            alert.showAndWait();
        }
    }

    private void copyToDownloads(String name, File downloadDir) throws IOException {
        try (var inputStream = getClass().getResourceAsStream(name)) {
            if (inputStream == null) {
                System.out.println("Resource not found: " + name);
                return;
            }
            Files.copy(
                inputStream,
                new File(downloadDir, name).toPath(),
                StandardCopyOption.REPLACE_EXISTING
            );
        }
    }

    @FXML
    public void handleExitBtn(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Are you sure you wish to exit?");
        alert.setContentText("Thank you for using our Fitness Diet Tracker!");
        Optional <ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Stage stage = (Stage) exitButton.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    public void handleAboutBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("about.fxml"));
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.show();
    }

}