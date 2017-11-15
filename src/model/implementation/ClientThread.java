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
        System.out.println("Handler succesfully built for " + name);
    }

    public void write(String s) {
        try { this.dos.writeUTF(s); } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void run() {
        String inputMessage;
        String dest;
        String messageToDest;
        System.out.println("Thread running for " + name);
        write("Welcome to the server " + name + ", your messages are sent to all by default. " +
                "Type @[user] [message] to send a DM.\n" +
                "Type exit to leave.");
        while (true){
            try {

                inputMessage = dis.readUTF();
                System.out.println(name + " : " + inputMessage);

                // Leaving
                if (inputMessage.toLowerCase().equals("exit"))  break;

                // Sends a DM if the first char is a @
                if(inputMessage.charAt(0) == '@'){
                    dest = (inputMessage.split(" ")[0]).replace("@", "");
                    messageToDest = inputMessage.replaceFirst(inputMessage.split(" ")[0], "");
                    // Test if the recipient is connected before sending the DM
                    if (Server.userlist.clientIsLogged(dest))
                        Server.userlist.getThread(dest).write(name + " :" + messageToDest);
                }

                // TODO send a message to ALL, CLIENT PART, Proper connexion ending

            }catch (IOException e)
                { e.printStackTrace(); }

        }
        System.out.println(name + " left");

        try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
