package com.example.fitnesstrackergp_gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
}