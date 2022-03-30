package model.pets;

import model.configurables.FileLocations;
import model.configurables.RandomGenerator;
import model.persistence.Writable;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import model.goodsandservices.Item;

import static ui.app.PixelPetGame.*;

// represents an abstract pet
public abstract class Pet implements Writable {
    public final double likesMultiplier = 1.1;
    public final double dislikesMultiplier = 0.5;

    private File petDataDir;
    private String spritesDir;

    private String name;
    private String animalType;
    private String breed;
    private State state;

    private ArrayList<String> allNoises;
    private ArrayList<String> personalities;
    private ArrayList<String> likes;
    private ArrayList<String> dislikes;
    private ArrayList<String> cannotHaves;

    private int age;
    private int happiness;
    private int hunger;
    private int thirst;
    private int health;
    private int numWaste;

    protected final FileLocations fileLoc = new FileLocations();
    protected final RandomGenerator rng = new RandomGenerator();

    // EFFECTS: constructs a new pet with name
    public Pet(String name) {
        this.name = name;
        this.state = State.IDLING;

        this.personalities = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();

        this.age = 0;
        this.happiness = 100;
        this.hunger = 100;
        this.thirst = 100;
        this.health = 100;
        this.numWaste = 0;
    }

    public abstract String makeNoise();

    // MODIFIES: this
    // EFFECTS:  changes the pet's state depending on the item type
    //           and consumes item, affecting care levels
    public void consumeItem(Item item) {
        if (item.getType().equals("Toy")) {
            setState(State.PLAYING);
        } else if (item.getType().equals("Food")) {
            setState(State.EATING);
        }

        if (checkIfCannotHave(item)) {
            setHealth(-999);
        } else {
            ArrayList<Integer> newGains = factorGainMultipliers(item);
            incrementCareLevels(newGains.get(0), newGains.get(1), newGains.get(2), newGains.get(3));
        }
    }

    // EFFECTS:  returns the modified gain values of an item as a list after
    //           taking into account the pet's likes/dislikes/cannotHaves
    //           formatted as [happiness, hunger, thirst, health] gains
    private ArrayList<Integer> factorGainMultipliers(Item item) {
        int happinessGain = item.getHappinessPoints();
        int hungerGain = item.getHungerPoints();
        int thirstGain = item.getThirstPoints();
        int healthGain = item.getHealthPoints();

        if (checkIfLikes(item)) {
            happinessGain *= likesMultiplier;
            hungerGain *= likesMultiplier;
            thirstGain *= likesMultiplier;
            healthGain *= likesMultiplier;
        } else if (checkIfDislikes(item)) {
            happinessGain *= dislikesMultiplier;
            hungerGain *= dislikesMultiplier;
            thirstGain *= dislikesMultiplier;
            healthGain *= dislikesMultiplier;
        }

        return new ArrayList<>(Arrays.asList(happinessGain, hungerGain, thirstGain, healthGain));
    }


    // MODIFIES: this
    // REQUIRES: numWaste > 0
    // EFFECTS:  adds count to numWaste
    public void createWaste(int count) {
        setNumWaste(getNumWaste() + count);
    }

    // MODIFIES: this
    // REQUIRES: numWaste >= count
    // EFFECTS:  subtracts count from numWaste
    public void removeWaste(int count) {
        setNumWaste(getNumWaste() - count);
    }

    // EFFECTS: returns true if item is liked by pet
    public boolean checkIfLikes(Item item) {
        return likes.contains(item.getName());
    }

    // EFFECTS: returns true if item is disliked by pet
    public boolean checkIfDislikes(Item item) {
        return dislikes.contains(item.getName());
    }

    // EFFECTS: returns true if item cannot be given to the pet
    public boolean checkIfCannotHave(Item item) {
        return cannotHaves.contains(item.getName());
    }

    // EFFECTS: returns true if pet is dead
    public boolean checkIsDead() {
        return (happiness <= 0 || hunger <= 0 || thirst <= 0 || health <= 0);
    }

