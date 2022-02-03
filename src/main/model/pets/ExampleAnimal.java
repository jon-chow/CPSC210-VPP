package model.pets;

import java.io.File;
import java.util.List;

import model.configurables.RandomGenerator;

public class ExampleAnimal extends Pet {
    private final String dataKey = "Example";

    private List<String> allNoises;

    // EFFECTS: constructs an ExampleAnimal with name and breed
    public ExampleAnimal(String name, String breed) {
        super(name);
        super.setAnimalType(dataKey);
        super.setBreed(breed);
        super.setBreedsDataDir(new File(fileLoc.getDataDir(dataKey)));
        super.setSpritesDir(fileLoc.getSpritesDir(dataKey));

        allNoises = List.of("Noise 1", "Noise 2", "Noise 3");

        try {
            super.fetchBreedData();
            super.parseBreedData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: generates a random onomatopoeia that an ExampleAnimal would make
    public String makeNoise() {
        int randomIndex = RandomGenerator.randomNumberUpTo(allNoises.size());

        return allNoises.get(randomIndex);
    }
}
