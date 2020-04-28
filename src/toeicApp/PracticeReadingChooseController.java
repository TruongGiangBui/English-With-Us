package toeicApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class PracticeReadingChooseController {
    private int selectedTopic=SelectTopicPracticeToeicController.selectedTopic;
    @FXML
    private Button backButton,nextbutton,previousbutton;
    @FXML
    private Label question,answer1,answer2,answer3,answer4,numberQuestion,noti;
    @FXML
    private Button button1,button2,button3,button4;

    private ArrayList<ToeicWords> datatopic=new ArrayList<>();
    private ArrayList<Integer> order=new ArrayList<>();
    private ArrayList<Integer> questionlist=new ArrayList<>();
    private  Random random=new Random();
    private int selectedword=0;
    @FXML
    public void initialize()
    {
        datatopic=Controller.dataBase.getWord(selectedTopic);
        numberQuestion.setText("0/12");
        for(int i=0;i<datatopic.size();i++)
        {
            order.add(i);
        }
        for(int i=0;i<6;i++)
        {
            int x=random.nextInt(12);
            int y=random.nextInt(12);
            int temp=order.get(x);
            order.set(x,order.get(y));
            order.set(y,temp);
        }
        setDisplay();
    }

    @FXML
    public void handleScene(ActionEvent event) throws IOException {
        Parent root;
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (event.getSource() == backButton) {
            root = FXMLLoader.load(getClass().getResource("SelectPracticeOption.fxml"));
        }
        else {
            root = FXMLLoader.load(getClass().getResource("Practice_reading_choose.fxml"));
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
    }

    private void setDisplay()
    {
        numberQuestion.setText(String.valueOf(selectedword+1)+"/"+String.valueOf(datatopic.size()));
        question.setText(datatopic.get(order.get(selectedword)).getWord());
        questionlist.clear();
        while(questionlist.size()<4)
        {
            int x=random.nextInt(12);
            if(!questionlist.contains(x)&&x!=selectedword) questionlist.add(x);
        }

        questionlist.set(random.nextInt(4),selectedword);

        answer1.setText(datatopic.get(order.get(questionlist.get(0))).getViedescriptions());
        answer2.setText(datatopic.get(order.get(questionlist.get(1))).getViedescriptions());
        answer3.setText(datatopic.get(order.get(questionlist.get(2))).getViedescriptions());
        answer4.setText(datatopic.get(order.get(questionlist.get(3))).getViedescriptions());
    }
    @FXML
    public void handleQuestion(ActionEvent event)
    {
        if(event.getSource()==button1)
        {
            System.out.println(questionlist.get(0)+" "+selectedword);
            if(questionlist.get(0)==selectedword){
                noti.setText("Correct");
            }
            else {
                noti.setText("Incorrect\nAnswer: "+answer());
            }
        }else
        if(event.getSource()==button2)
        {
            System.out.println(questionlist.get(1)+" "+selectedword);
            if(questionlist.get(1)==selectedword){
                noti.setText("Correct");
            }
            else {
                noti.setText("Incorrect\nAnswer: "+answer());
            }
        }else
        if(event.getSource()==button3)
        {
            System.out.println(questionlist.get(2)+" "+selectedword);
            if(questionlist.get(2)==selectedword){
                noti.setText("Correct");
            }
            else {
                noti.setText("Incorrect\nAnswer: "+answer());
            }
        }else
        if(event.getSource()==button4)
        {
            System.out.println(questionlist.get(3)+" "+selectedword);
            if(questionlist.get(3)==selectedword){
                noti.setText("Correct");
            }
            else {
                noti.setText("Incorrect\nAnswer: "+answer());
            }
        }
        noti.setVisible(true);
    }
    @FXML private void changeQuestion(ActionEvent event)
    {
        if(event.getSource()==nextbutton)
        {
            selectedword=(selectedword+1)%datatopic.size();
            noti.setVisible(false);
            setDisplay();
        }else if(event.getSource()==previousbutton)
        {
            if(selectedword==0) selectedword=datatopic.size()-1;
            else selectedword--;
            noti.setVisible(false);
            setDisplay();
        }
    }
    public String answer()
    {
        int x=-1;
        for(int i=0;i<4;i++)
        {
            if(questionlist.get(i)==selectedword) x=i;
        }
        switch (x)
        {
            case 0: return "A";
            case 1:return "B";
            case 2: return "C";
            case 3: return "D";
            default: return "";
        }
    }
}
