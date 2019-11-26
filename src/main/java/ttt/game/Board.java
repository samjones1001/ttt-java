package ttt.game;

import java.util.ArrayList;

import static java.lang.Character.isDigit;

public class Board {
    private String[] spaces;
    private int size;

    public Board() {
        this.size = 3;
        spaces = generateState();
    }

    public Board(int size) {
        this.size = size;
        spaces = generateState();
    }

    public Board(int size, String[] state) {
        this.size = size;
        spaces = state;
    }

    public String[] getSpaces() {
        return spaces;
    }

    public ArrayList<Integer> availableSpaces() {
        ArrayList<Integer> availableSpaces = new ArrayList<>();
        int index = 0;

        for(String space : spaces) {
            if (isDigit(space.charAt(0))) {
                availableSpaces.add(index);
            }
            index++;
        }
        return availableSpaces;
    }

    public Board occupySpace(String symbol, Integer space) {
        String[] state = spaces.clone();
        state[space] = symbol;
        return new Board(size, state);
    }

    public Boolean isFull() {
        return availableSpaces().size() == 0;
    }

    private String[] generateState() {
        spaces = new String[size*size];
        int spaceIndex = 1;

        for(int i = 0; i < spaces.length; i++) {
            spaces[i] = Integer.toString(spaceIndex);
            spaceIndex += 1;
        }
        return spaces;
    }
}


