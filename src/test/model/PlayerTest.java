package model;

import model.goodsandservices.Item;
import model.goodsandservices.Shop;
import model.pets.ExampleAnimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
    void addToInventoryTest() {
        Item item1 = null;
        Item item2 = null;

        try {
            item1 = new Item("Chicken","Food");
            item2 = new Item("Squeaky Mouse","Toy");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        Item item1 = null;
        Item item2 = null;

        try {
            item1 = new Item("Chicken","Food");
            item2 = new Item("Squeaky Mouse","Toy");
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        Item item1 = null;
        ExampleAnimal animal = null;

        try {
            item1 = new Item("Chicken","Food");
            animal = new ExampleAnimal("Animal", "Aleph");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<Item> inventory = new ArrayList<>();
        ArrayList<Integer> inventoryQuantity = new ArrayList<>();
        inventory.add(item1);
        inventoryQuantity.add(3);
        plr1.setInventory(inventory);
        plr1.setInventoryQuantity(inventoryQuantity);

        assertTrue(plr1.giveItemTo(item1, animal,1));
        ArrayList<Integer> expectedVal1 = new ArrayList<>();
        expectedVal1.add(2);
        assertEquals(expectedVal1, plr1.getInventoryQuantity());

        assertTrue(plr1.giveItemTo(item1, animal,1));
        ArrayList<Integer> expectedVal2 = new ArrayList<>();
        expectedVal2.add(1);
        assertEquals(expectedVal2, plr1.getInventoryQuantity());

        assertFalse(plr1.giveItemTo(item1, animal,100000));
    }

    @Test
    void buyItemFromTest() throws IOException {
        plr1.setMoney(10000000);

        Shop shop1 = new Shop("Petpix Toys");
        Item item1 = new Item("Chicken", "Food");
        Item item2 = new Item("Bone", "Toy");
        shop1.addShopItem(item1, 50);
        shop1.addShopItem(item2, 50);

        ArrayList<Item> expectedVal1A = new ArrayList<>();
        ArrayList<Integer> expectedVal1B = new ArrayList<>();
        expectedVal1A.add(item1);
        expectedVal1B.add(1);
        int expectedVal1C = plr1.getMoney() - item1.getPrice();

        assertTrue(plr1.buyItemFrom(item1, 1, shop1));
        assertEquals(expectedVal1A, plr1.getInventory());
        assertEquals(expectedVal1B, plr1.getInventoryQuantity());
        assertEquals(expectedVal1C, plr1.getMoney());

        ArrayList<Item> expectedVal2A = new ArrayList<>();
        ArrayList<Integer> expectedVal2B = new ArrayList<>();
        expectedVal2A.add(item1);
        expectedVal2A.add(item2);
        expectedVal2B.add(1);
        expectedVal2B.add(1);
        int expectedVal2C = plr1.getMoney() - item2.getPrice();

        assertTrue(plr1.buyItemFrom(item2, 1, shop1));
        assertEquals(expectedVal2A, plr1.getInventory());
        assertEquals(expectedVal2B, plr1.getInventoryQuantity());
        assertEquals(expectedVal2C, plr1.getMoney());
    }

    @Test
    void buyItemFromMultipleTest() throws IOException {
        plr1.setMoney(10000000);

        Shop shop1 = new Shop("Petpix Toys");
        Item item1 = new Item("Chicken", "Food");
        Item item2 = new Item("Bone", "Toy");
        shop1.addShopItem(item1, 50);
        shop1.addShopItem(item2, 50);

        ArrayList<Item> expectedVal1A = new ArrayList<>();
        ArrayList<Integer> expectedVal1B = new ArrayList<>();
        expectedVal1A.add(item1);
        expectedVal1B.add(9);
        int expectedVal1C = plr1.getMoney() - item1.getPrice() * 9;

        assertTrue(plr1.buyItemFrom(item1, 9, shop1));
        assertEquals(expectedVal1A, plr1.getInventory());
        assertEquals(expectedVal1B, plr1.getInventoryQuantity());
        assertEquals(expectedVal1C, plr1.getMoney());
    }

    @Test
    void buyItemFromNotEnoughMoneyTest() throws IOException {
        plr1.setMoney(0);

        Shop shop1 = new Shop("Petpix Toys");
        Item item1 = new Item("Chicken", "Food");
        Item item2 = new Item("Bone", "Toy");
        shop1.addShopItem(item1, 50);
        shop1.addShopItem(item2, 50);

        ArrayList<Item> expectedVal1A = new ArrayList<>();
        ArrayList<Integer> expectedVal1B = new ArrayList<>();
        int expectedVal1C = 0;

        assertFalse(plr1.buyItemFrom(item1, 9, shop1));
        assertEquals(expectedVal1A, plr1.getInventory());
        assertEquals(expectedVal1B, plr1.getInventoryQuantity());
        assertEquals(expectedVal1C, plr1.getMoney());
    }

    @Test
    void buyItemFromLittleInStockTest() throws IOException {
        plr1.setMoney(2000);

        Shop shop1 = new Shop("Petpix Toys");
        Item item1 = new Item("Chicken", "Food");
        shop1.addShopItem(item1, 5);

        ArrayList<Item> expectedVal1A = new ArrayList<>();
        ArrayList<Integer> expectedVal1B = new ArrayList<>();
        int expectedVal1C = 2000;

        assertFalse(plr1.buyItemFrom(item1, 9, shop1));
        assertEquals(expectedVal1A, plr1.getInventory());
        assertEquals(expectedVal1B, plr1.getInventoryQuantity());
        assertEquals(expectedVal1C, plr1.getMoney());
    }

    @Test
    void playerNameTest() {
        plr1.setPlayerName("New Name");
        assertEquals("New Name", plr1.getPlayerName());

        plr1.setPlayerName("Newer Name");
        assertEquals("Newer Name", plr1.getPlayerName());
    }
}