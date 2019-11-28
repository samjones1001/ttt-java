package ttt.console;

import ttt.game.UserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.StrictMath.sqrt;

public class Console implements UserInterface {
    private IOService consoleIO;

    private static final String rowSplitter = " | ";
    private static final String padderCharacter = " ";
    private static final String boardSplitterCharacter = "-";

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
        int rowSize = (int) sqrt(boardState.length);
        int numOfSplitterCharsRequired = (rowSize * 4) + 3;

        String boardSplitter =  IntStream.range(0, numOfSplitterCharsRequired)
                .mapToObj(integer -> boardSplitterCharacter).collect(Collectors.joining(""));
        return "\n" + boardSplitter + "\n";
    }

    private String[] buildRowStrings(Object[] boardState) {
        Object[][] rows = splitToRows(boardState);
        String[] rowStrings = Arrays.stream(rows).map(this::buildRowString)
                .toArray(String[]::new);

        return rowStrings;
    }

    private Object[][] splitToRows(Object[] boardState) {
        int numOfRows = (int) sqrt(boardState.length);
        Object[][] rows = new Object[numOfRows][numOfRows];
        ArrayList<Object> cells = new ArrayList<>(Arrays.asList(boardState));

        rows = Arrays.stream(rows).map(row -> Arrays.stream(row)
                .map(elem -> cells.remove(0)).toArray(Object[]::new)).toArray(Object[][]::new);
        return rows;
    }

    private String buildRowString(Object[] rowArray) {
        String[] rowStringArray = Arrays.stream(rowArray).map(Object::toString).map(this::padCell).toArray(String[]::new);
        return padRow(String.join(rowSplitter, rowStringArray));
    }

    private String padCell(String cell) {
        return isSingleCharacterCell(cell) ? cell + padderCharacter : cell;
    }

    private boolean isSingleCharacterCell(String cell) {
        return cell.length() == 1;
    }

    private String padRow(String rowString) {
        return padderCharacter + rowString + padderCharacter;
    }

    private Boolean isValidInput(String input, String[] validInputs) {
        return Arrays.asList(validInputs).contains(input);
    }
}
