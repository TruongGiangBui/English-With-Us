package toeicApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        //Create new window and load fxml file
        //Show stage
        Parent root = FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        Scene scene=new Scene(root, 900, 630);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        scene.setFill(Color.TRANSPARENT);
        File file=new File("src/Resources/Icon/biglogo.png");
        primaryStage.getIcons().add(new Image(file.toURI().toURL().toString()));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        Controller.dataBase.closeConnect();
        super.stop();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
