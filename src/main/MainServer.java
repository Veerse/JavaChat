package main;

import model.contrat.IServer;
import model.implementation.Server;

import java.io.IOException;

public class MainServer {

    public static void main(String[] args) throws IOException {
        IServer s = new Server(33333);
        s.start();
    }
}
