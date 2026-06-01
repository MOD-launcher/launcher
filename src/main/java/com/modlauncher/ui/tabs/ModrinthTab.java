package com.modlauncher.ui.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.modlauncher.api.ModrinthAPI;
import com.modlauncher.api.ModInfo;

import java.util.List;

public class ModrinthTab {
    
    private ModrinthAPI api = new ModrinthAPI();
    
    public VBox getContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        
        // Search bar
        HBox searchBox = new HBox(10);
        TextField searchField = new TextField();
        searchField.setPromptText("Search mods...");
        searchField.setPrefWidth(300);
        
        ComboBox<String> versionCombo = new ComboBox<>();
        versionCombo.getItems().addAll("1.20.1", "1.20", "1.19.2", "1.18.2", "1.17.1");
        versionCombo.setValue("1.20.1");
        
        Button searchBtn = new Button("Search");
        
        searchBox.getChildren().addAll(searchField, versionCombo, searchBtn);
        
        // Results area
        ListView<String> resultsList = new ListView<>();
        resultsList.setPrefHeight(400);
        
        // Search action
        searchBtn.setOnAction(e -> {
            String query = searchField.getText();
            String version = versionCombo.getValue();
            
            if (!query.isEmpty()) {
                List<ModInfo> mods = api.searchMods(query, version);
                resultsList.getItems().clear();
                
                for (ModInfo mod : mods) {
                    resultsList.getItems().add(mod.getName() + " - " + mod.getDescription());
                }
            }
        });
        
        root.getChildren().addAll(
            new Label("Modrinth Mods"),
            searchBox,
            new Label("Results:"),
            resultsList
        );
        
        return root;
    }
}
