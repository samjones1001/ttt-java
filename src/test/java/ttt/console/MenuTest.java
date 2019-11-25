package ttt.console;

import org.junit.jupiter.api.Test;
import ttt.game.GameConfig;
import ttt.mocks.MockConsoleIO;
import ttt.player.HumanPlayer;
import ttt.player.UnbeatablePlayer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {
    @Test
    void allowsAUserToSelectPlayerType() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("1", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();

        assertTrue(config.getCurrentPlayer() instanceof HumanPlayer);
        assertTrue(config.getOpponent() instanceof UnbeatablePlayer);
    }

    @Test
    void constinuesToPromptUntilProvidedAValidPlayerType() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("Not Valid", "3", "1", "-5", "!", " ", "", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();

        assertTrue(config.getCurrentPlayer() instanceof HumanPlayer);
        assertTrue(config.getOpponent() instanceof UnbeatablePlayer);
    }

    @Test
    void printsWelcomeMessageAndTwoPlayerSelectionMessages() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("1", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        menu.start();

        assertEquals(3, mockConsoleIO.outputCallCount);
    }

    @Test
    void clearsOutputOnGameStartAndPlayerTwoSelection() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("1", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        menu.start();

        assertEquals(2, mockConsoleIO.clearCallCount);
    }
}
