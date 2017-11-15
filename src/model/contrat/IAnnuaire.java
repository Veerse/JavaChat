package model.contrat;

import model.implementation.ThreadService;

import java.net.Socket;

public interface IAnnuaire {

    // Subscribe/Unsuscribe
    boolean addClient(String ID, Socket s, ThreadService t);
    boolean removeClient(String ID);

    // Other methods
    boolean clientIsLogged(String ID);

    // Getters and Setters
    Socket getSocket(String ID);
    ThreadService getThread(String ID);

}
