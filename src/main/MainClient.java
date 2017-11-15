package main;

import model.contrat.IClient;
import model.implementation.Client;

import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws IOException {
        IClient c = new Client(33333);
        c.start();
    }
}