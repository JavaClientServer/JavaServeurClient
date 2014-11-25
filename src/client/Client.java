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

    public Message add_g(Commande cmd,String name,String ... nicknames){
        String[] tmp = new String[nicknames.length+1];
        for(int i= 0;i<nicknames.length;i++)tmp[i+1]=nicknames[i];
        tmp[0] = name;
        return new Message(cmd,tmp);
    }
    public Message add(String name,String ... nicknames){
        return add_g(Commande.ADD,name,nicknames);
    }
    public Message add_nickname(String existing,String ... nicknames){
        return add_g(Commande.ADDS,existing,nicknames);
    }

    public Message get() {
        return new Message(Commande.GET);
    }

    public static void main(String []args) {
        // ip nico -> 134.59.215.124:6356
        // ip max -> 134.59.214.216:6969
        // ip max::free -> 83.157.117.225
        Client c = new Client("83.157.117.225",6969);
        System.out.println(c.add("Etienne","toto","tutu"));
        if(c.send(c.add("Etienne","toto","tutu"))) {
            c.receive();
        }if(c.send(c.add("Maxime","toto","tutu"))) {
            c.receive();
        }if(c.send(c.add("Etienne","tito","titu"))) {
            c.receive();
        }
        if(c.send(c.add_nickname("tito","tata","ttttt","toto"))){
            c.receive();
        }
        if(c.send(c.get())){
            c.receive();
        }
        c.close();
    }
}
