package model;

import model.pets.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PetTest {
    @BeforeEach
    void runBefore() {
        Pet pet1 = new Pet("Pet1");
    }

    @Test
    void consumeItemTest() {

    }
}