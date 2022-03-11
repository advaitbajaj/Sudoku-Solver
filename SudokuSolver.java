public class SudokuSolver {
    static boolean solver(int[][] board) {
        int row = -1;
        int col = -1;
        int n = board.length;
        boolean empty = true;

        // to find row and column with 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 0) {
                    row = i;
                    col = j;
                    empty = false;
                    break;
                }
            }

            if (!empty) {
                break;
            }
        }

        if (empty) {                     
            return true;
        }

        // backtracking
        for (int number = 1; number <= 9; number++) {
            if (isSafe(board, row, col, number)) {
                board[row][col] = number;
                if (solver(board)) {
                    return true;
                } else {
                    board[row][col] = 0;
                }
            }
        }

        return false;
    }

    private static boolean isSafe(int[][] board, int row, int col, int element) {
        // check if row and column is safe
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == element) {
                return false;
            }
            
            if (board[row][i] == element) {
                return false;
            }
        }

        // check if grid is safe
        int sqrt = (int)(Math.sqrt(board.length));
        int rowStart = row - row % sqrt;
        int colStart = col - col % sqrt;

        for (int r = rowStart; r < rowStart + sqrt; r++) {
            for (int c = colStart; c < colStart + sqrt; c++) {
                if (board[r][c] == element) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void display(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (i!= 0 && i % 3 == 0) {
                for(int n = 0; n < board.length; n++) {
                    System.out.print("==");
                }
                System.out.println();
            }
            for (int j = 0; j < board.length; j++) {
                if (j != 0 && j % 3 == 0) {
                    System.out.print("|");
                }
                System.out.print(board[i][j] + " ");
            }            
            System.out.println();            
        }
    }
    
    public static void main(String[] args) {
        int[][] board = new int[][]{
                {0, 0, 5, 3, 0, 0, 0, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 2, 0},
                {0, 7, 0, 0, 1, 0, 5, 0, 0},
                {4, 0, 0, 0, 0, 5, 3, 0, 0},
                {0, 1, 0, 0, 7, 0, 0, 0, 6},
                {0, 0, 3, 2, 0, 0, 0, 8, 0},
                {0, 6, 0, 5, 0, 0, 0, 0, 9},
                {0, 0, 4, 0, 0, 0, 0, 3, 0},
                {0, 0, 0, 0, 0, 9, 7, 0, 0}
        };

        if (solver(board)) {
            display(board);
        } else {
            System.out.println("Cannot solve");
        }

    }
}
