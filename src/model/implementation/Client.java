
package model.implementation;
import model.contrat.IClient;

import java.io.*;
import java.net.Socket;

public class Client extends Thread implements IClient {

    public static int PORT = 33333;
    private String name;
    private Socket s;

    private DataOutputStream OutToServer;
    private BufferedReader InFromServeur;


    public Client(String name){

        this.name = name;

        // Socket
        try {
            s = new Socket ("127.0.0.1", PORT);
        } catch (IOException e) { e.printStackTrace(); }

        // Buffer write
        try {
            OutToServer = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {e.printStackTrace(); }

        // Buffer read
        try {
            InFromServeur = new BufferedReader(new InputStreamReader(s.getInputStream()));
        } catch (IOException e) { e.printStackTrace(); }
    }

    public void write (String message) throws IOException {
        OutToServer.writeBytes(message);
    }

    public String read () throws IOException {
        return InFromServeur.readLine();
    }

}
