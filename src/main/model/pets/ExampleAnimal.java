package model.pets;

import java.io.File;

public class ExampleAnimal extends Pet {
    private final String dataKey = "Example";

    // EFFECTS: constructs an ExampleAnimal with name and breed
    public ExampleAnimal(String name, String breed) {
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

    // EFFECTS: generates a random onomatopoeia that an ExampleAnimal would make
    public String makeNoise() {
        return "";
    }
}
