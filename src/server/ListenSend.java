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
        System.out.println("Client connecté : "+oStream.toString()+" "+iStream.toString());
        while(true) {
            this.receive();
        }
    }

    public boolean receive() {
        Object o;
        try {
            o = iStream.readObject();
            setChanged();
            notifyObservers(o);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
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