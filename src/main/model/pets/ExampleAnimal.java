package model.pets;

import java.io.File;
import java.util.ArrayList;

import model.configurables.RandomGenerator;

public class ExampleAnimal extends Pet implements Locomotion {
    private final String dataKey = "Example";

    // EFFECTS: constructs an ExampleAnimal with name and breed
    public ExampleAnimal(String name, String breed) {
        super(name);
        super.setAnimalType(dataKey);
        super.setBreed(breed);
        super.setPetDataDir(new File(fileLoc.getDataDir(dataKey)));
        super.setSpritesDir(fileLoc.getSpritesDir(dataKey));

        try {
            super.fetchPetData();
            super.parseBreedData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: returns the state of default movement
    @Override
    public String moving() {
        return "walking";
    }

    // EFFECTS: returns the state of idling
    @Override
    public String idling() {
        return "idling";
    }

    // EFFECTS: returns the state of eating
    @Override
    public String eating() {
        return "eating";
    }

    // EFFECTS: returns the state of playing
    @Override
    public String playing() {
        return "playing";
    }

    // EFFECTS: returns the state of jumping
    @Override
    public String jumping() {
        return "jumping";
    }

    // EFFECTS: returns the state of calling
    @Override
    public String calling() {
        return "calling";
    }

    // EFFECTS: returns the state of excreting waste
    @Override
    public String excretingWaste() {
        return "pooping";
    }

    // EFFECTS: returns a random onomatopoeia from the noise list of the animal
    @Override
    public String makeNoise() {
        ArrayList<String> noises = super.getAllNoises();

        int randomIndex = RandomGenerator.randomNumberUpTo(noises.size());

        return noises.get(randomIndex);
    }
}
