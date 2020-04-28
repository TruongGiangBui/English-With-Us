package toeicApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectPracticeOptionController  {
    @FXML
    private Button backButton,button1,button2,button3,button4;
    @FXML
    public void handleScene(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getSource() == backButton) {
            root = FXMLLoader.load(getClass().getResource("SelectTopicPracticeToeic.fxml"));
        }else if(event.getSource()==button1) {
            root = FXMLLoader.load(getClass().getResource("Practice_reading_write.fxml"));
        }
        else if(event.getSource()==button2)
        {
            root=FXMLLoader.load(getClass().getResource("Practice_reading_choose.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("SelectPracticeOption.fxml"));
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
    }
}
