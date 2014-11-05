package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Etienne on 05/11/2014.
 */
public class Client {

    private PrintStream output;
    private DataInputStream input;
    private Socket client;

    public void send(String msg) {
        this.output.println(msg);
    }

    public Client(String ip, int port){
        try{
            client = new Socket(ip,port);
            this.output = new PrintStream(client.getOutputStream());
            this.input = new DataInputStream(client.getInputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String []args) {
        Client c = new Client("134.59.214.216",6969);
        c.send("coucou ma biche");

    }

}
