package client;

import message.Commande;
import message.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Etienne.
 */
public final class Marshalling {

    private Marshalling() {
    }

    public static ArrayList<String> translate(Object msg) {
        if (msg instanceof Message) {
            ArrayList<String> retour = new ArrayList<String>();
            retour.add(((Message) msg).getCommande().toString());
            retour.addAll(((Message) msg).getArgs());
            return retour;
        }
        return null;
    }

    public static Message translate(ArrayList<String> msg) {
        Commande retour;
        if (msg.get(0).equals(Commande.ADD.toString())) retour = Commande.ADD;
        else if (msg.get(0).equals(Commande.ADDS.toString())) retour = Commande.ADDS;
        else if (msg.get(0).equals(Commande.GET.toString())) retour = Commande.GET;
        else retour = Commande.ERREUR;
        msg.remove(0);
        return new Message(retour, (List<String>) msg);
    }

}
