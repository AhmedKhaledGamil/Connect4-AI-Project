package sample;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.Arrays;


public class boardController
{

    // Defining AI LOGIC behind the game
    private Logic AI = new Logic();
    // Board values = 0 (Empty), = 1 (Player), = 2 (AI)
    private int[][] board = AI.createBoardMatrix();


    // AnchorPane used in the scene
    public AnchorPane anchorPane;
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;


    // Red and Yellow coins images
    private Image red_coin = new Image("sample/red_circle.png");
    private Image yellow_coin = new Image("sample/yellow_circle.png");


    // Player turn = true, AI turn = false, Default is player first!
    private boolean player_or_ai = true;

    // Red coin turn (true) or Yellow coin turn (false), Default Red is first!
    private boolean red_or_yellow = true;

    // Used to determine difficulty
    private String difficulty = "Random";

    // Variables used for dynamic creation
    // All calculations are based and limited to the board size
    private double center_to_center_distance = 97.5;

    private double column1_y = 505;
    private int column1_row_count = 6;
    private double column2_y = 505;
    private int column2_row_count = 6;
    private double column3_y = 505;
    private int column3_row_count = 6;
    private double column4_y = 505;
    private int column4_row_count = 6;
    private double column5_y = 505;
    private int column5_row_count = 6;
    private double column6_y = 505;
    private int column6_row_count = 6;
    private double column7_y = 505;
    private int column7_row_count = 6;


    // Always constant
    private int col1_x_pos = 5;
    private int col2_x_pos = 90;
    private int col3_x_pos = 175;
    private int col4_x_pos = 260;
    private int col5_x_pos = 345;
    private int col6_x_pos = 430;
    private int col7_x_pos = 515;


    private void toggleColor()
    {
        red_or_yellow = !red_or_yellow;
    }

    private void changeTurn() { player_or_ai = !player_or_ai;}

    public void setFirst(boolean value)
    {
        player_or_ai = value;
        if(!player_or_ai)
            AITurn();
    }


    private void adjustBoard(int col)
    {
        int board_value;
        if(player_or_ai)
            board_value = 1;
        else
            board_value = 2;
        switch (col)
        {
            case 1:
                column1_y = column1_y - center_to_center_distance;
                column1_row_count = column1_row_count - 1;
                board[column1_row_count][col-1] = board_value;
                changeTurn();
                break;
            case 2:
                column2_y = column2_y - center_to_center_distance;
                column2_row_count = column2_row_count - 1;
                board[column2_row_count][col-1] = board_value;
                changeTurn();
                break;
            case 3:
                column3_y = column3_y - center_to_center_distance;
                column3_row_count = column3_row_count - 1;
                board[column3_row_count][col-1] = board_value;
                changeTurn();
                break;
            case 4:
                column4_y = column4_y - center_to_center_distance;
                column4_row_count = column4_row_count - 1;
                board[column4_row_count][col-1] = board_value;
                changeTurn();
                break;
            case 5:
                column5_y = column5_y - center_to_center_distance;
                column5_row_count = column5_row_count - 1;
                board[column5_row_count][col-1] = board_value;
                changeTurn();
                break;
            case 6:
                column6_y = column6_y - center_to_center_distance;
                column6_row_count = column6_row_count - 1;
                board[column6_row_count][col-1] = board_value;
                changeTurn();
                break;
            case 7:
                column7_y = column7_y - center_to_center_distance;
                column7_row_count = column7_row_count - 1;
                board[column7_row_count][col-1] = board_value;
                changeTurn();
                break;
        }

    }

    private void AITurn()
    {
        if(difficulty.equals("Random"))
        {
            // Parameters used!
            int col_index = AI.random_move();
            int row_count;
            double x;

            switch (col_index)
            {
                case 1:
                    row_count = column1_row_count;
                    x = col1_x_pos;
                    insertCoin(row_count,col_index,x,column1_y);
                    adjustBoard(col_index);
                    break;
                case 2:
                    row_count = column2_row_count;
                    x = col2_x_pos;
                    insertCoin(row_count,col_index,x,column2_y);
                    adjustBoard(col_index);
                    break;
                case 3:
                    row_count = column3_row_count;
                    x = col3_x_pos;
                    insertCoin(row_count,col_index,x,column3_y);
                    adjustBoard(col_index);
                    break;
                case 4:
                    row_count = column4_row_count;
                    x = col4_x_pos;
                    insertCoin(row_count,col_index,x,column4_y);
                    adjustBoard(col_index);
                    break;
                case 5:
                    row_count = column5_row_count;
                    x = col5_x_pos;
                    insertCoin(row_count,col_index,x,column5_y);
                    adjustBoard(col_index);
                    break;
                case 6:
                    row_count = column6_row_count;
                    x = col6_x_pos;
                    insertCoin(row_count,col_index,x,column6_y);
                    adjustBoard(col_index);
                    break;
                case 7:
                    row_count = column7_row_count;
                    x = col7_x_pos;
                    insertCoin(row_count,col_index,x,column7_y);
                    adjustBoard(col_index);
                    break;
            }
        }
    }

