package model.pets;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PetSubClassesTest {
    Dog dog;

    @BeforeEach
    void runBefore() {
        try {
            dog = new Dog("Pet2", "Shiba Inu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SUBCLASSES OF PET TESTS:
    // Dog Class:
    @Test
    void dogMakeNoiseTest() {
        ArrayList<String> expectedVals = dog.getAllNoises();
        assertTrue(expectedVals.contains(dog.makeNoise()));
        assertTrue(expectedVals.contains(dog.makeNoise()));
        assertTrue(expectedVals.contains(dog.makeNoise()));
    }
}
