package ui.menus;

import model.goodsandservices.AdoptionClinic;
import model.pets.Pet;

import java.io.IOException;
import java.util.ArrayList;

import static ui.app.TerminalApp.scanner;
import static ui.configurables.Commands.*;

public class AdoptionMenu {
    private static AdoptionClinic adoptionClinic;
    private static Pet adoptedPet;

    // EFFECTS: initiates the adoption process
    public static Pet initAdoption() throws IOException {
        adoptionClinic = new AdoptionClinic();
        String selectedAnimalType = "";
        String selectedBreed = "";

        boolean hasAdoptedPet = false;
        while (!hasAdoptedPet) {
            System.out.println("Welcome to the Pixel Pet Adoption Clinic!");
            selectedAnimalType = adoptionDeclareAnimalType();
            selectedBreed = adoptionDeclareBreed(selectedAnimalType);
            hasAdoptedPet = adoptionConfirmAdoption(selectedAnimalType, selectedBreed);
        }
        adoptedPet = adoptionClinic.generatePet(selectedAnimalType, selectedBreed);

        adoptionNamePet(adoptedPet.getBreed());

        return adoptedPet;
    }

    // EFFECTS: prompts a selection for the type of animal of the pet
    private static String adoptionDeclareAnimalType() {
        StringBuilder animalTypeListing = new StringBuilder();
        ArrayList<String> allAnimalTypes = adoptionClinic.getAnimalTypes();

        System.out.println("Please select the type of pet you would like to adopt from the list below:");

        int index = 0;
        int maxIndex = allAnimalTypes.size() - 1;
        for (String animalType : allAnimalTypes) {
            animalTypeListing.append(index != 0 ? "\n" : "").append(index++).append(" = ").append(animalType);
        }
        System.out.println(animalTypeListing);

        return allAnimalTypes.get(getValidIndexSelection(maxIndex));
    }

    // EFFECTS: prompts a selection for the breed of the pet
    private static String adoptionDeclareBreed(String selectedAnimalType) throws IOException {
        StringBuilder breedListing = new StringBuilder();
        ArrayList<String> allBreeds = adoptionClinic.fetchBreeds(selectedAnimalType);

        System.out.println("\nPlease select the breed of your " + selectedAnimalType + " from the list below:");

        int index = 0;
        int maxIndex = allBreeds.size() - 1;
        for (String breed : allBreeds) {
            breedListing.append(index != 0 ? "\n" : "").append(index++).append(" = ").append(breed);
        }
        System.out.println(breedListing);

        return allBreeds.get(getValidIndexSelection(maxIndex));
    }

    // EFFECTS: returns true if user confirms adoption of pet
    private static boolean adoptionConfirmAdoption(String animalType, String breed) {
        System.out.println("\nYou have selected a pet " + animalType + " of the breed " + breed + ".");
        System.out.println("Confirm the adoption by entering '"
                            + CONFIRMATION_KEY
                            + "' or enter any other key to make a different choice.");
        String choice = scanner.nextLine();

        return (choice.equals(CONFIRMATION_KEY));
    }

    // MODIFIES: Pet
    // EFFECTS: prompts to give the pet a name
    private static void adoptionNamePet(String pet) {
        String nameGiven = "";

        boolean confirmedName = false;
        while (!confirmedName) {
            System.out.println("\nPlease enter a name for your " + pet + ":");
            nameGiven = scanner.nextLine();
            confirmedName = adoptionConfirmPetName(nameGiven);
        }
        adoptedPet.setName(nameGiven);

        System.out.println("\nCongratulations! You have successfully adopted " + adoptedPet.getName() + "!");
    }

    // EFFECTS: returns true if user confirms naming of pet
    private static boolean adoptionConfirmPetName(String name) {
        System.out.println("\nYou have selected your pet's name to be \"" + name + "\".");
        System.out.println("Confirm this name by entering '"
                            + CONFIRMATION_KEY
                            + "' or enter any other key to choose a different name.");
        String choice = scanner.nextLine();

        return (choice.equals(CONFIRMATION_KEY));
    }

    // REQUIRES: maxIndex >= 0
    // EFFECTS: validates user input selection and returns the selected value
    private static int getValidIndexSelection(int maxSelection) {
        boolean validChoice = false;
        int indexOfSelected = 0;
        while (!validChoice) {
            try {
                indexOfSelected = Integer.parseInt(scanner.nextLine());
                validChoice = (0 <= indexOfSelected && indexOfSelected <= maxSelection);
            } catch (NumberFormatException e) {
                validChoice = false;
            }
        }

        return indexOfSelected;
    }
}
