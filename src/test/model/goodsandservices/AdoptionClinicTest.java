package model.goodsandservices;

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
    void fetchBreedsTest() throws IOException {
        ArrayList<String> listOfPets = new ArrayList<>(Arrays.asList("Aleph","Bet","Gimel"));
        assertEquals(listOfPets, adoptionClinic.fetchBreeds("Example"));
    }
}