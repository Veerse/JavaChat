package main;

import model.contrat.IServer;
import model.implementation.Server;

import java.net.Socket;

public class MainServer {

    public static void main(String[] args) {

        IServer s = new Server(33333);


    }
}
