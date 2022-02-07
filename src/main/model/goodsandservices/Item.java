package model.goodsandservices;

import model.configurables.FileLocations;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class Item {
    private final String dataKey = "Item";
    private final String itemsDir = FileLocations.getDataDir(dataKey);
    private String spritesDir = FileLocations.getSpritesDir(dataKey);
    private File itemsDataDir = new File(itemsDir);

    private JSONObject itemData;
    private String name;
    private String type;

    private int price = 0;
    private int happinessPoints = 0;
    private int hungerPoints = 0;
    private int thirstPoints = 0;
    private int healthPoints = 0;

    // EFFECTS: constructs an item with name and type
    public Item(String name, String type) throws IOException {
        this.name = name;
        this.type = type;
        this.fetchItemData();
        this.parseItemData();
    }

    // MODIFIES: this
    // REQUIRES: itemsDataDir exists
    // EFFECTS:  gathers data from itemsDataDir and stores it
    private void fetchItemData() throws IOException {
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
            String fileName = this.name.toLowerCase() + "_" + this.type.toLowerCase();
            this.spritesDir += fileName.replaceAll("\\s+","") + "/";

            int price = data.getInt("price");
            JSONArray carePoints = data.getJSONArray("carePoints");

            this.price = price;
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

    public int getPrice() {
        return price;
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
}
