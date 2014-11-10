package server;

import message.Message;

import java.io.*;
import java.util.Observable;

/**
 * Classe ListenSend
 * Thread crée à chaque acceptation et connection de client.
 * Gère le décodage et l'encodage des données ainsi que l'envoie et la reception.
 * Notifie le serveur lors de la reception d'un message.
 */
public class ListenSend extends Observable implements Runnable {

    private ObjectInputStream iStream = null;
    private ObjectOutputStream oStream = null;

    public ListenSend(InputStream inputS, OutputStream outputS) {
        try {
            this.iStream = new ObjectInputStream(inputS);
            this.oStream = new ObjectOutputStream(outputS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOStream() {
        return oStream;
    }

    @Override
    public void run() {
        Object o;
        System.out.println("Client connecté : "+oStream.toString()+" "+iStream.toString());
        while(true) {
            try {
                o = iStream.readObject();
                setChanged();
                notifyObservers(o);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean send(Object o) {
        try {
            getOStream().writeObject((Message)o);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}