package ttt.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    @Test void boardHasNineEmptySpaces() {
        Board board = new Board();
        assertEquals(9, board.availableSpaces().length, "should begin with nine empty spaces");
    }

    @Test void canOccupyASpace() {
        Board board = new Board();
        Board newBoard = board.occupySpace('X', 0);
        assertEquals(8, newBoard.availableSpaces().length, "should be able to occupy a space");
    }

    @Test void occupiedSpacesAreMaintainedInState() {
        Board board = new Board();
        Board newBoard = board.occupySpace('X', 0);
        assertEquals('X', newBoard.getSpaces()[0], "should maintain occupied spaces in state");
    }
}
