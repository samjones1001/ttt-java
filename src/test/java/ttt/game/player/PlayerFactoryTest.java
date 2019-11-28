package ttt.game.player;

import org.junit.jupiter.api.Test;
import ttt.console.Console;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PlayerFactoryTest {
    @Test
    void returnsNullIfPassedAnInvalidPlayerType() {
        Console console = new Console();

        Player player = PlayerFactory.create("invalidType", "player 1", "x", console);

        assertNull(player);
    }

    @Test
    void returnsAPlayerIfPassedAValidPlayerType() {
        Console console = new Console();

        Player player = PlayerFactory.create("humanPlayer", "player 1", "x", console);
        Player player2 = PlayerFactory.create("unbeatablePlayer", "player 1", "x", console);

        assertNotNull(player);
        assertNotNull(player2);
    }
}
