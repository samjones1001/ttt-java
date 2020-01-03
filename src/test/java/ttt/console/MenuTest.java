package ttt.console;

import org.junit.jupiter.api.Test;
import ttt.game.Board;
import ttt.game.GameConfig;
import ttt.game.player.Player;
import ttt.mocks.MockConsoleIO;
import ttt.game.player.HumanPlayer;
import ttt.game.player.UnbeatablePlayer;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MenuTest {
    @Test
    void allowsAUserToSelectPlayerType() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "", "2", "", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();

        assertTrue(config.getCurrentPlayer() instanceof HumanPlayer);
        assertTrue(config.getOpponent() instanceof UnbeatablePlayer);
    }

    @Test
    void allowsUserToSelectMarkerColour() {
        String xWithAnsiRedModifier = "\u001B[31mX\u001B[0m";
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "1", "2", "", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();
        Player playerOne = config.getCurrentPlayer();

        assertEquals(xWithAnsiRedModifier, playerOne.getMarker());
    }

    @Test
    void markersAreUncolouredByDefault() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "", "2", "", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();
        Player playerOne = config.getCurrentPlayer();

        assertEquals("X", playerOne.getMarker());
    }

    @Test
    void continuesToPromptUntilProvidedAValidPlayerType() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("Not Valid", "3", "1", "", "-5", "!", " ", "", "2", "", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();

        assertTrue(config.getCurrentPlayer() instanceof HumanPlayer);
        assertTrue(config.getOpponent() instanceof UnbeatablePlayer);
    }

    @Test
    void printsWelcomeMessageFourPlayerSetupMessagesAndBoardSelectionMessage() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "", "2", "", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        menu.start();

        assertEquals(6, mockConsoleIO.outputCallCount);
    }

    @Test
    void clearsOutputOnGameStartPlayerTwoSelectionMarkerColourSelectionsAndBoardSelection() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "", "2", "", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        menu.start();

        assertEquals(5, mockConsoleIO.clearCallCount);
    }

    @Test
    void allowsUsertoSelectA3x3Grid() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "", "1", "", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();
        Board board = config.getBoard();

        assertEquals(9, board.getSpaces().length);
    }

    @Test
    void allowsUsertoSelectA4x4Grid() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "", "1", "", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();
        Board board = config.getBoard();

        assertEquals(16, board.getSpaces().length);
    }
}
