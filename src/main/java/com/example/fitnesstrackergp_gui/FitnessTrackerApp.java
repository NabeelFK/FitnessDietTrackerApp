package com.example.fitnesstrackergp_gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class FitnessTrackerApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FitnessTrackerApp.class.getResource("menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 375, 450);
        stage.setTitle("Fitness Tracker");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void init() {
        Parameters params = getParameters();
        ArgsManager.getInstance().setRawArgs(params.getRaw());
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            if (args.length != 3) {
                System.out.println("Usage of Fitness Tracker app: java Main <profile file> <goals file> <meal file>");
                System.exit(1);
            }
            launch(args);
        } else {
            launch();
        }
    }
}

