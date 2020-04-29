package toeicApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectTestOptionController {
    public static boolean eng_vie_option=true;
    public static int level=1;
    @FXML
    private ToggleButton easy,normal,hard,vie_eng,eng_vie;
    @FXML
    private Button backButton,start_write,start_choose;

    @FXML
    public void handleScene(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getSource() == backButton) {
            SelectTopicTestToeicController.selectedTopic.clear();
            root = FXMLLoader.load(getClass().getResource("SelectTopicTestToiec.fxml"));
        }else
        if(event.getSource()==start_choose)
        {
            if(vie_eng.isSelected()) eng_vie_option=false;
            else if(eng_vie.isSelected()) eng_vie_option=true;
            if(easy.isSelected()) level=1;
            else if(normal.isSelected()) level=2;
            else if(hard.isSelected()) level=3;
            root=FXMLLoader.load(getClass().getResource("Test_reading_choose.fxml"));
        }else
        if(event.getSource()==start_write)
        {
            if(vie_eng.isSelected()) eng_vie_option=false;
            else if(eng_vie.isSelected()) eng_vie_option=true;
            if(easy.isSelected()) level=1;
            else if(normal.isSelected()) level=2;
            else if(hard.isSelected()) level=3;
            root=FXMLLoader.load(getClass().getResource("Test_reading_write.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("SelectTestOption.fxml"));
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
    }
}
