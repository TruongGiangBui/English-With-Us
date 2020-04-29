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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class TestReadingChooseController {
    private boolean eng_vie=SelectTestOptionController.eng_vie_option;
    private int userID=Controller.getUserID();
    private int level=SelectTestOptionController.level;
    private ArrayList<Integer> selectedTopic=SelectTopicTestToeicController.selectedTopic;;
    @FXML
    private ToggleButton button1,button2,button3,button4;
    @FXML
    private ToggleGroup toggleGroup=new ToggleGroup();
    @FXML
    private AnchorPane noti;
    @FXML
    private Button backButton,backButton1,nextbutton,previousbutton,startfinish;
    @FXML
    private Label question,answer1,answer2,answer3,answer4,numberQuestion,clock,pointlabel;

    private ArrayList<ToeicWords> datatopic=new ArrayList<>();
    private ArrayList<Integer> order=new ArrayList<>();
    private ArrayList<Integer> answerlist=new ArrayList<>();
    private ArrayList<Survey> questions=new ArrayList<>();
    private Random random=new Random();
    private Timeline timeline;
    private int selectedword=0;
    private boolean started=false;
    private boolean finished=false;

    @FXML
    public void initialize()
    {
        button1.setToggleGroup(toggleGroup);
        button2.setToggleGroup(toggleGroup);
        button3.setToggleGroup(toggleGroup);
        button4.setToggleGroup(toggleGroup);
        nextbutton.setDisable(true);
        previousbutton.setDisable(true);
        for(int topic:selectedTopic) {
            datatopic.addAll(Controller.dataBase.getWord(topic));
        }
        numberQuestion.setText("0/"+datatopic.size());
        order=randomInt(datatopic.size());

        if(eng_vie){
        for(int i=0;i<datatopic.size();i++)
        {
            ArrayList<String> Answerslist=Controller.dataBase.getVieDescription();
            answerlist.add(-1);
            ToeicWords w=datatopic.get(order.get(i));
            ArrayList<String> ans=new ArrayList<>();
            while(ans.size()<4)
            {
                int x=random.nextInt(Answerslist.size());
                if(Answerslist.get(x)!=w.getViedescriptions()&&!ans.contains(Answerslist.get(x))) ans.add(Answerslist.get(x));
            }
            int x=random.nextInt(4);
            ans.set(x,w.getViedescriptions());
            Survey survey=new Survey(w.getWord(),ans.get(0),ans.get(1),ans.get(2),ans.get(3),x);
            questions.add(survey);
        }}else{
            for(int i=0;i<datatopic.size();i++)
            {
                ArrayList<String> Answerslist=Controller.dataBase.getallWord();
                answerlist.add(-1);
                ToeicWords w=datatopic.get(order.get(i));
                ArrayList<String> ans=new ArrayList<>();
                while(ans.size()<4)
                {
                    int x=random.nextInt(Answerslist.size());
                    if(Answerslist.get(x)!=w.getWord()&&!ans.contains(Answerslist.get(x))) ans.add(Answerslist.get(x));
                }
                int x=random.nextInt(4);
                ans.set(x,w.getWord());
                Survey survey=new Survey(w.getViedescriptions(),ans.get(0),ans.get(1),ans.get(2),ans.get(3),x);
                questions.add(survey);
            }
        }

    }
    private ArrayList<Integer> randomInt(int n)
    {
        ArrayList<Integer> a=new ArrayList<>();
        for(int i=0;i<n;i++)
        {
            a.add(i);
        }
        for(int i=0;i<a.size();i++)
        {
            int x=random.nextInt(a.size());
            int y=random.nextInt(a.size());
            int temp=a.get(x);
            a.set(x,a.get(y));
            a.set(y,temp);
        }
        return a;
    }
    @FXML
    public void handleScene(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getSource() == backButton||event.getSource()==backButton1) {
            root = FXMLLoader.load(getClass().getResource("SelectTestOption.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("Test_reading_choose.fxml"));
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
    }

    private void setDisplay()
    {
        numberQuestion.setText(String.valueOf(selectedword+1)+"/"+String.valueOf(datatopic.size()));
        question.setText(questions.get(selectedword).getQuestion());

        answer1.setText(questions.get(selectedword).getAnswer().get(0));
        answer2.setText(questions.get(selectedword).getAnswer().get(1));
        answer3.setText(questions.get(selectedword).getAnswer().get(2));
        answer4.setText(questions.get(selectedword).getAnswer().get(3));
        if(answerlist.get(selectedword)!=-1){
            switch (answerlist.get(selectedword))
            {
                case 0: button1.setSelected(true);
                break;
                case 1: button2.setSelected(true);
                    break;
                case 2: button3.setSelected(true);
                    break;
                case 3: button4.setSelected(true);
                    break;
            }
        }
    }
    @FXML private void changeQuestion(ActionEvent event)
    {
        if(button1.isSelected()==true) answerlist.set(selectedword,0);
        else
        if(button2.isSelected()==true) answerlist.set(selectedword,1);
        else
        if(button3.isSelected()==true) answerlist.set(selectedword,2);
        else
        if(button4.isSelected()==true) answerlist.set(selectedword,3);
        if(event.getSource()==nextbutton)
        {
            selectedword=(selectedword+1)%datatopic.size();
            button1.setSelected(false);
            button2.setSelected(false);
            button3.setSelected(false);
            button4.setSelected(false);
            setDisplay();

        }else if(event.getSource()==previousbutton)
        {
            if(selectedword==0) selectedword=datatopic.size()-1;
            else selectedword--;
            button1.setSelected(false);
            button2.setSelected(false);
            button3.setSelected(false);
            button4.setSelected(false);
            setDisplay();
        }
        if(finished){
            clock.setText(convert(questions.get(selectedword).getCorrectanswer()));
        }
    }
    private String convert(int x)
    {
        switch (x)
        {
            case 0: return "A";
            case 1:return "B";
            case 2: return "C";
            case 3: return "D";
            default: return "";
        }
    }
    @FXML
    public void handleQuestion(ActionEvent event)
    {

    }
    private int second;
    private int minute;
    @FXML
    public void start()
    {

        if(!started) {
            setDisplay();
            startfinish.setText("Nộp bài");
            started=true;
            int time=getTime();
            minute=time/60;
            second=time-minute*60;
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    nextbutton.setDisable(false);
                    previousbutton.setDisable(false);
                    if (second == 0) {
                        minute--;
                        second = 60;
                    }second--;
                    if(second==15&&minute==0) clock.setTextFill(Color.RED);
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
        else{
            result();
        }
    }
    private int getTime()
    {
        int time=0;
        if(level==1) time=90*selectedTopic.size();
        else if(level==2) time=60*selectedTopic.size();
        else if(level==3) time=45*selectedTopic.size();
        return time;
    }
    private void result()
    {
        int point=0;
        for(int i=0;i<questions.size();i++)
        {
            if(questions.get(i).getCorrectanswer()==answerlist.get(i))
            {
                point++;
            }
        }
        noti.setVisible(true);
        pointlabel.setText(point+"/"+datatopic.size());
        if(selectedTopic.size()==1)
        {
            Controller.dataBase.updateUserChooseResult(userID,selectedTopic.get(0),point);
        }
    }
    @FXML
    private void showCorrectAnswers()
    {
        timeline.stop();
        noti.setVisible(false);
        startfinish.setDisable(true);
        finished=true;
        clock.setText(convert(questions.get(selectedword).getCorrectanswer()));
    }

}
