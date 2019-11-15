package ttt.console;

import ttt.service.IOService;

import java.util.Arrays;

public class Console {
    private IOService consoleIO;

    public Console() {
        consoleIO = new ConsoleIO();
    }

    public Console(IOService consoleIO) {
        this.consoleIO = consoleIO;
    }

    public void displayBoard(Object[] boardState) {
        consoleIO.output(buildBoardOutput(boardState));
    }

    private String buildBoardOutput(Object[] boardState) {
        return String.join("\n--------------\n", buildRowStrings(boardState));
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
        return padRow(String.join(" | ", rowStringArray));
    }

    private String padRow(String rowString) {
        return " " + rowString + " ";
    }
}
