package ttt.game;

import java.util.Arrays;

public class Board {
    private Object[] spaces;

    public Board() {
        spaces = new Object[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
    }

    private Board(Object[] state) {
        spaces = state;
    }

    public Object[] getSpaces() {
        return spaces;
    }

    public Object[] availableSpaces() {
        return Arrays.stream(spaces).filter(space -> space instanceof Integer).toArray();
    }

    public Board occupySpace(char symbol, Integer space) {
        spaces[space] = symbol;
        return new Board(spaces);
    }
}


