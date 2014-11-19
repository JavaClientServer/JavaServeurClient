package client;

import message.Commande;
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
            Message msg = (Message)input.readObject();
            System.out.print(msg.getResult());
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

    public Message add(String name,String ... nicknames){
        String[] tmp = new String[nicknames.length+1];
        for(int i= 0;i<nicknames.length;i++)tmp[i+1]=nicknames[i];
        tmp[0] = name;
        return new Message(Commande.ADD,tmp);
    }

    public Message get() {
        return new Message(Commande.GET);
    }

    public static void main(String []args) {
        Client c = new Client("134.59.214.216",6969);
        System.out.println(c.add("Etienne","toto","tutu"));
        if(c.send(c.add("Etienne","toto","tutu"))) {
            c.receive();
        }
        if(c.send(c.get())){
            c.receive();
        }
        c.close();
    }
}
