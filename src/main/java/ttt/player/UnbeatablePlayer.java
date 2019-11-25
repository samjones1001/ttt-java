package ttt.player;

import ttt.game.Board;
import ttt.game.Game;
import ttt.game.GameRules;

public class UnbeatablePlayer implements Player {
    private String name;
    private String marker;
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

            int score = minimax(newBoard, 5, -100, 100,  opponentMarker);
            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private void setFields(Game game) {
        this.rules = game.getRules();
        this.thisPlayerMarker = game.getCurrentPlayer().getMarker();
        this.opponentMarker = game.getOpponent().getMarker();
    }

    private int minimax(Board board, int depth, int alpha, int beta, String nextMarker) {
        Integer score = getScore(board);
        if (rules.isGameOver(board, thisPlayerMarker, opponentMarker) || depth == 0) {
            return score;
        }

        if (nextMarker.equals(thisPlayerMarker)) {
            return maximise(board,depth-1, alpha, beta, nextMarker);
        } else {
            return minimise(board,depth-1, alpha, beta, nextMarker);
        }
    }

    private Integer getScore(Board board) {
        if (rules.isWon(board, thisPlayerMarker)) {
            return 1;
        } else if (rules.isWon(board, opponentMarker)) {
            return -1;
        } else {
            return 0;
        }
    }

    private int maximise(Board board, int depth, int alpha, int beta, String marker) {
        int maxScore = -100;
        for (Object move : board.availableSpaces()) {
            Board newBoard = board.occupySpace(marker, (int) move);
            int score = minimax(newBoard, depth, alpha, beta, opponentMarker);
            if (score > maxScore) {
                maxScore = score;
            }
            if (score > alpha) {
                alpha = score;
            }
            if (beta <= alpha) {
                break;
            }
        }
        return maxScore;
    }

    private int minimise(Board board, int depth, int alpha, int beta, String marker) {
        int minScore = 100;
        for (Object move : board.availableSpaces()) {
            Board newBoard = board.occupySpace(marker, (int) move);
            int score = minimax(newBoard, depth, alpha, beta, thisPlayerMarker);
            if (score < minScore) {
                minScore = score;
            }
            if (score < beta) {
                beta = score;
            }
            if (beta <= alpha) {
                break;
            }
        }
        return minScore;
    }
}
