package model;

import model.pets.Dog;

import model.pets.ExampleAnimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Player plr1;

    @BeforeEach
    void runBefore() {
        plr1 = new Player();
    }

    @Test
    void renamePlayerTest() {
        plr1.renamePlayer("New name");
        assertEquals("New name", plr1.getPlayerName());

        plr1.renamePlayer("Another new name");
        assertEquals("Another new name", plr1.getPlayerName());
    }

    @Test
    void addToInventoryTest() {
        Item item1 = new Item("Chicken","Food");
        Item item2 = new Item("Squeaky Mouse","Toy");

        plr1.addToInventory(item1, 1);
        ArrayList<Item> expectedVal1A = new ArrayList<>();
        expectedVal1A.add(item1);
        ArrayList<Integer> expectedVal1B = new ArrayList<>();
        expectedVal1B.add(1);
        assertEquals(expectedVal1A, plr1.getInventory());
        assertEquals(expectedVal1B, plr1.getInventoryQuantity());

        plr1.addToInventory(item2,1 );
        ArrayList<Item> expectedVal2A = new ArrayList<>(Arrays.asList(item1, item2));
        ArrayList<Integer> expectedVal2B = new ArrayList<>(Arrays.asList(1, 1));
        assertEquals(expectedVal2A, plr1.getInventory());
        assertEquals(expectedVal2B, plr1.getInventoryQuantity());

        plr1.addToInventory(item1, 1);
        ArrayList<Item> expectedVal3A = new ArrayList<>(Arrays.asList(item1, item2));
        ArrayList<Integer> expectedVal3B = new ArrayList<>(Arrays.asList(2, 1));
        assertEquals(expectedVal3A, plr1.getInventory());
        assertEquals(expectedVal3B, plr1.getInventoryQuantity());
    }

    @Test
    void removeFromInventoryTest() {
        Item item1 = new Item("Chicken","Food");
        Item item2 = new Item("Squeaky Mouse","Toy");

        ArrayList<Item> inventory = new ArrayList<>(Arrays.asList(item1, item2));
        ArrayList<Integer> inventoryQuantity = new ArrayList<>(Arrays.asList(2, 2));
        plr1.setInventory(inventory);
        plr1.setInventoryQuantity(inventoryQuantity);

        plr1.removeFromInventory(item1, 1);
        ArrayList<Item> expectedVal1A = new ArrayList<>(Arrays.asList(item1, item2));
        ArrayList<Integer> expectedVal1B = new ArrayList<>(Arrays.asList(1, 2));
        assertEquals(expectedVal1A, plr1.getInventory());
        assertEquals(expectedVal1B, plr1.getInventoryQuantity());

        plr1.removeFromInventory(item2, 2);
        ArrayList<Item> expectedVal2A = new ArrayList<>();
        expectedVal2A.add(item1);
        ArrayList<Integer> expectedVal2B = new ArrayList<>();
        expectedVal2B.add(1);
        assertEquals(expectedVal2A, plr1.getInventory());
        assertEquals(expectedVal2B, plr1.getInventoryQuantity());
    }

    @Test
    void giveItemToTest() {
        Item item1 = new Item("Chicken","Food");
        ExampleAnimal animal = new ExampleAnimal("Animal", "Aleph");
        animal.setHappiness(50);
        animal.setHunger(50);
        animal.setThirst(50);
        animal.setHealth(50);

        plr1.giveItemTo(item1, animal);
        assertEquals(65, animal.getHappiness());
        assertEquals(70, animal.getHunger());
        assertEquals(60, animal.getThirst());
        assertEquals(55, animal.getHealth());
    }

    @Test
    void buyItemFromTest() {
        plr1.setMoney(300);

        Shop shop1 = new Shop("Petpix Toys");
        Item item1 = new Item("Chicken", "Food");
        shop1.addShopItem(item1, 100, 50);

        plr1.buyItemFrom(item1, 1, shop1);
        ArrayList<Item> expectedVal1 = new ArrayList<>();
        expectedVal1.add(item1);
        assertEquals(expectedVal1, plr1.getInventory());
        assertEquals(100, plr1.getMoney());

        plr1.buyItemFrom(item1, 2, shop1);
        ArrayList<Item> expectedVal2 = new ArrayList<>(Arrays.asList(item1, item1));
        assertEquals(expectedVal2, plr1.getInventory());
        assertEquals(0, plr1.getMoney());

        plr1.buyItemFrom(item1, 1, shop1);
        ArrayList<Item> expectedVal3 = new ArrayList<>(Arrays.asList(item1, item1));
        assertEquals(expectedVal3, plr1.getInventory());
        assertEquals(0, plr1.getMoney());
    }

    @Test
    void checkEnoughMoneyTest() {
        plr1.setMoney(200);
        assertTrue(plr1.checkEnoughMoney(199));
        assertTrue(plr1.checkEnoughMoney(200));
        assertFalse(plr1.checkEnoughMoney(201));
    }
}