package sample;

import java.util.Random;

public class Logic
{

    private int row_count = 6;
    private int col_count = 7;


    // Initial Values of Alpha and Beta

    int MAX = 10000;
    int MIN = -10000;


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



    /*
    Returns optimal value and which column
    Initiall called for root
    Maximizer at root
    Initially called for root
    Start -> Start at which level
    Depth -> Go to which level
    Index -> Which Child
    Max or Min -> true for max, false for min
    Values -> input
    Alpha and Beta used for pruning
    */

    int[] minimax(int start, int depth, int index,int num_children_nodes,
                boolean max_or_min,
                int[] values, int alpha, int beta){

        int best;
        int old_best;
        int[] temp;
        int[] return_value = new int[2];

        if (start == depth) {
            return_value[0] = values[index];
            return_value[1] = index;
            return return_value;
        }

        if(max_or_min)
        {
            best = MIN;
            // i < Number of Children Nodes
            for(int i = 0; i < num_children_nodes; i++)
            {
                temp = minimax(start+1,depth,(index * num_children_nodes + i),num_children_nodes,
                        false, values, alpha, beta);
                if(temp[0] > best)
                    return_value[1] = temp[1];
                best = Math.max(best,temp[0]);
                alpha = Math.max(alpha,best);

                // Pruning
                if (beta <= alpha)
                    break;
            }
            return_value[0] = best;
            return return_value;
        }

        else
        {
            best = MAX;
            // i < Number of Children Nodes
            for(int i = 0; i < num_children_nodes; i++)
            {
                temp = minimax(start+1,depth,(index * num_children_nodes + i),num_children_nodes,
                        true, values, alpha, beta);
                if(temp[0] < best)
                    return_value[1] = temp[1];
                best = Math.min(best,temp[0]);
                beta = Math.min(beta,best);

                // Pruning
                if (beta <= alpha)
                    break;

            }
            return_value[0] = best;
            return return_value;
        }
    }
}
