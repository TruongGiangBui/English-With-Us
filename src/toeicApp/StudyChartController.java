package toeicApp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.*;

public class StudyChartController {
    @FXML
    private PieChart passedTopic;
    @FXML
    public void initialize(){
        double passed=Controller.dataBase.passedPercent(Controller.getUserID());

        ObservableList<PieChart.Data> chartdata= FXCollections.observableArrayList(
                new PieChart.Data("Completed",passed),
                new PieChart.Data("Incomplete",100-passed));
        passedTopic.getData().addAll(chartdata);
        passedTopic.setLabelLineLength(10);
        passedTopic.setLegendSide(Side.BOTTOM);
        passedTopic.setLegendVisible(true);
        passedTopic.setLegendVisible(true);
    }
    @FXML
    public void handleScene()
    {

    }

}
