package ttt.game;

import java.util.Arrays;

import static java.lang.StrictMath.sqrt;

public class GameRules {
    public int[][] winConditions(Board board) {
        int[][] rowIndices = winningRowIndices(board);
        int[][] columnIndices = winningColumnIndices(board);
        int[][] diagonalIndices = winningDiagonalIndices(board);
        int[][] allWinningIndices = new int[rowIndices.length + columnIndices.length + diagonalIndices.length][];

        System.arraycopy(rowIndices, 0, allWinningIndices, 0, rowIndices.length);
        System.arraycopy(columnIndices, 0, allWinningIndices,rowIndices.length, columnIndices.length);
        System.arraycopy(diagonalIndices, 0, allWinningIndices, rowIndices.length + columnIndices.length, diagonalIndices.length);

        return allWinningIndices;
    }

    public boolean isTied(Board board) {
        return board.isFull();
    }

    public boolean isWon(Board board, String marker) {
        Object[] boardState = board.getSpaces();
        for (int[] condition : winConditions(board)) {
            if (isWinningLine(condition, boardState) && boardState[condition[0]] == marker) {
                return true;
            }
        }
        return false;
    }

    public boolean isGameOver(Board board, String currentPlayerMarker, String opponentMarker) {
        return isTied(board) || isWon(board, currentPlayerMarker) || isWon(board, opponentMarker);
    }

    private boolean isWinningLine(int[] line, Object[] boardState) {
        boolean flag = true;
        Object first = boardState[line[0]];

        for (int index = 1; index < line.length; index++) {
            if (boardState[line[index]] != first) flag = false;
        }
        return flag;
    }

    private int[] allIndices(Board board) {
        int[] indices = new int[board.getSpaces().length];

        for(int index = 0; index < board.getSpaces().length; index++) {
            indices[index] = index;
        }
        return indices;
    }

    private int[][] winningRowIndices(Board board) {
        int rowSize = (int) sqrt(board.getSpaces().length);
        int[][] rowIndices = new int[rowSize][rowSize];
        int[] allIndices = allIndices(board);

        for (int i = 0; i < rowSize; i++) {
            rowIndices[i] = Arrays.copyOfRange(allIndices, i * rowSize, i * rowSize + rowSize);
        }
        return rowIndices;
    }

    private int[][] winningColumnIndices(Board board) {
        int columnSize = (int) sqrt(board.getSpaces().length);
        int[][] columnIndices = new int[columnSize][columnSize];
        int[][] rowIndices = winningRowIndices(board);

        for (int columnNumber = 0; columnNumber < columnSize; columnNumber++) {
            for (int index = 0; index < columnSize; index ++) {
                columnIndices[columnNumber][index] = rowIndices[index][columnNumber];
            }
        }
        return columnIndices;
    }

    private int[][] winningDiagonalIndices(Board board) {
        int diagonalSize = (int) sqrt(board.getSpaces().length);
        int[][] diagonalIndices = new int[2][diagonalSize];

        diagonalIndices[0] = leftToRightDiagonalIndices(board);
        diagonalIndices[1] = rightToLeftDiagonalIndices(board);
        return diagonalIndices;
    }

    private int[] leftToRightDiagonalIndices(Board board) {
        int diagonalSize = (int) sqrt(board.getSpaces().length);
        int[] diagonalIndices = new int[diagonalSize];
        int[][] rowIndices = winningRowIndices(board);

        for (int index = 0; index < diagonalSize; index++) {
            diagonalIndices[index] = rowIndices[index][index];
        }
        return diagonalIndices;
    }

    private int[] rightToLeftDiagonalIndices(Board board) {
        int diagonalSize = (int) sqrt(board.getSpaces().length);
        int[] diagonalIndices = new int[diagonalSize];
        int[][] rowIndices = winningRowIndices(board);

        for (int index = diagonalSize - 1; index >= 0; index--) {
            diagonalIndices[diagonalSize - (index + 1)] = rowIndices[diagonalSize - (index + 1)][index];
        }
        return diagonalIndices;
    }
}

