package ttt.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    @Test void boardBeginsWithNineEmptySpacesByDefault() {
        Board board = new Board();

        assertEquals(9, board.availableSpaces().size());
    }

    @Test void boardCanBeginWithSizexSizeEmptySpaces() {
        Board board = new Board(4);

        assertEquals(16, board.availableSpaces().size());
    }

    @Test void canOccupyASpace() {
        Board board = new Board();
        Board newBoard = board.occupySpace("X", 0);

        assertEquals(8, newBoard.availableSpaces().size());
    }

    @Test void occupiedSpacesAreRemainOccupied() {
        Board board = new Board();
        Board newBoard = board.occupySpace("X", 0);

        assertEquals("X", newBoard.getSpaces()[0]);
    }

    @Test void boardIsNotFullWhenNoSpacesAreOccupied() {
        Board board = new Board();

        assertFalse(board.isFull());
    }

    @Test void boardIsNotFullWhenOnlySomeSpacesAreOccupied() {
        String[] partiallyFilledBoard = {"1", "X", "3", "4", "O", "6", "7", "X", "O"};
        Board board = new Board(3, partiallyFilledBoard);

        assertFalse(board.isFull());
    }

    @Test void boardIsFullWhenAllSpacesAreOccupied() {
        String[] filledBoard = {"X", "X", "X", "X", "X", "X", "X", "X", "X"};
        Board board = new Board(3, filledBoard);

        assertTrue(board.isFull());
    }
}
