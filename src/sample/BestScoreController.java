package sample;

import dataBase.dbHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.ResourceBundle;

public class BestScoreController extends MainAppController implements Initializable {
    @FXML
    private ImageView logo;

    @FXML
    private ListView<String> ListEasy;

    @FXML
    private ListView<String> ListHard;

    @FXML
    private ListView<String> ListNormal;

    @FXML
    private Button mode;

    @FXML
    private AnchorPane parent;

    @FXML
    private Button undo;

    public void Display(String db) throws SQLException {
        dbHandler handler1=new dbHandler();
        Connection connection1= handler1.getConnection();
        Statement statement;
        String sql="SELECT Score, Name FROM "+db;
        try{
            statement=connection1.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String Score=resultSet.getString("Score");
                String Name=resultSet.getString("Name");
                String list=Score+" \' "+Name+" \' ";
                if(db=="easy"){
                    ListEasy.getItems().add(list);
                }else if(db=="normal"){
                    ListNormal.getItems().add(list);
                }else{
                    ListHard.getItems().add(list);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(light){
            LightMode();
        }else{
            DarkMode();
        }
        try {
            SetLevel(null);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void LightDarkMode(ActionEvent event) {
        light=!light;
        if(light){
            LightMode();
        }else{
            DarkMode();
        }
    }

    public void UndoPanel(ActionEvent event) throws IOException {
        parent.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainApp.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
    }

    public void SetLevel(MouseEvent mouseEvent) throws SQLException {
        String easy="easy";
        String normal="normal";
        String hard="hard";
        Display(easy);
        Display(normal);
        Display(hard);
    }
}
