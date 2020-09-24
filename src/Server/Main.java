package Server;

import sample.Sockets;

public class Main {

/**
*
*
 */
    public static void main(String[] args) throws Exception {
        server Serv =new server();
        Serv.listing();
        Sockets.newSocket("127.0.0.1", 30000,"127.0.0.1-5000-Hello");
        System.out.print("Hello world");




    }
}
