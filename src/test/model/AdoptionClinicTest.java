package model;

import model.pets.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        pet1 = new Dog("Doggo","Shiba Inu");
        pet2 = new Dog("Bork","Husky");
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
        assertEquals(0, adoptionClinic.findByNameBreed("Doggo", "Shiba Inu"));
        assertEquals(1, adoptionClinic.findByNameBreed("Bork", "Husky"));
    }

    @Test
    void checkPetExists() {
        ArrayList<Pet> pets =  new ArrayList<>();
        pets.add(pet1);
        adoptionClinic.setPets(pets);

        assertTrue(adoptionClinic.checkPetExists(pet1));
        assertFalse(adoptionClinic.checkPetExists(pet2));
    }
}