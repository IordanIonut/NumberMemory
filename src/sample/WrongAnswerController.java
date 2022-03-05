package sample;

import dataBase.dbHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class WrongAnswerController extends GameEasyController implements Initializable {
    @FXML
    private Label score;

    @FXML
    private ImageView logo;

    @FXML
    private AnchorPane parent;

    @FXML
    private TextField name;

    @FXML
    private Label yourscore;

    @FXML
    private Button play;

    private Connection connection;
    private dbHandler handler;

    public void Insert(String value){
        String nameplayer=name.getText();
        String insertCommand="INSERT INTO "+value +"(Score,Name) VALUES ('";
        String insertValues=yourscore.getText()+"','"+nameplayer+"')";
        String insert=insertCommand+insertValues;
        connection=handler.getConnection();
        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate(insert);
        }catch (SQLException e1){
            e1.printStackTrace();
            e1.getCause();
        }
    }

    public void BestScorePlayer(String value) throws SQLException {
        dbHandler handler1=new dbHandler();
        Connection connection1= handler1.getConnection();
        Statement statement=connection1.createStatement();
        ResultSet resultSet1;
        String sql1="SELECT Score FROM "+value+" WHERE Score=(SELECT MAX(Score) FROM "+value+")";
        resultSet1=statement.executeQuery(sql1);
        while (resultSet1.next()) {
            score.setText(resultSet1.getString(1));
        }

    }

    public void InsertDb(){
        if(Integer.parseInt(score.getText())<Integer.parseInt(yourscore.getText())) {
            if (game == 1) {
                String easy = "easy";
                Insert(easy);
            } else if (game == 2) {
                String normal = "normal";
                Insert(normal);
            } else {
                String hard = "hard";
                Insert(hard);
            }
        }
    }

    public void BestScore() throws SQLException {
        if(game==1){
            String easy ="easy";
            BestScorePlayer(easy);
        }else if(game==2){
            String normal="normal";
            BestScorePlayer(normal);
        }else{
            String hard="hard";
            BestScorePlayer(hard);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        handler = new dbHandler();
        if(light){
            LightMode();
        }else{
            DarkMode();
        }
        try {
            BestScore();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        YourScore(null);
    }

    public void Replay(ActionEvent event) throws IOException {
        InsertDb();
        parent.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DificultyMode.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
        Level=1;
    }

    public void YourScore(MouseEvent mouseEvent) {
        yourscore.setText(""+Level);
    }
}
