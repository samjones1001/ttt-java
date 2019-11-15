package ttt.game;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameRunnerTest {
    @Test
    public void createsAGameFromThePassedConfigObject() {
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Object[] filledBoard = {'X', 'X', 'X', 'X', 'O', 'X', 'X', 'X', 'O'};
        Board board = new Board(filledBoard);
        GameConfig config = new GameConfig(player1, player2, board);
        GameRunner runner = new GameRunner();

        runner.run(config);
        Game game = runner.getGame();

        assertEquals(player1, game.getCurrentPlayer());
        assertEquals(player2, game.getOpponent());
        assertEquals(board, game.getBoard());
    }
}
