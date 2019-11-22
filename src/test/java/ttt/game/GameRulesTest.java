package ttt.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameRulesTest {
    @Test
    public void aGameIsNotTiedIfSpacesRemainAvailable() {
        String[] boardState = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isTied(board));
    }

    @Test
    public void aGameIsTiedIfNoSpacesRemainAvailable() {
        String[] boardState = new String[] {"X", "X", "X", "X", "X", "X", "X", "X", "X"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isTied(board));
    }

    @Test
    public void aLineDoesNotWinIfNoSpacesAreOccupied() {
        String[] boardState = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isWon(board, "X"));
    }

    @Test
    public void aLineDoesNotWinIfNotAllSpacesContainTheSameMarker() {
        String[] boardState = new String[] {"X", "X", "O", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isWon(board, "X"));
    }

    @Test
    public void aLineWinsIfAllSpacesContainTheSameMarker() {
        String[] boardState = new String[] {"X", "X", "X", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board, "X"));
    }

    @Test
    public void aVerticalLineCanWin() {
        String[] boardState = new String[] {"X", "2", "3", "X", "5", "6", "X", "8", "9"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board, "X"));
    }

    @Test
    public void aDiagonalLineCanWin() {
        String[] boardState = new String[] {"X", "2", "3", "4", "X", "6", "7", "8", "X"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board, "X"));
    }

    @Test
    public void aGameIsNotOverIfNeitherPlayerHasWonAndtheBoardIsNotFull() {
        String[] boardState = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isGameOver(board, "X", "O"));
    }

    @Test
    public void aGameIsOverIfTheBoardIsFull() {
        String[] boardState = new String[] {"X", "X", "X", "X", "X", "X", "X", "X", "X"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isGameOver(board, "X", "O"));
    }

    @Test
    public void aGameIsOverIfAPlayerHasWon() {
        String[] boardState = new String[] {"X", "X", "X", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isGameOver(board, "X", "B"));
    }
}
