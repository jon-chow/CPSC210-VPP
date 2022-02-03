package model;

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
        pet1 = new ExampleAnimal("Pet1","Aleph");
        pet2 = new ExampleAnimal("Pet2","Bet");
    }

    @Test
    void addPetTest() {
        adoptionClinic.addPet(pet1);
        ArrayList<Pet> expectedVal1 = new ArrayList<>();
        expectedVal1.add(pet1);
        assertEquals(expectedVal1, adoptionClinic.getPets());

        adoptionClinic.addPet(pet2);
        ArrayList<Pet> expectedVal2 = new ArrayList<>(Arrays.asList(pet1, pet2));
        assertEquals(expectedVal2, adoptionClinic.getPets());
    }

    @Test
    void removePetTest() {
        adoptionClinic.setPets(new ArrayList<>(Arrays.asList(pet1, pet2)));

        adoptionClinic.removePet(pet1);
        ArrayList<Pet> expectedVal1 = new ArrayList<>();
        expectedVal1.add(pet2);
        assertEquals(expectedVal1, adoptionClinic.getPets());

        adoptionClinic.removePet(pet2);
        ArrayList<Pet> expectedVal2 = new ArrayList<>();
        assertEquals(expectedVal2, adoptionClinic.getPets());
    }

    @Test
    void findPetByNameTest() {
        adoptionClinic.setPets(new ArrayList<>(Arrays.asList(pet1, pet2)));
        assertEquals(0, adoptionClinic.findByNameBreed("Pet1", "Aleph"));
        assertEquals(1, adoptionClinic.findByNameBreed("Pet2", "Bet"));
    }

    @Test
    void checkPetExistsTest() {
        ArrayList<Pet> pets =  new ArrayList<>();
        pets.add(pet1);
        adoptionClinic.setPets(pets);

        assertTrue(adoptionClinic.checkPetExists(pet1));
        assertFalse(adoptionClinic.checkPetExists(pet2));
    }

    @Test
    void fetchBreedsTest() throws IOException {
        ArrayList<String> listOfPets = new ArrayList<>(Arrays.asList("Aleph","Bet","Gimel"));
        assertEquals(listOfPets, adoptionClinic.fetchBreeds("Example"));
    }
}