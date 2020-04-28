package toeicApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class LearnToeicController  {
    private int selectedTopic=SelectTopicLearnToeicController.selectedTopic;
    @FXML
    private Button backButton,nextButton,prevButton;
    @FXML
    private Label topic,wordDisplay,engdescriptionDisplay,viedescriptionDisplay,engexampleDisplay,vieexampleDisplay,typeDisplay,pronounceDisplay;
    @FXML
    private ImageView imageWord;


    @FXML
    public void handlePhone(){
        try {
            File file=new File("src/Resources/Audio/" + datatopic.get(selectedWord).getWord() + ".mp3");
            String localURL=file.toURI().toURL().toString();
            Media media = new Media(localURL);
            MediaPlayer mp = new MediaPlayer(media);
            mp.setCycleCount(MediaPlayer.INDEFINITE);
            mp.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                     if(mp.getCycleCount()==3) mp.stop();
                }
            });
            mp.play();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private int selectedWord;
    private ArrayList<ToeicWords> datatopic;
    // store word data which will be loaded from database
    @FXML
    public void initialize() throws IOException {
            // show first word in word list
           datatopic=Controller.dataBase.getWord(selectedTopic);
           //getting word by selected topic from database
           selectedWord=0;
           topic.setText(String.valueOf(datatopic.get(0).getIndexofTopic())+": "+datatopic.get(0).getTopic());
           wordDisplay.setText(datatopic.get(0).getWord());
           pronounceDisplay.setText(datatopic.get(0).getPronounce());
           engdescriptionDisplay.setText(datatopic.get(0).getEngdescirption());
           viedescriptionDisplay.setText(datatopic.get(0).getViedescriptions());
           typeDisplay.setText(datatopic.get(0).getType());
           engexampleDisplay.setText(datatopic.get(0).getEngexample());
           vieexampleDisplay.setText(datatopic.get(0).getVieexample());
           File file =new File("src/Resources/imageToeic/"+datatopic.get(0).getWord()+".jpg");
           String localURL=file.toURI().toURL().toString();
           imageWord.setImage(new Image(localURL));

    }
    @FXML
    public void handlePage(ActionEvent event) throws MalformedURLException {
        //handling when user want to change word
        int numword=datatopic.size();
        if(event.getSource()==nextButton)
        {
            selectedWord=(selectedWord+1)%numword;
        }else if(event.getSource()==prevButton)
        {
            if(selectedWord==0) selectedWord=numword-1;
            else selectedWord=selectedWord-1;
        }
        wordDisplay.setText(datatopic.get(selectedWord).getWord());
        pronounceDisplay.setText(datatopic.get(selectedWord).getPronounce());
        engdescriptionDisplay.setText(datatopic.get(selectedWord).getEngdescirption());
        viedescriptionDisplay.setText(datatopic.get(selectedWord).getViedescriptions());
        typeDisplay.setText(datatopic.get(selectedWord).getType());
        engexampleDisplay.setText(datatopic.get(selectedWord).getEngexample());
        vieexampleDisplay.setText(datatopic.get(selectedWord).getVieexample());
        try {
            File file = new File("src/Resources/imageToeic/" + datatopic.get(selectedWord).getWord() + ".jpg");
            String localURL = file.toURI().toURL().toString();
            imageWord.setImage(new Image(localURL));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleScene(ActionEvent event) throws IOException {
        //change scene
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        if(event.getSource()==backButton)
        {
            root= FXMLLoader.load(getClass().getResource("SelectTopicLearnToeic.fxml"));
        }
        else
        {
            root=FXMLLoader.load(getClass().getResource("learnToeic.fxml"));
        }

        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("toeicApp/button.css");
        stage.setScene(scene);
        stage.show();

    }


}
