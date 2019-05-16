package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("menu.fxml"));
        Parent menu = loader.load();
        primaryStage.setTitle("Connect 4");
        primaryStage.setScene(new Scene(menu, 600, 600));
        primaryStage.setResizable(false);

        menuController menuController = loader.getController();

        ObjectInputStream in = null;
        try {
            FileInputStream fileInputStream = new FileInputStream("board.ser");
            in = new ObjectInputStream(fileInputStream);
            int[][] board = (int[][]) in.readObject();
            fileInputStream.close();
            in.close();
            menuController.continueBtn.setVisible(true);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            menuController.continueBtn.setVisible(false);
        }

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
