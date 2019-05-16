package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Arrays;

public class resultController {

    public Text textID;

    public void backToMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu.fxml"));
        Parent menu = loader.load();

        Scene menuScene = new Scene(menu, 600, 600);


        menuController menuController = loader.getController();
        ObjectInputStream in = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("board.ser");
            in = new ObjectInputStream(fileInputStream);
            int[][] board = (int[][]) in.readObject();

            System.out.println(Arrays.deepToString(board));
            fileInputStream.close();
            in.close();
            menuController.continueBtn.setVisible(true);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            menuController.continueBtn.setVisible(false);
        }

        // This line gets the stage information
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.show();

    }
}
