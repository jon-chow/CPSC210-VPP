package model;

import model.pets.*;

public class PixelPetGame {
    private boolean endGame = false;
    private AdoptionClinic adoptionClinic = new AdoptionClinic();
    private Shop toyShop = new Shop("Toys 4 Pets");
    private Player player = new Player();
    private Pet adoptedPet;

    // EFFECTS: constructs a new pixel pet game
    public PixelPetGame() {
//        adoptedPet = new Dog("Doggo", "Shiba Inu");
//        Item item = new Item("Chicken","Food");
    }

    // SETTERS
    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    // GETTERS
    public boolean getEndGame() {
        return endGame;
    }

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
