package model.goodsandservices;

import model.configurables.FileLocations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item1;
    Item item2;
    Item item3;

    @BeforeEach
    void runBefore() {
        try {
            item1 = new Item("Chicken", "Food");
            item2 = new Item("Bone", "Toy");
            item3 = new Item("Chicken", "Toy");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSpritesDirTest() {
        FileLocations fileLoc = new FileLocations();

        String expectedVal1 = fileLoc.itemsSpriteDir + "chicken_food/";
        assertEquals(expectedVal1, item1.getSpritesDir());

        String expectedVal2 = fileLoc.itemsSpriteDir + "bone_toy/";
        assertEquals(expectedVal2, item2.getSpritesDir());

        String expectedVal3 = fileLoc.itemsSpriteDir + "chicken_toy/";
        assertEquals(expectedVal3, item3.getSpritesDir());
    }

    @Test
    void getNameTest() {
        assertEquals("Chicken", item1.getName());
        assertEquals("Bone", item2.getName());
        assertEquals("Chicken", item3.getName());
    }

    @Test
    void getTypeTest() {
        assertEquals("Food", item1.getType());
        assertEquals("Toy", item2.getType());
        assertEquals("Toy", item3.getType());
    }

    @Test
    void getPriceTest() {
        assertEquals(230, item1.getPrice());
        assertEquals(150, item2.getPrice());
        assertEquals(380, item3.getPrice());
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

        assertEquals(34, item3.getHappinessPoints());
        assertEquals(0, item3.getHungerPoints());
        assertEquals(0, item3.getThirstPoints());
        assertEquals(16, item3.getHealthPoints());
    }
}