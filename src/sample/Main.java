package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.IntStream;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main extends Application {//implements Runnable{

    @Override
    public void start(Stage primaryStage) throws Exception{




        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Dixon.MSJ");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


    public static void main(String[] args)  {
        //Sockets.newSocket("192.168.100.27", 40000,"hello");
        launch(args);



    }


    public void run() {

    }
}
