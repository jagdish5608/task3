import java.util.Scanner;

public class SudokuSolver {

    private static final int SIZE = 9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] board = new int[SIZE][SIZE];

        System.out.println("Enter Sudoku grid (9x9). Use 0 for empty cells:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        sc.close();

        System.out.println("\nOriginal Sudoku:");
        printBoard(board);

        if (solve(board)) {
            System.out.println("\nSolved Sudoku:");
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    // Backtracking solve function
    private static boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == 0) { // empty cell
                    for (int num = 1; num <= 9; num++) {

                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;  // place number

                            if (solve(board)) {
                                return true;        // solved
                            }

                            board[row][col] = 0;    // backtrack
                        }
                    }
                    return false; // no number fits here
                }
            }
        }
        return true; // no empty cells left
    }

    // Check if num can be placed at (row, col)
    private static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check row
        for (int x = 0; x < SIZE; x++) {
            if (board[row][x] == num) return false;
        }

        // Check column
        for (int x = 0; x < SIZE; x++) {
            if (board[x][col] == num) return false;
        }

        // Check 3x3 box
        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    
                    return false;
                }
            }
        }

        return true;
    }

    // Print the Sudoku board
    private static void printBoard(int[][] board) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                System.out.print(board[r][c] + " ");
            }
            System.out.println();
        }
    }
}