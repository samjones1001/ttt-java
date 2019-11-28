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

    @Test
    void correctlyDisplaysAnEmptyBoard() {
        String expectedOutput = " 1  | 2  | 3  \n---------------\n 4  | 5  | 6  \n---------------\n 7  | 8  | 9  ";
        Object[] boardState = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        String output = printedBoard(boardState);

        assertEquals(expectedOutput, output);
    }

    @Test
    void correctlyDisplaysA4x4Board() {
        String expectedOutput = " 1  | 2  | 3  | 4  \n-------------------\n 5  | 6  | 7  | 8  \n-------------------\n 9  | 10 | 11 | 12 \n-------------------\n 13 | 14 | 15 | 16 ";
        Object[] boardState = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        String output = printedBoard(boardState);

        assertEquals(expectedOutput, output);
    }

    @Test
    void correctlyDisplaysAPartiallyFilledBoard() {
        String expectedOutput = " X  | O  | X  \n---------------\n 4  | 5  | 6  \n---------------\n 7  | 8  | 9  ";
        Object[] boardState = {'X', 'O', 'X', 4, 5, 6, 7, 8, 9};

        String output = printedBoard(boardState);

        assertEquals(expectedOutput, output);
    }

    @Test
    void correctlyDisplaysAFilledBoard() {
        String expectedOutput = " X  | O  | X  \n---------------\n O  | X  | O  \n---------------\n X  | O  | X  ";
        Object[] boardState = {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X'};

        String output = printedBoard(boardState);

        assertEquals(expectedOutput, output);
    }

    @Test
    void returnsValidInputs() {
        String[] validInputs = new String[] {"1"};
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);

        assertEquals("1", console.getAndValidateInput(validInputs, "Please Provide Valid Input"));
    }

    @Test
    void continuesToPromptUntilValidInputProvided() {
        String[] validInputs = new String[] {"1"};
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("-1", "1000", "Invalid", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);

        assertEquals("1", console.getAndValidateInput(validInputs, "Please Provide Valid Input"));
    }

    @Test
    void displaysAnErrorMessageWhenProvidedInvalidInput() {
        String[] validInputs = new String[] {"1"};
        ArrayList<String> inputs = new ArrayList<>(Arrays.asList("Invalid", "1"));
        MockConsoleIO mockConsoleIO = new MockConsoleIO(inputs);
        Console console = new Console(mockConsoleIO);

        console.getAndValidateInput(validInputs, "Please Provide Valid Input");

        assertEquals("Please Provide Valid Input", mockConsoleIO.lastOutput);
    }

    @Test
    void sendsOutputToIO() {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);

        console.displayOutput("Some Output");

        assertEquals("Some Output", mockConsoleIO.lastOutput);
    }

    @Test
    void clearsTheOutput() {
        MockConsoleIO mockConsoleIO = new MockConsoleIO();
        Console console = new Console(mockConsoleIO);

        console.clear();

        assertEquals(1, mockConsoleIO.clearCallCount);
    }
}
