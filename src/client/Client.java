package client;


import java.io.*;
import java.net.Socket;

/**
 * Created by Etienne on 05/11/2014.
 */
public class Client {


    private Socket client;



    public Client(String ip, int port){
        try{
            client = new Socket(ip,port);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.client.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return this.client;
    }

    public static void main(String []args) {
        // ip nico -> 134.59.215.124:6356
        // ip max -> 134.59.214.216:6969
        // ip max::free -> 83.157.117.225
        Client c = new Client("localhost",6969);
        SendReceive sendReceive = new SendReceive(c.getSocket());
        Protocole proto = new Protocole();
        if(sendReceive.send(proto.addNew("Etienne", "toto", "tutu"))) {
            sendReceive.receive();
        }if(sendReceive.send(proto.addNew("Maxime", "toto", "tutu"))) {
            sendReceive.receive();
        }if(sendReceive.send(proto.addNew("Etienne", "tito", "titu"))) {
            sendReceive.receive();
        }
        if(sendReceive.send(proto.addNickname("tito", "tata", "ttttt", "toto"))){
            sendReceive.receive();
        }
        if(sendReceive.send(proto.get())){
            sendReceive.receive();
        }
        c.close();
    }


}
