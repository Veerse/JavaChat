package model.implementation;

import model.contrat.IAnnuaire;


import java.net.Socket;
import java.util.Hashtable;

public class Annuaire extends Thread implements IAnnuaire {

    private Hashtable <String, Socket> m_socket;
    private Hashtable <String, ThreadService> m_thread;

    public Annuaire(){
        m_socket = new Hashtable <String, Socket> ();
        m_thread = new Hashtable <String, ThreadService> ();
    }

    @Override
    public synchronized boolean addClient(String ID, Socket s, ThreadService t) {
        if (clientIsLogged(ID))   return false;

        m_socket.put (ID, s);
        m_thread.put (ID, t);
        return true;
    }

    @Override
    public synchronized boolean removeClient(String ID) {
        if (clientIsLogged(ID))   return false;

        m_socket.remove(ID);
        m_thread.remove(ID);
        return true;
    }

    @Override
    public boolean clientIsLogged(String ID) {
        return (m_thread.get (ID) != null) && (m_socket.get(ID) != null) ;
    }

    // Getters and Setters
    @Override
    public synchronized Socket getSocket(String ID) {
        if (clientIsLogged(ID))   return this.m_socket.get(ID);
        else return null;
    }

    @Override
    public synchronized ThreadService getThread(String ID) {
        if (clientIsLogged(ID))   return this.m_thread.get(ID);
        else return null;
    }

}
