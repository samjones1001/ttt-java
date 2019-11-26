package ttt.console;

import org.junit.jupiter.api.Test;
import ttt.game.Board;
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
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("1", "2", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();

        assertTrue(config.getCurrentPlayer() instanceof HumanPlayer);
        assertTrue(config.getOpponent() instanceof UnbeatablePlayer);
    }

    @Test
    void continuesToPromptUntilProvidedAValidPlayerType() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("Not Valid", "3", "1", "-5", "!", " ", "", "2", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();

        assertTrue(config.getCurrentPlayer() instanceof HumanPlayer);
        assertTrue(config.getOpponent() instanceof UnbeatablePlayer);
    }

    @Test
    void printsWelcomeMessageTwoPlayerSelectionMessagesAndBoardSelectionMessage() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("1", "2", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        menu.start();

        assertEquals(4, mockConsoleIO.outputCallCount);
    }

    @Test
    void clearsOutputOnGameStartPlayerTwoSelectionAndBoardSelection() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("1", "2", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        menu.start();

        assertEquals(3, mockConsoleIO.clearCallCount);
    }

    @Test
    void allowsUsertoSelectA3x3Grid() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("1", "1", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();
        Board board = config.getBoard();

        assertEquals(9, board.getSpaces().length);
    }

    @Test
    void allowsUsertoSelectA4x4Grid() {
        ArrayList<String> inputs = new ArrayList<String>(Arrays.asList("1", "1", "2"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);
        Menu menu = new Menu(console);

        GameConfig config = menu.start();
        Board board = config.getBoard();

        assertEquals(16, board.getSpaces().length);
    }
}
