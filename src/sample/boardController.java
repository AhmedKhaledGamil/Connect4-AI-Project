package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class boardController
{

    // Defining AI LOGIC behind the game
    private Logic AI = new Logic();

    // Board values = 0 (Empty), = 1 (Player), = 2 (AI)
    private Board board = new Board(6,7);

    // AI Difficulty Type, Random, Evaluated!
    // Used to determine difficulty
    private String difficulty = "Evaluated";

    // Used only at AI if it plays First!, default false.
    private boolean ai_first_turn = false;




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

    // Used for draw check and winner
    private int coins_count = 42;
    private int winner;

    // Variables used for dynamic creation
    // All calculations are based and limited to the board size
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
        if(!player_or_ai) {
            ai_first_turn = true;
            AITurn();
        }
    }

    private void adjustBoard(int col)
    {
        int board_value;
        if(player_or_ai)
            board_value = 1;
        else
            board_value = 2;
        double center_to_center_distance = 97.5;
        switch (col)
        {
            case 1:
                column1_y -= center_to_center_distance;
                column1_row_count -= 1;
                board.setXY(column1_row_count,col-1,board_value);
                //printCols();
                break;
            case 2:
                column2_y -= center_to_center_distance;
                column2_row_count -= 1;
                board.setXY(column2_row_count,col-1,board_value);
                //printCols();
                break;
            case 3:
                column3_y -= center_to_center_distance;
                column3_row_count -= 1;
                board.setXY(column3_row_count,col-1,board_value);
                //printCols();
                break;
            case 4:
                column4_y -= center_to_center_distance;
                column4_row_count -= 1;
                board.setXY(column4_row_count,col-1,board_value);
                //printCols();
                break;
            case 5:
                column5_y -= center_to_center_distance;
                column5_row_count -= 1;
                board.setXY(column5_row_count,col-1,board_value);
                //printCols();
                break;
            case 6:
                column6_y -= center_to_center_distance;
                column6_row_count -= 1;
                board.setXY(column6_row_count,col-1,board_value);
                //printCols();
                break;
            case 7:
                column7_y -= center_to_center_distance;
                column7_row_count -= 1;
                board.setXY(column7_row_count,col-1,board_value);
                //printCols();
                break;
        }

        changeTurn();
    }

    private void resetSavedBoard() throws IOException {

        Path fileToDeletePath = Paths.get("board.ser");
        Files.deleteIfExists(fileToDeletePath);

    }

    private void saveBoard(Board board) {

        System.out.println(Arrays.deepToString(board.getBoard()));
        ObjectOutputStream out = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("board.ser", false);
            out = new ObjectOutputStream(fileOutputStream);

            out.writeObject(board.getBoard());
            out.flush();
            fileOutputStream.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadBoard() {


        ObjectInputStream in = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("board.ser");
            in = new ObjectInputStream(fileInputStream);
            board.setClass_board((int[][]) in.readObject());
            fileInputStream.close();
            in.close();

            System.out.println(Arrays.deepToString(board.getBoard()));

            for (int i = board.getRow_count() - 1; i >= 0; i--){
                for (int j = 0; j < board.getCol_count(); j++){

                    if (board.getBoard()[i][j] == 1){
                        red_or_yellow = false;
                        player_or_ai = true;
                        switch (j + 1) {
                            case 1:
                                insertCoin(column1_row_count, j + 1, col1_x_pos, column1_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 2:
                                insertCoin(column2_row_count, j + 1, col2_x_pos, column2_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 3:
                                insertCoin(column3_row_count, j + 1, col3_x_pos, column3_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 4:
                                insertCoin(column4_row_count, j + 1, col4_x_pos, column4_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 5:
                                insertCoin(column5_row_count, j + 1, col5_x_pos, column5_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 6:
                                insertCoin(column6_row_count, j + 1, col6_x_pos, column6_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 7:
                                insertCoin(column7_row_count, j + 1, col7_x_pos, column7_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                        }
                    }
                    else if (board.getBoard()[i][j] == 2){
                        red_or_yellow = true;
                        player_or_ai = false;
                        switch (j + 1) {
                            case 1:
                                insertCoin(column1_row_count, j + 1, col1_x_pos, column1_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 2:
                                insertCoin(column2_row_count, j + 1, col2_x_pos, column2_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 3:
                                insertCoin(column3_row_count, j + 1, col3_x_pos, column3_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 4:
                                insertCoin(column4_row_count, j + 1, col4_x_pos, column4_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 5:
                                insertCoin(column5_row_count, j + 1, col5_x_pos, column5_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 6:
                                insertCoin(column6_row_count, j + 1, col6_x_pos, column6_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                            case 7:
                                insertCoin(column7_row_count, j + 1, col7_x_pos, column7_y);
                                adjustBoard(j + 1);
                                coins_count -= 1;
                                break;
                        }
                    }
                }
            }
            red_or_yellow = false;
            player_or_ai = true;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println(Arrays.deepToString(board.getBoard()));
    }


    private void AIRandom() {
        int col_index = AI.random_move();
        while (true) {
            if (isValidMove(col_index))
                break;
            else
                col_index = AI.random_move();
        }
        int row_count;
        double x;

        switch (col_index) {
            case 1:
                row_count = column1_row_count;
                x = col1_x_pos;
                insertCoin(row_count, col_index, x, column1_y);
                adjustBoard(col_index);
                break;
            case 2:
                row_count = column2_row_count;
                x = col2_x_pos;
                insertCoin(row_count, col_index, x, column2_y);
                adjustBoard(col_index);
                break;
            case 3:
                row_count = column3_row_count;
                x = col3_x_pos;
                insertCoin(row_count, col_index, x, column3_y);
                adjustBoard(col_index);
                break;
            case 4:
                row_count = column4_row_count;
                x = col4_x_pos;
                insertCoin(row_count, col_index, x, column4_y);
                adjustBoard(col_index);
                break;
            case 5:
                row_count = column5_row_count;
                x = col5_x_pos;
                insertCoin(row_count, col_index, x, column5_y);
                adjustBoard(col_index);
                break;
            case 6:
                row_count = column6_row_count;
                x = col6_x_pos;
                insertCoin(row_count, col_index, x, column6_y);
                adjustBoard(col_index);
                break;
            case 7:
                row_count = column7_row_count;
                x = col7_x_pos;
                insertCoin(row_count, col_index, x, column7_y);
                adjustBoard(col_index);
                break;
        }
    }

    private void AITurn() {
        if(ai_first_turn){
            // Insert Coin in the middle as First AI move always!
            ai_first_turn = false;
            insertCoin(column4_row_count,4,col4_x_pos,column4_y);
            adjustBoard(4);
        }
        else {
            Tree root = AI.createTree(board);
            //AI.printBoard(board);
            //Long start = System.currentTimeMillis();
            AI.parseTree(root);
            //System.out.println("Parsed the tree in "+ (System.currentTimeMillis()-start) + " milliseconds");
            int[] value = AI.minimax(0,3,0,7,true,AI.getValues(),AI.MIN,AI.MAX);
            AI.values.clear();
            System.out.println(Arrays.toString(value));
            int x,y,z;
            z = value[1] % 7;
            y = Math.floorDiv(value[1],7) % 7;
            x = Math.floorDiv(Math.floorDiv(value[1],7),7) % 7;
            Board b = root.children[x].children[y].children[z].getBoard();
            AI.printBoard(b);
            //if(value[0] == 0){
            //    AIRandom();
            //} else {
                /*
                col1: 0->48 col2: 49->97 col3: 98->146 col4: 147->195
                col5: 196->244 col6: 245->293 col7: 294->342
                */
                if(value[1] >= 0 && value[1] <= 48)
                {   if (column1_row_count == 0){
                        AIRandom();
                    }
                    else{
                        insertCoin(column1_row_count,1,col1_x_pos,column1_y);
                        adjustBoard(1);
                    }
                }
                else if(value[1] >= 49 && value[1] <= 97)
                {
                    if (column2_row_count == 0){
                        AIRandom();
                    }
                    else{
                        insertCoin(column2_row_count,2,col2_x_pos,column2_y);
                        adjustBoard(2);
                    }
                }
                else if(value[1] >= 98 && value[1] <= 146)
                {
                    if (column3_row_count == 0){
                        AIRandom();
                    }
                    else{
                        insertCoin(column3_row_count,3,col3_x_pos,column3_y);
                        adjustBoard(3);
                    }
                }
                else if(value[1] >= 147 && value[1] <= 195)
                {
                    if (column4_row_count == 0){
                        AIRandom();
                    }
                    else{
                        insertCoin(column4_row_count,4,col4_x_pos,column4_y);
                        adjustBoard(4);
                    }
                }
                else if(value[1] >= 196 && value[1] <= 244)
                {
                    if (column5_row_count == 0){
                        AIRandom();
                    }
                    else{
                        insertCoin(column5_row_count,5,col5_x_pos,column5_y);
                        adjustBoard(5);
                    }
                }
                else if(value[1] >= 245 && value[1] <= 293)
                {
                    if (column6_row_count == 0){
                        AIRandom();
                    }
                    else{
                        insertCoin(column6_row_count,6,col6_x_pos,column6_y);
                        adjustBoard(6);
                    }
                }
                else if(value[1] >= 294 && value[1] <= 342)
                {
                    if (column7_row_count == 0){
                        AIRandom();
                    }
                    else{
                        insertCoin(column7_row_count,7, col7_x_pos,column7_y);
                        adjustBoard(7);
                    }
                }
                coins_count -= 1;
            }
        //}
    }

    public void column1_pressed()
    {
        if(player_or_ai && column1_row_count > 0)
        {
            // x = 5 , col = 1 always in column1
            int col1_index = 1;
            final KeyFrame kf1 = new KeyFrame(Duration.seconds(0),
                    event -> {
                        insertCoin(column1_row_count, col1_index, col1_x_pos, column1_y);
                        adjustBoard(col1_index);
                        coins_count -= 1;
                    });
            final KeyFrame kf2 = new KeyFrame(Duration.seconds(2),event -> gameState());
            final KeyFrame kf3 = new KeyFrame(Duration.seconds(3),event -> AITurn());
            final KeyFrame kf4 = new KeyFrame(Duration.seconds(4),event -> gameState());
            final Timeline timeline = new Timeline(kf1, kf2,kf3,kf4);
            Platform.runLater(timeline::play);
        }
    }

    public void column2_pressed()
    {
        if(player_or_ai && column2_row_count > 0)
        {
            // x = 90 , col = 2 always in column2
            int col2_index = 2;
            final KeyFrame kf1 = new KeyFrame(Duration.seconds(0),
                    event -> {
                        insertCoin(column2_row_count, col2_index, col2_x_pos, column2_y);
                        adjustBoard(col2_index);
                        coins_count -= 1;
                    });
            final KeyFrame kf2 = new KeyFrame(Duration.seconds(2),event -> gameState());
            final KeyFrame kf3 = new KeyFrame(Duration.seconds(3),event -> AITurn());
            final KeyFrame kf4 = new KeyFrame(Duration.seconds(4),event -> gameState());
            final Timeline timeline = new Timeline(kf1,kf2,kf3,kf4);
            Platform.runLater(timeline::play);
        }
    }

    public void column3_pressed()
    {
        if(player_or_ai && column3_row_count > 0)
        {
            // x = 175 , col = 3 always in column3
            int col3_index = 3;
            final KeyFrame kf1 = new KeyFrame(Duration.seconds(0),
                    event -> {
                        insertCoin(column3_row_count, col3_index, col3_x_pos, column3_y);
                        adjustBoard(col3_index);
                        coins_count -= 1;
                    });
            final KeyFrame kf2 = new KeyFrame(Duration.seconds(2),event -> gameState());
            final KeyFrame kf3 = new KeyFrame(Duration.seconds(3),event -> AITurn());
            final KeyFrame kf4 = new KeyFrame(Duration.seconds(4),event -> gameState());
            final Timeline timeline = new Timeline(kf1, kf2,kf3,kf4);
            Platform.runLater(timeline::play);
        }
    }

    public void column4_pressed()
    {
        if (player_or_ai && column4_row_count > 0)
        {
            // x = 260 , col = 4 always in column4
            int col4_index = 4;
            final KeyFrame kf1 = new KeyFrame(Duration.seconds(0),
                    event -> {
                        insertCoin(column4_row_count, col4_index, col4_x_pos, column4_y);
                        adjustBoard(col4_index);
                        coins_count -= 1;
                    });
            final KeyFrame kf2 = new KeyFrame(Duration.seconds(2),event -> gameState());
            final KeyFrame kf3 = new KeyFrame(Duration.seconds(3),event -> AITurn());
            final KeyFrame kf4 = new KeyFrame(Duration.seconds(4),event -> gameState());
            final Timeline timeline = new Timeline(kf1, kf2,kf3,kf4);
            Platform.runLater(timeline::play);
        }
    }

    public void column5_pressed()
    {
        if (player_or_ai && column5_row_count > 0)
        {
            // x = 345 , col = 5 always in column5
            int col5_index = 5;
            final KeyFrame kf1 = new KeyFrame(Duration.seconds(0),
                    event -> {
                        insertCoin(column5_row_count, col5_index, col5_x_pos, column5_y);
                        adjustBoard(col5_index);
                        coins_count -= 1;
                    });
            final KeyFrame kf2 = new KeyFrame(Duration.seconds(2),event -> gameState());
            final KeyFrame kf3 = new KeyFrame(Duration.seconds(3),event -> AITurn());
            final KeyFrame kf4 = new KeyFrame(Duration.seconds(4),event -> gameState());
            final Timeline timeline = new Timeline(kf1, kf2,kf3,kf4);
            Platform.runLater(timeline::play);
        }
    }

    public void column6_pressed()
    {
        if (player_or_ai && column6_row_count > 0)
        {
            // x = 430 , col = 6 always in column6
            int col6_index = 6;
            final KeyFrame kf1 = new KeyFrame(Duration.seconds(0),
                    event -> {
                        insertCoin(column6_row_count, col6_index, col6_x_pos, column6_y);
                        adjustBoard(col6_index);
                        coins_count -= 1;
                    });
            final KeyFrame kf2 = new KeyFrame(Duration.seconds(2),event -> gameState());
            final KeyFrame kf3 = new KeyFrame(Duration.seconds(3),event -> AITurn());
            final KeyFrame kf4 = new KeyFrame(Duration.seconds(4),event -> gameState());
            final Timeline timeline = new Timeline(kf1, kf2,kf3,kf4);
            Platform.runLater(timeline::play);
        }
    }

    public void column7_pressed()
    {
        if (player_or_ai && column7_row_count > 0) {
            // x = 515 , col = 7 always in column7
            int col7_index = 7;
            final KeyFrame kf1 = new KeyFrame(Duration.seconds(0),
                    event -> {
                        insertCoin(column7_row_count, col7_index, col7_x_pos, column7_y);
                        adjustBoard(col7_index);
                        coins_count -= 1;
                    });
            final KeyFrame kf2 = new KeyFrame(Duration.seconds(2),event -> gameState());
            final KeyFrame kf3 = new KeyFrame(Duration.seconds(3),event -> AITurn());
            final KeyFrame kf4 = new KeyFrame(Duration.seconds(4),event -> gameState());
            final Timeline timeline = new Timeline(kf1, kf2,kf3,kf4);
            Platform.runLater(timeline::play);
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


//    private void printCols()
//    {
//        System.out.println(column1_row_count);
//        System.out.println(column2_row_count);
//        System.out.println(column3_row_count);
//        System.out.println(column4_row_count);
//        System.out.println(column5_row_count);
//        System.out.println(column6_row_count);
//        System.out.println(column7_row_count);
//        System.out.println("------------------------------------");
//    }

    private boolean isValidMove(int col)
    {
        switch (col)
        {
            case 1:
                if(column1_row_count > 0)
                    return true;
                break;
            case 2:
                if(column2_row_count > 0)
                    return true;
                break;
            case 3:
                if(column3_row_count > 0)
                    return true;
                break;
            case 4:
                if(column4_row_count > 0)
                    return true;
                break;
            case 5:
                if(column5_row_count > 0)
                    return true;
                break;
            case 6:
                if(column6_row_count > 0)
                    return true;
                break;
            case 7:
                if(column7_row_count > 0)
                    return true;
                break;
        }
        return false;
    }

    // Returns 0 if draw, 1 if Player wins, 2 if AI Wins, -1 to continue.
    private int checkWin(Board b) {
        if (coins_count == 0)
            return 0;
        int[][] board = b.getBoard();
        int rows = b.getRow_count();
        int cols = b.getCol_count();
        int row;
        int column;
        // Check for horizontal wins!
        for (row = 0; row < rows; row++) {
            for (column = 0; column < cols - 3; column++) {

                if (board[row][column] == 1 &&
                        board[row][column] == board[row][column + 1] &&
                        board[row][column] == board[row][column + 2] &&
                        board[row][column] == board[row][column + 3]) {
                    return 1;
                } else if (board[row][column] == 2 &&
                        board[row][column] == board[row][column + 1] &&
                        board[row][column] == board[row][column + 2] &&
                        board[row][column] == board[row][column + 3]) {
                    return 2;
                }

            }
        }

        // check for a vertical win
        for (row = rows - 1; row > rows - 4; row--) {
            for (column = 0; column < cols; column++) {
                if (board[row][column] == 1 &&
                        board[row][column] == board[row - 1][column] &&
                        board[row][column] == board[row - 2][column] &&
                        board[row][column] == board[row - 3][column]) {
                    return 1;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row - 1][column] &&
                        board[row][column] == board[row - 2][column] &&
                        board[row][column] == board[row - 3][column]) {
                    return 2;
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
                    return 1;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row + 1][column + 1] &&
                        board[row][column] == board[row + 2][column + 2] &&
                        board[row][column] == board[row + 3][column + 3]) {
                    return 2;
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
                    return 1;
                }
                else if (board[row][column] == 2 &&
                        board[row][column] == board[row - 1][column + 1] &&
                        board[row][column] == board[row - 2][column + 2] &&
                        board[row][column] == board[row - 3][column + 3]) {
                    return 2;
                }
            }
        }

        // If no conditions are true
        return -1;
    }


    private void changeToResult() throws IOException {

        // Parent board = FXMLLoader.load(getClass().getResource("board.fxml"));
        // Those lines do the same thing

        resetSavedBoard();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("result.fxml"));
        Parent result = loader.load();


        Scene resultScene = new Scene(result, 600, 600);
        resultController controller = loader.getController();
        switch (winner){
            case 0:
                controller.textID.setText("DRAW!");
                break;
            case 1:
                controller.textID.setText("PLAYER WINS!");
                break;
            case 2:
                controller.textID.setText("AI WINS!");
        }

        List<Window> open = Stage.getWindows().filtered(Window::isShowing);
        Stage window = (Stage) open.get(0);
        window.setScene(resultScene);
        window.show();
    }

    private void gameState()
    {
        saveBoard(board);
        winner = checkWin(board);
        if(winner == 0 || winner == 1 || winner == 2) {
            try {
                changeToResult();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}


//    // Answer is 15
//    int[] values = {-7,17,11,-13,-18,18,-13,-16,15,-9,-14,7,-2,12,9,7,-11,-15,-17,10,-9,12,15,-3,13,-12,-1};

//    // Answer is 12
//    int[]values = {3,12,8,-4,9,-2,16,6,10,-2,16,6,-11,18,9,17,-1,-2,-8,-2,-16,8,15,9,-6,-14,9,12,-5,-8,-3,10,-5,1,8,-15,10,-10,-13,4
//    ,9,1,2,-2,0,-19,20,5,17,3,-10,14,-5,-13,-1,11,-17,-12,18,4,-16,3,8,-9};

//    int temp[] = AI.mini_max(0,3,0,3,true,values,AI.MIN,AI.MAX);
