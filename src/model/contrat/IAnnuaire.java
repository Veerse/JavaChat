package model.contrat;

import model.implementation.ClientThread;

import java.net.Socket;
import java.util.Hashtable;

public interface IAnnuaire {

    // Subscribe/Unsuscribe
    boolean addClient(String ID, Socket s, ClientThread t);
    boolean removeClient(String ID);

    // Other methods
    boolean clientIsLogged(String ID);

    // Getters and Setters
    Socket getSocket(String ID);
    ClientThread getThread(String ID);

}
