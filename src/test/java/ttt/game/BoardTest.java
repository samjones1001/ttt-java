package ttt.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test void boardIsNotFullWhenNoSpacesAreOccupied() {
        Board board = new Board();

        assertFalse(board.isFull(), "should not be full when no spaces are occupied");
    }

    @Test void boardIsNotFullWhenOnlySomeSpacesAreOccupied() {
        Object[] partiallyFilledBoard = {1, 'X', 3, 4, 'O', 6, 7, 'X', 'O'};
        Board board = new Board(partiallyFilledBoard);

        assertFalse(board.isFull(), "should not be full when only some spaces are occupied");
    }

    @Test void boardIsFullWhenAllSpacesAreOccupied() {
        Object[] filledBoard = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};
        Board board = new Board(filledBoard);

        assertTrue(board.isFull(), "should be full when all spaces are occupied");
    }
}
