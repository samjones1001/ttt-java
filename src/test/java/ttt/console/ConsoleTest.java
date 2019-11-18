package ttt.console;

import org.junit.jupiter.api.Test;
import ttt.mocks.MockConsoleIO;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleTest {
    private String printedBoard(Object[] boardState) {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);

        console.displayBoard(boardState);

        return mockConsoleIO.lastOutput;
    }

    @Test void correctlyDisplaysAnEmptyBoard() {
        String expectedOutput = " 1 | 2 | 3 \n--------------\n 4 | 5 | 6 \n--------------\n 7 | 8 | 9 ";
        Object[] boardState = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        String output = printedBoard(boardState);

        assertEquals(expectedOutput, output);
    }

    @Test void correctlyDisplaysAPartiallyFilledBoard() {
        String expectedOutput = " X | O | X \n--------------\n 4 | 5 | 6 \n--------------\n 7 | 8 | 9 ";
        Object[] boardState = {'X', 'O', 'X', 4, 5, 6, 7, 8, 9};

        String output = printedBoard(boardState);

        assertEquals(expectedOutput, output);
    }

    @Test void correctlyDisplaysAFilledBoard() {
        String expectedOutput = " X | O | X \n--------------\n O | X | O \n--------------\n X | O | X ";
        Object[] boardState = {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'};

        String output = printedBoard(boardState);

        assertEquals(expectedOutput, output);
    }

    @Test void getsInputFromIO() {
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);

        assertEquals("1", console.getInput());
    }

    @Test void sendsOutputToIO() {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);

        console.displayOutput("Some Output");

        assertEquals("Some Output", mockConsoleIO.lastOutput);
    }
}
