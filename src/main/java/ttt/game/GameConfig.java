package ttt.game;

import ttt.service.PlayerService;

public class GameConfig {
    private PlayerService currentPlayer;
    private PlayerService opponent;
    private Board board;

    public GameConfig(PlayerService currentPlayer, PlayerService opponent, Board board) {
        this.currentPlayer = currentPlayer;
        this.opponent = opponent;
        this.board = board;
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
}


