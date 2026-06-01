package com.modlauncher.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TechnicAPI {
    
    private static final Logger logger = LoggerFactory.getLogger(TechnicAPI.class);
    private static final String BASE_URL = "https://api.technicpack.net";
    private final OkHttpClient client = new OkHttpClient();
    
    /**
     * Search for mods on Technic
     */
    public List<ModInfo> searchMods(String query) {
        List<ModInfo> mods = new ArrayList<>();
        
        try {
            String url = BASE_URL + "/search?q=" + query;
            Request request = new Request.Builder().url(url).build();
            
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                JsonArray json = JsonParser.parseString(response.body().string()).getAsJsonArray();
                
                for (int i = 0; i < json.size(); i++) {
                    JsonObject modJson = json.get(i).getAsJsonObject();
                    ModInfo mod = new ModInfo(
                        modJson.get("name").getAsString(),
                        modJson.has("description") ? modJson.get("description").getAsString() : "No description",
                        modJson.get("slug").getAsString()
                    );
                    mods.add(mod);
                }
            }
        } catch (IOException e) {
            logger.error("Error searching Technic", e);
        }
        
        return mods;
    }
    
    /**
     * Get mod details
     */
    public ModInfo getModDetails(String modSlug) {
        try {
            String url = BASE_URL + "/mod/" + modSlug;
            Request request = new Request.Builder().url(url).build();
            
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                JsonObject json = JsonParser.parseString(response.body().string()).getAsJsonObject();
                return new ModInfo(
                    json.get("name").getAsString(),
                    json.has("description") ? json.get("description").getAsString() : "No description",
                    json.get("slug").getAsString()
                );
            }
        } catch (IOException e) {
            logger.error("Error fetching mod details", e);
        }
        
        return null;
    }
}
