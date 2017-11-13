package model.implementation;

import model.contrat.IServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements IServer  {

    private int port;
    private ServerSocket server_socket;
    private Annuaire clients;

    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    public Server(int port) throws IOException {

        clients = new Annuaire();
        server_socket = new ServerSocket(port);

    }

    public void run() throws IOException {
        Socket client = server_socket.accept();

    }

    private void clientsubscription(String ID) {
        System.out.println(ID);

    }


}
