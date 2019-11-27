package ttt.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Character.isDigit;

public class Board {
    private String[] spaces;
    private int size;

    public Board(int size) {
        this.size = size;
        spaces = generateInitialState();
    }

    public Board(int size, String[] state) {
        this.size = size;
        spaces = state;
    }

    public String[] getSpaces() {
        return spaces;
    }

    public ArrayList<Integer> availableSpaces() {
        return Arrays.stream(spaces).filter(space -> isDigit(space.charAt(0)))
                .map(space -> Integer.parseInt(space) - 1)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public int[][] rows() {
        return  IntStream.range(0, size)
                .mapToObj(i -> Arrays.copyOfRange(allIndices(), i * size, i * size + size))
                .toArray(int[][]::new);
    }

    public int[][] columns() {
        return IntStream.range(0, size)
                .mapToObj(columnNumber -> IntStream.range(0, size)
                        .map(cell -> (cell * size) + columnNumber)
                        .toArray())
                .toArray(int[][]::new);
    }

    public int[][] diagonals() {
        int[][] diagonalIndices = new int[2][];

        diagonalIndices[0] = leftToRightDiagonalIndices();
        diagonalIndices[1] = rightToLeftDiagonalIndices();
        return diagonalIndices;
    }

    public Board occupySpace(String symbol, Integer space) {
        String[] state = spaces.clone();
        state[space] = symbol;
        return new Board(size, state);
    }

    public Boolean isFull() {
        return availableSpaces().size() == 0;
    }

    private String[] generateInitialState() {
        spaces = new String[size*size];
        return IntStream.range(1, spaces.length+1).mapToObj(Integer::toString).toArray(String[]::new);
    }

    private int[] allIndices() {
        return IntStream.range(0, getSpaces().length).toArray();
    }

    private int[] leftToRightDiagonalIndices() {
        return IntStream.range(0, size).map(cell -> cell * (size + 1)).toArray();
    }

    private int[] rightToLeftDiagonalIndices() {
        return IntStream.range(1, size + 1).map(cell -> (cell * size) - cell).toArray();
    }
}
