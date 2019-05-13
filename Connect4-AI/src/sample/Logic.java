package sample;

import java.util.Random;

public class Logic
{

    private int row_count = 6;
    private int col_count = 7;

    public int[][] createBoardMatrix()
    {
        int [][]board = new int[row_count][col_count];
        for(int i = 0; i < row_count; i++)
        {
            for(int j = 0; j < col_count; j++)
            {
                board[i][j] = 0;
            }
        }
        return board;
    }


    public int random_move()
    {
        Random rand = new Random();
        // Obtain a number between [0-6]
        // Add 1 to get the desired range [1-7]
        return (rand.nextInt(7) + 1);
    }
}
