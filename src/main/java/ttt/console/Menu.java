package ttt.console;

import ttt.game.Board;
import ttt.game.GameConfig;
import ttt.game.messaging.MessageBuilder;
import ttt.game.player.Player;
import ttt.game.player.PlayerFactory;
import ttt.game.UserInterface;

import java.util.HashMap;
import java.util.Map;

public class Menu {
    private UserInterface userInterface;

    private final static String welcomeMessage = "Welcome to Tic-Tac-Toe!";
    private final static String playerSelectionMessage = "Select a type for %s\n\n1) Human Player\n2) Unbeatable Computer Player";
    private final static String markerColourSelectionMessage = "Select a colour for %s's marker. Press enter to use your terminal's default colour.\n\n1) Red\n2) Green\n3) Yellow\n4) Blue\n5) Purple";
    private final static String boardSizeMessage = "What size board would you like to play on?\n\n1) 3x3\n2) 4x4";

    private static final String playerOneName = "Player 1";
    private static final String playerOneMarker = "X";
    private static final String playerTwoName = "Player 2";
    private static final String playerTwoMarker = "O";

    private static final String menuSelectionErrorMessage = "Please select an option from the menu";

    private static final String[] defaultMenuInputs = new String[]{"1", "2"};
    private final static Map<String, String> playerTypes = new HashMap<>() {
        {
            put("1", PlayerFactory.humanPlayerString);
            put("2", PlayerFactory.unbeatablePlayerString);
        }
    };

    private static final String[] markerColourMenuInputs = new String[]{"1", "2", "3", "4", "5", ""};
    private static final String ANSI_RED = "\u001B[31m%s\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m%s\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m%s\u001B[0m";
    private static final String ANSI_BLUE = "\u001B[34m%s\u001B[0m";
    private static final String ANSI_PURPLE = "\u001B[35m%s\u001B[0m";
    private static final String ANSI_DEFAULT = "%s";
    private final static Map<String, String> markerColours = new HashMap<>() {
        {
            put("1", ANSI_RED);
            put("2", ANSI_GREEN);
            put("3", ANSI_YELLOW);
            put("4", ANSI_BLUE);
            put("5", ANSI_PURPLE);
            put("", ANSI_DEFAULT);
        }
    };

    private final static Map<String, Integer> boardSizes = new HashMap<>() {
        {
            put("1", 3);
            put("2", 4);
        }
    };


    public Menu(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public GameConfig start() {
        userInterface.clear();
        userInterface.displayOutput(welcomeMessage);
        Player playerOne = setupPlayer(playerOneName, playerOneMarker);
        userInterface.clear();
        Player playerTwo = setupPlayer(playerTwoName, playerTwoMarker);
        int boardSize = selectBoardSize();

        return new GameConfig(playerOne, playerTwo, new Board(boardSize));
    }

    private Player setupPlayer(String name, String marker) {
        String playerTypeSelection = selectPlayerType(name);
        String colourisedMarker = selectPlayerMarkerColour(name, marker);
        return createPlayer(playerTypeSelection, name, colourisedMarker, userInterface);
    }

    private String selectPlayerType(String name) {
        userInterface.displayOutput(MessageBuilder.buildMessage(playerSelectionMessage, name));
        String userInput = userInterface.getAndValidateInput(defaultMenuInputs, menuSelectionErrorMessage);
        return playerTypes.get(userInput);
    }

    private String selectPlayerMarkerColour(String name, String marker) {
        userInterface.displayOutput(MessageBuilder.buildMessage(markerColourSelectionMessage, name));
        String userInput = userInterface.getAndValidateInput(markerColourMenuInputs, menuSelectionErrorMessage);
        return String.format(markerColours.get(userInput), marker);
    }

    private Player createPlayer(String type, String name, String marker, UserInterface console) {
        return PlayerFactory.create(type, name, marker, console);
    }

    private int selectBoardSize() {
        userInterface.clear();
        userInterface.displayOutput(boardSizeMessage);
        String userInput = userInterface.getAndValidateInput(defaultMenuInputs, menuSelectionErrorMessage);

        return boardSizes.get(userInput);
    }
}
