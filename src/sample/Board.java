package sample;

public class Board {
    private int row_count = 6;
    private int col_count = 7;
    private int[][] board = new int[row_count][col_count];

    public int getRow_count() {
        return row_count;
    }

    public void setRow_count(int row_count) {
        this.row_count = row_count;
    }

    public int getCol_count() {
        return col_count;
    }

    public void setCol_count(int col_count) {
        this.col_count = col_count;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void initializeBoard() {
        for(int i = 0; i < row_count; i++)
        {
            for(int j = 0; j < col_count; j++)
            {
                this.board[i][j] = 0;
            }
        }
    }
}
