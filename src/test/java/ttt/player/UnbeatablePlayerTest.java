package ttt.player;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.game.Board;
import ttt.game.Game;
import ttt.mocks.MockConsoleIO;
import ttt.service.PlayerService;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnbeatablePlayerTest {
    @Test
    void picksTheLastSpaceIfOnlyOneSpaceRemains() {
        Object[] boardState = new Object[]{"x", "x", "x", "x", "x", 'x', "x", "9"};
        MockConsoleIO consoleIO = new MockConsoleIO();
        Console console = new Console(consoleIO);
        PlayerService player = new UnbeatablePlayer("Player 1", "X");
        PlayerService player2 = new UnbeatablePlayer("Player 2", "O");
        Board board = new Board(boardState);
        Game game = new Game(player, player2, board, console);

        assertEquals(8, player.getMove(game));
    }
}
