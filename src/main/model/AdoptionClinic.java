package model;

import model.pets.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class AdoptionClinic {
    public AdoptionClinic() {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public ArrayList<String> fetchDogBreeds() throws Exception {
        File breedsDataDir = new File("data/petBreeds/DogBreeds.json");
        String content = FileUtils.readFileToString(breedsDataDir, "utf-8");
        JSONObject breedsJson = new JSONObject(content);
        JSONArray breedsArray = breedsJson.getJSONArray("breeds");

        ArrayList<String> dogBreeds = new ArrayList<>();
        for (int i = 0; i < breedsArray.length(); i++) {
            JSONObject breedData = breedsArray.getJSONObject(i);
            String breedName = breedData.getString("breedName");
            dogBreeds.add(breedName);
        }

        return dogBreeds;
    }
}
