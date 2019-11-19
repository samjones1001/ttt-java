package ttt.game;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.mocks.MockConsoleIO;
import ttt.player.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    private Game setupGame(ArrayList<String> gameInputs, Object[] boardState) {
        MockConsoleIO mockConsoleIO = new MockConsoleIO(gameInputs);
        Console console = new Console(mockConsoleIO);
        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        return new Game(player1, player2, new Board(boardState), console);
    }

    @Test
    void startsWithPlayerOnesTurn() {
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, new Board(), new Console());

        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void switchesToPlayerTwosTurnAfterPlayerOnesTurn() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, new Board(), console);

        game.playTurn();

        assertEquals(player2, game.getCurrentPlayer());
    }

    @Test
    void revertsToPlayerOnesTurnAfterPlayerTwosTurn() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);

        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, new Board(), console);

        game.playTurn();
        game.playTurn();

        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void theScreenIsClearedAtTheStartOfEachTurn() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);

        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, new Board(), console);

        game.playTurn();

        assertEquals(1, mockConsoleIO.clearCallCount);
    }

    @Test
    void displaysAMessageWithNoPreviousMoveOnFirstTurn() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, new Board(), console);

        game.playTurn();

        assertEquals("Player 1's turn.", mockConsoleIO.lastOutput);
    }

    @Test
    void displaysAMessageWithPreviousMoveOnSusequentTurns() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, new Board(), console);

        game.playTurn();
        game.playTurn();

        assertEquals("Player 2's turn. Player 1 took space 1", mockConsoleIO.lastOutput);
    }

    @Test
    void addsAMarkerToTheBoard() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        Object[] boardState = new Object[]{1,2,3,4,5,6,7,8,9};
        Game game = setupGame(inputs, boardState);

        game.playTurn();

        assertEquals("X", game.boardState()[0]);
    }

    @Test
    void theGameIsNotOverWhenTheBoardStillHasFreeSpaces() {
        ArrayList<String> inputs = new ArrayList<>();
        Object[] partiallyFilledBoard = {1, "X", 3, 4, "O", 6, 7, "X", "O"};
        Board board = new Board(partiallyFilledBoard);
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, board, new Console());

        assertFalse(game.gameOver());
    }

    @Test
    void theGameIsOverWhenTheBoardHasNoFreeSpaces() {
        Object[] filledBoard = {"X", "X", "X", "X", "O", "X", "X", "X", "O"};
        Board board = new Board(filledBoard);
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, board, new Console());

        assertTrue(game.gameOver());
    }

    @Test
    void theGameIsOverWhenTheGameHasBeenWon() {
        Object[] wonBoard = {"X", "X", "X", 4, 5, 6, 7, 8, 9};
        Board board = new Board(wonBoard);
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, board, new Console());

        assertTrue(game.gameOver());
    }

    @Test
    void aMessageIsDisplayedWhenTheGameIsTied() {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);

        Object[] filledBoard = {"X", "X", "X", "X", "O", "X", "X", "X", "O"};
        Board board = new Board(filledBoard);
        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, board, console);

        game.gameOver();

        assertEquals("Game Over - It's a Tie!", mockConsoleIO.lastOutput);
    }

    @Test
    void aMessageIsDisplayedWhenTheGameIsWon() {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);

        Object[] filledBoard = {"X", "X", "X", 4, 5, 6, 7, 8, 9};
        Board board = new Board(filledBoard);
        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, board, console);

        game.gameOver();

        assertEquals("Game Over - Player 2 Won!", mockConsoleIO.lastOutput);
    }
}