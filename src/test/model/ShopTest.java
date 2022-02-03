package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    Shop shop1;
    Item item1;
    Item item2;
    Item item3;

    @BeforeEach
    void runBefore() {
        shop1 = new Shop("Shop1");
        item1 = new Item("Chicken", "Food");
        item2 = new Item("Squeaky Mouse", "Toy");
        item3 = new Item("Bone", "Toy");

        ArrayList<Item> items = new ArrayList<>(Arrays.asList(item1, item2));
        ArrayList<Integer> prices = new ArrayList<>(Arrays.asList(50, 100));
        ArrayList<Integer> stocks = new ArrayList<>(Arrays.asList(20, 10));
        shop1.setShopItems(items);
        shop1.setPriceOfItems(prices);
        shop1.setQuantityInStock(stocks);
    }

    @Test
    void addShopItemTest() {
        shop1.addShopItem(item3,300,30);
        ArrayList<Item> expectedVal1A = new ArrayList<>(Arrays.asList(item1, item2, item3));
        ArrayList<Integer> expectedVal1B = new ArrayList<>(Arrays.asList(50, 100, 300));
        ArrayList<Integer> expectedVal1C = new ArrayList<>(Arrays.asList(20, 10, 30));
        assertEquals(expectedVal1A, shop1.getShopItems());
        assertEquals(expectedVal1B, shop1.getPriceOfItems());
        assertEquals(expectedVal1C, shop1.getQuantityInStock());
    }

    @Test
    void removeShopItemTest() {
        shop1.removeShopItem(item2);
        ArrayList<Item> expectedVal1A = new ArrayList<>();
        expectedVal1A.add(item1);
        ArrayList<Integer> expectedVal1B = new ArrayList<>();
        expectedVal1B.add(50);
        ArrayList<Integer> expectedVal1C = new ArrayList<>();
        expectedVal1C.add(20);
        assertEquals(expectedVal1A, shop1.getShopItems());
        assertEquals(expectedVal1B, shop1.getPriceOfItems());
        assertEquals(expectedVal1C, shop1.getQuantityInStock());
    }

    @Test
    void sellItemTo() {
        Player plr = new Player();

        shop1.sellItemTo(item1, 1, plr);
        ArrayList<Integer> expectedVal1 = new ArrayList<>(Arrays.asList(19, 10));
        assertEquals(expectedVal1, shop1.getQuantityInStock());

        shop1.sellItemTo(item2, 3, plr);
        ArrayList<Integer> expectedVal2 = new ArrayList<>(Arrays.asList(19, 7));
        assertEquals(expectedVal2, shop1.getQuantityInStock());
    }

    @Test
    void changeItemPriceTest() {
        shop1.changeItemPrice(item1, 999);
        ArrayList<Integer> expectedVal1 = new ArrayList<>(Arrays.asList(999, 100));
        assertEquals(expectedVal1, shop1.getPriceOfItems());

        shop1.changeItemPrice(item2, 0);
        ArrayList<Integer> expectedVal2 = new ArrayList<>(Arrays.asList(999, 0));
        assertEquals(expectedVal2, shop1.getPriceOfItems());
    }

    @Test
    void incrementItemQuantityTest() {
        shop1.incrementItemQuantity(item1, 10);
        ArrayList<Integer> expectedVal1 = new ArrayList<>(Arrays.asList(30, 10));
        assertEquals(expectedVal1, shop1.getQuantityInStock());

        shop1.incrementItemQuantity(item2, 80);
        ArrayList<Integer> expectedVal2 = new ArrayList<>(Arrays.asList(30, 90));
        assertEquals(expectedVal2, shop1.getQuantityInStock());
    }

    @Test
    void decrementItemQuantityTest() {
        shop1.decrementItemQuantity(item1, 10);
        ArrayList<Integer> expectedVal1 = new ArrayList<>(Arrays.asList(10, 10));
        assertEquals(expectedVal1, shop1.getQuantityInStock());

        shop1.decrementItemQuantity(item2, 8);
        ArrayList<Integer> expectedVal2 = new ArrayList<>(Arrays.asList(10, 2));
        assertEquals(expectedVal2, shop1.getQuantityInStock());
    }

    @Test
    void checkIsInShopTest() {
        assertTrue(shop1.checkIsInShop(item1));
        assertTrue(shop1.checkIsInShop(item2));
        assertFalse(shop1.checkIsInShop(item3));
    }
}