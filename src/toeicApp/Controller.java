package toeicApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Controller {
    public static  ConnectDataBase dataBase=new ConnectDataBase();
    private static boolean logedin=false;
    private static int userID=-1;
    @FXML
    ImageView icon1,icon2,icon3;
    @FXML
    private Button learnbutton;
    @FXML
    private Button testbutton,practicebutton,aboutbutton,close,result;
    @FXML
    private Button signin,signup,signout,signup1,cancel;
    @FXML
    private AnchorPane loginform,signupform;
    @FXML
    private TextField username,username1;
    @FXML
    private PasswordField password,password1;
    @FXML
    private Label userName,alert;
    @FXML
    public void handleScene(ActionEvent event) throws IOException {

        Stage stage;
        Parent   root=FXMLLoader.load(getClass().getResource("mainwindow.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        if(logedin) {
            if (event.getSource() == learnbutton) {
                root = FXMLLoader.load(getClass().getResource("SelectTopicLearnToeic.fxml"));
            } else if (event.getSource() == practicebutton) {
                root = FXMLLoader.load(getClass().getResource("SelectTopicPracticeToeic.fxml"));
            } else if (event.getSource() == testbutton) {
                root = FXMLLoader.load(getClass().getResource("SelectTopicTestToiec.fxml"));
            } else if(event.getSource()==result) {
                root = FXMLLoader.load(getClass().getResource("Study_chart.fxml"));
            }
        }
        if(event.getSource()==close) {
            stage.close();
            return;
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("toeicApp/button.css");
        stage.setScene(scene);
        stage.show();
    }
    public void initialize() throws MalformedURLException {

        File file1=new File("src/Resources/Icon/book.png");
        icon1.setImage(new Image(file1.toURI().toURL().toString()));
        File file2=new File("src/Resources/Icon/pen.png");
        icon2.setImage(new Image(file2.toURI().toURL().toString()));
//        File file3=new File("src/Resources/Icon/heart.png");
//        icon3.setImage(new Image(file3.toURI().toURL().toString()));
        userName.setText("truonggiang");

        if(!logedin)
        {
            loginform.setVisible(true);
        }else loginform.setVisible(false);
    }
    public void handleLoginform(ActionEvent event)
    {
        if(event.getSource()==signin)
        {
            userID=dataBase.submitLogin(username.getText().trim(),password.getText());
            if(userID!=-1)
            {
                logedin=true;
                loginform.setVisible(false);
                userName.setText(username.getText());
                username.clear();
                password.clear();
                alert.setVisible(false);
            }
            else {
                alert.setText("Incorrect password or account does not exist");
                alert.setVisible(true);
            }
        }
        if(event.getSource()==signup)
        {
            signupform.setVisible(true);
        }
        if (event.getSource()==signout)
        {
            logedin=false;
            loginform.setVisible(true);
        }
        if(event.getSource()==signup1)
        {
            dataBase.newuser(username1.getText().trim(),password1.getText());
            signupform.setVisible(false);
        }
        if(event.getSource()==cancel)
        {
            signupform.setVisible(false);
        }
    }
    public static int getUserID()
    {
        return userID;
    }
}
