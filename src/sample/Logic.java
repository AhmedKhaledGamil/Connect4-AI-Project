package sample;

import java.util.Arrays;
import java.util.Random;

public class Logic
{

    // Initial Values of Alpha and Beta

    int MAX = 10000;
    int MIN = -10000;

    // AI token = 2
    int token = 2;


    public void toggleToken()
    {
        if(token == 2)
            token = 1;
        else if(token == 1)
            token = 2;
    }


    public int random_move()
    {
        Random rand = new Random();
        // Obtain a number between [0-6]
        // Add 1 to get the desired range [1-7]
        return (rand.nextInt(7) + 1);
    }


    public int[] minimax(int start, int depth, int index,int num_children_nodes,
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



    // Currently not working, in progress!
    public Board[] createPossibleBoards(final Board board, int num_children_nodes){
        Board[] possibleBoards = new Board[num_children_nodes];
        for (int i = 0; i < num_children_nodes; i++) {
            possibleBoards[i] = new Board(6,7);
            possibleBoards[i].setBoard(board);
        }
        int row;
        for (int i = 0; i < board.getCol_count() ; i++) {
            row = getColPos(possibleBoards[i],i);
            if(row != 0)
                possibleBoards[i].setXY(row,i,token);
        }
        for (int m = 0; m <num_children_nodes ; m++) {
            System.out.println(Arrays.deepToString(possibleBoards[m].getBoard()));
        }
        System.out.println("---------------------------------------------------------------------" +
                "--------------------------------------------------------------------------------");

        return possibleBoards;
    }


    public Tree createTree(Board board){
        Tree temp = new Tree(board);
        Board[] temp1 = createPossibleBoards(temp.getBoard(),7);
        for (int i = 0; i < 7; i++) {
            temp.children[i] = new Tree(temp1[i]);
            toggleToken();
            Board [] temp2 = createPossibleBoards(temp.children[i].getBoard(),7);
            for (int j = 0; j < 7; j++) {
                temp.children[i].children[j] = new Tree(temp2[j]);
                toggleToken();
                Board[] temp3 = createPossibleBoards(temp.children[i].children[j].getBoard(),7);
                for (int k = 0; k < 7; k++) {
                    temp.children[i].children[j].children[k] = new Tree(temp3[k]);
                }
            }
        }
        return temp;
    }


   int getColPos(Board board, int col){
        int pos = 0;
        int row;
        for (int i = board.getRow_count(); i > 0 ; i--) {
            row = i - 1;
            if(board.getXY(row,col) == 0) {
                pos = row;
                break;
            }
        }
        return pos;
   }
}
