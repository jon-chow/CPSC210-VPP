package model.goodsandservices;

import model.configurables.FileLocations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item1;
    Item item2;

    @BeforeEach
    void runBefore() {
        try {
            item1 = new Item("Chicken", "Food");
            item2 = new Item("Bone", "Toy");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSpritesDirTest() {
        String expectedVal1 = FileLocations.itemsSpriteDir + "chicken_food/";
        assertEquals(expectedVal1, item1.getSpritesDir());

        String expectedVal2 = FileLocations.itemsSpriteDir + "bone_toy/";
        assertEquals(expectedVal2, item2.getSpritesDir());
    }

    @Test
    void getNameTest() {
        assertEquals("Chicken", item1.getName());
        assertEquals("Bone", item2.getName());
    }

    @Test
    void getTypeTest() {
        assertEquals("Food", item1.getType());
        assertEquals("Toy", item2.getType());
    }

    @Test
    void getPriceTest() {
        assertEquals(230, item1.getPrice());
        assertEquals(150, item2.getPrice());
    }

    @Test
    void getCarePointsTest() {
        assertEquals(15, item1.getHappinessPoints());
        assertEquals(20, item1.getHungerPoints());
        assertEquals(10, item1.getThirstPoints());
        assertEquals(8, item1.getHealthPoints());

        assertEquals(21, item2.getHappinessPoints());
        assertEquals(0, item2.getHungerPoints());
        assertEquals(0, item2.getThirstPoints());
        assertEquals(5, item2.getHealthPoints());
    }
}