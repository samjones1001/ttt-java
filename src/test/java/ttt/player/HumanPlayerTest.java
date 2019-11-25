package ttt.player;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.game.Board;
import ttt.game.Game;
import ttt.mocks.MockConsoleIO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanPlayerTest {
    public Game setupGame(ArrayList<String> inputs) {
        MockConsoleIO consoleIO = new MockConsoleIO(inputs);
        Console console = new Console(consoleIO);
        HumanPlayer player = new HumanPlayer("Player 1", "X", console);
        HumanPlayer player2 = new HumanPlayer("Player 2", "O", console);
        Board board = new Board();
        return new Game(player, player2, board, console);
    }

    @Test
    void getsInputFromTheConsole() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO consoleIO = new MockConsoleIO(inputs);
        Console console = new Console(consoleIO);
        HumanPlayer player = new HumanPlayer("Player 1", "X", console);
        HumanPlayer player2 = new HumanPlayer("Player 2", "O", console);
        Board board = new Board();
        Game game = new Game(player, player2, board, console);

        player.getMove(game);

        assertEquals(1, consoleIO.inputCallCount);
    }

    @Test
    void acceptsValidInputAndTransformsToMachineReadableIndex() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        Game game = setupGame(inputs);
        Player player = game.getCurrentPlayer();

        assertEquals(0, player.getMove(game));
    }

    @Test
    void continuesToPromptIfProvidedInvalidSpaceIndex() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("-1", "0", "1000", "not valid", "1"));
        Game game = setupGame(inputs);
        Player player = game.getCurrentPlayer();

        assertEquals(0, player.getMove(game));
    }

    @Test
    void continuesToPromptIfProvidedOccupiedSpaceIndex() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "1", "1", "2"));
        Game game = setupGame(inputs);
        Player player = game.getCurrentPlayer();

        game.playTurn();

        assertEquals(1, player.getMove(game));
    }
}
