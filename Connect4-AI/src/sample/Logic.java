package sample;

import java.util.*;

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


    List<Integer> values = new ArrayList<>();

    public void parseTree(Tree tree) {

        if (tree.children != null) {
            for (int i = 0; i < tree.children.length; i++) {
                parseTree(tree.children[i]);
            }
        }
        else {
            values.add(evaluation(tree.board));
        }

    }

    public int evaluation(Board b){

        int rows = b.getRow_count(), cols = b.getCol_count();
        int [][] board = b.getBoard();

        int score = 0;
        int row;
        int column;

        // check for horizontal
        for (row = 0; row < rows; row++) {
            for (column = 0; column < cols - 3; column++) {

                if (board[row][column] == 1 &&
                        board[row][column] == board[row][column + 1] &&
                        board[row][column] == board[row][column + 2] &&
                        board[row][column] == board[row][column + 3]) {
                    score -= 9;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row][column + 1] &&
                        board[row][column] == board[row][column + 2] &&
                        board[row][column] == board[row][column + 3]) {
                    score += 9;
                }

                if (board[row][column] == 1 &&
                        board[row][column] == board[row][column + 1] &&
                        board[row][column] == board[row][column + 2]) {
                    score -= 4;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row][column + 1] &&
                        board[row][column] == board[row][column + 2]) {
                    score += 4;
                }

                if (board[row][column] == 1 &&
                        board[row][column] == board[row][column + 1]) {
                    score -= 1;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row][column + 1]) {
                    score += 1;
                }
            }
        }

        // check for a vertical win
        for (row = 0; row < rows - 3; row++) {
            for (column = 0; column < cols; column++) {
                if (board[row][column] == 1 &&
                        board[row][column] == board[row + 1][column] &&
                        board[row][column] == board[row + 2][column] &&
                        board[row][column] == board[row + 3][column]) {
                    score -= 9;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row + 1][column] &&
                        board[row][column] == board[row + 2][column] &&
                        board[row][column] == board[row + 3][column]) {
                    score += 9;
                }

                if (board[row][column] == 1 &&
                        board[row][column] == board[row + 1][column] &&
                        board[row][column] == board[row + 2][column]) {
                    score -= 4;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row + 1][column] &&
                        board[row][column] == board[row + 2][column]) {
                    score += 4;
                }

                if (board[row][column] == 1 &&
                        board[row][column] == board[row + 1][column]) {
                    score -= 1;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row + 1][column]) {
                    score += 1;
                }
            }
        }

        // check for a diagonal win (positive slope)
        for (row = 0; row < rows - 3; row++) {
            for (column = 0; column < cols - 3; column++) {
                if (board[row][column] == 1 &&
                        board[row][column] == board[row + 1][column + 1] &&
                        board[row][column] == board[row + 2][column + 2] &&
                        board[row][column] == board[row + 3][column + 3]) {
                    score -= 9;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row + 1][column + 1] &&
                        board[row][column] == board[row + 2][column + 2] &&
                        board[row][column] == board[row + 3][column + 3]) {
                    score += 9;
                }

                if (board[row][column] == 1 &&
                        board[row][column] == board[row + 1][column + 1] &&
                        board[row][column] == board[row + 2][column + 2]) {
                    score -= 4;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row + 1][column + 1] &&
                        board[row][column] == board[row + 2][column + 2]) {
                    score += 4;
                }

                if (board[row][column] == 1 &&
                        board[row][column] == board[row + 1][column + 1]) {
                    score -= 1;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row + 1][column + 1]) {
                    score += 1;
                }
            }
        }

        // check for a diagonal win (negative slope)
        for (row = rows - 3; row < rows; row++) {
            for (column = 0; column < cols - 3; column++) {
                if (board[row][column] == 1 &&
                        board[row][column] == board[row - 1][column + 1] &&
                        board[row][column] == board[row - 2][column + 2] &&
                        board[row][column] == board[row - 3][column + 3]) {
                    score -= 9;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row - 1][column + 1] &&
                        board[row][column] == board[row - 2][column + 2] &&
                        board[row][column] == board[row - 3][column + 3]) {
                    score += 9;
                }

                if (board[row][column] == 1 &&
                        board[row][column] == board[row - 1][column + 1] &&
                        board[row][column] == board[row - 2][column + 2]) {
                    score -= 4;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row - 1][column + 1] &&
                        board[row][column] == board[row - 2][column + 2]) {
                    score += 4;
                }

                if (board[row][column] == 1 &&
                        board[row][column] == board[row - 1][column + 1]) {
                    score -= 1;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row - 1][column + 1]) {
                    score += 1;
                }
            }
        }


        return score;
    }

