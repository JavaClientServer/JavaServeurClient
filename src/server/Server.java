package server;

import message.Message;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Class Server
 * Gestion du serveur de nom/surnom
 */
public class Server implements Observer{

    private ServerSocket socketServer;
    private Socket socketClient;

    public Server(int portNumber) {
        try {
            this.socketServer = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getSocketServer() {
        return this.socketServer;
    }

    public Socket getSocketClient() {
        return this.socketClient;
    }

    public boolean acceptConnexion() {
        try {
            System.out.println("J'attend !");
            this.socketClient = socketServer.accept();
            servEcoute newEcoute = new servEcoute(socketClient.getInputStream(),socketClient.getOutputStream());
            newEcoute.addObserver(this);
            Thread ecouteur = new Thread(newEcoute);
            ecouteur.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        servEcoute threadenCours = ((servEcoute) o);
        try {
            ((servEcoute) o).getoStream().writeObject(new Message("J'ai reçu"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class servEcoute extends Observable implements Runnable {

        private ObjectInputStream iStream = null;

        private ObjectOutputStream oStream = null;

        public servEcoute(InputStream inputS, OutputStream outputS) {
            try {
                this.iStream = new ObjectInputStream(inputS);
                this.oStream = new ObjectOutputStream(outputS);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public ObjectOutputStream getoStream() {
            return oStream;
        }

        @Override
        public void run() {
            Object o;
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
    }

    public static void main(String arg[]) throws IOException {
            Server serv = new Server(6969);
            while(true) {
                serv.acceptConnexion();
            }

    }


}
