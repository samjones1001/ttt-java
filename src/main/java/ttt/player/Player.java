package ttt.player;

import ttt.game.Game;
import ttt.service.ClientService;
import ttt.service.PlayerService;

public class Player implements PlayerService {
    private String name;
    private String marker;
    private ClientService userInterface;

    private static final String moveError = "Please Provide Valid Input";

    public Player(String name, String marker, ClientService userInterface) {
        this.name = name;
        this.marker = marker;
        this.userInterface = userInterface;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getMarker() {
        return marker;
    }

    @Override
    public Integer getMove(Game game) {
        String[] spaceStrings = game.availableSpaces();
        return Integer.parseInt(userInterface.getAndValidateInput(spaceStrings, moveError)) -1;
    }
}