    public void column1_pressed()
    {
        if(player_or_ai && column1_row_count > 0)
        {
            // x = 5 , col = 1 always in column1
            int col1_index = 1;
            insertCoin(column1_row_count, col1_index, col1_x_pos, column1_y);
            adjustBoard(col1_index);
            AITurn();
            System.out.println(Arrays.deepToString(board));
        }
    }

    public void column2_pressed()
    {
        if(player_or_ai && column2_row_count > 0)
        {
            // x = 90 , col = 2 always in column2
            int col2_index = 2;
            insertCoin(column2_row_count, col2_index, col2_x_pos, column2_y);
            adjustBoard(col2_index);
            AITurn();
            System.out.println(Arrays.deepToString(board));
        }
    }

    public void column3_pressed()
    {
        if(player_or_ai && column3_row_count > 0)
        {
            // x = 175 , col = 3 always in column3
            int col3_index = 3;
            insertCoin(column3_row_count, col3_index, col3_x_pos, column3_y);
            adjustBoard(col3_index);
            AITurn();
            System.out.println(Arrays.deepToString(board));
        }
    }

    public void column4_pressed()
    {
        if (player_or_ai && column4_row_count > 0)
        {
            // x = 260 , col = 4 always in column4
            int col4_index = 4;
            insertCoin(column4_row_count, col4_index, col4_x_pos, column4_y);
            adjustBoard(col4_index);
            AITurn();
            System.out.println(Arrays.deepToString(board));
        }
    }

    public void column5_pressed()
    {
        if (player_or_ai && column5_row_count > 0)
        {
            // x = 345 , col = 5 always in column5
            int col5_index = 5;
            insertCoin(column5_row_count, col5_index, col5_x_pos, column5_y);
            adjustBoard(col5_index);
            AITurn();
            System.out.println(Arrays.deepToString(board));
        }
    }

    public void column6_pressed()
    {
        if (player_or_ai && column6_row_count > 0)
        {
            // x = 430 , col = 6 always in column6
            int col6_index = 6;
            insertCoin(column6_row_count, col6_index, col6_x_pos, column6_y);
            adjustBoard(col6_index);
            AITurn();
            System.out.println(Arrays.deepToString(board));
        }
    }

    public void column7_pressed()
    {
        if (player_or_ai && column7_row_count > 0) {
            // x = 515 , col = 7 always in column7
            int col7_index = 7;
            insertCoin(column7_row_count, col7_index, col7_x_pos, column7_y);
            adjustBoard(col7_index);
            AITurn();
            System.out.println(Arrays.deepToString(board));
        }
    }


    private void insertCoin(int row, int col, double x, double y)
    {
        if (red_or_yellow) {
            create_newImageView(red_coin, row, col, x, y);
            toggleColor();
        } else {
            create_newImageView(yellow_coin, row, col, x, y);
            toggleColor();
        }
    }

    private void create_newImageView(Image image,int row, int col, double x, double y)
    {
        ImageView imageView = new ImageView();
        imageView.setId(String.format("pos_%s%s", row, col));
        double coin_height = 80;
        imageView.setFitHeight(coin_height);
        double coin_width = 80;
        imageView.setFitWidth(coin_width);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setOpacity(1); // 0 (Min) -> 1 (Max), a double value that can take fractions.
        imageView.setImage(image);
        anchorPane.getChildren().add(imageView);
        switch (col)
        {
            case 1:
                btn1.toFront();
                break;
            case 2:
                btn2.toFront();
                break;
            case 3:
                btn3.toFront();
                break;
            case 4:
                btn4.toFront();
                break;
            case 5:
                btn5.toFront();
                break;
            case 6:
                btn6.toFront();
                break;
            case 7:
                btn7.toFront();
                break;
        }
    }
}