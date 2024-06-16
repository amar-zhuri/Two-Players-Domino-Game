package tests;

import boardgame.ConsoleGame;
import boardgame.model.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleGameTest {

    @Test
    public void testParseMoveValidInput() {
        Position expected = new Position(2, 3);
        Position actual = ConsoleGame.parseMove("2 3");
        assertEquals(expected, actual);
    }

    @Test
    public void testParseMoveValidInputWithSpaces() {
        Position expected = new Position(2, 3);
        Position actual = ConsoleGame.parseMove(" 2 3 ");
        assertEquals(expected, actual);
    }

    @Test
    public void testParseMoveInvalidInputNonNumeric() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConsoleGame.parseMove("a b");
        });
    }

    @Test
    public void testParseMoveInvalidInputSingleNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConsoleGame.parseMove("2");
        });
    }

    @Test
    public void testParseMoveInvalidInputMoreThanTwoNumbers() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConsoleGame.parseMove("2 3 4");
        });
    }

    @Test
    public void testParseMoveInvalidInputSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConsoleGame.parseMove("2@ 3");
        });
    }

    @Test
    public void testParseMoveInvalidInputEmptyString() {
        assertThrows(IllegalArgumentException.class, () -> {
            ConsoleGame.parseMove("");
        });
    }
}
