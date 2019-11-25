package ttt.game;

import java.util.ArrayList;

public class Board {
    private String[] spaces;

    public Board() {
        spaces = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    }

    public Board(String[] state) {
        spaces = state;
    }

    public String[] getSpaces() {
        return spaces;
    }

    public ArrayList<Integer> availableSpaces() {
        ArrayList<Integer> availableSpaces = new ArrayList<>();
        int index = 0;

        for(String space : spaces) {
            try {
                Integer.parseInt(space);
                availableSpaces.add(index);
            } catch (NumberFormatException err) {}

            index++;
        }
        return availableSpaces;
    }

    public Board occupySpace(String symbol, Integer space) {
        String[] state = spaces.clone();
        state[space] = symbol;
        return new Board(state);
    }

    public Boolean isFull() {
        return availableSpaces().size() == 0;
    }
}


