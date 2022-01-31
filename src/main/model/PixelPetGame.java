package model;

import model.pets.*;

public class PixelPetGame {
    private boolean endGame = false;
    private AdoptionClinic adoptionClinic = new AdoptionClinic();
    private Shop toyShop = new Shop("Toys 4 Pets");
    private Player plr = new Player();

    public PixelPetGame() {
        Dog dog1 = new Dog("Inu", "Shiba Inu");
    }
}
