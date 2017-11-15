package model.implementation;

import model.contrat.IThreadService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.Set;

public class ThreadService implements Runnable, IThreadService{

    Scanner scn;
    private String name;
    private DataInputStream dis;
    private DataOutputStream dos;
    Socket s;


    public ThreadService(Socket s, String name, DataInputStream dis, DataOutputStream dos) {
        scn = new Scanner(System.in);
        this.name = name;
        this.dis = dis;
        this.dos = dos;
        this.s = s;
        System.out.println("Handler succesfully built for " + name);
    }

    @Override
    public void write(String s) {
        try { this.dos.writeUTF(s); } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void run() {
        String inputMessage;
        String dest;
        String messageToDest;
        System.out.println("Thread running for " + name);
        write("Welcome to the server " + name + ". By default your message will be sent to the server\n"+
                "Type @[user] [message] to send a DM.\n" +
                "Type @all [message] to send your message to all connected users.\n"+
                "Type @list to see all connected users.\n"+
                "Type @exit to leave.");
        while (true){
            try {

                inputMessage = dis.readUTF();


                // Leaving
                if (inputMessage.toLowerCase().equals("@exit")) {
                    this.prepareToExit();
                    return;
                }
                if (inputMessage.length()>=3 && inputMessage.substring(0,4).equals("@all")) {

                    this.sendAll(inputMessage);
                } else if (inputMessage.equals("@list")) {
                    this.printLoggedInUsers();
                }
                else if(inputMessage.charAt(0) == '@'){
                    // Sends a DM if the first char is a @
                    dest = (inputMessage.split(" ")[0]).replace("@", "");
                    messageToDest = inputMessage.replaceFirst(inputMessage.split(" ")[0], "");
                    // Test if the recipient is connected before sending the DM
                    if (Server.userlist.clientIsLogged(dest)) {
                        Server.userlist.getThread(dest).write(name + " :" + messageToDest);
                    }
                    else {
                        Server.userlist.getThread(name).write("the user " + dest +" is not logged in" );
                    }
                } else {
                    System.out.println(name + " : " + inputMessage);
                }




            }catch (IOException e)
            { e.printStackTrace(); }

        }


    }

    public void sendAll(String message){
        String messageToSend = message.substring(4);
        Set<String> clients=Server.userlist.getAllThreads();
        for (String cl:clients){
            if(!cl.equals(name))
                Server.userlist.getThread(cl).write(name + " (all) :" + messageToSend);
        }
        // Test if the recipient is connected before sending the DM

    }
    public void printLoggedInUsers(){
        Set<String> clients=Server.userlist.getAllThreads();
        StringBuilder users=new StringBuilder();
        for (String cl:clients){
            if(!cl.equals(name)){
                users.append(cl+" ");
            }

        }
        Server.userlist.getThread(name).write(users.toString());
    }
    public void prepareToExit() {
        Server.userlist.getThread(name).write("See you soon " + name);
        Server.userlist.getThread(name).write("@exit");
        Server.userlist.removeClient(name);

    }
}
