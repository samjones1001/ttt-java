package ttt.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    @Test void boardHasNineEmptySpaces() {
        Board board = new Board();
        assertEquals(9, board.availableSpaces().length, "should have nine empty spaces");
    }
}
