package Server;
import sample.Sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class server {

    private int port = 30000;

    private ServerSocket server1;

    public server() throws IOException {
        this.server1=new ServerSocket(port);
    }

    /**
     * Abre y dirige el mensaje del server
     */
    public void listing(){
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                String mensaje;
                while(true) {
                    try {
                        mensaje = Sockets.newServerlistening(server1);
                        String[] arrOfStr = mensaje.split("-", 4);
                        if (mensaje!=null){
                            Sockets.newSocket(arrOfStr[0],Integer.parseInt(arrOfStr[1]),arrOfStr[2]);
                            System.out.println(arrOfStr[2]);
                            System.out.println(mensaje);
                            mensaje = null;
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread2.start();

    }
}
