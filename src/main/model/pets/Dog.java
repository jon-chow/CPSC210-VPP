package model.pets;

import model.configurables.FileLocations;
import model.configurables.RandomGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Dog extends Pet {
    private final String dataKey = "Dog";

    // EFFECTS: constructs a Dog with name and breed
    public Dog(String name, String breed) throws IOException {
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
