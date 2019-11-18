package ttt.player;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.game.Board;
import ttt.game.Game;
import ttt.mocks.MockConsoleIO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    void getsInputFromTheConsole() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO consoleIO = new MockConsoleIO(inputs);
        Console console = new Console(consoleIO);
        Player player = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Board board = new Board();
        Game game = new Game(player, player2, board, console);

        player.getMove(game);

        assertEquals(1, consoleIO.inputCallCount);
    }

    @Test
    void acceptsValidInputAndTransformsToMachineReadableIndex() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO consoleIO = new MockConsoleIO(inputs);
        Console console = new Console(consoleIO);
        Player player = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Board board = new Board();
        Game game = new Game(player, player2, board, console);

        assertEquals(0, player.getMove(game));
    }

    @Test
    void continuesToPromptIfProvidedInvalidSpaceIndex() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("-1", "0", "1000", "not valid", "1"));
        MockConsoleIO consoleIO = new MockConsoleIO(inputs);
        Console console = new Console(consoleIO);
        Player player = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Board board = new Board();
        Game game = new Game(player, player2, board, console);

        assertEquals(0, player.getMove(game));
    }

    @Test
    void continuesToPromptIfProvidedOccupiedSpaceIndex() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "1", "1", "2"));
        MockConsoleIO consoleIO = new MockConsoleIO(inputs);
        Console console = new Console(consoleIO);
        Player player = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Board board = new Board();
        Game game = new Game(player, player2, board, console);

        game.playTurn();

        assertEquals(1, player.getMove(game));
    }
}
