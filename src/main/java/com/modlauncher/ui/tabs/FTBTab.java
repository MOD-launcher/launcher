package com.modlauncher.ui.tabs;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import com.modlauncher.api.FTBAPI;
import com.modlauncher.api.ModInfo;

import java.util.List;

public class FTBTab {
    
    private FTBAPI api = new FTBAPI();
    
    public VBox getContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));
        
        // Search bar
        HBox searchBox = new HBox(10);
        TextField searchField = new TextField();
        searchField.setPromptText("Search modpacks...");
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
                List<ModInfo> modpacks = api.searchModpacks(query);
                resultsList.getItems().clear();
                
                for (ModInfo pack : modpacks) {
                    resultsList.getItems().add(pack.getName() + " - " + pack.getDescription());
                }
            }
        });
        
        root.getChildren().addAll(
            new Label("FTB Modpacks"),
            searchBox,
            new Label("Results:"),
            resultsList
        );
        
        return root;
    }
}
