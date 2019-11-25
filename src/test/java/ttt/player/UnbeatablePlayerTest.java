package ttt.player;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.game.Board;
import ttt.game.Game;
import ttt.mocks.MockConsoleIO;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnbeatablePlayerTest {
    @Test
    void picksTheLastSpaceIfOnlyOneSpaceRemains() {
        String[] boardState = new String[]{"O", "O", "O", "O", "O", "O", "O", "O", "9"};
        MockConsoleIO consoleIO = new MockConsoleIO();
        Console console = new Console(consoleIO);
        Player player = new UnbeatablePlayer("Player 1", "X");
        Player player2 = new UnbeatablePlayer("Player 2", "O");
        Board board = new Board(boardState);
        Game game = new Game(player, player2, board, console);

        assertEquals(8, player.getMove(game));
    }

    @Test
    void picksAWinningSpaceIfAvailable() {
        String[] boardState = new String[]{"X", "X", "3", "4", "5", "6", "7", "8", "9"};
        MockConsoleIO consoleIO = new MockConsoleIO();
        Console console = new Console(consoleIO);
        Player player = new UnbeatablePlayer("Player 1", "X");
        Player player2 = new UnbeatablePlayer("Player 2", "O");
        Board board = new Board(boardState);
        Game game = new Game(player, player2, board, console);

        assertEquals(2, player.getMove(game));
    }

    @Test
    void preventsAnOpponentFromWinningIfPossible() {
        String[] boardState = new String[]{"X", "O", "3", "4", "O", "6", "7", "8", "9"};
        MockConsoleIO consoleIO = new MockConsoleIO();
        Console console = new Console(consoleIO);
        Player player = new UnbeatablePlayer("Player 1", "X");
        Player player2 = new UnbeatablePlayer("Player 2", "O");
        Board board = new Board(boardState);
        Game game = new Game(player, player2, board, console);

        assertEquals(7, player.getMove(game));
    }

    @Test
    void prefersToWinThanPreventOpponentFromWinning() {
        String[] boardState = new String[]{"X", "X", "3", "4", "5", "6", "O", "O", "9"};
        MockConsoleIO consoleIO = new MockConsoleIO();
        Console console = new Console(consoleIO);
        Player player = new UnbeatablePlayer("Player 1", "X");
        Player player2 = new UnbeatablePlayer("Player 2", "O");
        Board board = new Board(boardState);
        Game game = new Game(player, player2, board, console);

        assertEquals(2, player.getMove(game));
    }
}
