package ttt.game;

public class GameRules {
    private Integer[][] winConditions = new Integer[][] {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public boolean isTied(Board board) {
        return board.isFull();
    }

    public boolean isWon(Board board, String marker) {
        Object[] boardState = board.getSpaces();
        for (Integer[] condition : winConditions) {
            if (boardState[condition[0]] == boardState[condition[1]] &&
                boardState[condition[1]] == boardState[condition[2]] &&
                boardState[condition[1]] == marker) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameOver(Board board, String currentPlayerMarker, String opponentMarker) {
        return isTied(board) || isWon(board, currentPlayerMarker) || isWon(board, opponentMarker);
    }
}
