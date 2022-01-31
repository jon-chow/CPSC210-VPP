package model;

public class Item {
    private String type;
    private int happinessPoints;
    private int thirstPoints;
    private int hungerPoints;
    private int healthPoints;

    // MODIFIES:
    // REQUIRES:
    // EFFECTS:
    public Item(String type, int happinessPoints, int thirstPoints, int hungerPoints, int healthPoints) {
        this.type = type;
        this.happinessPoints = happinessPoints;
        this.thirstPoints = thirstPoints;
        this.hungerPoints = hungerPoints;
        this.healthPoints = healthPoints;
    }

    // GETTERS
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
