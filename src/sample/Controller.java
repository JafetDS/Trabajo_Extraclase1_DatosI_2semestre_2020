package sample;

import javafx.application.Platform;
import javafx.fxml.Initializable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 *
 * @author Jafet Dixon
 */


public class Controller implements Initializable,Runnable{

    @FXML
    public VBox vbox;
    public TextField puerto_in;
    public TextField ip_in;
    public TextArea Caja_de_texto;
    public String mensaje;
    public ServerSocket server1;

    //192.168.100.27

    /**
     *
     * @param event
     * @throws Exception
     * @description   Crea una nueva ventana de chat
     *
     */

    @FXML
    public void Other_Windows(ActionEvent event) throws Exception {
        String[] args = new String[0];
        Main main2=new Main();
        Stage primaryStage=new Stage();
        main2.start(primaryStage);

    }
//_________________________________________________________________________________________________________________________________________________

    /**
     *
     * @param event
     * @throws Exception
     * @description Envia los Emojis atravez del socket
     */
    @FXML
    public void send_Emojis(ActionEvent event) throws Exception {
        Button button = (Button) event.getSource();
        Sockets.newSocket(ip_in.getText(),Integer.parseInt(puerto_in.getText()),button.getId());
        /**
         * Instaciasion de un objeto tipo emoji
         */
        Emoji emoji=new Emoji(button.getId());
        try {
            emoji.setEmoji();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mostrar_emojis(emoji);
    }

    public void mostrar_emojis(Emoji emoji){
        Button label=new Button ();
        label.setGraphic(new ImageView(emoji.getEmoji()));
        label.setStyle("-fx-background-color:#ffa750");
        Label fecha=new Label(emoji.getFecha());
        fecha.setFont(new Font("Arial",11));
        fecha.setStyle("-fx-background-color:#ffa750");
        VBox pane= new VBox();
        pane.setAlignment(Pos.TOP_RIGHT);
        pane.setPadding(new Insets(0,0,0,40));
        pane.getChildren().addAll(label,fecha);
        vbox.getChildren().add(pane);
    }

    @FXML
    public void enviar(ActionEvent event) throws Exception {
        Sockets.newSocket(ip_in.getText(), Integer.parseInt(puerto_in.getText()), Caja_de_texto.getText());
        Mensaje mensaje1=new Mensaje(Caja_de_texto.getText());
        mostrar_mensaje(mensaje1);
    }

    public void mostrar_mensaje(Mensaje mensaje1)  {
        Button label=new Button (mensaje1.getMensaje());
        label.setFont(new Font("System",16));
        label.setStyle("-fx-background-color:#ffa750");
        label.setWrapText(true);

        Label fecha=new Label(mensaje1.getFecha());
        fecha.setFont(new Font("Arial",11));
        fecha.setStyle("-fx-background-color:#ffa750");

        VBox pane= new VBox();
        pane.setAlignment(Pos.TOP_RIGHT);
        pane.setPadding(new Insets(0,0,0,40));
        pane.getChildren().addAll(label,fecha);
        vbox.getChildren().add(pane);
        Caja_de_texto.setText("");
    }

//_________________________________________________________________________________________________________________________________

    /**
     *
     * @param event
     * Codigo basado en: https://ricardogeek.com/como-definir-y-ejecutar-threads-en-java/
     */
    @FXML
    private void conectar(ActionEvent event) {
        try {
            ServerSocket server=new ServerSocket(Integer.parseInt(puerto_in.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        mensaje = Sockets.newServerlistening(server);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread2.start();
/**
 * Clases para mostrar los emojis y mensajes recibidos
 * SE APLICA CONCEPTO DE SOBRE CARGA
 */

    }
    public void show_in_vbox(Mensaje mensaje1){
        Button label = new Button(mensaje1.getMensaje());
        label.setFont(new Font("System",16));
        label.setStyle("-fx-background-color:#ffd653 ");
        label.setWrapText(true);
        Label fecha=new Label(mensaje1.getFecha());
        fecha.setFont(new Font("Arial",11));
        fecha.setStyle("-fx-background-color:#ffd653 ");


        VBox pane= new VBox();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(0,40,0,0));
        pane.getChildren().addAll(label,fecha);
        vbox.getChildren().add(pane);
    }

    public void show_in_vbox(Emoji emoji){
        Button label = new Button();
        label.setGraphic(new ImageView(emoji.getEmoji()));
        label.setStyle("-fx-background-color:#ffd653 ");
        label.setWrapText(true);

        Label fecha=new Label(emoji.getFecha());
        fecha.setFont(new Font("Arial",11));
        fecha.setStyle("-fx-background-color:#ffd653 ");


        VBox pane= new VBox();
        pane.setAlignment(Pos.TOP_LEFT);
        pane.setPadding(new Insets(0,40,0,0));
        pane.getChildren().addAll(label,fecha);
        vbox.getChildren().add(pane);
    }

    /**
     * Codigo basado en: https://riptutorial.com/es/javafx/example/7291/actualizando-la-interfaz-de-usuario-usando-platform-runlater
     */
    @Override
    public void run() {
        Runnable updater = new Runnable() {
            @Override
            public void run() {

                if (mensaje.charAt(0)=='#') {
                    Emoji emoji=new Emoji(mensaje);
                    try {
                        emoji.setEmoji();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    show_in_vbox(emoji);
                } else {
                    Mensaje mensaje1=new Mensaje(mensaje);
                    show_in_vbox(mensaje1);
                }

                mensaje=" ";
            }
        };
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {}
            // UI update is run on the Application thread
            if (mensaje!=" ") {
                Platform.runLater(updater);
            }
        }
    }

//___________________________________________________________________________________________________________________________________________
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mensaje=" ";
        Thread hilo=new Thread(this);
        hilo.start();
        vbox.setPadding(new Insets(5,10,0,10));
        vbox.setSpacing(5);
    }

}