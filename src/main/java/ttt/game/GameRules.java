package ttt.game;

public class GameRules {
    private Integer[][] winConditions = new Integer[][] {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public Boolean isTied(Board board) {
        return board.isFull();
    }

    public Boolean isWon(Board board) {
        Object[] boardState = board.getSpaces();
        for (Integer[] condition : winConditions) {
            if (boardState[condition[0]] == boardState[condition[1]] &&
                boardState[condition[1]] == boardState[condition[2]]) {
                return true;
            }
        }
        return false;
    }
}
