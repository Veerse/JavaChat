package main;

import model.contrat.IAnnuaire;
import model.implementation.Annuaire;

import java.net.Socket;

public class Main {

    public static void main(String[] args) {

        IAnnuaire a = new Annuaire();
        Socket s = new Socket();
        Thread t = new Thread();

        a.addClient("Test", s, t);

        Socket s2 = a.getSocket("Test");

        System.out.println(s.equals(s2));

    }
}
