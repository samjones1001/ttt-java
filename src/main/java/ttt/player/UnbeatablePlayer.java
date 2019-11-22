package ttt.player;

import ttt.game.Board;
import ttt.game.Game;
import ttt.game.GameRules;
import ttt.service.PlayerService;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UnbeatablePlayer implements PlayerService {
    private String name;
    private String marker;
    private Game game;
    private GameRules rules;
    private String thisPlayerMarker;
    private String opponentMarker;

    public UnbeatablePlayer(String name, String marker) {
        this.name = name;
        this.marker = marker;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getMarker() {
        return this.marker;
    }

    @Override
    public Integer getMove(Game game) {
        return findBestMove(game);
    }

    private Integer findBestMove(Game game) {
        setFields(game);
        Board board = game.getBoard();
        Integer bestMove = null;
        int bestScore = -100;

        for (Integer move : board.availableSpaces()) {
            Board newBoard = board.occupySpace(thisPlayerMarker, (int) move);

            int score = minimax(newBoard, opponentMarker);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private void setFields(Game game) {
        this.game = game;
        this.rules = game.getRules();
        this.thisPlayerMarker = game.getCurrentPlayer().getMarker();
        this.opponentMarker = game.getOpponent().getMarker();
    }

    private int minimax(Board board, String nextMarker) {
        Integer score = getScore(board);
        if (score != null) {
            return score;
        }

        if (nextMarker.equals(thisPlayerMarker)) {
            return maximise(board, nextMarker);
        } else {
            return minimise(board, nextMarker);
        }
    }

    private Integer getScore(Board board) {
        if (rules.isWon(board, thisPlayerMarker)) {
            return 1;
        } else if (rules.isWon(board, opponentMarker)) {
            return -1;
        } else if (rules.isTied(board)) {
            return 0;
        } else {
            return null;
        }
    }

    private int maximise(Board board, String marker) {
        int maxScore = -100;
        for (Object move : board.availableSpaces()) {
            Board newBoard = board.occupySpace(marker, (int) move);
            int score = minimax(newBoard, opponentMarker);
            if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }

    private int minimise(Board board, String marker) {
        int minScore = 100;
        for (Object move : board.availableSpaces()) {
            Board newBoard = board.occupySpace(marker, (int) move);
            int score = minimax(newBoard, thisPlayerMarker);
            if (score < minScore) {
                minScore = score;
            }
        }
        return minScore;
    }
}
