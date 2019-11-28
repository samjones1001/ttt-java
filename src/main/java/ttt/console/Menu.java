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
    private final static String boardSizeMessage = "What size board would you like to play on?\n\n1) 3x3\n2) 4x4";

    private static final String playerOneName = "Player 1";
    private static final String playerOneMarker = "X";
    private static final String playerTwoName = "Player 2";
    private static final String playerTwoMarker = "O";

    private static final String menuSelectionErrorMessage = "Please select an option from the menu";

    private static final String[] menuInputs = new String[]{"1", "2"};
    private final static Map<String, String> playerTypes = new HashMap<>() {
        {
            put("1", PlayerFactory.humanPlayerString);
            put("2", PlayerFactory.unbeatablePlayerString);
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
        Player playerOne = selectPlayerType(playerOneName, playerOneMarker);
        userInterface.clear();
        Player playerTwo = selectPlayerType(playerTwoName, playerTwoMarker);
        int boardSize = selectBoardSize();

        return new GameConfig(playerOne, playerTwo, new Board(boardSize));
    }

    private Player selectPlayerType(String name, String marker) {
        userInterface.displayOutput(MessageBuilder.buildMessage(playerSelectionMessage, name));
        String userInput = userInterface.getAndValidateInput(menuInputs, menuSelectionErrorMessage);
        return createPlayer(playerTypes.get(userInput),name, marker, userInterface);
    }

    private Player createPlayer(String type, String name, String marker, UserInterface console) {
        return PlayerFactory.create(type, name, marker, console);
    }

    private int selectBoardSize() {
        userInterface.clear();
        userInterface.displayOutput(boardSizeMessage);
        String userInput = userInterface.getAndValidateInput(menuInputs, menuSelectionErrorMessage);

        return boardSizes.get(userInput);
    }
}
