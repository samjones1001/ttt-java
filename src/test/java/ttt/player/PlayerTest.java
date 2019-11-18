package ttt.player;

import org.junit.jupiter.api.Test;
import ttt.console.Console;
import ttt.mocks.MockConsoleIO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test void getsInputFromTheConsole() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO consoleIO = new MockConsoleIO(inputs);
        Console console = new Console(consoleIO);
        Player player = new Player("Player 1", "X", console);

        player.get_move();

        assertEquals(1, consoleIO.inputCallCount);
    }

    @Test void transformsInputToMachineReadableIndex() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO consoleIO = new MockConsoleIO(inputs);
        Console console = new Console(consoleIO);
        Player player = new Player("Player 1", "X", console);

        assertEquals(0, player.get_move());
    }
}
