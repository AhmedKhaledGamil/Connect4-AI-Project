package sample;

public class Board {
    private int row_count = 6;
    private int col_count = 7;
    private int[][] class_board = new int[row_count][col_count];

    public int getRow_count() {
        return this.row_count;
    }

    public int getCol_count() {
        return this.col_count;
    }

    public int[][] getBoard() {
        return this.class_board;
    }

    public void setBoard(int[][] board) {
        this.class_board = board;
    }

    public int[][] initializeBoard() {
        for(int i = 0; i < row_count; i++)
        {
            for(int j = 0; j < col_count; j++)
            {
                this.class_board[i][j] = 0;
            }
        }
        return this.class_board;
    }

    public int getXY(int row, int col) {
        return this.class_board[row][col];
    }

    public void setXY(int row, int col, int value) {
        this.class_board[row][col] = value;
    }

    public Board(int row, int col) {
        this.row_count = row;
        this.col_count = col;
        this.class_board = initializeBoard();
    }
}
