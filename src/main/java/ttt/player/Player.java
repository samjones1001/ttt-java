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

    public int get_move(Game game) {
        int machineReadableIndex = Integer.parseInt(console.getAndValidateInput(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"})) -1;
        return machineReadableIndex;
    }
}
