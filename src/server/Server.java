package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Class Server
 * Gestion du serveur de nom/surnom
 */
public class Server implements Observer{

    private ServerSocket socketServer;
    private Protocoles proto;

    public Server(int portNumber) {
        try {
            this.socketServer = new ServerSocket(portNumber);
            this.proto = new Protocoles();
            System.out.println("Initialisation serveur ok, port :"+portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getSocketServer() {
        return this.socketServer;
    }

    public boolean acceptConnexion() {
        try {
            Socket newClient = socketServer.accept();
            ListenSend clientListenSend = new ListenSend(newClient);
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
        ArrayList<String> msg = Marshalling.translate(arg);
        if (!msg.equals(null)) {
            ((ListenSend) o).send(Marshalling.translate(this.proto.commande(msg)));
        }
    }

    public static void main(String arg[]) throws IOException {
            Server serveur = new Server(6969);
            while(true) {
                serveur.acceptConnexion();
            }

    }


}
