package model.pets;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import model.FileLocations;
import model.Item;

public abstract class Pet {
    public final FileLocations fileLoc = new FileLocations();

    private File breedsDataDir;
    private JSONObject breedData;
    private String spritesDir;

    private String name;
    private String animalType;
    private String breed;
    private String state;

    private ArrayList<String> personalities;
    private ArrayList<String> likes;
    private ArrayList<String> dislikes;

    private int age;
    private int happiness;
    private int hunger;
    private int thirst;
    private int health;
    private int numWaste;

    private final double likesMultiplier = 1.5;
    private final double dislikesMultiplier = 0.5;

    // EFFECTS: constructs a new pet with name
    public Pet(String name) {
        this.name = name;
        this.state = "Neutral";

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

    // EFFECTS: generates a random onomatopoeia that the animalType would make
    public abstract String makeNoise();

    // MODIFIES: this
    // EFFECTS:  consumes item, affecting pet's care levels
    public void consumeItem(Item item) {

    }

    // MODIFIES: this
    // REQUIRES: numWaste > 0
    // EFFECTS:  adds to numWaste
    public void createWaste(int count) {

    }

    // MODIFIES: this
    // REQUIRES: count <= numWaste
    // EFFECTS:  subtracts from numWaste
    public void removeWaste(int count) {

    }

    // EFFECTS: returns true if item is liked by pet
    public boolean checkIfLikes(Item item) {
        return false;
    }

    // EFFECTS: returns true if item is disliked by pet
    public boolean checkIfDislikes(Item item) {
        return false;
    }

    // EFFECTS: returns true if pet is dead
    public boolean checkIsDead() {
        return false;
    }

    // EFFECTS: returns care levels in a list,
    //          in the order [happiness, hunger, thirst, health]
    public ArrayList<Integer> alertCareStats() {
        return new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: happiness, hunger, thirst, health >= 0
    // EFFECTS:  decreases pet's corresponding care levels
    public void decrementCareLevels(int happiness, int hunger, int thirst, int health) {
//        this.happiness -= happiness;
//        this.hunger -= hunger;
//        this.thirst -= thirst;
//        this.health -= health;
    }

    // MODIFIES: this
    // REQUIRES: happiness, hunger, thirst, health >= 0
    // EFFECTS:  increases pet's corresponding care levels
    public void incrementCareLevels(int happiness, int hunger, int thirst, int health) {
//        this.happiness += happiness;
//        this.hunger += hunger;
//        this.thirst += thirst;
//        this.health += health;
    }

    // MODIFIES: this
    // EFFECTS:  adds a personality to pet's personalities
    public void addPersonality(String personality) {
        // this.personalities.add(personality);
    }

    // MODIFIES: this
    // REQUIRES: personality is a personality of pet
    // EFFECTS:  removes a personality from pet's personalities
    public void removePersonality(String personality) {

    }

    // MODIFIES: this
    // EFFECTS:  adds a like to pet's likes
    public void addLikes(String like) {

    }

    // MODIFIES: this
    // REQUIRES: like is a like of pet
    // EFFECTS:  removes a like from pet's likes
    public void removeLikes(String like) {

    }

    // MODIFIES: this
    // EFFECTS:  adds a dislike to pet's dislikes
    public void addDislikes(String dislike) {

    }

    // MODIFIES: this
    // REQUIRES: dislike is a dislike of pet
    // EFFECTS:  removes a dislike from pet's dislikes
    public void removeDislikes(String dislike) {

    }

    // MODIFIES: this
    // REQUIRES: breedsDataDir exists
    // EFFECTS:  gathers data from breedsDataDir and stores it
    public void fetchBreedData() throws Exception {
        String content = FileUtils.readFileToString(this.getBreedsDataDir(), "utf-8");
        JSONObject breedsJson = new JSONObject(content);
        JSONArray breedsArray = breedsJson.getJSONArray("breeds");

        for (int i = 0; i < breedsArray.length(); i++) {
            JSONObject breedData = breedsArray.getJSONObject(i);
            String breedName = breedData.getString("breedName");

            if (breedName.equals(this.getBreed())) {
                this.breedData = breedData;
                break;
            }
        }
    }

    // MODIFIES: this
    // REQUIRES: breedData exists
    // EFFECTS:  parses data from breedData and assigns
    //           corresponding variables the data contained
    public void parseBreedData() {
        JSONObject data = this.breedData;

        if (data != JSONObject.NULL) {
            JSONArray personalities = data.getJSONArray("personalities");
            JSONArray likes = data.getJSONArray("likes");
            JSONArray dislikes = data.getJSONArray("dislikes");

            this.setPersonalities(jsonArrayToStringList(personalities));
            this.setLikes(jsonArrayToStringList(likes));
            this.setDislikes(jsonArrayToStringList(dislikes));

            this.setSpritesDir(this.getSpritesDir() + data.getString("spriteFilesDir"));
        }
    }

    // EFFECTS: returns a list of String parsed from a JSONArray
    public ArrayList<String> jsonArrayToStringList(JSONArray jsonArr) {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < jsonArr.length(); i++) {
            String str = jsonArr.getString(i);
            list.add(str);
        }
        return list;
    }

    // GETTERS
    public File getBreedsDataDir() {
        return breedsDataDir;
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

    public String getState() {
        return this.state;
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
    public void setBreedsDataDir(File breedsDataDir) {
        this.breedsDataDir = breedsDataDir;
    }

    public void setSpritesDir(String spritesDir) {
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

    public void setState(String state) {
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
