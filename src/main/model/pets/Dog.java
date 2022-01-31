package model.pets;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.json.*;

public class Dog extends Pet {
    private final File breedsDataDir = new File("data/petBreeds/DogBreeds.json");
    private JSONObject breedData;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Dog(String name, String breed) {
        super(name);
        super.setBreed(breed);
        super.setSpritesDir(super.getSpritesDir() + "dogs/");

        try {
            this.fetchBreedData();
            this.parseBreedData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void fetchBreedData() throws Exception {
        String content = FileUtils.readFileToString(breedsDataDir, "utf-8");
        JSONObject breedsJson = new JSONObject(content);
        JSONArray breedsArray = breedsJson.getJSONArray("breeds");

        for (int i = 0; i < breedsArray.length(); i++) {
            JSONObject breedData = breedsArray.getJSONObject(i);
            String breedName = breedData.getString("breedName");

            if (breedName.equals(super.getBreed())) {
                this.breedData = breedData;
                break;
            }
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private void parseBreedData() {
        JSONObject data = this.breedData;

        if (data != JSONObject.NULL) {
            JSONArray personalities = data.getJSONArray("personalities");
            JSONArray likes = data.getJSONArray("likes");
            JSONArray dislikes = data.getJSONArray("dislikes");

            this.setPersonalities(jsonArrayToStringList(personalities));
            this.setLikes(jsonArrayToStringList(likes));
            this.setDislikes(jsonArrayToStringList(dislikes));

            this.setSpritesDir(this.getSpritesDir() + data.getString("spriteFilesDir"));
        }
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    private ArrayList<String> jsonArrayToStringList(JSONArray jsonArr) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < jsonArr.length(); i++) {
            String str = jsonArr.getString(i);
            list.add(str);
        }
        return list;
    }
}
