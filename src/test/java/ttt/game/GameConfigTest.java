package ttt.game;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.game.player.HumanPlayer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameConfigTest {
    @Test
    void createsAConfigObjectWithThePassedOptions() {
        HumanPlayer player1 = new HumanPlayer("Player 1", "X", new Console());
        HumanPlayer player2 = new HumanPlayer("Player 2", "O", new Console());
        Board board = new Board(3);

        GameConfig config = new GameConfig(player1, player2, board);

        assertEquals(player1, config.getCurrentPlayer());
        assertEquals(player2, config.getOpponent());
        assertEquals(board, config.getBoard());
    }
}
