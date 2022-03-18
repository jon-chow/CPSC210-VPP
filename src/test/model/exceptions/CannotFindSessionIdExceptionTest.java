package model.exceptions;

import model.persistence.PersistenceReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.app.PixelPetGame;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CannotFindSessionIdExceptionTest {
    File testPersistenceFile;
    PixelPetGame game;
    PersistenceReader reader;

    @BeforeEach
    void runBefore() throws IOException, CannotFindSessionIdException {
        testPersistenceFile = new File("data/persistence/ExceptionTest.json");
        game = new PixelPetGame(true, null);
    }

    @Test
    void cannotFindSessionIdExceptionPassTest() {
        try {
            reader = new PersistenceReader(testPersistenceFile, game, 10);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void cannotFindSessionIdExceptionFailTest() {
        try {
            reader = new PersistenceReader(testPersistenceFile, game, 5);
            fail();
        } catch(CannotFindSessionIdException e) {
        } catch (IOException e) {
            fail();
        }
    }
}