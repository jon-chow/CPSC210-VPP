package model.pets;

import java.io.File;

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
            String spriteDir = data.getString("spritesDirectory");

            for (int i = 0; i < personalities.length(); i++) {
                String personality = personalities.getString(i);
                super.addPersonality(personality);
            }

            for (int i = 0; i < likes.length(); i++) {
                String like = likes.getString(i);
                super.addLikes(like);
            }

            for (int i = 0; i < dislikes.length(); i++) {
                String dislike = dislikes.getString(i);
                super.addDislikes(dislike);
            }

            super.setSpritesDir(spriteDir);
        }
    }
}
