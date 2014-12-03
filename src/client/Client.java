package client;


import message.Message;
import server.ProtocolesInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Etienne on 05/11/2014.
 */
public class Client {

    public Client(String ip, int port){
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "ServeurStrobbes";
            Registry registry = LocateRegistry.getRegistry(ip,port);
            ProtocolesInterface comp = (ProtocolesInterface) registry.lookup(name);
            comp.commande()
            System.out.println();
        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }


    public static void main(String []args) {
        // ip nico -> 134.59.215.124:6356
        // ip max -> 134.59.214.216:6969
        // ip max::free -> 83.157.117.225
        Client c = new Client("localhost",6969);
    }




}
