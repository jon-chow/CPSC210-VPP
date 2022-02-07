package ui.menus;

import model.Player;
import model.pets.Pet;

public class PetStatsMenu {
    // EFFECTS:
    public static void checkPetStats(Pet pet, Player player) {
        System.out.println("Hey " + player.getPlayerName() + ", here's some information about your pet:");
        displayGeneral(pet);
        displayCareLevels(pet);
        displayCharacteristics(pet);
    }

    // EFFECTS: displays the name, animalType, and breed of pet
    public static void displayGeneral(Pet pet) {
        System.out.println("[ GENERAL ]:");
        System.out.println("- Name: " + pet.getName());
        System.out.println("- Animal Type: " + pet.getAnimalType());
        System.out.println("- Breed: " + pet.getBreed());
    }

    // EFFECTS: displays the age, happiness, hunger, health, and thirst levels of pet
    public static void displayCareLevels(Pet pet) {
        System.out.println("[ CARE LEVELS ]:");
        System.out.println("- Age: " + pet.getAge());
        System.out.println("- Happiness: " + pet.getHappiness());
        System.out.println("- Hunger: " + pet.getHunger());
        System.out.println("- Thirst: " + pet.getThirst());
        System.out.println("- Health: " + pet.getHealth());
    }

    // EFFECTS: displays the personalities, likes, and dislikes of pet
    public static void displayCharacteristics(Pet pet) {
        String personalities = pet.getPersonalities().toString().replaceAll("\\[|\\]","");
        String likes = pet.getLikes().toString().replaceAll("\\[|\\]","");
        String dislikes = pet.getDislikes().toString().replaceAll("\\[|\\]","");

        System.out.println("[ CHARACTERISTICS ]:");
        System.out.println("- Personalities: " + personalities);
        System.out.println("- Likes: " + likes);
        System.out.println("- Dislikes: " + dislikes);
    }

    // EFFECTS: displays a message about pet being dead
    public static void showPetDiedMenu(Pet pet) {
        System.out.println("Oh no! " + pet.getName() + " has died!");
        System.out.println("How unfortunate...");
    }
}
