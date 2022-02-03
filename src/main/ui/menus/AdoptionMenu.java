package ui.menus;

import model.AdoptionClinic;
import model.pets.Pet;

import java.io.IOException;
import java.util.ArrayList;

import static ui.TerminalApp.scanner;

public class AdoptionMenu {
    private static AdoptionClinic adoptionClinic;
    private static Pet adoptedPet;

    // MODIFIES: this
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

    // MODIFIES: Pet
    // EFFECTS: prompts to give the pet a name
    private static void adoptionNamePet(String pet) {
        String nameGiven = "";

        boolean confirmedName = false;
        while (!confirmedName) {
            System.out.println("Please enter a name for your " + pet + ":");
            nameGiven = scanner.nextLine();
            System.out.println("");
            confirmedName = adoptionConfirmPetName(nameGiven);
        }
        adoptedPet.setName(nameGiven);

        System.out.println("Congratulations! You have successfully adopted " + adoptedPet.getName() + "!");
    }

    // EFFECTS: returns true if user confirms naming of pet
    private static boolean adoptionConfirmPetName(String name) {
        System.out.println("You have selected a pet " + name);
        System.out.println("Enter 'y' to confirm this name "
                + "or enter any other key to choose a different name.");
        String choice = scanner.nextLine();
        System.out.println("");

        return (choice.equals("y"));
    }

    // EFFECTS: returns true if user confirms adoption of pet
    private static boolean adoptionConfirmAdoption(String animalType, String breed) {
        System.out.println("You have selected a pet " + animalType + " of the breed " + breed + ".");
        System.out.println("Enter 'y' to confirm the adoption "
                + "or enter any other key to choose something else.");
        String choice = scanner.nextLine();
        System.out.println("");

        return (choice.equals("y"));
    }

    // EFFECTS: prompts a selection for the type of animal of the pet
    private static String adoptionDeclareAnimalType() {
        String selectedAnimalType;
        String animalTypeListing = "";
        ArrayList<String> allAnimalTypes = adoptionClinic.getAnimalTypes();

        System.out.println("Please select the type of pet you would like to adopt from the list below:");
        int index = 0;
        for (String animalType : allAnimalTypes) {
            animalTypeListing += (index != 0 ? "\n" : "") + (index++) + " = " + animalType;
        }

        System.out.println(animalTypeListing);
        selectedAnimalType = allAnimalTypes.get(Integer.parseInt(scanner.nextLine()));
        System.out.println("");

        return selectedAnimalType;
    }

    // EFFECTS: prompts a selection for the breed of the pet
    private static String adoptionDeclareBreed(String selectedAnimalType) throws IOException {
        String selectedBreed;
        String breedListing = "";
        ArrayList<String> allBreeds = adoptionClinic.fetchBreeds(selectedAnimalType);

        System.out.println("Please select the breed of your " + selectedAnimalType + ":");
        int index = 0;
        for (String breed : allBreeds) {
            breedListing += (index != 0 ? "\n" : "") + (index++) + " = " + breed;
        }

        System.out.println(breedListing);
        selectedBreed = allBreeds.get(Integer.parseInt(scanner.nextLine()));
        System.out.println("");

        return selectedBreed;
    }
}
