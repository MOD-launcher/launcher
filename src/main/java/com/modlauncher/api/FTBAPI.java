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

public class FTBAPI {
    
    private static final Logger logger = LoggerFactory.getLogger(FTBAPI.class);
    private static final String BASE_URL = "https://api.curseforge.com";
    private final OkHttpClient client = new OkHttpClient();
    
    /**
     * Search for modpacks on FTB
     */
    public List<ModInfo> searchModpacks(String query) {
        List<ModInfo> modpacks = new ArrayList<>();
        
        try {
            // FTB uses similar endpoint structure, adapting for their API
            String url = "https://api.ftb.ninja/v1/modpacks?search=" + query;
            Request request = new Request.Builder().url(url).build();
            
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                JsonArray json = JsonParser.parseString(response.body().string()).getAsJsonArray();
                
                for (int i = 0; i < json.size(); i++) {
                    JsonObject packJson = json.get(i).getAsJsonObject();
                    ModInfo pack = new ModInfo(
                        packJson.get("name").getAsString(),
                        packJson.has("synopsis") ? packJson.get("synopsis").getAsString() : "No description",
                        String.valueOf(packJson.get("id").getAsInt())
                    );
                    modpacks.add(pack);
                }
            }
        } catch (IOException e) {
            logger.error("Error searching FTB", e);
        }
        
        return modpacks;
    }
    
    /**
     * Get modpack details
     */
    public ModInfo getModpackDetails(String modpackId) {
        try {
            String url = "https://api.ftb.ninja/v1/modpacks/" + modpackId;
            Request request = new Request.Builder().url(url).build();
            
            Response response = client.newCall(request).execute();
            if (response.isSuccessful() && response.body() != null) {
                JsonObject json = JsonParser.parseString(response.body().string()).getAsJsonObject();
                return new ModInfo(
                    json.get("name").getAsString(),
                    json.has("synopsis") ? json.get("synopsis").getAsString() : "No description",
                    String.valueOf(json.get("id").getAsInt())
                );
            }
        } catch (IOException e) {
            logger.error("Error fetching modpack details", e);
        }
        
        return null;
    }
}
