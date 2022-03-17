package model.configurables;

// class for storing all sprites/json files paths
// easier to configure directories this way
public class FileLocations {
    // DATA FOLDER
    public static final String dataDir = "data/";
    public static final String assetsDir = dataDir + "assets/";
    public static final String backgroundsDir = assetsDir + "backgrounds/";
    public static final String iconsDir = assetsDir + "icons/";
    public static final String spritesDir = assetsDir + "sprites/";

    // SPECIFIC FILES
    public static final String persistenceDir = dataDir + "persistence/Persistence.json";
    public static final String itemsDir = dataDir + "Items.json";
    public static final String exampleBreedsDir = dataDir + "petBreeds/ExampleAnimalBreeds.json";
    public static final String dogBreedsDir = dataDir + "petBreeds/DogBreeds.json";

    // GUI FILES
    public static final String gameIconDir = iconsDir + "PixelPet.png";
//    public static final String gameBGMDir = assetsDir + "";

    public static final String itemsSpriteDir = spritesDir + "items/";
    public static final String exampleSpritesDir = spritesDir + "example_animals/";
    public static final String dogSpritesDir = spritesDir + "dogs/";

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
