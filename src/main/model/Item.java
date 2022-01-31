package model;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;

public class Item {
    private final File itemsDataDir = new File("data/Items.json");
    private JSONObject itemData;

    private String spritesDir = "data/sprites/items/";
    private String name;
    private String type;

    private int happinessPoints = 0;
    private int hungerPoints = 0;
    private int thirstPoints = 0;
    private int healthPoints = 0;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
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

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void consume() {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
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

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
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
