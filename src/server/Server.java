package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : Hasitha Lakshan
 * Project :Group Chat Application
 * Date :8/7/2022
 * Time :4:42 PM
 */

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(5000);
        Socket accept;

        System.out.println("Waiting for Client ...");
        accept= serverSocket.accept();
        System.out.println("Client Connected");
    }

}
