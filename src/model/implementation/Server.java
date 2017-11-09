package model.implementation;

import model.contrat.IServer;

import java.net.Socket;

public class Server extends Thread implements IServer  {

    Socket s;
    Annuaire clients;
    private static int cpt = 0;

    public Server(){
        clients = new Annuaire();
    }



}