    // EFFECTS: returns the current care levels in a list,
    //          in the order [happiness, hunger, thirst, health]
    public ArrayList<Integer> alertCareStats() {
        return new ArrayList<>(Arrays.asList(happiness, hunger, thirst, health));
    }

    // MODIFIES: this
    // REQUIRES: happiness, hunger, thirst, health >= 0
    // EFFECTS:  decreases pet's corresponding care levels
    public void decrementCareLevels(int happiness, int hunger, int thirst, int health) {
        this.happiness -= happiness;
        if (this.happiness < 0) {
            this.happiness = 0;
        }

        this.hunger -= hunger;
        if (this.hunger < 0) {
            this.hunger = 0;
        }

        this.health -= health;
        if (this.health < 0) {
            this.health = 0;
        }

        this.thirst -= thirst;
        if (this.thirst < 0) {
            this.thirst = 0;
        }
    }

    // MODIFIES: this
    // REQUIRES: happiness, hunger, thirst, health >= 0
    // EFFECTS:  increases pet's corresponding care levels
    public void incrementCareLevels(int happiness, int hunger, int thirst, int health) {
        this.happiness += happiness;
        if (this.happiness > MAX_HAPPINESS) {
            this.happiness = MAX_HAPPINESS;
        }

        this.hunger += hunger;
        if (this.hunger > MAX_HUNGER) {
            this.hunger = MAX_HUNGER;
        }

        this.health += health;
        if (this.health > MAX_HEALTH) {
            this.health = MAX_HEALTH;
        }

        this.thirst += thirst;
        if (this.thirst > MAX_THIRST) {
            this.thirst = MAX_THIRST;
        }
    }

    // MODIFIES: this
    // EFFECTS:  adds a personality to pet's personalities
    public void addPersonality(String personality) {
        personalities.add(personality);
    }

    // MODIFIES: this
    // EFFECTS:  removes a personality from pet's personalities
    public void removePersonality(String personality) {
        personalities.remove(personality);
    }

    // MODIFIES: this
    // EFFECTS:  adds a like to pet's likes
    public void addLikes(String like) {
        likes.add(like);
    }

    // MODIFIES: this
    // EFFECTS:  removes a like from pet's likes
    public void removeLikes(String like) {
        likes.remove(like);
    }

    // MODIFIES: this
    // EFFECTS:  adds a dislike to pet's dislikes
    public void addDislikes(String dislike) {
        dislikes.add(dislike);
    }

    // MODIFIES: this
    // EFFECTS:  removes a dislike from pet's dislikes
    public void removeDislikes(String dislike) {
        dislikes.remove(dislike);
    }

    // MODIFIES: this
    // EFFECTS:  adds a cannotHave to pet's cannotHaves
    public void addCannotHaves(String cannotHave) {
        cannotHaves.add(cannotHave);
    }

    // MODIFIES: this
    // EFFECTS:  removes a cannotHave from pet's cannotHaves
    public void removeCannotHaves(String cannotHave) {
        cannotHaves.remove(cannotHave);
    }

    // MODIFIES: this
    // REQUIRES: petDataDir exists
    // EFFECTS:  gathers data from petDataDir, parses it, and then stores it
    protected void gatherPetData() throws IOException {
        String content = FileUtils.readFileToString(this.getPetDataDir(), "utf-8");
        JSONObject data = new JSONObject(content);

        JSONArray noisesArray = data.getJSONArray("noises");
        allNoises = new ArrayList<>();
        for (int i = 0; i < noisesArray.length(); i++) {
            allNoises.add(noisesArray.getString(i));
        }

        JSONArray breedsArray = data.getJSONArray("breeds");
        for (int i = 0; i < breedsArray.length(); i++) {
            JSONObject breedData = breedsArray.getJSONObject(i);
            String breedName = breedData.getString("breedName");

            if (breedName.equals(this.getBreed())) {
                parseBreedData(breedData);
                // break; // commented out for code coverage...
            }
        }
    }

