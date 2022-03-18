package model.persistence;

import static org.junit.jupiter.api.Assertions.*;

import model.exceptions.CannotFindSessionIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import model.pets.*;
import model.Player;
import model.goodsandservices.Item;
import ui.app.PixelPetGame;


class ReaderWriterTest {
    File testPersistenceFile;

    PixelPetGame game;

    ConverterJsonArrays converterJsonArrays;
    PersistenceReader reader;
    PersistenceWriter writer;

    @BeforeEach
    void runBefore() throws IOException, CannotFindSessionIdException {
        converterJsonArrays = new ConverterJsonArrays();
        testPersistenceFile = new File("data/persistence/ReaderWriterTest.json");
        game = new PixelPetGame(true, null);
    }

    @Test
    void ReadWriteInitialTest() {
        try {
            initialGame();
            writer = new PersistenceWriter(testPersistenceFile, game);
            reader = new PersistenceReader(testPersistenceFile, game, 10);

            Player expectedPlayer = createInitialPlayer();
            Player actualPlayer = game.getPlayer();
            assertEqualsPlayer(expectedPlayer, actualPlayer);

            Pet expectedPet = new ExampleAnimal("Aleph", "Aleph");
            Pet actualPet = game.getPet();
            assertEqualsPet(expectedPet, actualPet);
        } catch(Exception e) {
            fail();
        }
    }

    @Test
    void ReadWriteNextTest() {
        try {
            nextGame();
            writer = new PersistenceWriter(testPersistenceFile, game);
            reader = new PersistenceReader(testPersistenceFile, game, 9);

            Player expectedPlayer = createNextPlayer();
            Player actualPlayer = game.getPlayer();
            assertEqualsPlayer(expectedPlayer, actualPlayer);

            Pet expectedPet = new Dog("Shiba Inu", "Shiba Inu");
            Pet actualPet = game.getPet();
            assertEqualsPet(expectedPet, actualPet);
        } catch(Exception e) {
            fail();
        }
    }

    void assertEqualsPlayer(Player expectedPlayer, Player actualPlayer) {
        assertEquals(expectedPlayer.getInventory().size(), actualPlayer.getInventory().size());
        assertEquals(expectedPlayer.getPlayerName(), actualPlayer.getPlayerName());
        assertEquals(expectedPlayer.getMoney(), actualPlayer.getMoney());
    }

    void assertEqualsPet(Pet expectedPet, Pet actualPet) {
        assertEquals(expectedPet.getName(), actualPet.getName());
        assertEquals(expectedPet.getAnimalType(), actualPet.getAnimalType());
        assertEquals(expectedPet.getBreed(), actualPet.getBreed());
        assertEquals(expectedPet.getState(), actualPet.getState());
        assertEquals(expectedPet.getAge(), actualPet.getAge());
        assertEquals(expectedPet.getHappiness(), actualPet.getHappiness());
        assertEquals(expectedPet.getHunger(), actualPet.getHunger());
        assertEquals(expectedPet.getThirst(), actualPet.getThirst());
        assertEquals(expectedPet.getHealth(), actualPet.getHealth());

        assertEquals(expectedPet.getAllNoises().size(), actualPet.getAllNoises().size());
        assertEquals(expectedPet.getLikes().size(), actualPet.getLikes().size());
        assertEquals(expectedPet.getDislikes().size(), actualPet.getDislikes().size());
        assertEquals(expectedPet.getPersonalities().size(), actualPet.getPersonalities().size());
        assertEquals(expectedPet.getCannotHaves().size(), actualPet.getCannotHaves().size());
    }

    void initialGame() throws IOException {
        game.setPlayer(createInitialPlayer());
        game.setPet(new ExampleAnimal("Aleph", "Aleph"));
        game.setSessionId(10);
    }

    Player createInitialPlayer() throws IOException {
        Player plr = new Player();
        plr.setPlayerName("Initial");
        plr.setMoney(5000);

        ArrayList<Item> inventory = new ArrayList<>();
        inventory.add(new Item("Chicken","Food"));
        inventory.add(new Item("Bone","Toy"));
        ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(3);
        quantities.add(6);
        plr.setInventory(inventory);
        plr.setInventoryQuantity(quantities);

        return plr;
    }

    void nextGame() throws IOException {
        game.setPlayer(createNextPlayer());
        game.setPet(new Dog("Shiba Inu", "Shiba Inu"));
        game.setSessionId(9);
    }

    Player createNextPlayer() throws IOException {
        Player plr = new Player();
        plr.setPlayerName("Next");
        plr.setMoney(1000);

        ArrayList<Item> inventory = new ArrayList<>();
        inventory.add(new Item("Chicken","Food"));
        ArrayList<Integer> quantities = new ArrayList<>();
        quantities.add(1);
        plr.setInventory(inventory);
        plr.setInventoryQuantity(quantities);

        return plr;
    }
}