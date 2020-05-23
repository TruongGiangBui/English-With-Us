package toeicApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;

public class StudyChartController {
    private ConnectDataBase database=Controller.getDataBase();
    @FXML
    private PieChart passedTopic;
    @FXML
    private LineChart<Number,Number> chart;
    @FXML
    private Button backButton,close;
    @FXML
    public void initialize(){
        double passed=database.passedPercent(Controller.getUserID());

        ObservableList<PieChart.Data> chartdata= FXCollections.observableArrayList(
                new PieChart.Data("Completed",passed),
                new PieChart.Data("Incomplete",100-passed));
        passedTopic.getData().addAll(chartdata);
        passedTopic.setLabelLineLength(10);
        passedTopic.setLegendSide(Side.BOTTOM);
        passedTopic.setLegendVisible(true);
        passedTopic.setLegendVisible(true);
        studychart();

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
    public void studychart()
    {
        chart.setTitle("Kết quả học tập");
        XYChart.Series<Number,Number> series = new XYChart.Series<Number, Number>();
        series.setName("Point");

        HashMap<Integer,Integer> A=database.getHistory(Controller.getUserID());
        for(int i=1;i<=A.size();i++)
        {
            series.getData().add(new XYChart.Data(i, A.get(i)));
        }
        chart.setCreateSymbols(false);

        chart.getData().add(series);
        System.out.println(123);
    }
}
