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

public class SelectTopicPracticeToeicController {
    public static int selectedTopic;
    @FXML
    private Button backButton,buttonSelect1,buttonSelect2,buttonSelect3,buttonSelect4,buttonSelect5,buttonSelect6,buttonSelect7,buttonSelect8,buttonSelect9,buttonSelect10,buttonSelect11,buttonSelect12,buttonSelect13,buttonSelect14,buttonSelect15,buttonSelect16,buttonSelect17,buttonSelect18,buttonSelect19,buttonSelect20,buttonSelect21,buttonSelect22,buttonSelect23,buttonSelect24,buttonSelect25,buttonSelect26,buttonSelect27,buttonSelect28,buttonSelect29,buttonSelect30,buttonSelect31,buttonSelect32,buttonSelect33,buttonSelect34,buttonSelect35,buttonSelect36,buttonSelect37,buttonSelect38,buttonSelect39,buttonSelect40,buttonSelect41,buttonSelect42,buttonSelect43,buttonSelect44,buttonSelect45,buttonSelect46,buttonSelect47,buttonSelect48,buttonSelect49,buttonSelect50;
    @FXML
    private void handleSelectTopic(ActionEvent event) throws IOException {
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        Parent root;
        if(event.getSource()==backButton)
        {
            root= FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        }
        else if((selectedTopic=checkSelectButton(event))!=0)
        {
            root= FXMLLoader.load(getClass().getResource("SelectPracticeOption.fxml"));
        }else
        {
            root=FXMLLoader.load(getClass().getResource("SelectTopicLearnToeic.fxml"));
        }

        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("toeicApp/button.css");
        stage.setScene(scene);
        stage.show();
    }
    private int checkSelectButton(ActionEvent event)
    {
        if(event.getSource()==buttonSelect1) return 1;
        else if(event.getSource()==buttonSelect2) return 2;
        else if(event.getSource()==buttonSelect3) return 3;
        else if(event.getSource()==buttonSelect4) return 4;
        else if(event.getSource()==buttonSelect5) return 5;
        else if(event.getSource()==buttonSelect6) return 6;
        else if(event.getSource()==buttonSelect7) return 7;
        else if(event.getSource()==buttonSelect8) return 8;
        else if(event.getSource()==buttonSelect9) return 9;
        else if(event.getSource()==buttonSelect10) return 10;
        else if(event.getSource()==buttonSelect11) return 11;
        else if(event.getSource()==buttonSelect12) return 12;
        else if(event.getSource()==buttonSelect13) return 13;
        else if(event.getSource()==buttonSelect14) return 14;
        else if(event.getSource()==buttonSelect15) return 15;
        else if(event.getSource()==buttonSelect16) return 16;
        else if(event.getSource()==buttonSelect17) return 17;
        else if(event.getSource()==buttonSelect18) return 18;
        else if(event.getSource()==buttonSelect19) return 19;
        else if(event.getSource()==buttonSelect20) return 20;
        else if(event.getSource()==buttonSelect21) return 21;
        else if(event.getSource()==buttonSelect22) return 22;
        else if(event.getSource()==buttonSelect23) return 23;
        else if(event.getSource()==buttonSelect24) return 24;
        else if(event.getSource()==buttonSelect25) return 25;
        else if(event.getSource()==buttonSelect26) return 26;
        else if(event.getSource()==buttonSelect27) return 27;
        else if(event.getSource()==buttonSelect28) return 28;
        else if(event.getSource()==buttonSelect29) return 29;
        else if(event.getSource()==buttonSelect30) return 30;
        else if(event.getSource()==buttonSelect31) return 31;
        else if(event.getSource()==buttonSelect32) return 32;
        else if(event.getSource()==buttonSelect33) return 33;
        else if(event.getSource()==buttonSelect34) return 34;
        else if(event.getSource()==buttonSelect35) return 35;
        else if(event.getSource()==buttonSelect36) return 36;
        else if(event.getSource()==buttonSelect37) return 37;
        else if(event.getSource()==buttonSelect38) return 38;
        else if(event.getSource()==buttonSelect39) return 39;
        else if(event.getSource()==buttonSelect40) return 40;
        else if(event.getSource()==buttonSelect41) return 41;
        else if(event.getSource()==buttonSelect42) return 42;
        else if(event.getSource()==buttonSelect43) return 43;
        else if(event.getSource()==buttonSelect44) return 44;
        else if(event.getSource()==buttonSelect45) return 45;
        else if(event.getSource()==buttonSelect46) return 46;
        else if(event.getSource()==buttonSelect47) return 47;
        else if(event.getSource()==buttonSelect48) return 48;
        else if(event.getSource()==buttonSelect49) return 49;
        else if(event.getSource()==buttonSelect50) return 50;
        else return 0;
    }

}
