package model;

import java.util.ArrayList;

import model.pets.*;

public class AdoptionClinic {
    private ArrayList<Pet> pets;

    // EFFECTS: constructs an AdoptionClinic with an empty list of pets
    public AdoptionClinic() {
        this.pets = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a pet to the list of pets
    public void addPet(Pet pet) {

    }

    // MODIFIES: this
    // REQUIRES: pet exists in pets
    // EFFECTS:  removes a pet from the list of pets
    public void removePet(Pet pet) {

    }

    // REQUIRES: pet exists in pets
    // EFFECTS:  returns the index of pet with name and breed in list of pets,
    public int findByNameBreed(String name, String breed) {
        return 0;
    }

    // EFFECTS:  returns true if pet exists in list of pets
    public boolean checkPetExists(Pet pet) {
        return false;
    }

    // GETTERS
    public ArrayList<Pet> getPets() {
        return pets;
    }

    // SETTERS
    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
