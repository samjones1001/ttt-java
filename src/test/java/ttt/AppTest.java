package ttt;

import org.junit.jupiter.api.Test;
import ttt.mocks.MockConsoleIO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {
    @Test void canPlayAFullGame() {
        App ttt = new App();
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "7", "6", "9", "8"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        ttt.consoleIO = mockConsoleIO;
        ttt.main(new String[]{});

        assertEquals("Game Over - It's a Tie!", mockConsoleIO.lastOutput);
    }
}
