package ttt.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test void boardBeginsWithNineEmptySpaces() {
        Board board = new Board();

        assertEquals(9, board.availableSpaces().length);
    }

    @Test void canOccupyASpace() {
        Board board = new Board();
        Board newBoard = board.occupySpace("X", 0);

        assertEquals(8, newBoard.availableSpaces().length);
    }

    @Test void occupiedSpacesAreMaintainedInState() {
        Board board = new Board();
        Board newBoard = board.occupySpace("X", 0);

        assertEquals("X", newBoard.getSpaces()[0]);
    }

    @Test void boardIsNotFullWhenNoSpacesAreOccupied() {
        Board board = new Board();

        assertFalse(board.isFull());
    }

    @Test void boardIsNotFullWhenOnlySomeSpacesAreOccupied() {
        Object[] partiallyFilledBoard = {1, 'X', 3, 4, 'O', 6, 7, 'X', 'O'};
        Board board = new Board(partiallyFilledBoard);

        assertFalse(board.isFull());
    }

    @Test void boardIsFullWhenAllSpacesAreOccupied() {
        Object[] filledBoard = {'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};
        Board board = new Board(filledBoard);

        assertTrue(board.isFull());
    }
}
