package ttt.game;

import java.util.Arrays;

public class Board {
    private Object[] spaces = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public Object[] availableSpaces() {
        return Arrays.stream(spaces).filter(space -> space instanceof Integer).toArray();
    }
}


