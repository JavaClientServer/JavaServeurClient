package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Class Server
 * Gestion du serveur de nom/surnom
 */
public class Server {

    private ObjectInputStream iStream = null;
    private ObjectOutputStream oStream = null;
    private Socket socket = null;

    public Server(String ip, int portDest, int portSrc) throws UnknownHostException {
        try {
            this.socket = new Socket(ip, portDest, InetAddress.getLocalHost(), portSrc);
            oStream = new ObjectOutputStream(socket.getOutputStream());
            oStream.flush();
            iStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server(String ip) throws UnknownHostException {
        try {
            new Server(ip, 6969, 6969);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public boolean send(Serializable msg) {
        try {
            oStream.writeObject(msg);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public boolean isConnected() {
        return iStream != null & oStream != null;
}

    public boolean receive() {

        try {
            Serializable msg = (Serializable) iStream.readObject();
            System.out.println("J'ai reçu ceci :"+msg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String arg[]) throws IOException {

        Server serv = new Server(arg[0]);
        while(serv.isConnected()) {
            if(serv.receive()) {
                serv.send(new String("J'ai bien reçu copain !"));
            }
        }

    }

}
