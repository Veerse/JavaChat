package model.implementation;

import model.contrat.IClientThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientThread implements Runnable{

    Scanner scn;
    private String name;
    private DataInputStream dis;
    private DataOutputStream dos;
    Socket s;


    public ClientThread (Socket s, String name, DataInputStream dis, DataOutputStream dos) {
        scn = new Scanner(System.in);
        this.name = name;
        this.dis = dis;
        this.dos = dos;
        this.s = s;
        System.out.println("Handler succesfully built");
    }

    @Override
    public void run() {
        String inputMessage;
        System.out.println("Thread running...");
        while (true){
            try {
                inputMessage = dis.readUTF();
                System.out.println(inputMessage);
            } catch (IOException e) { e.printStackTrace(); }

        }
    }
}
