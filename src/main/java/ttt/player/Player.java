package ttt.player;

import ttt.console.Console;

public class Player {
    private String name;
    private String marker;
    private Console console;

    public Player(String name, String marker, Console console) {
        this.name = name;
        this.marker = marker;
        this.console = console;
    }

    public String get_move() {
        int machineReadableIndex = Integer.parseInt(console.get_input()) -1;
        return Integer.toString(machineReadableIndex);
    }
}
