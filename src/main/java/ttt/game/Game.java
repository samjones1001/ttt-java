package ttt.game;

import ttt.player.Player;

public class Game {
    private Player currentPlayer;
    private Player opponent;
    private Board board;

    public Game(Player currentPlayer, Player opponent, Board board) {
        this.currentPlayer = currentPlayer;
        this.opponent = opponent;
        this.board = board;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void playTurn() {
        switchPlayers();
    }

    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }
}
