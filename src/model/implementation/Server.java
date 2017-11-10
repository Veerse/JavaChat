package model.implementation;

import model.contrat.IServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread implements IServer  {

    public static int PORT = 33333;

    private ServerSocket s;
    private Annuaire clients;

    private BufferedReader inFromClient;
    private DataOutputStream outToClient;

    public Server() throws IOException {

        clients = new Annuaire();
        s = new ServerSocket(PORT);

        Socket client = s.accept();

    }

    private BufferedReader read (Socket s ) throws IOException {
        inFromClient=new BufferedReader((new InputStreamReader(s.getInputStream())))
        return inFromClient;
    }
    private DataOutputStream write (Socket s) throws IOException{
        outToClient=new DataOutputStream(s.getOutputStream());
        return outToClient;
    }


    private void clientsubscription(String ID){
        System.out.println(ID);

    }







}
