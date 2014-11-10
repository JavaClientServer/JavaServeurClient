package server;

import message.Message;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
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
            System.out.println("Initialisation serveur ok, port :"+portNumber);
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
            this.socketClient = socketServer.accept();
            ListenSend clientListenSend = new ListenSend(socketClient.getInputStream(),socketClient.getOutputStream());
            clientListenSend.addObserver(this);
            Thread ecouteur = new Thread(clientListenSend);
            ecouteur.start();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        ListenSend client = ((ListenSend) o);
        System.out.println("Reception de : "+arg.toString());
        ((ListenSend) o).send(new String ("J'ai reçu : "+arg.toString()));
    }

    public static void main(String arg[]) throws IOException {
            Server serveur = new Server(6969);
            while(true) {
                serveur.acceptConnexion();
            }

    }


}
