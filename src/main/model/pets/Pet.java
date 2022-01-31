package model.pets;

import model.Item;

import java.util.ArrayList;

public class Pet {
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

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Pet(String name) {
        this.name = name;
        this.animalType = "Unknown";
        this.breed = "Unknown";
        this.state = "Neutral";

        this.personalities = new ArrayList<>();
        this.likes = new ArrayList<>();
        this.dislikes = new ArrayList<>();

        this.age = 0;
        happiness = 100;
        hunger = 100;
        thirst = 100;
        health = 100;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void consumeItem(Item item) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void makeNoise() {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void natureCalls() {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public boolean checkIfLikes(Item item) {
        return false;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public boolean checkIsDead() {
        return false;
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void alertCareStats() {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void addPersonality(String personality) {
        // this.personalities.add(personality);
    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void removePersonality(String personality) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void addLikes(String like) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void removeLikes(String like) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void addDislikes(String dislike) {

    }

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public void removeDislikes(String dislike) {

    }

    // GETTERS
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

    // SETTERS
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
}
