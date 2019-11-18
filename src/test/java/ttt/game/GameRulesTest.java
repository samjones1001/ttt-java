package ttt.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRulesTest {
    @Test
    public void aGameIsNotTiedIfSpacesRemainAvailable() {
        Object[] boardState = new Object[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isTied(board));
    }

    @Test
    public void aGameIsTiedIfNoSpacesRemainAvailable() {
        Object[] boardState = new Object[] {"X", "X", "X", "X", "X", "X", "X", "X", "X"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isTied(board));
    }

    @Test
    public void aLineDoesNotWinIfNoSpacesAreOccupied() {
        Object[] boardState = new Object[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isWon(board));
    }

    @Test
    public void aLineDoesNotWinIfNotAllSpacesContainTheSameMarker() {
        Object[] boardState = new Object[] {"X", "X", "O", 4, 5, 6, 7, 8, 9};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isWon(board));
    }

    @Test
    public void aLineWinsIfAllSpacesContainTheSameMarker() {
        Object[] boardState = new Object[] {"X", "X", "X", 4, 5, 6, 7, 8, 9};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board));
    }

    @Test
    public void aVerticalLineCanWin() {
        Object[] boardState = new Object[] {"X", 2, 3, "X", 5, 6, "X", 8, 9};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board));
    }

    @Test void aHorizontalLineCanWin() {
        Object[] boardState = new Object[] {"X", 2, 3, 4, "X", 6, 7, 8, "X"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board));
    }
}
