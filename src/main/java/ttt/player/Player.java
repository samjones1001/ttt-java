package ttt.player;

import ttt.console.Console;
import ttt.game.Game;

public class Player {
    private String name;
    private String marker;
    private Console console;

    public Player(String name, String marker, Console console) {
        this.name = name;
        this.marker = marker;
        this.console = console;
    }

    public String getMarker() {
        return marker;
    }

    public int getMove(Game game) {
        String[] spaceStrings = game.availableSpaces();
        int machineReadableIndex = Integer.parseInt(console.getAndValidateInput(spaceStrings)) -1;
        return machineReadableIndex;
    }
}
