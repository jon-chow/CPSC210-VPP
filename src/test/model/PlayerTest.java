package model;

import model.goodsandservices.Item;
import model.goodsandservices.Shop;
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

        ArrayList<Item> inventory = new ArrayList<>();
        ArrayList<Integer> inventoryQuantity = new ArrayList<>();
        inventory.add(item1);
        inventoryQuantity.add(2);
        plr1.setInventory(inventory);
        plr1.setInventoryQuantity(inventoryQuantity);

        plr1.giveItemTo(item1, animal);
        ArrayList<Integer> expectedVal1 = new ArrayList<>();
        expectedVal1.add(1);
        assertEquals(expectedVal1, plr1.getInventoryQuantity());

        plr1.giveItemTo(item1, animal);
        ArrayList<Integer> expectedVal2 = new ArrayList<>();
        assertEquals(expectedVal2, plr1.getInventoryQuantity());
    }

    @Test
    void buyItemFromTest() {
        plr1.setMoney(1000);

        Shop shop1 = new Shop("Petpix Toys");
        Item item1 = new Item("Chicken", "Food");
        Item item2 = new Item("Bone", "Toy");
        shop1.addShopItem(item1, 100, 50);
        shop1.addShopItem(item2, 500, 50);

        plr1.buyItemFrom(item1, 1, shop1);
        ArrayList<Item> expectedVal1A = new ArrayList<>();
        ArrayList<Integer> expectedVal1B = new ArrayList<>();
        expectedVal1A.add(item1);
        expectedVal1B.add(1);
        assertEquals(expectedVal1A, plr1.getInventory());
        assertEquals(expectedVal1B, plr1.getInventoryQuantity());
        assertEquals(900, plr1.getMoney());

        plr1.buyItemFrom(item2, 1, shop1);
        ArrayList<Item> expectedVal3A = new ArrayList<>();
        ArrayList<Integer> expectedVal3B = new ArrayList<>();
        expectedVal3A.add(item1);
        expectedVal3A.add(item2);
        expectedVal3B.add(1);
        expectedVal3B.add(1);
        assertEquals(expectedVal3A, plr1.getInventory());
        assertEquals(expectedVal3B, plr1.getInventoryQuantity());
        assertEquals(400, plr1.getMoney());
    }

    @Test
    void buyItemFromMultipleTest() {
        plr1.setMoney(1000);

        Shop shop1 = new Shop("Petpix Toys");
        Item item1 = new Item("Chicken", "Food");
        Item item2 = new Item("Bone", "Toy");
        shop1.addShopItem(item1, 100, 50);
        shop1.addShopItem(item2, 500, 50);

        plr1.buyItemFrom(item1, 9, shop1);
        ArrayList<Item> expectedVal2A = new ArrayList<>();
        ArrayList<Integer> expectedVal2B = new ArrayList<>();
        expectedVal2A.add(item1);
        expectedVal2B.add(9);
        assertEquals(expectedVal2A, plr1.getInventory());
        assertEquals(expectedVal2B, plr1.getInventoryQuantity());
        assertEquals(100, plr1.getMoney());
    }
}