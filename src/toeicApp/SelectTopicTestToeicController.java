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
import java.util.ArrayList;

public class SelectTopicTestToeicController {
    // Control select topic

    public static ArrayList<Integer> selectedTopic=new ArrayList<>();
    // number of topic had been selected
    @FXML
    private Button backButton,okbutton;
    @FXML
    private ToggleButton buttonSelect1,buttonSelect2,buttonSelect3,buttonSelect4,buttonSelect5,buttonSelect6,buttonSelect7,buttonSelect8,buttonSelect9,buttonSelect10,buttonSelect11,buttonSelect12,buttonSelect13,buttonSelect14,buttonSelect15,buttonSelect16,buttonSelect17,buttonSelect18,buttonSelect19,buttonSelect20,buttonSelect21,buttonSelect22,buttonSelect23,buttonSelect24,buttonSelect25,buttonSelect26,buttonSelect27,buttonSelect28,buttonSelect29,buttonSelect30,buttonSelect31,buttonSelect32,buttonSelect33,buttonSelect34,buttonSelect35,buttonSelect36,buttonSelect37,buttonSelect38,buttonSelect39,buttonSelect40,buttonSelect41,buttonSelect42,buttonSelect43,buttonSelect44,buttonSelect45,buttonSelect46,buttonSelect47,buttonSelect48,buttonSelect49,buttonSelect50;
    @FXML
    private void handleSelectTopic(ActionEvent event) throws IOException {
        // handling change scene
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        // get primary stage address
        Parent root;
        if(event.getSource()==backButton)
        {
            root= FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        }
        else if(event.getSource()==okbutton)
        {
            checkSelectButton();
            for(int x:selectedTopic)
            {
                System.out.println(x);
            }
            root= FXMLLoader.load(getClass().getResource("Test_reading_choose.fxml"));
        }else
        {
            root=FXMLLoader.load(getClass().getResource("selectTopictTestToeic.fxml"));
        }

        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("toeicApp/button.css");
        stage.setScene(scene);
        stage.show();
    }
    private void checkSelectButton()
    {
        if(buttonSelect1.isSelected())
        {
            selectedTopic.add(1);
        }
        if(buttonSelect2.isSelected())
        {
            selectedTopic.add(2);
        }
        if(buttonSelect3.isSelected())
        {
            selectedTopic.add(3);
        }
        if(buttonSelect4.isSelected())
        {
            selectedTopic.add(4);
        }
        if(buttonSelect5.isSelected())
        {
            selectedTopic.add(5);
        }
        if(buttonSelect6.isSelected())
        {
            selectedTopic.add(6);
        }
        if(buttonSelect7.isSelected())
        {
            selectedTopic.add(7);
        }
        if(buttonSelect8.isSelected())
        {
            selectedTopic.add(8);
        }
        if(buttonSelect9.isSelected())
        {
            selectedTopic.add(9);
        }
        if(buttonSelect10.isSelected())
        {
            selectedTopic.add(10);
        }
        if(buttonSelect11.isSelected())
        {
            selectedTopic.add(11);
        }
        if(buttonSelect12.isSelected())
        {
            selectedTopic.add(12);
        }
        if(buttonSelect13.isSelected())
        {
            selectedTopic.add(13);
        }
        if(buttonSelect14.isSelected())
        {
            selectedTopic.add(14);
        }
        if(buttonSelect15.isSelected())
        {
            selectedTopic.add(15);
        }
        if(buttonSelect16.isSelected())
        {
            selectedTopic.add(16);
        }
        if(buttonSelect17.isSelected())
        {
            selectedTopic.add(17);
        }
        if(buttonSelect18.isSelected())
        {
            selectedTopic.add(18);
        }
        if(buttonSelect19.isSelected())
        {
            selectedTopic.add(19);
        }
        if(buttonSelect20.isSelected())
        {
            selectedTopic.add(20);
        }
        if(buttonSelect21.isSelected())
        {
            selectedTopic.add(21);
        }
        if(buttonSelect22.isSelected())
        {
            selectedTopic.add(22);
        }
        if(buttonSelect23.isSelected())
        {
            selectedTopic.add(23);
        }
        if(buttonSelect24.isSelected())
        {
            selectedTopic.add(24);
        }
        if(buttonSelect25.isSelected())
        {
            selectedTopic.add(25);
        }
        if(buttonSelect26.isSelected())
        {
            selectedTopic.add(26);
        }
        if(buttonSelect27.isSelected())
        {
            selectedTopic.add(27);
        }
        if(buttonSelect28.isSelected())
        {
            selectedTopic.add(28);
        }
        if(buttonSelect29.isSelected())
        {
            selectedTopic.add(29);
        }
        if(buttonSelect30.isSelected())
        {
            selectedTopic.add(30);
        }
        if(buttonSelect31.isSelected())
        {
            selectedTopic.add(31);
        }
        if(buttonSelect32.isSelected())
        {
            selectedTopic.add(32);
        }
        if(buttonSelect33.isSelected())
        {
            selectedTopic.add(33);
        }
        if(buttonSelect34.isSelected())
        {
            selectedTopic.add(34);
        }
        if(buttonSelect35.isSelected())
        {
            selectedTopic.add(35);
        }
        if(buttonSelect36.isSelected())
        {
            selectedTopic.add(36);
        }
        if(buttonSelect37.isSelected())
        {
            selectedTopic.add(37);
        }
        if(buttonSelect38.isSelected())
        {
            selectedTopic.add(38);
        }
        if(buttonSelect39.isSelected())
        {
            selectedTopic.add(39);
        }
        if(buttonSelect40.isSelected())
        {
            selectedTopic.add(40);
        }
        if(buttonSelect41.isSelected())
        {
            selectedTopic.add(41);
        }
        if(buttonSelect42.isSelected())
        {
            selectedTopic.add(42);
        }
        if(buttonSelect43.isSelected())
        {
            selectedTopic.add(43);
        }
        if(buttonSelect44.isSelected())
        {
            selectedTopic.add(44);
        }
        if(buttonSelect45.isSelected())
        {
            selectedTopic.add(45);
        }
        if(buttonSelect46.isSelected())
        {
            selectedTopic.add(46);
        }
        if(buttonSelect47.isSelected())
        {
            selectedTopic.add(47);
        }
        if(buttonSelect48.isSelected())
        {
            selectedTopic.add(48);
        }
        if(buttonSelect49.isSelected())
        {
            selectedTopic.add(49);
        }
        if(buttonSelect50.isSelected())
        {
            selectedTopic.add(50);
        }

    }

}
