package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {
    @FXML
    private Button bestscore;

    @FXML
    private ImageView logo;

    @FXML
    private Button mode;

    @FXML
    private AnchorPane parent;

    @FXML
    private Button play;

    static boolean light=true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(light){
            LightMode();
        }else {
            DarkMode();
        }
    }

    public void LightDarkMode(ActionEvent mouseEvent) {
        light=!light;
        if(light){
            LightMode();
        }else{
            DarkMode();
        }
    }

    public  void LightMode(){
        parent.getStylesheets().remove("style/DarkMode.css");
        parent.getStylesheets().add("style/LightMode.css");
    }

    public void DarkMode(){
        parent.getStylesheets().remove("style/LightMode.css");
        parent.getStylesheets().add("style/DarkMode.css");
    }

    public void Play(ActionEvent event) throws IOException {
        play.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DificultyMode.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
    }

    public void BescScore(ActionEvent event) throws IOException {
        bestscore.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("BestScore.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
    }
}
