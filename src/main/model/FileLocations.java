package model;

// Easier to configure directories this way
public class FileLocations {
    // GENERIC FOLDERS
    private final String spritesDir = "data/sprites/";
    private final String itemsSpriteDir = spritesDir + "items/";
    private final String dogSpritesDir = spritesDir + "dogs/";

    // SPECIFIC FILES
    private final String dataDir = "data/";
    private final String itemsDir = dataDir + "Items.json";
    private final String dogBreedsDir = dataDir + "petBreeds/DogBreeds.json";

    // GETTERS
    // GENERIC FOLDERS:
    public String getSpritesDir(String dataKey) {
        switch (dataKey.toLowerCase()) {
            case "item": return itemsSpriteDir;
            case "dog": return dogSpritesDir;
            default: return spritesDir;
        }
    }

    // GETTERS
    // SPECIFIC FILES:
    public String getDataDir(String dataKey) {
        switch (dataKey.toLowerCase()) {
            case "item": return itemsDir;
            case "dog": return dogBreedsDir;
            default: return dataDir;
        }
    }
}
