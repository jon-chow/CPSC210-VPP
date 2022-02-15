package model.configurables;

// class for storing all sprites/json files paths
// easier to configure directories this way
public class FileLocations {
    // GENERIC FOLDERS
    public static final String spritesDir = "data/sprites/";
    public static final String itemsSpriteDir = spritesDir + "items/";
    public static final String exampleSpritesDir = spritesDir + "example_animals/";
    public static final String dogSpritesDir = spritesDir + "dogs/";

    // SPECIFIC FILES
    public static final String dataDir = "data/";
    public static final String itemsDir = dataDir + "Items.json";
    public static final String exampleBreedsDir = dataDir + "petBreeds/ExampleAnimalBreeds.json";
    public static final String dogBreedsDir = dataDir + "petBreeds/DogBreeds.json";

    // GETTERS
    // GENERIC FOLDERS:
    public static String getSpritesDir(String dataKey) {
        switch (dataKey.toLowerCase().replaceAll("\\s+","")) {
            case "item": return itemsSpriteDir;
            case "dog": return dogSpritesDir;
            default: return exampleSpritesDir;
        }
    }

    // GETTERS
    // SPECIFIC FILES:
    public static String getDataDir(String dataKey) {
        switch (dataKey.toLowerCase().replaceAll("\\s+","")) {
            case "item": return itemsDir;
            case "dog": return dogBreedsDir;
            default: return exampleBreedsDir;
        }
    }
}
