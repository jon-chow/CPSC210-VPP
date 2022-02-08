package model.goodsandservices;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.configurables.FileLocations;
import model.pets.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import static ui.app.PixelPetGame.ANIMALS_IN_ADOPTION_CLINIC;

public class AdoptionClinic {
    private final FileLocations fileLoc = new FileLocations();
    private final ArrayList<String> animalTypes;

    // EFFECTS: constructs an AdoptionClinic with a list of animalType options
    public AdoptionClinic() {
        animalTypes = ANIMALS_IN_ADOPTION_CLINIC;
    }

    // REQUIRES: breed is a valid breed of animalType
    // EFFECTS: produces a Pet of the subclass animalType of a specified breed
    public Pet generatePet(String animalType, String breed) throws IOException {
        switch (animalType) {
            case "Dog": return new Dog(breed, breed);
            default: return new ExampleAnimal(breed, breed);
        }
    }

    // EFFECTS: returns a list of breeds for the specified animalType
    public ArrayList<String> fetchBreeds(String animalType) throws IOException {
        ArrayList<String> breeds = new ArrayList<>();

        File breedsDataDir = new File(fileLoc.getDataDir(animalType));
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
    public ArrayList<String> getAnimalTypes() {
        return animalTypes;
    }
}
