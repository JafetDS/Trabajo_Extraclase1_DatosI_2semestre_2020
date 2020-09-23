package sample;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase para iniciar el socket client y el socket Server
 *
 * SE PUEDE UTILIZAR EN CUALQUIER PROYECTO : MODULARIDAD
 */

public class Sockets  {
    public static void newSocket(String Ip, int port,String mensaje)  throws Exception{newSocket2(Ip,  port,mensaje); }
    private static void newSocket2(String Ip, int port,String mensaje)  throws Exception {

        try {
            Socket soket = new Socket(Ip, port);
            DataOutputStream salida = new DataOutputStream(soket.getOutputStream());
            salida.writeUTF(mensaje);
            salida.close();

        }
        catch (UnknownHostException e){
             //   System.out.println("jua2");
                System.out.println(e.getMessage());

        }
        catch (IOException e) {
            System.out.println(e.getMessage());

        }

    }
  //_______________________________________________________________________________________________________________________________

    public static String newServerlistening(ServerSocket server) throws Exception{
       return newServerlistening2(server);
    }
    public static String newServerlistening2(ServerSocket server) throws Exception{
            System.out.println("Listing");
            DataInputStream entrada = new DataInputStream(server.accept().getInputStream());
            String mensaje=entrada.readUTF();
            entrada.close();
            return mensaje;
    }



    //______________________________________________________________________________________________________________________________________




}
