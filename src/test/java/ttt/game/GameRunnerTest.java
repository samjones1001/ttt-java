package ttt.game;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.console.ConsoleGameRunner;
import ttt.mocks.MockConsoleIO;
import ttt.game.player.HumanPlayer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRunnerTest {
    @Test
    public void createsAGameFromThePassedConfigObject() {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);
        ConsoleGameRunner runner = new ConsoleGameRunner(console);

        HumanPlayer player1 = new HumanPlayer("Player 1", "X", console);
        HumanPlayer player2 = new HumanPlayer("Player 2", "O", console);
        Board board = new Board(3, new String[] {"X", "X", "X", "X", "O", "X", "X", "X", "O"});
        GameConfig config = new GameConfig(player1, player2, board);

        runner.run(config);
        Game game = runner.getGame();

        assertEquals(player1, game.getCurrentPlayer());
        assertEquals(player2, game.getOpponent());
        assertEquals(board, game.getBoard());
    }

    @Test
    public void canPlayAFullGame() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "7", "6", "9", "8"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        ConsoleGameRunner runner = new ConsoleGameRunner(console);

        HumanPlayer player1 = new HumanPlayer("Player 1", "X", console);
        HumanPlayer player2 = new HumanPlayer("Player 2", "O", console);
        Board board = new Board(3);
        GameConfig config = new GameConfig(player1, player2, board);

        runner.run(config);

        assertEquals("Game Over - It's a Tie!", mockConsoleIO.lastOutput);
    }
}
