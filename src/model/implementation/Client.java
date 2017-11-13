
package model.implementation;
import model.contrat.IClient;

import java.io.*;
import java.net.Socket;

public class Client implements IClient {

    private int port;
    private String name;
    private Socket socket;

    private DataOutputStream dOut;
    private DataInputStream dIn;


    public Client(String name, int port) throws IOException {

        this.name = name;
        this.port = port;

    }

    public void connexion() throws IOException {
        socket = new Socket("127.0.0.1", port);
        dOut = new DataOutputStream(socket.getOutputStream());
        dIn = new DataInputStream(socket.getInputStream());
    }

    public void subscription() throws IOException {
        dOut.writeBytes("s:" + this.name);
        dOut.flush();
    }

    public void write (String message) throws IOException {
        dOut.writeBytes(message);
    }

    public String read () throws IOException {
        return dIn.readLine();
    }

}
