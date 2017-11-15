package main;

import model.contrat.IClient;
import model.implementation.Client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class MainClient {

    public static void main(String args[]) throws UnknownHostException, IOException {
        IClient c = new Client(33333);
        c.start();
    }
}