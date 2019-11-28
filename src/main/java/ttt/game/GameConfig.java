package ttt.game;

import ttt.game.player.Player;

public class GameConfig {
    private Player currentPlayer;
    private Player opponent;
    private Board board;

    public GameConfig(Player currentPlayer, Player opponent, Board board) {
        this.currentPlayer = currentPlayer;
        this.opponent = opponent;
        this.board = board;
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
}


