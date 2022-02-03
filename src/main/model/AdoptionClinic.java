package model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.pets.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class AdoptionClinic {
    private ArrayList<Pet> pets;

    // EFFECTS: constructs an AdoptionClinic with an empty list of pets
    public AdoptionClinic() {
        this.pets = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a pet to the list of pets
    public void addPet(Pet pet) {

    }

    // MODIFIES: this
    // REQUIRES: pet exists in pets
    // EFFECTS:  removes a pet from the list of pets
    public void removePet(Pet pet) {

    }

    // REQUIRES: pet exists in pets
    // EFFECTS:  returns the index of pet with name and breed in list of pets,
    public int findByNameBreed(String name, String breed) {
        return 0;
    }

    // EFFECTS: returns true if pet exists in list of pets
    public boolean checkPetExists(Pet pet) {
        return false;
    }

    // EFFECTS: returns a list of breeds for the specified animalType
    public ArrayList<String> fetchBreeds(String animalType) throws IOException {
        ArrayList<String> breeds = new ArrayList<>();

        File breedsDataDir = new File(Pet.fileLoc.getDataDir(animalType));
        String content = FileUtils.readFileToString(breedsDataDir, "utf-8");
        JSONObject breedsJson = new JSONObject(content);
        JSONArray breedsArray = breedsJson.getJSONArray("breeds");

        for (int i = 0; i < breedsArray.length(); i++) {
            JSONObject breedData = breedsArray.getJSONObject(i);
            breeds.add(breedData.getString("breedName"));
        }

        return breeds;
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
