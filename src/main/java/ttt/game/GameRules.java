package ttt.game;

import java.util.Arrays;
import java.util.stream.IntStream;

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
        int[][] winningLines = Arrays.stream(winConditions(board))
                .filter(line -> isWinningLine(line, boardState) && boardState[line[0]] == marker)
                .toArray(int[][]::new);

        return winningLines.length > 0;
    }

    public boolean isGameOver(Board board, String currentPlayerMarker, String opponentMarker) {
        return isTied(board) || isWon(board, currentPlayerMarker) || isWon(board, opponentMarker);
    }

    private boolean isWinningLine(int[] line, Object[] boardState) {
        Object firstCell = boardState[line[0]];
        Integer[] divergentCells = Arrays.stream(line)
                .filter(cell -> boardState[cell] != firstCell).boxed()
                .toArray(Integer[]::new);

        return divergentCells.length == 0;
    }

    private int[] allIndices(Board board) {
        return IntStream.range(0, board.getSpaces().length).toArray();
    }

    private int[][] winningRowIndices(Board board) {
        int rowSize = (int) sqrt(board.getSpaces().length);
        int[] allIndices = allIndices(board);

        return  IntStream.range(0, rowSize)
                .mapToObj(i -> Arrays.copyOfRange(allIndices, i * rowSize, i * rowSize + rowSize))
                .toArray(int[][]::new);
    }

    private int[][] winningColumnIndices(Board board) {
        int columnSize = (int) sqrt(board.getSpaces().length);
        return IntStream.range(0, columnSize)
                .mapToObj(columnNumber -> IntStream.range(0, columnSize)
                        .map(cell -> (cell * columnSize) + columnNumber)
                        .toArray())
                .toArray(int[][]::new);
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
        return IntStream.range(0, diagonalSize).map(cell -> cell * (diagonalSize + 1)).toArray();
    }

    private int[] rightToLeftDiagonalIndices(Board board) {
        int diagonalSize = (int) sqrt(board.getSpaces().length);
        return IntStream.range(1, diagonalSize + 1).map(cell -> (cell * diagonalSize) - cell).toArray();
    }
}

