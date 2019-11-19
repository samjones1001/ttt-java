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
    private Integer previousMove;

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

    public Boolean gameOver() {
        if (rules.isTied(board) || rules.isWon(board)) {
            gameOverScreen();
            return true;
        }
        return false;
    }

    public void playTurn() {
        this.console.displayBoard(this.board.getSpaces());
        this.console.displayOutput(turnStartMessage());

        int space = currentPlayer.getMove(this);
        board = board.occupySpace(currentPlayer.getMarker(), space);
        previousMove = space + 1;
        switchPlayers();
    }

    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }

    private void gameOverScreen() {
        this.console.displayBoard(this.board.getSpaces());
        String message = "Game Over - ";
        message += rules.isTied(board) ? "It's a Tie!" : winningPlayerMessage();

        console.displayOutput(message);
    }

    private String winningPlayerMessage() {
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        formatter.format("%s Won!", opponent.getName());
        return builder.toString();
    }

    private String turnStartMessage() {
        StringBuilder builder = new StringBuilder();
        Formatter formatter = new Formatter(builder);
        if (previousMove != null) {
            formatter.format("%s's turn. %s took space %s", currentPlayer.getName(), opponent.getName(), previousMove);
        } else {
            formatter.format("%s's turn.", currentPlayer.getName());
        }
        return builder.toString();
    }
}
