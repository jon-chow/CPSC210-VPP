package model.pets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.configurables.FileLocations;
import model.configurables.RandomGenerator;

public class ExampleAnimal extends Pet {
    private final String dataKey = "ExampleAnimal";

    // EFFECTS: constructs an ExampleAnimal with name and breed
    public ExampleAnimal(String name, String breed) throws IOException {
        super(name);
        super.setAnimalType(dataKey);
        super.setBreed(breed);
        super.setPetDataDir(new File(FileLocations.getDataDir(dataKey)));
        super.setSpritesDir(FileLocations.getSpritesDir(dataKey));
        super.gatherPetData();
    }

    // EFFECTS: returns a random onomatopoeia from the noise list of the animal
    @Override
    public String makeNoise() {
        ArrayList<String> noises = super.getAllNoises();
        int randomIndex = RandomGenerator.randomNumberUpTo(noises.size());
        return noises.get(randomIndex);
    }
}
