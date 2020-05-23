package toeicApp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private static ConnectDataBase dataBase=new ConnectDataBase();
    private static boolean logedin=false;
    private static int userID=-1;
    private static int turn=-1;
    @FXML
    ImageView icon1,icon2,icon3;
    @FXML
    private Button learnbutton;
    @FXML
    private Button testbutton,practicebutton,aboutbutton,close,result,userwordbutton;
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
    private LineChart<Number,Number> chart;
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
            } else if(event.getSource()==userwordbutton)
                root = FXMLLoader.load(getClass().getResource("Userword.fxml"));
        }
        if(event.getSource()==close) {
            stage.close();
            return;
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
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

        if(logedin){
            studychart();
        }
    }
    public void handleLoginform(ActionEvent event)
    {
        if(event.getSource()==signin)
        {
            ArrayList<Integer> usersdata =dataBase.submitLogin(username.getText().trim(),password.getText());
            userID=usersdata.get(0);
            turn=usersdata.get(1);
            if(userID!=-1)
            {
                logedin=true;
                loginform.setVisible(false);
                userName.setText(username.getText());
                username.clear();
                password.clear();
                alert.setVisible(false);
                studychart();
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
            chart.getData().clear();
            dataBase.savedata(userID,turn);
            logedin=false;
            loginform.setVisible(true);
        }
        if(event.getSource()==signup1)
        {
            if(username1.getText().trim()!=""&&password1.getText().trim()!="") {
                dataBase.newuser(username1.getText().trim(), password1.getText());
                signupform.setVisible(false);
            }
        }
        if(event.getSource()==cancel)
        {
            signupform.setVisible(false);
        }
    }
    public void studychart()
    {
        chart.setTitle("Kết quả học tập");
        XYChart.Series<Number,Number> series = new XYChart.Series<>();
        series.setName("Point");

        HashMap<Integer,Integer> A=Controller.dataBase.getHistory(Controller.userID);
        for(int i=1;i<=A.size();i++)
        {
            series.getData().add(new XYChart.Data(i, A.get(i)));
        }
        chart.setCreateSymbols(false);

        chart.getData().add(series);
    }
    public static int getUserID()
    {
        return userID;
    }
    public static int getTurn()
    {
        return turn;
    }

    public static boolean isLogedin() {
        return logedin;
    }

    public static ConnectDataBase getDataBase() {
        return dataBase;
    }
}
