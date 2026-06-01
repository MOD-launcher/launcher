package com.modlauncher.ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import com.modlauncher.ui.tabs.ModrinthTab;
import com.modlauncher.ui.tabs.TechnicTab;
import com.modlauncher.ui.tabs.FTBTab;

public class MainWindow {

    public Scene createScene() {
        BorderPane root = new BorderPane();
        
        // Create tab pane
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        // Create tabs for each mod source
        Tab modrinthTab = new Tab("Modrinth", new ModrinthTab().getContent());
        Tab technicTab = new Tab("Technic", new TechnicTab().getContent());
        Tab ftbTab = new Tab("FTB", new FTBTab().getContent());
        
        tabPane.getTabs().addAll(modrinthTab, technicTab, ftbTab);
        
        root.setCenter(tabPane);
        
        return new Scene(root, 1000, 700);
    }
}
