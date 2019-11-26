package ttt.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameRulesTest {
    @Test
    void generatesCorrectWinConditionsForA3x3Board() {
        int[][] expectedWinConditions = new int[][] {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
        Board board = new Board();
        GameRules rules = new GameRules();

        assertArrayEquals(expectedWinConditions, rules.winConditions(board));
    }

    @Test
    void generatesCorrectWinConditionsForA4x4Board() {
        int[][] expectedWinConditions = new int[][] {{0,1,2,3}, {4,5,6,7}, {8,9,10,11}, {12,13,14,15}, {0,4,8,12}, {1,5,9,13}, {2,6,10,14}, {3,7,11,15}, {0,5,10,15}, {3,6,9,12}};
        Board board = new Board(4);
        GameRules rules = new GameRules();

        assertArrayEquals(expectedWinConditions, rules.winConditions(board));
    }

    @Test
    void aGameIsNotTiedIfSpacesRemainAvailable() {
        String[] boardState = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isTied(board));
    }

    @Test
    void aGameIsTiedIfNoSpacesRemainAvailable() {
        String[] boardState = new String[] {"X", "X", "X", "X", "X", "X", "X", "X", "X"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isTied(board));
    }

    @Test
    void aLineDoesNotWinIfNoSpacesAreOccupied() {
        String[] boardState = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isWon(board, "X"));
    }

    @Test
    void aLineDoesNotWinIfNotAllSpacesContainTheSameMarker() {
        String[] boardState = new String[] {"X", "X", "O", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isWon(board, "X"));
    }

    @Test
    void aLineWinsIfAllSpacesContainTheSameMarker() {
        String[] boardState = new String[] {"X", "X", "X", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board, "X"));
    }

    @Test
    void aVerticalLineCanWin() {
        String[] boardState = new String[] {"X", "2", "3", "X", "5", "6", "X", "8", "9"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board, "X"));
    }

    @Test
    void aDiagonalLineCanWin() {
        String[] boardState = new String[] {"X", "2", "3", "4", "X", "6", "7", "8", "X"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isWon(board, "X"));
    }

    @Test
    void aGameIsNotOverIfNeitherPlayerHasWonAndtheBoardIsNotFull() {
        String[] boardState = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertFalse(rules.isGameOver(board, "X", "O"));
    }

    @Test
    void aGameIsOverIfTheBoardIsFull() {
        String[] boardState = new String[] {"X", "X", "X", "X", "X", "X", "X", "X", "X"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isGameOver(board, "X", "O"));
    }

    @Test
    void aGameIsOverIfAPlayerHasWon() {
        String[] boardState = new String[] {"X", "X", "X", "4", "5", "6", "7", "8", "9"};
        Board board = new Board(3, boardState);
        GameRules rules = new GameRules();

        assertTrue(rules.isGameOver(board, "X", "B"));
    }
}
