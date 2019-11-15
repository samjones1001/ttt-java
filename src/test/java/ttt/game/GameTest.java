package ttt.game;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.mocks.MockConsoleIO;
import ttt.player.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    void startsWithPlayerOnesTurn() {
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, new Board());

        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void switchesToPlayerTwosTurnAfterPlayerOnesTurn() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, new Board());

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
        Game game = new Game(player1, player2, new Board());

        game.playTurn();
        game.playTurn();

        assertEquals(player1, game.getCurrentPlayer());
    }

    @Test
    void addsAMarkerToTheBoard() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);

        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Game game = new Game(player1, player2, new Board());

        game.playTurn();

        assertEquals("X", game.boardState()[0]);
    }

    @Test
    void theGameIsNotOverWhenTheBoardStillHasFreeSpaces() {
        Object[] partiallyFilledBoard = {1, 'X', 3, 4, 'O', 6, 7, 'X', 'O'};
        Board board = new Board(partiallyFilledBoard);
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, board);

        assertFalse(game.gameOver());
    }

    @Test
    void theGameIsOverWhenTheBoardHasNoFreeSpaces() {
        Object[] filledBoard = {'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O'};
        Board board = new Board(filledBoard);
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, board);

        assertTrue(game.gameOver());
    }
}