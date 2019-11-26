package ttt.game;

import ttt.messaging.MessageBuilder;
import ttt.service.ClientService;
import ttt.player.Player;

import java.util.ArrayList;

public class Game {
    private Player currentPlayer;
    private Player opponent;
    private Board board;
    private ClientService userInterface;
    private GameRules rules;
    private String previousMove;

    private static final String gameOverBaseString = "Game Over - ";
    private static final String tieString = "It's a Tie!";
    private static final String winningPlayerBaseString = "%s Won!";
    private static final String turnStartBaseString = "%s's turn.";
    private static final String opponentMoveBaseString = " %s took space %s.";
    private static final int humanReadableIndexModifier = 1;

    public Game(Player currentPlayer, Player opponent, Board board, ClientService userInterface) {
        this.currentPlayer = currentPlayer;
        this.opponent = opponent;
        this.board = board;
        this.userInterface = userInterface;
        this.rules = new GameRules();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponent() {
        return opponent;
    }

    public Board getBoard() {
        return board;
    }

    public GameRules getRules() { return rules; }

    public Object[] boardState() {
        return board.getSpaces();
    }

    public String[] availableSpaces() {
        ArrayList<Integer> availableSpaceIndexes = board.availableSpaces();
        String[] spaceStrings = new String[availableSpaceIndexes.size()];

        for (int index = 0; index < availableSpaceIndexes.size(); index++ ) {
            spaceStrings[index] = (String.valueOf(availableSpaceIndexes.get(index) + humanReadableIndexModifier));
        }

        return spaceStrings;
    }

    public void playTurn() {
        this.userInterface.displayBoard(this.board.getSpaces());
        this.userInterface.displayOutput(turnStartMessage());

        int space = currentPlayer.getMove(this);
        board = board.occupySpace(currentPlayer.getMarker(), space);
        previousMove = Integer.toString(space + humanReadableIndexModifier);
        switchPlayers();
    }

    public Boolean gameOver() {
        if (rules.isGameOver(board, currentPlayer.getMarker(), opponent.getMarker())) {
            gameOverScreen();
            return true;
        }
        return false;
    }

    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }

    private void gameOverScreen() {
        this.userInterface.displayBoard(this.board.getSpaces());
        String message = gameOverBaseString;
        message += rules.isTied(board) ? tieString : winningPlayerMessage();

        userInterface.displayOutput(message);
    }

    private String winningPlayerMessage() {
        return MessageBuilder.buildMessage(winningPlayerBaseString, opponent.getName());
    }

    private String turnStartMessage() {
        String message = MessageBuilder.buildMessage(turnStartBaseString, currentPlayer.getName());
        if (previousMove != null) {
            message +=  opponentMoveBaseString;
            return MessageBuilder.buildMessage(message, opponent.getName(), previousMove);
        }
        return message;
    }
}
