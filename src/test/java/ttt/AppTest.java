package ttt;

import org.junit.jupiter.api.Test;
import ttt.mocks.MockConsoleIO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {
    @Test void createsAConfigObjectWithThePassedOptions() {
        App ttt = new App();
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        ttt.consoleIO = mockConsoleIO;
        ttt.main(new String[]{});

        assertEquals("Game Over", mockConsoleIO.lastOutput);
    }
}
