package ttt.game;

import java.util.Arrays;

public class GameRules {
    public int[][] winConditions;

    public GameRules(Board board) {
        this.winConditions = generateWinConditions(board);
    }

    public boolean isTied(Board board) {
        return board.isFull();
    }

    public boolean isWon(Board board, String marker) {
        Object[] boardState = board.getSpaces();
        int[][] winningLines = Arrays.stream(winConditions)
                .filter(line -> isWinningLine(line, boardState) && boardState[line[0]] == marker)
                .toArray(int[][]::new);

        return winningLines.length > 0;
    }

    public boolean isGameOver(Board board, String currentPlayerMarker, String opponentMarker) {
        return isTied(board) || isWon(board, currentPlayerMarker) || isWon(board, opponentMarker);
    }

    private int[][] generateWinConditions(Board board) {
        int[][] rowIndices = board.rows();
        int[][] columnIndices = board.columns();
        int[][] diagonalIndices = board.diagonals();
        int[][] allWinningIndices = new int[rowIndices.length + columnIndices.length + diagonalIndices.length][];

        System.arraycopy(rowIndices, 0, allWinningIndices, 0, rowIndices.length);
        System.arraycopy(columnIndices, 0, allWinningIndices,rowIndices.length, columnIndices.length);
        System.arraycopy(diagonalIndices, 0, allWinningIndices, rowIndices.length + columnIndices.length, diagonalIndices.length);

        return allWinningIndices;
    }

    private boolean isWinningLine(int[] line, Object[] boardState) {
        Object firstCell = boardState[line[0]];
        Integer[] divergentCells = Arrays.stream(line)
                .filter(cell -> boardState[cell] != firstCell).boxed()
                .toArray(Integer[]::new);

        return divergentCells.length == 0;
    }
}

