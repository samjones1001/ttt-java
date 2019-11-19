package ttt.player;

import ttt.game.Game;
import ttt.service.ClientService;

public class Player {
    private String name;
    private String marker;
    private ClientService userInterface;

    private static final String moveError = "Please Provide Valid Input";

    public Player(String name, String marker, ClientService userInterface) {
        this.name = name;
        this.marker = marker;
        this.userInterface = userInterface;
    }

    public String getName() {
        return name;
    }

    public String getMarker() {
        return marker;
    }

    public int getMove(Game game) {
        String[] spaceStrings = game.availableSpaces();
        return Integer.parseInt(userInterface.getAndValidateInput(spaceStrings, moveError)) -1;
    }
}
