package model.pets;

import model.goodsandservices.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ExampleAnimalTest {
    ExampleAnimal animal;

    @BeforeEach
    void runBefore() {
        animal = new ExampleAnimal("Pet1", "Aleph");
    }

    @Test
    void consumeItemTest() {
        Item item1 = new Item("Chicken","Food");
        animal.setHappiness(50);
        animal.setHunger(50);
        animal.setThirst(50);
        animal.setHealth(50);

        animal.consumeItem(item1);
        assertEquals(50 + item1.getHappinessPoints(), animal.getHappiness());
        assertEquals(50 + item1.getHungerPoints(), animal.getHunger());
        assertEquals(50 + item1.getThirstPoints(), animal.getThirst());
        assertEquals(50 + item1.getHealthPoints(), animal.getHealth());
    }

    @Test
    void makeNoiseTest() {
        assertNotEquals(0, animal.makeNoise().length());
    }

    @Test
    void createWasteTest() {
        animal.createWaste(1);
        assertEquals(1, animal.getNumWaste());

        animal.createWaste(4);
        assertEquals(5, animal.getNumWaste());
    }

    @Test
    void removeWasteTest() {
        animal.setNumWaste(5);

        animal.removeWaste(2);
        assertEquals(3, animal.getNumWaste());

        animal.removeWaste(3);
        assertEquals(0, animal.getNumWaste());
    }

    @Test
    void checkIfLikesTest() {
        Item chicken = new Item("Chicken", "Food");
        Item bone = new Item("Bone", "Toy");
        ArrayList<String> likes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        animal.setLikes(likes);

        assertTrue(animal.checkIfLikes(chicken));
        assertFalse(animal.checkIfLikes(bone));
    }

    @Test
    void checkIfDislikesTest() {
        Item chicken = new Item("Chicken", "Food");
        Item bone = new Item("Bone", "Toy");
        ArrayList<String> dislikes = new ArrayList<>();
        dislikes.add("Bone");
        animal.setDislikes(dislikes);

        assertFalse(animal.checkIfDislikes(chicken));
        assertTrue(animal.checkIfDislikes(bone));
    }

    @Test
    void checkIsDeadTest() {
        animal.setHunger(0);
        animal.setThirst(100);
        animal.setHealth(100);
        assertTrue(animal.checkIsDead());

        animal.setHunger(100);
        animal.setThirst(0);
        assertTrue(animal.checkIsDead());

        animal.setThirst(100);
        animal.setHealth(0);
        assertTrue(animal.checkIsDead());

        animal.setHunger(1);
        animal.setThirst(1);
        animal.setHealth(1);
        assertFalse(animal.checkIsDead());
    }

    @Test
    void alertCareStatsTest() {
        animal.setHappiness(100);
        animal.setHunger(100);
        animal.setThirst(100);
        animal.setHealth(100);
        ArrayList<Integer> careLevels1 = new ArrayList<>(Arrays.asList(100,100,100,100));
        assertEquals(careLevels1, animal.alertCareStats());


        animal.setHappiness(20);
        animal.setHunger(40);
        animal.setThirst(60);
        animal.setHealth(80);
        ArrayList<Integer> careLevels2 = new ArrayList<>(Arrays.asList(20,40,60,80));
        assertEquals(careLevels2, animal.alertCareStats());
    }

    @Test
    void decrementCareLevelsTest() {
        animal.setHappiness(100);
        animal.setHunger(100);
        animal.setThirst(100);
        animal.setHealth(100);

        animal.decrementCareLevels(10,20,30,40);
        assertEquals(90, animal.getHappiness());
        assertEquals(80, animal.getHunger());
        assertEquals(70, animal.getThirst());
        assertEquals(60, animal.getHealth());
    }

    @Test
    void incrementCareLevels() {
        animal.setHappiness(50);
        animal.setHunger(50);
        animal.setThirst(50);
        animal.setHealth(50);

        animal.incrementCareLevels(10,20,30,40);
        assertEquals(60, animal.getHappiness());
        assertEquals(70, animal.getHunger());
        assertEquals(80, animal.getThirst());
        assertEquals(90, animal.getHealth());
    }

    @Test
    void addPersonalityTest() {
        ArrayList<String> personalities = new ArrayList<>();
        animal.setPersonalities(personalities);

        animal.addPersonality("Outgoing");
        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Outgoing");
        assertEquals(expectedVal1, animal.getPersonalities());

        animal.addPersonality("Funny");
        ArrayList<String> expectedVal2 = new ArrayList<>();
        expectedVal2.add("Outgoing");
        expectedVal2.add("Funny");
        assertEquals(expectedVal2, animal.getPersonalities());
    }

    @Test
    void removePersonalityTest() {
        ArrayList<String> personalities = new ArrayList<>(Arrays.asList("Outgoing", "Shy"));
        animal.setPersonalities(personalities);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Outgoing");
        animal.removePersonality("Shy");
        assertEquals(expectedVal1, animal.getPersonalities());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        animal.removePersonality("Outgoing");
        assertEquals(expectedVal2, animal.getPersonalities());
    }

    @Test
    void addLikesTest() {
        ArrayList<String> likes = new ArrayList<>();
        animal.setLikes(likes);

        animal.addLikes("Chicken");
        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Chicken");
        assertEquals(expectedVal1, animal.getLikes());

        animal.addLikes("Avocado");
        ArrayList<String> expectedVal2 = new ArrayList<>();
        expectedVal2.add("Chicken");
        expectedVal2.add("Avocado");
        assertEquals(expectedVal2, animal.getLikes());
    }

    @Test
    void removeLikesTest() {
        ArrayList<String> likes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        animal.setLikes(likes);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Chicken");
        animal.removeLikes("Avocado");
        assertEquals(expectedVal1, animal.getLikes());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        animal.removeLikes("Chicken");
        assertEquals(expectedVal2, animal.getLikes());
    }

    @Test
    void addDislikesTest() {
        ArrayList<String> dislikes = new ArrayList<>();
        animal.setDislikes(dislikes);

        animal.addDislikes("Chicken");
        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Chicken");
        assertEquals(expectedVal1, animal.getDislikes());

        animal.addDislikes("Avocado");
        ArrayList<String> expectedVal2 = new ArrayList<>();
        expectedVal2.add("Chicken");
        expectedVal2.add("Avocado");
        assertEquals(expectedVal2, animal.getDislikes());
    }

    @Test
    void removeDislikesTest() {
        ArrayList<String> dislikes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        animal.setDislikes(dislikes);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Chicken");
        animal.removeDislikes("Avocado");
        assertEquals(expectedVal1, animal.getDislikes());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        animal.removeDislikes("Chicken");
        assertEquals(expectedVal2, animal.getDislikes());
    }
}