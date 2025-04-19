package com.example.fitnesstrackergp_gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class AdciSelectionController {

    @FXML
    private ViewHistoryController viewHistoryController;

    @FXML
    private Button sevenDaysButton;

    @FXML
    private Button thirtyDaysButton;

    @FXML
    private Button allTimeButton;

    public void setViewHistoryController(ViewHistoryController viewHistoryController) {
        this.viewHistoryController = viewHistoryController;
    }

    @FXML
    private void handleSevenDays() {
        viewHistoryController.updateMealHistory("7");
        closeWindow();
    }

    @FXML
    private void handleThirtyDays() {
        viewHistoryController.updateMealHistory("30");
        closeWindow();
    }

    @FXML
    private void handleAllTime() {
        viewHistoryController.updateMealHistory("0");
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) sevenDaysButton.getScene().getWindow();
        stage.close();
    }
}
