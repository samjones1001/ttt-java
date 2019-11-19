package ttt.player;

import ttt.console.Console;
import ttt.game.Game;

public class Player {
    private String name;
    private String marker;
    private Console console;

    private static final String moveError = "Please Provide Valid Input";

    public Player(String name, String marker, Console console) {
        this.name = name;
        this.marker = marker;
        this.console = console;
    }

    public String getName() {
        return name;
    }

    public String getMarker() {
        return marker;
    }

    public int getMove(Game game) {
        String[] spaceStrings = game.availableSpaces();
        return Integer.parseInt(console.getAndValidateInput(spaceStrings, moveError)) -1;
    }
}
