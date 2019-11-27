package ttt.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Character.isDigit;

public class Board {
    private String[] spaces;
    private int size;

    public Board() {
        this.size = 3;
        spaces = generateInitialState();
    }

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
}
