package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class boardController
{
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


    // Player first = 1, AI first = 0, Default is player first!
    private int player_or_ai = 1;

    // Red coin turn (true) or Yellow coin turn (false), Default Red is first!
    private boolean red_or_yellow = true;

    // Variables used for dynamic creation
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




    public void onMouseClick()
    {
        System.out.println("Image clicked!");
        System.out.println(player_or_ai);
    }

    private void toggleColor()
    {
        red_or_yellow = !red_or_yellow;
    }

    public void setFirst(int value)
    {
        player_or_ai = value;
    }

    public void column1_pressed(ActionEvent event)
    {
        // x = 5 , col = 1 always in column1
        if (red_or_yellow)
        {
            create_newImageView(red_coin,column1_row_count,1, 5, column1_y);
            toggleColor();
        }
        else
        {
            create_newImageView(yellow_coin,column1_row_count,1, 5, column1_y);
            toggleColor();
        }
        column1_y = column1_y - 97.5;
        column1_row_count = column1_row_count - 1;
    }

    public void column2_pressed(ActionEvent event)
    {
        // x = 90 , col = 2 always in column2
        if (red_or_yellow)
        {
            create_newImageView(red_coin,column2_row_count,2, 90, column2_y);
            toggleColor();
        }
        else
        {
            create_newImageView(yellow_coin,column2_row_count,2, 90, column2_y);
            toggleColor();
        }
        column2_y = column2_y - 97.5;
        column2_row_count = column2_row_count - 1;
    }

    public void column3_pressed(ActionEvent event)
    {
        // x = 175 , col = 3 always in column3
        if (red_or_yellow)
        {
            create_newImageView(red_coin,column3_row_count,3, 175, column3_y);
            toggleColor();
        }
        else
        {
            create_newImageView(yellow_coin,column3_row_count,3, 175, column3_y);
            toggleColor();
        }
        column3_y = column3_y - 97.5;
        column3_row_count = column3_row_count - 1;
    }

    public void column4_pressed(ActionEvent event)
    {
        // x = 260 , col = 4 always in column4
        if (red_or_yellow)
        {
            create_newImageView(red_coin,column4_row_count,4, 260, column4_y);
            toggleColor();
        }
        else
        {
            create_newImageView(yellow_coin,column4_row_count,4, 260, column4_y);
            toggleColor();
        }
        column4_y = column4_y - 97.5;
        column4_row_count = column4_row_count - 1;
    }

    public void column5_pressed(ActionEvent event)
    {
        // x = 345 , col = 5 always in column5
        if (red_or_yellow)
        {
            create_newImageView(red_coin,column5_row_count,5, 345, column5_y);
            toggleColor();
        }
        else
        {
            create_newImageView(yellow_coin,column5_row_count,5, 345, column5_y);
            toggleColor();
        }
        column5_y = column5_y - 97.5;
        column5_row_count = column5_row_count - 1;
    }

    public void column6_pressed(ActionEvent event)
    {
        // x = 430 , col = 6 always in column6
        if (red_or_yellow)
        {
            create_newImageView(red_coin,column6_row_count,6, 430, column6_y);
            toggleColor();
        }
        else
        {
            create_newImageView(yellow_coin,column6_row_count,6, 430, column6_y);
            toggleColor();
        }
        column6_y = column6_y - 97.5;
        column6_row_count = column6_row_count - 1;
    }

    public void column7_pressed(ActionEvent event)
    {
        // x = 515 , col = 7 always in column7
        if (red_or_yellow)
        {
            create_newImageView(red_coin,column7_row_count,7, 515, column7_y);
            toggleColor();
        }
        else
        {
            create_newImageView(yellow_coin,column7_row_count,7, 515, column7_y);
            toggleColor();
        }
        column7_y = column7_y - 97.5;
        column7_row_count = column7_row_count - 1;
    }


    private void create_newImageView(Image image,int row, int col, double x, double y)
    {
        ImageView imageView = new ImageView();
        imageView.setId(String.format("pos_%s%s", row, col));
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        imageView.setLayoutX(x);
        imageView.setLayoutY(y);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setOpacity(1);
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
