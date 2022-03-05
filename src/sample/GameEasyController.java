package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class GameEasyController extends DificultyModeController implements Initializable {
    @FXML
    private ImageView logo;

    @FXML
    private AnchorPane parent;

    @FXML
    private Button answer;

    @FXML
    private Label level;

    @FXML
    private Button mode;

    @FXML
    private Label read;

    @FXML
    private Button undo;

    @FXML
    private TextField write;

    @FXML
    private TextField write1;

    @FXML
    private TextField write2;

    static int Level=1;
    public long number1;
    public long number2;
    int timer=3000;
    Timer myTimer = new Timer();

    public void LightDarkMode(ActionEvent event) {
        light=!light;
        if(light){
            LightMode();
        }else{
            DarkMode();
        }
    }

    public void ReadEasy(){
        SelectLevel();
        read.setText("The number is: "+number1);
        myTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                Platform.runLater(() ->{
                    read.setText("Write the number that was chosen.");
                    write.setVisible(true);
                    answer.setVisible(true);
                });
            }
        }, timer);
    }

    public void ReadNormal(){
        SelectLevel();
        read.setText("The number is: "+number1+"   "+number2);
        myTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                Platform.runLater(() ->{
                    read.setText("Write the numbers that was chosen.");
                    write1.setVisible(true);
                    write2.setVisible(true);
                    answer.setVisible(true);
                });
            }
        }, timer);
    }

    public void ReadHard(){
        SelectLevel();
        read.setText("The number is: "+number1+" + "+number2);
        myTimer.schedule(new TimerTask(){
            @Override
            public void run() {
                Platform.runLater(() ->{
                    read.setText("Write the number that was chosen.");
                    write.setVisible(true);
                    answer.setVisible(true);
                });
            }
        }, timer);
    }

    public void ReadNumber(ActionEvent mouseEvent) {
        write1.setVisible(false);
        write2.setVisible(false);
        write.setVisible(false);
        answer.setVisible(false);
        if(game==1){
            ReadEasy();
        }
        if(game==2){
            ReadNormal();
        }
        if(game==3){
            ReadHard();
        }
    }

    public void WriteNumber(ActionEvent event) throws IOException {
        Answer(null);
    }

    public int RandomNumber(int num){
        Random random=new Random();
        return random.nextInt(num)+1;
    }

    public void SelectLevel(){
        if(Level<=10){
            number1= RandomNumber(100);
            number2= RandomNumber(100);
        }else if(Level<=20){
            number1= RandomNumber(1000);
            number2= RandomNumber(1000);
            timer=4000;
        }else if(Level<=30){
            number1= RandomNumber(10000);
            number2= RandomNumber(10000);
            timer=5000;
        }else if(Level<=40){
            number1= RandomNumber(100000);
            number2= RandomNumber(100000);
            timer=6000;
        }else if(Level<=50){
            number1= RandomNumber(1000000);
            number2= RandomNumber(1000000);
            timer=7000;
        }else if(Level<=60){
            number1= RandomNumber(10000000);
            number2= RandomNumber(10000000);
            timer=8000;
        }else if(Level<=70){
            number1= RandomNumber(100000000);
            number2= RandomNumber(100000000);
            timer=9000;
        }else{
            number1=RandomNumber(1000000000);
            number2=RandomNumber(1000000000);
            timer=10000;
        }
    }

    public void UndoPanel(ActionEvent event) throws IOException {
        undo.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DificultyMode.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
        Level=1;
        myTimer.cancel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(light){
            LightMode();
        }else{
            DarkMode();
        }
        ReadNumber(null);
        level.setText(""+Level);
    }

    public void AnswerWrong() throws IOException {
        undo.getScene().getWindow().hide();
        Stage signup=new Stage();
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("WrongAnswer.fxml")));
        Scene scene=new Scene(root);
        signup.setScene(scene);
        signup.show();
        myTimer.cancel();
    }

    public void Answer1() throws IOException {
        if(Integer.parseInt(write.getText())==number1) {
            ReadNumber(null);
            write.setText("");
            Level++;
            level.setText(" " + Level);
        }else{
            AnswerWrong();
        }
    }

    public void Answer2() throws IOException {
        if(Integer.parseInt(write1.getText())==number1 && Integer.parseInt(write2.getText())==number2){
            ReadNumber(null);
            write1.setText("");
            write2.setText("");
            Level++;
            level.setText(" " + Level);
        }else{
            AnswerWrong();
        }
    }

    public void Answer3() throws IOException {
        if(Integer.parseInt(write.getText())==(number1+number2)) {
            ReadNumber(null);
            write.setText("");
            Level++;
            level.setText(" " + Level);
        }else{
            AnswerWrong();
        }
    }

    public void Answer(ActionEvent event) throws IOException {
        if(game==1) {
            Answer1();
        }else if(game==2) {
            Answer2();
        }else if(game==3){
            Answer3();
        }
    }

    public void SetLevel(MouseEvent mouseEvent) {
    }
}
