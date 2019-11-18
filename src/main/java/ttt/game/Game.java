package ttt.game;

import ttt.console.Console;
import ttt.player.Player;

public class Game {
    private Player currentPlayer;
    private Player opponent;
    private Board board;
    private Console console;

    public Game(Player currentPlayer, Player opponent, Board board, Console console) {
        this.currentPlayer = currentPlayer;
        this.opponent = opponent;
        this.board = board;
        this.console = console;
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

    public Boolean gameOver() {
        return board.availableSpaces().length == 0;
    }

    public void playTurn() {
        this.console.displayBoard(this.board.getSpaces());
        int space = currentPlayer.get_move(this);
        board = board.occupySpace(currentPlayer.getMarker(), space);
        switchPlayers();
    }

    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }
}
