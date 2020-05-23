package toeicApp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;

public class TestReadingWriteController {
    private ConnectDataBase database=Controller.getDataBase();
    private ArrayList<Integer> selectedTopic=SelectTopicTestToeicController.selectedTopic;
    private int userID=Controller.getUserID();
    private int level=SelectTestOptionController.level;
    private Timeline timeline;
    private boolean started=false,finished=false;
    @FXML
    private Button backButton,nextButton,previousButton,backButton1,startfinish;
    @FXML
    private TextField textField;
    @FXML
    private Label question,correctLabel,answerLabel,clock,pointlabel,correctanswer,numberquestion;
    @FXML
    private AnchorPane noti,pane;
    private ArrayList<ToeicWords> datatopic=new ArrayList<>();
    private int selectedWord=0;
    private ArrayList<Integer> order=new ArrayList<>();
    private ArrayList<String> answers=new ArrayList<>();
    @FXML
    public void initialize()
    {
        Random random=new Random();
        for(int topic:selectedTopic)
        {
            ArrayList<ToeicWords> words=database.getWord(topic);
            datatopic.addAll(words);
        }

        for(int i=0;i<datatopic.size();i++)
        {
            order.add(i);
            answers.add("");
        }
        for(int i=0;i<datatopic.size();i++)
        {
            int x=random.nextInt(datatopic.size());
            int y=random.nextInt(datatopic.size());
            int temp=order.get(x);
            order.set(x,order.get(y));
            order.set(y,temp);
        }
        nextButton.setDisable(true);
        previousButton.setDisable(true);
    }

    @FXML
    public void handleScene(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getSource() == backButton||event.getSource()==backButton1) {
            root = FXMLLoader.load(getClass().getResource("SelectTestOption.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("Test_reading_write.fxml"));
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
    }
    @FXML
    public void changeQuestion(ActionEvent event)
    {
        if(event.getSource()==nextButton)
        {
            answers.set(order.get(selectedWord),textField.getText());
            selectedWord=(selectedWord+1)%datatopic.size();
            question.setText(datatopic.get(order.get(selectedWord)).getViedescriptions());
            textField.clear();
        }else if(event.getSource()==previousButton)
        {
            answers.set(order.get(selectedWord),textField.getText());
            if(selectedWord==0) selectedWord=datatopic.size()-1;
            else selectedWord--;
            question.setText(datatopic.get(order.get(selectedWord)).getViedescriptions());
            textField.clear();
        }

        if(finished){
            correctanswer.setText(datatopic.get(order.get(selectedWord)).getWord());
        }
        if(answers.get(order.get(selectedWord))!="")
        {
            textField.setText(answers.get(order.get(selectedWord)));
        }
        numberquestion.setText(String.valueOf(selectedWord+1)+"/"+datatopic.size());
    }
    @FXML
    public void shortcutKey(KeyEvent event)
    {
        if(event.getCode()==KeyCode.ENTER)
        {
            answers.set(order.get(selectedWord),textField.getText());
            selectedWord=(selectedWord+1)%datatopic.size();
            question.setText(datatopic.get(order.get(selectedWord)).getViedescriptions());
            textField.clear();
            if(answers.get(order.get(selectedWord))!="")
            {
                textField.setText(answers.get(order.get(selectedWord)));
            }
            numberquestion.setText(selectedWord+"/"+datatopic.size());
            if(finished){
                correctanswer.setText(datatopic.get(order.get(selectedWord)).getWord());
            }
        }
    }
    private int second;
    private int minute;
    @FXML
    public void start()
    {
        if(!started) {
            startfinish.setText("Nộp bài");
            question.setText(datatopic.get(order.get(selectedWord)).getViedescriptions());
            numberquestion.setText(String.valueOf(selectedWord+1)+"/"+datatopic.size());
            nextButton.setDisable(false);
            previousButton.setDisable(false);
            started=true;
            int time = getTime();
            minute = time / 60;
            second = time - minute * 60;
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    if (second == 0) {
                        minute--;
                        second = 60;
                    }
                    second--;
                    if (second == 15 && minute == 0) clock.setTextFill(Color.RED);
                    clock.setText(String.format("%02d:%02d", minute, second));
                }
            }));
            timeline.setCycleCount(time);
            timeline.play();
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    result();
                }
            });
        }
        else result();
    }

    private int getTime()
    {
        int time=0;
        if(level==1) time=120*selectedTopic.size();
        else if(level==2) time=90*selectedTopic.size();
        else if(level==3) time=60*selectedTopic.size();
        return time;
    }
    private void result()
    {
        int point=0;
        for(int i=0;i<datatopic.size();i++)
        {
            if(answers.get(i).contains(datatopic.get(i).getWord()))
            {
                point++;
            }
        }
        noti.setVisible(true);
        pointlabel.setText(point+"/"+datatopic.size());
        point=point*(4+level);
        if(selectedTopic.size()==1)
        {
            database.updateUserWriteResult(userID,selectedTopic.get(0),point);
        }
    }
    @FXML
    public void showCorrectAnswers()
    {
        timeline.stop();
        noti.setVisible(false);
        startfinish.setDisable(true);
        finished=true;
        pane.setVisible(true);
        correctanswer.setText(datatopic.get(order.get(selectedWord)).getWord());
    }
}
