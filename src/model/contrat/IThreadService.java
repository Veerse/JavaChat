package model.contrat;

/**
 * Created by Nassim on 14/11/2017.
 */
public interface IThreadService {

    void write(String s);
    void sendAll(String message);
    void printLoggedInUsers();
    void prepareToExit();
}
