package client;


import message.Message;
import server.Marshalling;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Etienne on 05/11/2014.
 */
public class Client {


    private Socket client;
    private SendReceive sendReceive;
    private Protocole proto;


    public Client(String ip, int port){
        try{
            this.client = new Socket(ip,port);
            this.sendReceive = new SendReceive(this.client);
            this.proto = new Protocole();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.sendReceive.close();
            this.client.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private ArrayList<String> getUserInput(){
        BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
        try {
            String s = br.readLine();
            ArrayList<String> items = new ArrayList<String>(Arrays.asList(s.split(" ")));
            return items;
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }

    public boolean listenUser() {
        ArrayList<String> cmd = getUserInput();
        if(cmd.get(0).equals("QUIT")){
            this.close();
            return false;
        }
        Message msg = Marshalling.translate(cmd);
        System.out.println(msg);
        if(this.sendReceive.send(msg)){
            this.sendReceive.receive();
        }
        return true;
    }

    public static void main(String []args) {
        // ip nico -> 134.59.215.124:6356
        // ip max -> 134.59.214.216:6969
        // ip max::free -> 83.157.117.225
        Client c = new Client("localhost",6969);
        while(c.listenUser());
    }




}
