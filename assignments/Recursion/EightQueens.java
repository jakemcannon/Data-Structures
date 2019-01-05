import java.util.Arrays;

public class EightQueens {


    //isRowValid(board, row, col) && !isUpperValid(board, row, col) || isLowerValid(board, row, col)
    public static boolean valid(int[][] board, int row, int col) {

        if(!isRowValid(board, row, col) || !isUpperValid(board, row, col) || !isLowerValid(board, row, col)) {
            return false;
        }

        return true;
    }

    //row = 0
    //col = 0

    public static boolean isRowValid(int[][] board, int row, int col) {
        for (int i = 1; col - i >= 0 ; i ++) {
            if (board[row][col -i] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isUpperValid(int[][] board, int row, int col) {
        for (int i = 1; row - i >= 0 && col - i >= 0; i++) {
            if (board[row - i][col - i] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLowerValid(int[][] board, int row, int col) {
        for (int i = 1; row + i > board.length && col - i >= 0; i++) {
            if (board[row + i][col - i] == 1) {
                return false;
            }
        }
        return true;
    }

    public static boolean solve(int[][] board, int col) {
        if (col == board.length) {
            return true;
        }
        for (int row = 0; row < board.length; row++) {
            System.out.println("row is " + row);
            if (valid(board, row, col)) {
                board[row][col] = 1;
                if (solve(board, col +1) == true) {
                    return true;
                }
                

                board[row][col] = 0;
            }
        }


        return false;
    }

    public static void main(String[] args) {
        int[][] board = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};


        solve(board,0);
        for (int[] row: board) {
            System.out.println(Arrays.toString(row));
        }
    }

}
