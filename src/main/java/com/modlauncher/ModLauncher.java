package com.modlauncher;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import com.modlauncher.ui.MainWindow;

public class ModLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            MainWindow mainWindow = new MainWindow();
            Scene scene = mainWindow.createScene();
            
            primaryStage.setTitle("MOD Launcher");
            primaryStage.setScene(scene);
            primaryStage.setWidth(1000);
            primaryStage.setHeight(700);
            primaryStage.show();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
