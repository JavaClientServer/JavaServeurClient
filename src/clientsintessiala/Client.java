package clientsintessiala;

import java.io.*;
import java.net.Socket;

/**
 * Created by Etienne on 05/11/2014.
 */
public class Client {

    private PrintWriter output;
    private BufferedReader input;
    private Socket client;

    public void send(String msg) {
        this.output.println(msg); output.flush();
    }

    public void receive(){
        Serializable msg = "";
        try {
            msg = input.readLine();
            System.out.println("recu : "+msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Client(String ip, int port){
        try{
            client = new Socket(ip,port);
            this.output = new PrintWriter(client.getOutputStream());
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void lister() {
        this.send("LIST\n");
        this.receive();
    }

    public void record(String surnom, String nom) {
        this.send("RECORD "+surnom+" "+nom+"\n");
        this.receive();
    }

    public void close() {
        try {
            this.send("QUIT\n");
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
        Client c = new Client("134.59.214.67",2014);
        c.lister();
        c.record("Françis_Longduzoubbogrodar", "FLD");
        c.close();
        //}
    }
}
