package ttt.game;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.player.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    void startsWithPlayerOnesTurn() {
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, new Board());

        assertEquals(player1, game.getCurrentPlayer(), "Should start on player 1s turn");
    }

    @Test
    void switchesToPlayerTwosTurnAfterPlayerOnesTurn() {
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, new Board());

        game.playTurn();

        assertEquals(player2, game.getCurrentPlayer(), "should switch to player 2s turn after player 1s turn");
    }

    @Test
    void revertsToPlayerOnesTurnAfterPlayerTwosTurn() {
        Player player1 = new Player("Player 1", "X", new Console());
        Player player2 = new Player("Player 2", "O", new Console());
        Game game = new Game(player1, player2, new Board());

        game.playTurn();
        game.playTurn();

        assertEquals(player1, game.getCurrentPlayer(), "should revert to player 1s turn after player 2s turn");
    }
}