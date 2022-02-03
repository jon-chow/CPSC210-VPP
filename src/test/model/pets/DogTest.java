package model.pets;

import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DogTest {
    Dog dog;

    @BeforeEach
    void runBefore() {
        dog = new Dog("Pet1", "Shiba Inu");
    }

    @Test
    void consumeItemTest() {
        Item item1 = new Item("Chicken","Food");
        dog.setHappiness(50);
        dog.setHunger(50);
        dog.setThirst(50);
        dog.setHealth(50);

        dog.consumeItem(item1);
        assertEquals(65, dog.getHappiness());
        assertEquals(70, dog.getHunger());
        assertEquals(60, dog.getThirst());
        assertEquals(55, dog.getHealth());
    }

    @Test
    void makeNoiseTest() {
        assertNotEquals(0, dog.makeNoise().length());
    }

    @Test
    void createWasteTest() {
        dog.createWaste(1);
        assertEquals(1, dog.getNumWaste());

        dog.createWaste(4);
        assertEquals(5, dog.getNumWaste());
    }

    @Test
    void removeWasteTest() {
        dog.setNumWaste(5);

        dog.removeWaste(2);
        assertEquals(3, dog.getNumWaste());

        dog.removeWaste(3);
        assertEquals(0, dog.getNumWaste());
    }

    @Test
    void checkIfLikesTest() {
        Item chicken = new Item("Chicken", "Food");
        Item bone = new Item("Bone", "Toy");
        ArrayList<String> likes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        dog.setLikes(likes);

        assertTrue(dog.checkIfLikes(chicken));
        assertFalse(dog.checkIfLikes(bone));
    }

    @Test
    void checkIfDislikesTest() {
        Item chicken = new Item("Chicken", "Food");
        Item bone = new Item("Bone", "Toy");
        ArrayList<String> dislikes = new ArrayList<>();
        dislikes.add("Bone");
        dog.setDislikes(dislikes);

        assertFalse(dog.checkIfDislikes(chicken));
        assertTrue(dog.checkIfDislikes(bone));
    }

    @Test
    void checkIsDeadTest() {
        dog.setHunger(0);
        dog.setThirst(100);
        dog.setHealth(100);
        assertTrue(dog.checkIsDead());

        dog.setHunger(100);
        dog.setThirst(0);
        assertTrue(dog.checkIsDead());

        dog.setThirst(100);
        dog.setHealth(0);
        assertTrue(dog.checkIsDead());

        dog.setHunger(1);
        dog.setThirst(1);
        dog.setHealth(1);
        assertFalse(dog.checkIsDead());
    }

    @Test
    void alertCareStatsTest() {
        dog.setHappiness(100);
        dog.setHunger(100);
        dog.setThirst(100);
        dog.setHealth(100);
        ArrayList<Integer> careLevels1 = new ArrayList<>(Arrays.asList(100,100,100,100));
        assertEquals(careLevels1, dog.alertCareStats());


        dog.setHappiness(20);
        dog.setHunger(40);
        dog.setThirst(60);
        dog.setHealth(80);
        ArrayList<Integer> careLevels2 = new ArrayList<>(Arrays.asList(20,40,60,80));
        assertEquals(careLevels2, dog.alertCareStats());
    }

    @Test
    void decrementCareLevelsTest() {
        dog.setHappiness(100);
        dog.setHunger(100);
        dog.setThirst(100);
        dog.setHealth(100);

        dog.decrementCareLevels(10,20,30,40);
        assertEquals(90, dog.getHappiness());
        assertEquals(80, dog.getHunger());
        assertEquals(70, dog.getThirst());
        assertEquals(60, dog.getHealth());
    }

    @Test
    void incrementCareLevels() {
        dog.setHappiness(50);
        dog.setHunger(50);
        dog.setThirst(50);
        dog.setHealth(50);

        dog.incrementCareLevels(10,20,30,40);
        assertEquals(60, dog.getHappiness());
        assertEquals(70, dog.getHunger());
        assertEquals(80, dog.getThirst());
        assertEquals(90, dog.getHealth());
    }

    @Test
    void addPersonalityTest() {
        dog.addPersonality("Outgoing");
        ArrayList<String> personalities = new ArrayList<>();
        personalities.add("Outgoing");
        assertEquals(personalities, dog.getPersonalities());

        dog.addPersonality("Shy");
        personalities.add("Shy");
        assertEquals(personalities, dog.getPersonalities());
    }

    @Test
    void removePersonalityTest() {
        ArrayList<String> personalities = new ArrayList<>(Arrays.asList("Outgoing", "Shy"));
        dog.setPersonalities(personalities);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Outgoing");
        dog.removePersonality("Shy");
        assertEquals(expectedVal1, dog.getPersonalities());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        dog.removePersonality("Outgoing");
        assertEquals(expectedVal2, dog.getPersonalities());
    }

    @Test
    void addLikesTest() {
        dog.addLikes("Chicken");
        ArrayList<String> likes = new ArrayList<>();
        likes.add("Chicken");
        assertEquals(likes, dog.getLikes());

        dog.addLikes("Avocado");
        likes.add("Avocado");
        assertEquals(likes, dog.getLikes());
    }

    @Test
    void removeLikesTest() {
        ArrayList<String> likes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        dog.setLikes(likes);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Chicken");
        dog.removeLikes("Avocado");
        assertEquals(expectedVal1, dog.getLikes());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        dog.removeLikes("Chicken");
        assertEquals(expectedVal2, dog.getLikes());
    }

    @Test
    void addDislikesTest() {
        dog.addDislikes("Chicken");
        ArrayList<String> dislikes = new ArrayList<>();
        dislikes.add("Chicken");
        assertEquals(dislikes, dog.getDislikes());

        dog.addDislikes("Avocado");
        dislikes.add("Avocado");
        assertEquals(dislikes, dog.getDislikes());
    }

    @Test
    void removeDislikesTest() {
        ArrayList<String> dislikes = new ArrayList<>(Arrays.asList("Chicken", "Avocado"));
        dog.setDislikes(dislikes);

        ArrayList<String> expectedVal1 = new ArrayList<>();
        expectedVal1.add("Chicken");
        dog.removeDislikes("Avocado");
        assertEquals(expectedVal1, dog.getDislikes());

        ArrayList<String> expectedVal2 = new ArrayList<>();
        dog.removeDislikes("Chicken");
        assertEquals(expectedVal2, dog.getDislikes());
    }
}