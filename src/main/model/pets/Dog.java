package model.pets;

import java.io.File;

public class Dog extends Pet {
    private final String dataKey = "Dog";

    // EFFECTS: constructs a Dog with name and breed
    public Dog(String name, String breed) {
        super(name);
        super.setAnimalType(dataKey);
        super.setBreed(breed);
        super.setBreedsDataDir(new File(fileLoc.getDataDir(dataKey)));
        super.setSpritesDir(fileLoc.getSpritesDir(dataKey));

        try {
            super.fetchBreedData();
            super.parseBreedData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: generates a random onomatopoeia that a Dog would make
    public String makeNoise() {
        return "";
    }
}
