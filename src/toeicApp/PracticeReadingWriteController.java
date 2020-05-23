package toeicApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PracticeReadingWriteController {
    private int selectedTopic=SelectTopicPracticeToeicController.selectedTopic;
    private ConnectDataBase database=Controller.getDataBase();
    @FXML
    private Button backButton;
    @FXML
    private TextField textField;
    @FXML
    private Label description,correctLabel,answerLabel;
    @FXML
    public void handleScene(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getSource() == backButton) {
            root = FXMLLoader.load(getClass().getResource("SelectPracticeOption.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("Practice_reading_write.fxml"));
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
    }
    private ArrayList<ToeicWords> datatopic;
    private int selectedWord=0;
    @FXML
    public void initialize()
    {
        Random random=new Random();
        datatopic=database.getWord(selectedTopic);
        Collections.shuffle(datatopic);
        description.setText(datatopic.get(selectedWord).getViedescriptions());
    }
    private int turn=0;
    @FXML
    public void handleWord(KeyEvent event)
    {
        if(event.getCode()==KeyCode.ENTER)
        {
            turn++;
            if(textField.getText().contains(datatopic.get(selectedWord).getWord())&&turn<4)
            {
                correctLabel.setText("Correct");
                correctLabel.setVisible(true);
                turn=4;
            }
            else if(turn<4)
            {
                correctLabel.setText("Incorrect");
                correctLabel.setVisible(true);
                textField.clear();
            }else
            if(turn==4)
            {
                answerLabel.setText(datatopic.get(selectedWord).getWord());
                answerLabel.setVisible(true);
                textField.clear();
            }
            if(turn==5)
            {
                answerLabel.setVisible(false);
                correctLabel.setVisible(false);
                turn=0;
                selectedWord=(selectedWord+1)%datatopic.size();
                description.setText(datatopic.get(selectedWord).getViedescriptions());
                textField.clear();
            }
        }
    }
}
