package sample;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class boardController
{
    // Player first = 1, AI first = 0, Default is player first!
    private int player_or_ai = 1;


    public void onMouseClick()
    {
        System.out.println("Image clicked!");
        System.out.println(player_or_ai);
    }

    public void setFirst(int value)
    {
        player_or_ai = value;
    }
}
