package ttt.player;

import ttt.game.Game;
import ttt.service.ClientService;

public class HumanPlayer implements Player {
    private String name;
    private String marker;
    private ClientService userInterface;

    private static final String moveError = "Please Provide Valid Input";

    public HumanPlayer(String name, String marker, ClientService userInterface) {
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
