package ttt.game;

import ttt.messaging.MessageBuilder;
import ttt.player.Player;
import ttt.service.ClientService;
import ttt.service.PlayerService;

public class Game {
    private PlayerService currentPlayer;
    private PlayerService opponent;
    private Board board;
    private ClientService userInterface;
    private GameRules rules;
    private String previousMove;
    private MessageBuilder messageBuilder;

    private static final String gameOverBaseString = "Game Over - ";
    private static final String tieString = "It's a Tie!";
    private static final String winningPlayerBaseString = "%s Won!";
    private static final String turnStartBaseString = "%s's turn.";
    private static final String opponentMoveBaseString = " %s took space %s.";

    public Game(PlayerService currentPlayer, PlayerService opponent, Board board, ClientService userInterface) {
        this.currentPlayer = currentPlayer;
        this.opponent = opponent;
        this.board = board;
        this.userInterface = userInterface;
        this.rules = new GameRules();
        this.messageBuilder = new MessageBuilder();
    }

    public PlayerService getCurrentPlayer() {
        return currentPlayer;
    }

    public PlayerService getOpponent() {
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
        this.userInterface.displayBoard(this.board.getSpaces());
        this.userInterface.displayOutput(turnStartMessage());

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
        PlayerService temp = currentPlayer;
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
        return messageBuilder.buildMessage(winningPlayerBaseString, opponent.getName());
    }

    private String turnStartMessage() {
        String message = messageBuilder.buildMessage(turnStartBaseString, currentPlayer.getName());
        if (previousMove != null) {
            message +=  opponentMoveBaseString;
            return messageBuilder.buildMessage(message, opponent.getName(), previousMove);
        }
        return message;
    }
}
