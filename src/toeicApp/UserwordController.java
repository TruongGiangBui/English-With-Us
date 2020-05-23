package toeicApp;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class UserwordController {
    private ConnectDataBase database=Controller.getDataBase();
    private int userID=Controller.getUserID();
    private ObservableList<UserWords> userWords;
    @FXML
    private ListView<UserWords> wordlist;
    @FXML
    private Label wo, pro,des,exam,note;
    @FXML
    private ContextMenu listcontextmenu;
    @FXML
    private AnchorPane add,deletew;
    @FXML
    private Button search,addWord,add_confirm,add_cancel,delete_confirm,delete_cancel,backButton,close;
    @FXML
    private TextField add_word,add_pronounce;
    @FXML
    private TextArea add_Des,add_Ex,add_Note;
    @FXML
    private TextField search_text;

    @FXML
    public void initialize()
    {
        userWords=database.getUserWord(userID);

        MenuItem deleteWord=new MenuItem("Delete");
        deleteWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deletew.setVisible(true);
            }
        });
        listcontextmenu.getItems().add(deleteWord);

        wordlist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserWords>() {
            @Override
            public void changed(ObservableValue<? extends UserWords> observable, UserWords oldValue, UserWords newValue) {
                if(newValue != null) {
                    UserWords userWords = wordlist.getSelectionModel().getSelectedItem();
                    wo.setText(userWords.getWord());
                    pro.setText(userWords.getPronounce());
                    des.setText(userWords.getDescription());
                    exam.setText(userWords.getExample());
                    note.setText(userWords.getNote());
                }
            }
        });
        SortedList<UserWords> userwords=new SortedList<UserWords>(userWords, new Comparator<UserWords>() {
            @Override
            public int compare(UserWords word1, UserWords words2) {
                return word1.getWord().compareTo(words2.getWord());
            }
        });

        wordlist.setItems(userwords);
        wordlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        wordlist.getSelectionModel().selectFirst();
    }
    @FXML
    public void handleScene(ActionEvent event) throws IOException {
        // handling change scene
        Stage stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        // get primary stage address
        Parent root= FXMLLoader.load(getClass().getResource("Userword.fxml"));
        if(event.getSource()==backButton)
        {
            root= FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        }
        else if(event.getSource()==close)
        {
            stage.close();
            return;
        }

        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);;
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void add()
    {
        add.setVisible(true);
    }
    @FXML
    public void addword(ActionEvent e)
    {
        if(e.getSource()==add_confirm)
        {
            String word=add_word.getText();
            String pro=add_pronounce.getText();
            String des=add_Des.getText();
            String ex=add_Ex.getText();
            String note=add_Note.getText();
            database.addUserWord(new UserWords(word,pro,des,ex,note),userID);
            add.setVisible(false);

            add_word.clear();
            add_pronounce.clear();
            add_Des.clear();
            add_Ex.clear();
            add_Note.clear();

            userWords.clear();
            userWords=database.getUserWord(userID);
            wordlist.setItems(userWords);
            wordlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            wordlist.getSelectionModel().selectFirst();
        }
        else if(e.getSource()==add_cancel)
        {
            add.setVisible(false);
        }
    }
    public void deleteWord(ActionEvent event)
    {
        if(event.getSource()==delete_confirm) {
            UserWords userWord = wordlist.getSelectionModel().getSelectedItem();
            database.deleteUserWord(userWord.getWord(), userID);
            userWords = database.getUserWord(userID);
            wordlist.setItems(userWords);
            wordlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            wordlist.getSelectionModel().selectFirst();
            deletew.setVisible(false);
        }
        else if(event.getSource()==delete_cancel)
        {
            deletew.setVisible(false);
        }
    }

    @FXML
    public void handleClickListView()
    {
        UserWords userWords=(UserWords) wordlist.getSelectionModel().getSelectedItem();
        wo.setText(userWords.getWord());
        pro.setText(userWords.getPronounce());
        des.setText(userWords.getDescription());
        exam.setText(userWords.getExample());
        note.setText(userWords.getNote());
    }
    @FXML public void search_word()
    {
        ObservableList<UserWords> list= FXCollections.observableArrayList();
        String w=search_text.getText().trim();
        System.out.println(w);
        for(UserWords word: database.getUserWord(userID))
        {
            if(word.getWord().contains(w))
            list.add(word);
        }
        System.out.println(list.get(0));
        userWords.clear();
        userWords.setAll(list);
        wordlist.setItems(userWords);
        wordlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        wordlist.getSelectionModel().selectFirst();
    }
    @FXML public void clearSearch()
    {
        userWords=database.getUserWord(userID);
        SortedList<UserWords> userwords=new SortedList<UserWords>(userWords, new Comparator<UserWords>() {
            @Override
            public int compare(UserWords word1, UserWords words2) {
                return word1.getWord().compareTo(words2.getWord());
            }
        });
        search_text.clear();
        wordlist.setItems(userwords);
        wordlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        wordlist.getSelectionModel().selectFirst();
    }
}
