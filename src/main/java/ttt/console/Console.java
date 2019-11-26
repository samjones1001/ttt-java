package ttt.console;

import ttt.service.IOService;
import ttt.service.ClientService;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.StrictMath.sqrt;

public class Console implements ClientService {
    private IOService consoleIO;

    private static final String rowSplitter = " | ";
    private static final String rowPadder = " ";

    public Console() {
        consoleIO = new ConsoleIO();
    }

    public Console(IOService consoleIO) {
        this.consoleIO = consoleIO;
    }

    @Override
    public void displayBoard(Object[] boardState) {
        consoleIO.clear();
        consoleIO.output(buildBoardOutput(boardState));
    }

    @Override
    public void displayOutput(String message) {
        consoleIO.output(message);
    }

    @Override
    public String getAndValidateInput(String[] validInputs, String errorMessage) {
        String input = consoleIO.input();
        while (!isValidInput(input, validInputs)) {
            displayOutput(errorMessage);
            return getAndValidateInput(validInputs, errorMessage);
        }
        return input;
    }

    @Override
    public void clear() {
        consoleIO.clear();
    }

    private String buildBoardOutput(Object[] boardState) {
        return String.join(buildBoardSplitter(boardState), buildRowStrings(boardState));
    }

    private String buildBoardSplitter(Object[] boardState) {
        String boardSplitterCharacter = "-";
        int rowSize = (int) sqrt(boardState.length);
        int numOfSplitterCharsRequired = (rowSize * 4) + 3;
        String boardSplitter =  IntStream.range(0, numOfSplitterCharsRequired).mapToObj(i -> boardSplitterCharacter).collect(Collectors.joining(""));
        return "\n" + boardSplitter + "\n";
    }

    private String[] buildRowStrings(Object[] boardState) {
        Object[][] rows = splitToRows(boardState);
        String[] rowStrings = new String[rows.length];

        for (int index = 0; index < rowStrings.length; index++) {
            rowStrings[index] = buildRowString(rows[index]);
        }
        return rowStrings;
    }

    private Object[][] splitToRows(Object[] boardState) {
        int numOfRows = (int) sqrt(boardState.length);
        Object[][] rows = new Object[numOfRows][numOfRows];
        int count = 0;
        for (Object[] row : rows) {
            for (int cellIndex = 0; cellIndex < numOfRows; cellIndex++) {
                row[cellIndex] = boardState[count];
                count++;
            }
        }
        return rows;
    }

    private String buildRowString(Object[] rowArray) {
        String[] rowStringArray = Arrays.stream(rowArray).map(Object::toString).toArray(String[]::new);
        for (int index = 0; index < rowStringArray.length; index++) {
            if (rowStringArray[index].length() == 1) {
                rowStringArray[index] += " ";
            }
        }
        return padRow(String.join(rowSplitter, rowStringArray));
    }

    private String padRow(String rowString) {
        return rowPadder + rowString + rowPadder;
    }

    private Boolean isValidInput(String input, String[] validInputs) {
        return Arrays.asList(validInputs).contains(input);
    }
}
