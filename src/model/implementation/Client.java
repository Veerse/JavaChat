package model.implementation;

import model.contrat.IClient;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client implements IClient {

    private Scanner scn;
    private Socket s;

    private DataInputStream dis;
    private DataOutputStream dos;

    private Thread sendMsg;
    private Thread readMsg;

    // Constructor
    public Client (int port) throws IOException {

        scn = new Scanner(System.in);

        s = new Socket("127.0.0.1", port);

        dis = new DataInputStream(s.getInputStream());
        dos = new DataOutputStream(s.getOutputStream());
        // boolean stop=false;
        sendMsg = new Thread(new Runnable(){
            @Override
            public void run() {
                while (true) {

                    String msg = scn.nextLine();
                    try { dos.writeUTF(msg); } catch (IOException e) { e.printStackTrace(); }
                }
            }
        });

        readMsg = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {

                        String msg = dis.readUTF();
                        if("@exit".equals(msg)){

                            return;
                        }
                        System.out.println(msg);
                    } catch (IOException e) { e.printStackTrace(); }
                }
            }
        });

    }

    // Methods
    @Override
    public void start(){
        readMsg.start();
        sendMsg.start();
    }

}