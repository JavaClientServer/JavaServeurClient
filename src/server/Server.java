package server;

import client.Protocole;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Class Server
 * Gestion du serveur de nom/surnom
 */
public class Server {

    public Server(int portNumber) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ServeurStrobbes";
            ProtocolesInterface engine = new Protocoles();
            ProtocolesInterface stub =
                    (ProtocolesInterface) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.createRegistry(portNumber);
            registry.rebind(name, stub);
            System.out.println("ServeurStrobbes bound");
        } catch (Exception e) {
            System.err.println("ServeurStrobbes exception:");
            e.printStackTrace();
        }
    }

    public static void main(String arg[]) throws IOException {
            Server serveur = new Server(6969);

    }


}
