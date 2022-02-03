package model;

import model.configurables.FileLocations;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class Item {
    private static final FileLocations fileLoc = new FileLocations();

    private final String dataKey = "Item";
    private final String itemsDir = fileLoc.getDataDir(dataKey);
    private String spritesDir = fileLoc.getSpritesDir(dataKey);
    private File itemsDataDir = new File(itemsDir);

    private JSONObject itemData;
    private String name;
    private String type;

    private int happinessPoints = 0;
    private int hungerPoints = 0;
    private int thirstPoints = 0;
    private int healthPoints = 0;

    // EFFECTS: constructs an item with name and type
    public Item(String name, String type) {
        this.name = name;
        this.type = type;

        try {
            this.fetchItemData();
            this.parseItemData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // REQUIRES: itemsDataDir exists
    // EFFECTS:  gathers data from itemsDataDir and stores it
    private void fetchItemData() throws Exception {
        String content = FileUtils.readFileToString(itemsDataDir, "utf-8");
        JSONObject itemsJson = new JSONObject(content);
        JSONArray itemsArray = itemsJson.getJSONArray("items");

        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject itemData = itemsArray.getJSONObject(i);
            String itemName = itemData.getString("itemName");
            String itemType = itemData.getString("itemType");

            if (itemName.equals(this.name) && itemType.equals(this.type)) {
                this.itemData = itemData;
                break;
            }
        }
    }

    // MODIFIES: this
    // REQUIRES: itemsData exists
    // EFFECTS:  parses data from itemsData and assigns
    //           corresponding variables the data contained
    private void parseItemData() {
        JSONObject data = this.itemData;

        if (data != JSONObject.NULL) {
            this.spritesDir += this.name.toLowerCase() + "_" + this.type.toLowerCase() + "/";

            JSONArray carePoints = data.getJSONArray("carePoints");

            this.happinessPoints = carePoints.getInt(0);
            this.hungerPoints  = carePoints.getInt(1);
            this.thirstPoints = carePoints.getInt(2);
            this.healthPoints = carePoints.getInt(3);
        }
    }

    // GETTERS
    public String getSpritesDir() {
        return spritesDir;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getHappinessPoints() {
        return happinessPoints;
    }

    public int getThirstPoints() {
        return thirstPoints;
    }

    public int getHungerPoints() {
        return hungerPoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHappinessPoints(int happinessPoints) {
        this.happinessPoints = happinessPoints;
    }

    public void setThirstPoints(int thirstPoints) {
        this.thirstPoints = thirstPoints;
    }

    public void setHungerPoints(int hungerPoints) {
        this.hungerPoints = hungerPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
