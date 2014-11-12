package server;

import message.Message;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
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
    private Socket socketClient;

    public ListenSend(Socket client) {
        try {
            this.socketClient = client;
            this.iStream = new ObjectInputStream(client.getInputStream());
            this.oStream = new ObjectOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocketClient() {
        return this.socketClient;
    }

    public ObjectOutputStream getOStream() {
        return oStream;
    }

    @Override
    public void run() {
        System.out.println("Client connecté : " + socketClient.toString());
        try {
            while (this.receive()) {
            }
        } catch (IOException e) {
             this.close();
        }
    }

    public boolean receive() throws IOException, ClassCastException{
        Object o;
        try {
            o = iStream.readObject();
            setChanged();
            notifyObservers(o);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean close() {
        try {
            socketClient.close();
            this.deleteObservers();
            System.out.println("Client : "+socketClient.toString()+" close");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean send(Object o) {
        try {
            if(! this.socketClient.isClosed()) {
                Message toto = new Message((String) o);
                getOStream().writeObject(o);
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}