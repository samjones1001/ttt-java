package ttt.game;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.mocks.MockConsoleIO;
import ttt.player.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRunnerTest {
    @Test
    public void createsAGameFromThePassedConfigObject() {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);
        GameRunner runner = new GameRunner(console);

        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Board board = new Board(new Object[] {'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O'});
        GameConfig config = new GameConfig(player1, player2, board);

        runner.run(config);
        Game game = runner.getGame();

        assertEquals(player1, game.getCurrentPlayer());
        assertEquals(player2, game.getOpponent());
        assertEquals(board, game.getBoard());
    }

    @Test
    public void canPlayAFullGame() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        GameRunner runner = new GameRunner(console);

        Player player1 = new Player("Player 1", "X", console);
        Player player2 = new Player("Player 2", "O", console);
        Board board = new Board();
        GameConfig config = new GameConfig(player1, player2, board);

        runner.run(config);

        assertEquals("Game Over", mockConsoleIO.lastOutput);
    }
}