    // MODIFIES: this
    // REQUIRES: data contains breed data and is not null
    // EFFECTS:  parses the data and assigns corresponding variables the data contained
    protected void parseBreedData(JSONObject data) {
        JSONArray personalities = data.getJSONArray("personalities");
        JSONArray likes = data.getJSONArray("likes");
        JSONArray dislikes = data.getJSONArray("dislikes");
        JSONArray cannotHaves = data.getJSONArray("cannotHaves");

        this.setPersonalities(jsonArrayToStringList(personalities));
        this.setLikes(jsonArrayToStringList(likes));
        this.setDislikes(jsonArrayToStringList(dislikes));
        this.setCannotHaves(jsonArrayToStringList(cannotHaves));

        this.setSpritesDir(this.getSpritesDir() + data.getString("spriteFilesDir") + "/");
    }

    // EFFECTS: returns a list of String parsed from a JSONArray
    private ArrayList<String> jsonArrayToStringList(JSONArray jsonArr) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < jsonArr.length(); i++) {
            String str = jsonArr.getString(i);
            list.add(str);
        }
        return list;
    }

    // EFFECTS: converts all pet data to a JSONObject and returns it
    @Override
    public JSONObject toJsonObj() {
        JSONObject itemDetails = new JSONObject();

        itemDetails.put("name", name);
        itemDetails.put("type", animalType);
        itemDetails.put("breed", breed);
        itemDetails.put("state", state);

        itemDetails.put("age", age);
        itemDetails.put("happiness", happiness);
        itemDetails.put("hunger", hunger);
        itemDetails.put("thirst", thirst);
        itemDetails.put("health", health);
        itemDetails.put("numWaste", numWaste);

//        itemDetails.put("likes", arrayListStringToJson(likes));
//        itemDetails.put("dislikes", arrayListStringToJson(dislikes));
//        itemDetails.put("personalities", arrayListStringToJson(personalities));
//        itemDetails.put("cannotHaves", arrayListStringToJson(cannotHaves));

        return itemDetails;
    }

    // GETTERS
    public File getPetDataDir() {
        return petDataDir;
    }

    public String getSpritesDir() {
        return spritesDir;
    }

    public String getName() {
        return this.name;
    }

    public String getAnimalType() {
        return this.animalType;
    }

    public String getBreed() {
        return this.breed;
    }

    public State getState() {
        return this.state;
    }

    public ArrayList<String> getAllNoises() {
        return allNoises;
    }

    public ArrayList<String> getPersonalities() {
        return this.personalities;
    }

    public ArrayList<String> getLikes() {
        return this.likes;
    }

    public ArrayList<String> getDislikes() {
        return this.dislikes;
    }

    public ArrayList<String> getCannotHaves() {
        return this.cannotHaves;
    }

    public int getAge() {
        return this.age;
    }

    public int getHappiness() {
        return this.happiness;
    }

    public int getHunger() {
        return this.hunger;
    }

    public int getThirst() {
        return this.thirst;
    }

    public int getHealth() {
        return this.health;
    }

    public int getNumWaste() {
        return numWaste;
    }

    // SETTERS
    protected void setPetDataDir(File petDataDir) {
        this.petDataDir = petDataDir;
    }

    protected void setSpritesDir(String spritesDir) {
        this.spritesDir = spritesDir;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setPersonalities(ArrayList<String> personalities) {
        this.personalities = personalities;
    }

    public void setLikes(ArrayList<String> likes) {
        this.likes = likes;
    }

    public void setDislikes(ArrayList<String> dislikes) {
        this.dislikes = dislikes;
    }

    public void setCannotHaves(ArrayList<String> cannotHave) {
        this.cannotHaves = cannotHave;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setNumWaste(int numWaste) {
        this.numWaste = numWaste;
    }
}
