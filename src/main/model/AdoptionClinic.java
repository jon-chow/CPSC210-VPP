package model;

import model.pets.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class AdoptionClinic {
    private ArrayList<Pet> pets;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public AdoptionClinic() {
        this.pets = new ArrayList<>();
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void addPet(Pet pet) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void removePet(Pet pet) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public int findByNameBreed(String name, String breed) {
        return 0;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public ArrayList<String> fetchDogBreeds() throws Exception {
        File breedsDataDir = new File("data/petBreeds/DogBreeds.json");
        String content = FileUtils.readFileToString(breedsDataDir, "utf-8");
        JSONObject breedsJson = new JSONObject(content);
        JSONArray breedsArray = breedsJson.getJSONArray("breeds");

        ArrayList<String> listOfBreeds = new ArrayList<>();
        for (int i = 0; i < breedsArray.length(); i++) {
            JSONObject breedData = breedsArray.getJSONObject(i);
            String breedName = breedData.getString("breedName");
            listOfBreeds.add(breedName);
        }

        return listOfBreeds;
    }

    // GETTERS
    public ArrayList<Pet> getPets() {
        return pets;
    }

    // SETTERS
    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
