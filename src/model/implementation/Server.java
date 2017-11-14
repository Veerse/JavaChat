package model.implementation;

import model.contrat.IClientThread;
import model.contrat.IServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements IServer {

    ServerSocket serverSocket;
    Socket s;

    public Server (int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) { e.printStackTrace(); }
        System.out.println("Server socket created on port " + port);
    }

    public void start () throws IOException {
        Socket s;

        while (true){
            s = serverSocket.accept();
            System.out.println("Someone has connected");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            System.out.println("Creating a handler...");
            ClientThread c_t = new ClientThread (s, "test", dis, dos);

            Thread t = new Thread(c_t);
            System.out.println("Starting the thread...");
            t.start();
        }
    }

    public void close (){
        try { serverSocket.close(); } catch (IOException e) { e.printStackTrace(); }
        System.out.println("Server closed");
    }
}