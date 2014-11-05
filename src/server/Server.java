package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class Server
 * Gestion du serveur de nom/surnom
 */
public class Server {

    private Socket socket = null;

    public Server(Socket socket) {
        this.socket = socket;
    }

    private void start() {

    }

    public static void main(String arg[]) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(8080);

        } catch (IOException e) {
            System.err.println("Impossible d'écouter le port 8080");
        }
        while(listening) new Server(serverSocket.accept()).start();
        serverSocket.close();

    }

}
