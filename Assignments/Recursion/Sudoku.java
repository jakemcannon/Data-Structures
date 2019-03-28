import java.text.CharacterIterator;
import java.util.Arrays;

public class Sudoku {

    public static boolean solve(int[][] board, int row, int col) {

        //base case 1: end of a column
        if (col == board[row].length) {
            col = 0;
            row ++;
        }
        //base case 2: We have solved the entire board
        if (row == board.length) {
            return true;
        }

        //if the square already has a number in it call solve on the next square
        if (board[row][col] != 0) {
            return solve(board,row,col +1);
        }

        for (int i = 0; i <=board.length; i++) {
            if (isValid(board, row, col, i)) {
                board[row][col] = i;
                if (solve(board, row, col + 1)) {
                    return true;
                }
            }
        }
        board[row][col] = 0;
        return false;
    }

    public static boolean isValid(int[][] board, int row, int col, int testNum) {
        if (!IsValid(board, row, col, testNum)) {
            return false;
        }
        return true;
    }

    public static boolean IsValid(int[][] board, int row, int col, int testNum) {
        //row
        for (int i = 0; i < 9; i++) {
            if (testNum == board[row][i]) {
                return false;
            }
        }

        //col
        for (int i = 0; i < 9; i++) {
            if (testNum == board[i][col]) {
                return false;
            }
        }

        int x = row - row % 3;
        int y = col - col % 3;

        for (int i = x; i < x+3; i++) {
            for (int j = y; j < y+3; j++) {
                if (board[i][j] == testNum) {
                    return true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 3, 0, 0, 5, 0, 0, 0, 0},
                {4, 0, 5, 0, 0, 0, 0, 0, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 8},
                {0, 4, 0, 0, 9, 0, 3, 0, 0},
                {0, 0, 0, 7, 0, 2, 0, 0, 6},
                {0, 8, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 7, 0, 0},
                {0, 0, 1, 6, 0, 0, 2, 0, 0},
                {9, 0, 0, 3, 0, 7, 0, 4, 0}};

        solve(board,0,0);

        for (int[] row: board) {
            System.out.println(Arrays.toString(row));
        }

    }
}

