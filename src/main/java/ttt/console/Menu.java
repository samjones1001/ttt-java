package ttt.console;

import ttt.game.Board;
import ttt.game.GameConfig;
import ttt.messaging.MessageBuilder;
import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.service.ClientService;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private ClientService userInterface;

    private final static String welcomeMessage = "Welcome to Tice-Tac-Toe!";
    private final static String playerSelectionMessage = "Select a type for %s\n\n1) Human Player\n2) Unbeatable Computer Player";

    private static final String playerOneName = "Player 1";
    private static final String playerOneMarker = "X";
    private static final String playerTwoName = "Player 2";
    private static final String playerTwoMarker = "O";

    private static final String menuSelectionErrorMessage = "Plase select an option from the menu";

    private static final String[] playerSelectionInputs = new String[]{"1", "2"};
    private final static Map<String, String> playerTypes = new HashMap<>() {
        {
            put("1", "humanPlayer");
            put("2", "unbeatablePlayer");
        }
    };


    public Menu(ClientService userInterface) {
        this.userInterface = userInterface;
    }

    public GameConfig start() {
        userInterface.clear();
        userInterface.displayOutput(welcomeMessage);
        Player playerOne = selectPlayerType(playerOneName, playerOneMarker);
        userInterface.clear();
        Player playerTwo = selectPlayerType(playerTwoName, playerTwoMarker);

        return new GameConfig(playerOne, playerTwo, new Board());
    }

    private Player selectPlayerType(String name, String marker) {
        userInterface.displayOutput(MessageBuilder.buildMessage(playerSelectionMessage, name));
        String userInput = userInterface.getAndValidateInput(playerSelectionInputs, menuSelectionErrorMessage);
        return createPlayer(playerTypes.get(userInput),name, marker, userInterface);
    }

    private Player createPlayer(String type, String name, String marker, ClientService console) {
        return PlayerFactory.create(type, name, marker, console);
    }
}
