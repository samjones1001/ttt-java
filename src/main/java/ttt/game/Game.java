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

    public Object[] boardState() {
        return board.getSpaces();
    }

    public Boolean gameOver() {
        return board.availableSpaces().length == 0;
    }

    public void playTurn() {
        String space = currentPlayer.get_move();
        board = board.occupySpace("X", Integer.parseInt(space));
        switchPlayers();
    }

    private void switchPlayers() {
        Player temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
    }
}
