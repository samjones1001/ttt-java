package ttt.console;

import org.junit.jupiter.api.Test;
import ttt.mocks.MockConsoleIO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleTest {
    @Test void correctlyDisplaysAnEmptyBoard() {
        String expectedOutput = " 1 | 2 | 3 \n--------------\n 4 | 5 | 6 \n--------------\n 7 | 8 | 9 ";
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);
        Object[] boardState = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        console.displayBoard(boardState);

        assertEquals(expectedOutput, mockConsoleIO.lastOutput);
    }

    @Test void correctlyDisplaysAPartiallyFilledBoard() {
        String expectedOutput = " X | O | X \n--------------\n 4 | 5 | 6 \n--------------\n 7 | 8 | 9 ";
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);
        Object[] boardState = {'X', 'O', 'X', 4, 5, 6, 7, 8, 9};

        console.displayBoard(boardState);

        assertEquals(expectedOutput, mockConsoleIO.lastOutput);
    }

    @Test void correctlyDisplaysAFilledBoard() {
        String expectedOutput = " X | O | X \n--------------\n O | X | O \n--------------\n X | O | X ";
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);
        Object[] boardState = {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'};

        console.displayBoard(boardState);

        assertEquals(expectedOutput, mockConsoleIO.lastOutput);
    }

    @Test void getsInputFromIO() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);

        assertEquals("1", console.get_input());
    }
}
