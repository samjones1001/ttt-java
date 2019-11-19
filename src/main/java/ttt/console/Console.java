package ttt.console;

import ttt.service.IOService;

import java.util.Arrays;

public class Console {
    private IOService consoleIO;

    private static final String boardSplitter = "\n--------------\n";
    private static final String rowSplitter = " | ";
    private static final String rowPadder = " ";

    public Console() {
        consoleIO = new ConsoleIO();
    }

    public Console(IOService consoleIO) {
        this.consoleIO = consoleIO;
    }

    public void displayBoard(Object[] boardState) {
        consoleIO.clear();
        consoleIO.output(buildBoardOutput(boardState));
    }

    public void displayOutput(String message) {
        consoleIO.output(message);
    }

    public String getAndValidateInput(String[] validInputs, String errorMessage) {
        String input = consoleIO.input();
        while (!Arrays.asList(validInputs).contains(input)) {
            displayOutput(errorMessage);
            return getAndValidateInput(validInputs, errorMessage);
        }
        return input;
    }

    private String buildBoardOutput(Object[] boardState) {
        return String.join(boardSplitter, buildRowStrings(boardState));
    }

    private String[] buildRowStrings(Object[] boardState) {
        Object[][] rows = splitToRows(boardState);
        return new String[] {buildRowString(rows[0]), buildRowString(rows[1]), buildRowString(rows[2])};
    }

    private Object[][] splitToRows(Object[] boardState) {
        return new Object[][] {Arrays.copyOfRange(boardState, 0, 3),
                               Arrays.copyOfRange(boardState, 3, 6),
                               Arrays.copyOfRange(boardState, 6, 9)};
    }

    private String buildRowString(Object[] rowArray) {
        String[] rowStringArray = Arrays.stream(rowArray).map(Object::toString).toArray(String[]::new);
        return padRow(String.join(rowSplitter, rowStringArray));
    }

    private String padRow(String rowString) {
        return rowPadder + rowString + rowPadder;
    }
}