//    public static int Evalc(int board[][], boolean turn) {
//
//        int rows = 6, cols = 7;
//
//        int player = 0;
//
//        int eval = 0;
//        int points = 0;
//        int row;
//        int sq;
//        int column;
////        int board[][] = new int[6][7];
//
////        for (sq = 0; sq < 42; sq++) {
////            board[sq / 7][sq % 7] = board[sq];
////        }
//
//
//        if (turn) {
//            player = 1;
//        } else {
//            player = 2;
//        }
//
//
//        // check for horizontal
//        for (row = 0; row < rows; row++) {
//            for (column = 0; column < cols - 3; column++) {
//
//                if (board[row][column] != 0 &&
//                        board[row][column] == board[row][column + 1] &&
//                        board[row][column] == board[row][column + 2] &&
//                        board[row][column] == board[row][column + 3]) {
//                    eval = -1000;
//                }
//
//                if (board[row][column] != 0 &&
//                        board[row][column] == board[row][column + 1] &&
//                        board[row][column] == board[row][column + 2]) {
//                    eval = -1000;
//                }
//
//                if (board[row][column] != 0 &&
//                        board[row][column] == board[row][column + 1]) {
//                    eval = -1000;
//                }
//            }
//        }
//
//        // check for a vertical win
//        for (row = 0; row < rows - 3; row++) {
//            for (column = 0; column < cols; column++) {
//                if (board[row][column] != 0 &&
//                        board[row][column] == board[row + 1][column] &&
//                        board[row][column] == board[row + 2][column] &&
//                        board[row][column] == board[row + 3][column]) {
//                    eval = -1000;
//                }
//            }
//        }
//
//        // check for a diagonal win (positive slope)
//        for (row = 0; row < rows - 3; row++) {
//            for (column = 0; column < cols - 3; column++) {
//                if (board[row][column] != 0 &&
//                        board[row][column] == board[row + 1][column + 1] &&
//                        board[row][column] == board[row + 2][column + 2] &&
//                        board[row][column] == board[row + 3][column + 3]) {
//                    eval = -1000;
//                }
//            }
//        }
//
//        // check for a diagonal win (negative slope)
//        for (row = rows - 3; row < rows; row++) {
//            for (column = 0; column < cols - 3; column++) {
//                if (board[row][column] != 0 &&
//                        board[row][column] == board[row - 1][column + 1] &&
//                        board[row][column] == board[row - 2][column + 2] &&
//                        board[row][column] == board[row - 3][column + 3]) {
//                    eval = -1000;
//                }
//            }
//        }
////          System.out.print(eval);
//
//        if (player == 1) {
//
//            for (column = 0; column < cols - 2; column++) {
//                for (row = 0; row < rows; row++) {
//                    if (row % 2 != 0 && board[row][column] != 0 &&
//                            board[row][column] == board[row][column + 1] &&
//                            board[row][column] == board[row][column + 2] && board[row][column] == 1) {
//                        points = -5000;
//                    }
//                    if (row % 2 == 0 && board[row][column] != 0 &&
//                            board[row][column] == board[row][column + 1] &&
//                            board[row][column] == board[row][column + 2] && board[row][column] == 2) {
//                        points = 5000;
//                    }
//                }
//            }
//        }
//
//        // check for a vertical win
//        for (row = 1; row <= rows - 2; row++) {
//            for (column = 0; column < cols - 2; column++) {
//                if (row % 2 != 0 &&
//                        board[row - 1][column] != 0 &&
//                        board[row - 1][column] == board[row - 1][column + 1] &&
//                        board[row - 1][column] == board[row - 1][column + 2] &&
//                        board[row - 1][column] == 1) {
//                    points = -5000;
//                }
//                if (row % 2 == 0 && board[row - 1][column] != 0 &&
//                        board[row - 1][column] == board[row - 1][column + 1] &&
//                        board[row - 1][column] == board[row - 1][column + 2] &&
//                        board[row - 1][column] == 2) {
//                    points = 5000;
//                }
//            }
//        }
//
//        // check for a diagonal win (positive slope)
//        for (row = 0; row < rows - 2; row++) {
//            for (column = 0; column < cols - 2; column++) {
//                if (row % 2 != 0 && board[row][column] != 0 &&
//                        board[row][column] == board[row][column + 1] &&
//                        board[row][column] == board[row][column + 2] && board[row][column] == 1) {
//                    points = -5000;
//                }
//                if (row % 2 == 0 && board[row][column] != 0 &&
//                        board[row][column] == board[row][column + 1] &&
//                        board[row][column] == board[row][column + 2] && board[row][column] == 2) {
//                    points = 5000;
//                }
//            }
//        }
//
//        // check for a diagonal win (negative slope)
//        for (row = rows - 3; row < rows && row % 2 != 0; row++) {
//            for (column = 0; column < cols - 2; column++) {
//                if (row % 2 != 0 && board[row][column] != 0 &&
//                        board[row][column] == board[row][column + 1] &&
//                        board[row][column] == board[row][column + 2] && board[row][column] == 1) {
//                    points = -5000;
//                }
//                if (row % 2 == 0 && board[row][column] != 0 &&
//                        board[row][column] == board[row][column + 1] &&
//                        board[row][column] == board[row][column + 2] && board[row][column] == 2) {
//                    points = 5000;
//                }
//            }
//        }
//
//        if (eval == -1000) {
//            return eval;
//        } else {
//            return points;
//
//        }
//
//    }
}
