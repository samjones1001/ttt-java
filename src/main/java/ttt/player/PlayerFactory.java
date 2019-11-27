package ttt.player;

import ttt.service.ClientService;

public class PlayerFactory {
    public final static String humanPlayerString = "humanPlayer";
    public final static String unbeatablePlayerString = "unbeatablePlayer";

    public static Player create(String type, String name, String marker, ClientService console) {
        if (humanPlayerString.equals(type)) return new HumanPlayer(name, marker, console);
        else if (unbeatablePlayerString.equals(type)) return new UnbeatablePlayer(name, marker);

        return null;
    }
}
