package model.persistence;

import model.pets.Dog;
import model.pets.ExampleAnimal;
import org.json.JSONArray;
import org.json.JSONObject;
import model.configurables.FileLocations;
import org.apache.commons.io.FileUtils;
import java.io.File;

import java.io.IOException;
import java.util.ArrayList;

import model.exceptions.CannotFindSessionIdException;

import ui.app.PixelPetGame;
import model.Player;
import model.pets.Pet;
import model.pets.State;
import model.goodsandservices.Shop;
import model.goodsandservices.Item;

import static model.persistence.ConverterJsonArrays.*;
import static model.persistence.ConverterJsonArrays.jsonToArrayListItem;

// reads in data from the Persistence.json to the game
public class PersistenceReader {
    private final FileLocations fileLoc = new FileLocations();
    private final File persistenceFile = new File(fileLoc.persistenceDir);

    private String content;
    private JSONObject savesData;
    private JSONArray sessionsArray;

    int id;
    String saveTime;
    JSONObject data;

    // EFFECTS: constructs a new persistence reader
    public PersistenceReader(PixelPetGame game, int sessionId) throws IOException, CannotFindSessionIdException {
        content = FileUtils.readFileToString(persistenceFile, "utf-8");
        savesData = new JSONObject(content);
        sessionsArray = savesData.getJSONArray("sessions");
        load(game, sessionId);
    }

    // EFFECTS: loads a previous game session
    private void load(PixelPetGame game, int sessionId) throws CannotFindSessionIdException, IOException {
        if (!findBySessionID(sessionId)) {
            throw new CannotFindSessionIdException("Session ID was not found in the database!");
        }

        int secondsPassed = data.getInt("secondsPassed");
        int ticksPassed = data.getInt("ticksPassed");

        Player player = fetchPlayerData();
        Pet pet = fetchPetData();
        Shop shop = fetchShopData();

        ArrayList<Shop> shops = new ArrayList<>();
        shops.add(shop);

        game.setSessionId(sessionId);
        game.setPlayer(player);
        game.setPet(pet);
        game.setShops(shops);
        game.setTicksPassed(ticksPassed);
        game.setSecondsPassed(secondsPassed);
    }

    // EFFECTS: searches for a slot in the persistence data that contains the specified sessionId
    //          returns false if no slot with sessionId is found
    private boolean findBySessionID(int sessionId) {
        boolean hasFound = false;

        for (int i = 0; i < sessionsArray.length(); i++) {
            JSONObject session = sessionsArray.getJSONObject(i);
            int id = session.getInt("id");

            if (id == sessionId) {
                this.id = id;
                saveTime = session.getString("saveTime");
                data = session.getJSONObject("data");
                hasFound = true;
            }
        }

        return hasFound;
    }

    // EFFECTS: returns a Player from the data in Persistence.json
    private Player fetchPlayerData() throws IOException {
        JSONObject playerObject = data.getJSONObject("player");

        String playerName = playerObject.getString("name");
        int playerMoney = playerObject.getInt("money");
        JSONObject playerInventory = playerObject.getJSONObject("inventory");
        JSONArray inventoryItems = playerInventory.getJSONArray("items");
        JSONArray inventoryQuantities = playerInventory.getJSONArray("quantities");

        Player plr = new Player();
        plr.setPlayerName(playerName);
        plr.setMoney(playerMoney);
        plr.setInventory(jsonToArrayListItem(inventoryItems));
        plr.setInventoryQuantity(jsonToArrayListInt(inventoryQuantities));

        return plr;
    }

    // EFFECTS: returns a Pet from the data in Persistence.json
    private Pet fetchPetData() throws IOException {
        JSONObject petObject = data.getJSONObject("pet");

        String petName = petObject.getString("name");
        String petType = petObject.getString("type");
        String petBreed = petObject.getString("breed");
        State petState = State.valueOf(petObject.getString("state"));

        int petAge = petObject.getInt("age");
        int petHappiness = petObject.getInt("happiness");
        int petHunger = petObject.getInt("hunger");
        int petThirst = petObject.getInt("thirst");
        int petHealth = petObject.getInt("health");
        int petNumWaste = petObject.getInt("numWaste");

//        ArrayList<String> petAllNoises = jsonToArrayListString(petObject.getJSONArray("allNoises"));
//        ArrayList<String> likes = jsonToArrayListString(petObject.getJSONArray("likes"));
//        ArrayList<String> dislikes = jsonToArrayListString(petObject.getJSONArray("dislikes"));
//        ArrayList<String> personalities = jsonToArrayListString(petObject.getJSONArray("personalities"));
//        ArrayList<String> cannotHaves = jsonToArrayListString(petObject.getJSONArray("cannotHaves"));

        Pet pet = buildPet(petType, petBreed);
        pet.setName(petName);
        pet.setState(petState);

        pet.setAge(petAge);
        pet.setHappiness(petHappiness);
        pet.setHunger(petHunger);
        pet.setThirst(petThirst);
        pet.setHealth(petHealth);
        pet.setNumWaste(petNumWaste);

        return pet;
    }

    // EFFECTS: creates a subclass of pet based on specified type and breed and returns it
    private Pet buildPet(String type, String breed) throws IOException {
        Pet pet;

        switch (type) {
            case "Dog": pet = new Dog(breed, breed);
            break;
            default: pet = new ExampleAnimal(breed, breed);
            break;
        }

        return pet;
    }

    // EFFECTS: returns a Shop from the data in Persistence.json
    private Shop fetchShopData() throws IOException {
        JSONObject shopObject = data.getJSONObject("shop");

        String shopName = shopObject.getString("name");
        JSONArray shopItemsJson = shopObject.getJSONArray("items");
        JSONArray shopPricesJson = shopObject.getJSONArray("prices");
        JSONArray shopQuantitiesJson = shopObject.getJSONArray("quantities");

        ArrayList<Item> items = jsonToArrayListItem(shopItemsJson);
        ArrayList<Integer> prices = jsonToArrayListInt(shopPricesJson);
        ArrayList<Integer> quantities = jsonToArrayListInt(shopQuantitiesJson);

        Shop shop = new Shop(shopName);
        shop.setShopItems(items);
        shop.setPriceOfItems(prices);
        shop.setQuantityInStock(quantities);

        return shop;
    }
}
