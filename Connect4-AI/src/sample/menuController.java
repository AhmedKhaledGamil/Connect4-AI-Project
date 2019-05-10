package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.io.IOException;


public class menuController
{


    public RadioButton ai_id;
    public RadioButton player_id;

//    public void startNewGame()
//    {
//        if(ai_id.isSelected()){
//            System.out.println("AI Starts First!");
//
//        }
//        if(player_id.isSelected()){
//            System.out.println("Player Starts First!");
//        }
//    }

    /*
     *  When this method is called it will change the scene to the board scene!
     * */

    public void changeSceneToBoard(ActionEvent event) throws IOException {

        // Parent board = FXMLLoader.load(getClass().getResource("board.fxml"));
        // Those line do the same thing
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("board.fxml"));
        Parent board = loader.load();


        Scene boardScene = new Scene(board, 600, 600);

        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        if(ai_id.isSelected() || player_id.isSelected())
        {
            if(ai_id.isSelected())
            {
                boardController controller = loader.getController();
                // AI first = 0
                controller.setFirst(0);
            }
            window.setScene(boardScene);
            window.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please choose who starts!");
            alert.showAndWait();
        }
    }
}
