package server;

import donnees.BaseDeDonnees;
import donnees.Personne;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 * Created by user on 12/11/2014.
 */
public interface ProtocolesInterface extends Remote {

   public ArrayList<String> commande(ArrayList<String> msg) throws RemoteException;

}
