package client;

import message.Message;

import java.io.*;
import java.net.Socket;

/**
 * Created by Etienne on 05/11/2014.
 */
public class Client {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;

    public boolean send(Serializable msg) {
        try {
            this.output.writeObject(msg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void receive(){
        try{
            Serializable msg = (Serializable)input.readObject();
            System.out.println("recu : "+msg);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public Client(String ip, int port){
        try{
            client = new Socket(ip,port);
            this.output = new ObjectOutputStream(client.getOutputStream());
            this.input = new ObjectInputStream(client.getInputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.input.close();
            this.output.close();
            this.client.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String []args) {
        //while(true){
            Client c = new Client("134.59.214.216",6969);
            Message msg = new Message("coucou ma biche");
            if(c.send(msg)) {
                c.receive();
            }
            if(c.send(new Message("exit"))){
                c.receive();
                c.close();
            }
        //}
    }
}
