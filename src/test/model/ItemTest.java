package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @BeforeEach
    void runBefore() {
        Item item1 = new Item("Chicken", "Food");
    }

    @Test
    void consumeTest() {

    }
}