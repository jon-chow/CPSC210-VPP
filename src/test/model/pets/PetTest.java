package model.pets;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    Pet pet1;

    @BeforeEach
    void runBefore() {
        pet1 = new Pet("Pet1");
    }

    @Test
    void consumeItemTest() {
        Item item1 = new Item("Chicken","Food");
        pet1.setHappiness(50);
        pet1.setHunger(50);
        pet1.setThirst(50);
        pet1.setHealth(50);

        pet1.consumeItem(item1);
        assertEquals(65, pet1.getHappiness());
        assertEquals(70, pet1.getHunger());
        assertEquals(60, pet1.getThirst());
        assertEquals(55, pet1.getHealth());
    }

    @Test
    void makeNoiseTest() {
        assertNotEquals(0, pet1.makeNoise().length());
    }

    @Test
    void createWasteTest() {
        pet1.createWaste(1);
        assertEquals(1, pet1.getNumWaste());

        pet1.createWaste(4);
        assertEquals(5, pet1.getNumWaste());
    }

    @Test
    void removeWasteTest() {
        pet1.setNumWaste(5);

        pet1.removeWaste(2);
        assertEquals(3, pet1.getNumWaste());

        pet1.removeWaste(3);
        assertEquals(0, pet1.getNumWaste());
    }

    @Test
    void checkIfLikesTest() {
        Item chicken = new Item("Chicken", "Food");
        Item bone = new Item("Bone", "Toy");
        ArrayList<String> likes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        pet1.setLikes(likes);

        assertTrue(pet1.checkIfLikes(chicken));
        assertFalse(pet1.checkIfLikes(bone));
    }

    @Test
    void checkIfDislikesTest() {
        Item chicken = new Item("Chicken", "Food");
        Item bone = new Item("Bone", "Toy");
        ArrayList<String> dislikes = new ArrayList<>();
        dislikes.add("Bone");
        pet1.setDislikes(dislikes);

        assertFalse(pet1.checkIfDislikes(chicken));
        assertTrue(pet1.checkIfDislikes(bone));
    }

    @Test
    void checkIsDeadTest() {
        pet1.setHunger(0);
        pet1.setThirst(100);
        pet1.setHealth(100);
        assertTrue(pet1.checkIsDead());

        pet1.setHunger(100);
        pet1.setThirst(0);
        assertTrue(pet1.checkIsDead());

        pet1.setThirst(100);
        pet1.setHealth(0);
        assertTrue(pet1.checkIsDead());

        pet1.setHunger(1);
        pet1.setThirst(1);
        pet1.setHealth(1);
        assertFalse(pet1.checkIsDead());
    }

    @Test
    void alertCareStatsTest() {
        pet1.setHappiness(100);
        pet1.setHunger(100);
        pet1.setThirst(100);
        pet1.setHealth(100);
        ArrayList<Integer> careLevels1 = new ArrayList<>(Arrays.asList(100,100,100,100));
        assertEquals(careLevels1, pet1.alertCareStats());


        pet1.setHappiness(20);
        pet1.setHunger(40);
        pet1.setThirst(60);
        pet1.setHealth(80);
        ArrayList<Integer> careLevels2 = new ArrayList<>(Arrays.asList(20,40,60,80));
        assertEquals(careLevels2, pet1.alertCareStats());
    }

    @Test
    void decrementCareLevelsTest() {
        pet1.setHappiness(100);
        pet1.setHunger(100);
        pet1.setThirst(100);
        pet1.setHealth(100);

        pet1.decrementCareLevels(10,20,30,40);
        assertEquals(90, pet1.getHappiness());
        assertEquals(80, pet1.getHunger());
        assertEquals(70, pet1.getThirst());
        assertEquals(60, pet1.getHealth());
    }

    @Test
    void addPersonalityTest() {
        pet1.addPersonality("Outgoing");
        ArrayList<String> personalities = new ArrayList<>();
        personalities.add("Outgoing");
        assertEquals(personalities, pet1.getPersonalities());

        pet1.addPersonality("Shy");
        personalities.add("Shy");
        assertEquals(personalities, pet1.getPersonalities());
    }

    @Test
    void removePersonalityTest() {
        ArrayList<String> personalities = new ArrayList<>(Arrays.asList("Outgoing", "Shy"));
        pet1.setPersonalities(personalities);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Outgoing");
        pet1.removePersonality("Shy");
        assertEquals(expectedVal1, pet1.getPersonalities());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        pet1.removePersonality("Outgoing");
        assertEquals(expectedVal2, pet1.getPersonalities());
    }

    @Test
    void addLikesTest() {
        pet1.addLikes("Chicken");
        ArrayList<String> likes = new ArrayList<>();
        likes.add("Chicken");
        assertEquals(likes, pet1.getLikes());

        pet1.addLikes("Avocado");
        likes.add("Avocado");
        assertEquals(likes, pet1.getLikes());
    }

    @Test
    void removeLikesTest() {
        ArrayList<String> likes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        pet1.setLikes(likes);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Chicken");
        pet1.removeLikes("Avocado");
        assertEquals(expectedVal1, pet1.getLikes());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        pet1.removeLikes("Chicken");
        assertEquals(expectedVal2, pet1.getLikes());
    }

    @Test
    void addDislikesTest() {
        pet1.addDislikes("Chicken");
        ArrayList<String> dislikes = new ArrayList<>();
        dislikes.add("Chicken");
        assertEquals(dislikes, pet1.getDislikes());

        pet1.addDislikes("Avocado");
        dislikes.add("Avocado");
        assertEquals(dislikes, pet1.getDislikes());
    }

    @Test
    void removeDislikesTest() {
        ArrayList<String> dislikes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        pet1.setDislikes(dislikes);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Chicken");
        pet1.removeDislikes("Avocado");
        assertEquals(expectedVal1, pet1.getDislikes());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        pet1.removeDislikes("Chicken");
        assertEquals(expectedVal2, pet1.getDislikes());
    }
}