package ttt.game;

import ttt.console.Console;
import ttt.player.Player;

import java.util.Formatter;

public class Game {
    private Player currentPlayer;
    private Player opponent;
    private Board board;
    private Console console;
    private GameRules rules;
    private String previousMove;

    private static String gameOverBaseString = "Game Over - ";
    private static String tieString = "It's a Tie!";
    private static String winningPlayerBaseString = "%s Won!";
    private static String turnStartBaseString = "%s's turn.";
    private static String opponentMoveBaseString = " %s took space %s.";
    private static String opponentSpaceMoveBaseString = " space %s.";

    public Game(Player currentPlayer, Player opponent, Board board, Console console) {
        this.currentPlayer = currentPlayer;
        this.opponent = opponent;
        this.board = board;
        this.console = console;
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

    public Object[] boardState() {
        return board.getSpaces();
    }

    public String[] availableSpaces() {
        Object[] availableSpaces = board.availableSpaces();
        String[] spaceStrings = new String[availableSpaces.length];

        for (int index = 0; index < availableSpaces.length; index++ ) {
            spaceStrings[index] = (String.valueOf(availableSpaces[index]));
        }

        return spaceStrings;
    }

    public void playTurn() {
        this.console.displayBoard(this.board.getSpaces());
        this.console.displayOutput(turnStartMessage());

        int space = currentPlayer.getMove(this);
        board = board.occupySpace(currentPlayer.getMarker(), space);
        previousMove = Integer.toString(space + 1);
        switchPlayers();
    }

    public Boolean gameOver() {
        if (rules.isTied(board) || rules.isWon(board)) {
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
        this.console.displayBoard(this.board.getSpaces());
        String message = gameOverBaseString;
        message += rules.isTied(board) ? tieString : winningPlayerMessage();

        console.displayOutput(message);
    }

    private String winningPlayerMessage() {
        return interpolateString(winningPlayerBaseString, opponent.getName());
    }

    private String turnStartMessage() {
        String message = interpolateString(turnStartBaseString, currentPlayer.getName());
        if (previousMove != null) {
            message +=  opponentMoveBaseString;
            return interpolateString(message, opponent.getName(), previousMove);
        }
        return message;
    }

    private String interpolateString(String baseString, String... values) {
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        formatter.format(baseString, values);
        return builder.toString();
    }
}
