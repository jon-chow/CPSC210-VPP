package ui.menus;

import model.pets.Pet;

public class PetStatsMenu {

    // EFFECTS: displays a message about pet being dead
    public static void showPetDiedMenu(Pet pet) {
        System.out.println("Oh no! " + pet.getName() + " has died!");
        System.out.println("How unfortunate...");
    }
}
