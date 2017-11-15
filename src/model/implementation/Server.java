package model.implementation;

import model.contrat.IServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements IServer {

    public static Annuaire userlist;
    ServerSocket serverSocket;
    Socket s;

    public Server (int port) throws IOException {
        userlist = new Annuaire();
        serverSocket = new ServerSocket(port);
        System.out.println("Server socket created on port " + port);
    }

    public void start () throws IOException {
        Socket s;

        while (true){
            // Blocks until there's a connexion coming
            s = serverSocket.accept();
            System.out.println("Someone has connected");

            DataInputStream dis = new DataInputStream(s.getInputStream());
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            String name = new String();

            // Wait for the user's name
            do {
                dos.writeUTF("Enter your name...");
                name = dis.readUTF();
            }while(userlist.clientIsLogged(name));

            // Creates the service thread
            System.out.print("Creating a handler...");
            ThreadService c_t = new ThreadService(s, name, dis, dos);

            // Starts the thread
            System.out.print("Starting the thread...");
            Thread t = new Thread(c_t);
            t.start();

            // Adds the client to the userlist
            userlist.addClient(name, s, c_t);
        }
    }

    public void close (){
        try { serverSocket.close(); } catch (IOException e) { e.printStackTrace(); }
        System.out.println("Server closed");
    }
}