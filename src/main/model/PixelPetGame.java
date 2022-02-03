package model;

import model.pets.*;

import java.io.IOException;

public class PixelPetGame {
    public static final int TICKS_PER_SECOND = 10;

    private boolean endGame = false;
    private AdoptionClinic adoptionClinic = new AdoptionClinic();
    private Shop toyShop = new Shop("Toys 4 Pets");
    private Player player = new Player();
    private Pet adoptedPet;

    // EFFECTS: constructs a new pixel pet game
    public PixelPetGame() {
//        adoptedPet = new ExampleAnimal("Animal", "Aleph");
//        Item item = new Item("Chicken","Food");
    }

//    // EFFECTS: progresses the game
//    public void tick() {
//        if (adoptedPet != null && adoptedPet.checkIsDead()) {
//            endGame = true;
//            return;
//        }
//    }

    // EFFECTS: returns true if game has ended
    public boolean isEnded() {
        return endGame;
    }

    // SETTERS
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    // GETTERS
    public AdoptionClinic getAdoptionClinic() {
        return adoptionClinic;
    }

    public Shop getToyShop() {
        return toyShop;
    }

    public Player getPlayer() {
        return player;
    }

    public Pet getAdoptedPet() {
        return adoptedPet;
    }
}
