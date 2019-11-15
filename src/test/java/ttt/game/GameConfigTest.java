package ttt.game;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameConfigTest {
    @Test void createsAConfigObjectWithThePassedOptions() {
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Board board = new Board();

        GameConfig config = new GameConfig(player1, player2, board);

        assertEquals(player1, config.getCurrentPlayer());
        assertEquals(player2, config.getOpponent());
        assertEquals(board, config.getBoard());
    }

}
