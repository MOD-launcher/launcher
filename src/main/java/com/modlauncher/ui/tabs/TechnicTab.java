package com.modlauncher.ui.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.modlauncher.api.TechnicAPI;
import com.modlauncher.api.ModInfo;

import java.util.List;

public class TechnicTab {
    
    private TechnicAPI api = new TechnicAPI();
    
    public VBox getContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        
        // Search bar
        HBox searchBox = new HBox(10);
        TextField searchField = new TextField();
        searchField.setPromptText("Search mods...");
        searchField.setPrefWidth(400);
        
        Button searchBtn = new Button("Search");
        
        searchBox.getChildren().addAll(searchField, searchBtn);
        
        // Results area
        ListView<String> resultsList = new ListView<>();
        resultsList.setPrefHeight(400);
        
        // Search action
        searchBtn.setOnAction(e -> {
            String query = searchField.getText();
            
            if (!query.isEmpty()) {
                List<ModInfo> mods = api.searchMods(query);
                resultsList.getItems().clear();
                
                for (ModInfo mod : mods) {
                    resultsList.getItems().add(mod.getName() + " - " + mod.getDescription());
                }
            }
        });
        
        root.getChildren().addAll(
            new Label("Technic Mods"),
            searchBox,
            new Label("Results:"),
            resultsList
        );
        
        return root;
    }
}
