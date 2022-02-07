package model.goodsandservices;

import model.PixelPetGame;
import model.pets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AdoptionClinicTest {
    AdoptionClinic adoptionClinic;
    Pet pet1;
    Pet pet2;

    @BeforeEach
    void runBefore() {
        adoptionClinic = new AdoptionClinic();

        try {
            pet1 = new ExampleAnimal("Pet1","Aleph");
            pet2 = new ExampleAnimal("Pet2","Bet");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void generatePetTest() {
        Pet exampleAnimal = null;
        Pet dog = null;
        try {
            exampleAnimal = adoptionClinic.generatePet("ExampleAnimal", "Aleph");
            dog = adoptionClinic.generatePet("Dog", "Shiba Inu");
        } catch (IOException e) {
            e.printStackTrace();
        }

        String expectedVal1A = "ExampleAnimal";
        String expectedVal1B = "Aleph";
        assertEquals(expectedVal1A, exampleAnimal.getAnimalType());
        assertEquals(expectedVal1B, exampleAnimal.getBreed());

        String expectedVal2A = "Dog";
        String expectedVal2B = "Shiba Inu";
        assertEquals(expectedVal2A, dog.getAnimalType());
        assertEquals(expectedVal2B, dog.getBreed());
    }

    @Test
    void fetchBreedsTest() throws IOException {
        ArrayList<String> listOfPets = new ArrayList<>(Arrays.asList("Aleph","Bet","Gimel"));
        assertEquals(listOfPets, adoptionClinic.fetchBreeds("Example"));
    }

    @Test
    void getAnimalTypes() {
        assertEquals(PixelPetGame.ANIMALS_IN_ADOPTION_CLINIC,
                adoptionClinic.getAnimalTypes());
    }
}