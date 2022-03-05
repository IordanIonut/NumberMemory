package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DificultyModeController extends MainAppController implements Initializable {
    @FXML
    private Button easy;

    @FXML
    private Button hard;

    @FXML
    private ImageView logo;

    @FXML
    private Button mode;

    @FXML
    private Button normal;

    @FXML
    private AnchorPane parent;

    @FXML
    private Button undo;

    static int game;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(light){
            LightMode();
        }else{
            DarkMode();
        }
    }

    public void Change(){
        light=!light;
    }

    public void NormalDificulty(ActionEvent event) throws IOException {
        game=2;
        normal.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
    }

    public void EasyDificulty(ActionEvent event) throws IOException {
        game=1;
        easy.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();

    }

    public void HardDificulty(ActionEvent event) throws IOException {
        game=3;
        hard.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
    }

    public void LightDarkMode(ActionEvent event) {
        Change();
        if(light){
            LightMode();
        }else{
            DarkMode();
        }
    }

    public void UndoPanel(ActionEvent event) throws IOException {
        undo.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainApp.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
    }

    public  void LightMode(){
        parent.getStylesheets().remove("style/DarkMode.css");
        parent.getStylesheets().add("style/LightMode.css");
    }

    public void DarkMode(){
        parent.getStylesheets().remove("style/LightMode.css");
        parent.getStylesheets().add("style/DarkMode.css");
    }
}
