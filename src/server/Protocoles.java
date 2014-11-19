package server;

import donnees.BaseDeDonnees;
import donnees.Personne;
import message.Commande;
import message.Message;

import java.util.ArrayList;

/**
 * Created by user on 12/11/2014.
 */
public class Protocoles {

    private BaseDeDonnees bdd;

    public Protocoles() {
        bdd = new BaseDeDonnees();
    }

    public Protocoles(BaseDeDonnees bdd) {
        this.bdd = bdd;
    }

    public BaseDeDonnees getBdd() {
        return bdd;
    }

    public void setBdd(BaseDeDonnees bdd) {
        this.bdd = bdd;
    }

    public Object add(Message message) {
        Message retour = new Message(Commande.ERREUR, "Surnom existe déjà");
        String nom = message.getArgs(0);
        message.getArgs().remove(0);
        Personne nouvelle = new Personne(nom, (ArrayList<String>)message.getArgs());
        if(bdd.addPersonne(nouvelle)) {
            retour = new Message(Commande.OK, "Opération effectuée avec succès !");
        }
        return (Object) retour;
    }

    public Object get() {
        Message retour = new Message(Commande.OK, "**** Liste de surnom ****\n"+ bdd.toString());
        return retour;
    }

    public Object commande(Object messageO) {
        Message message = ((Message)messageO);
        switch (message.getCommande()) {
            case NONE:
                break;
            case ADD :
                return this.add(message);
            case ADDS:
                break;
            case GET:
                return this.get();
            case OK:
                break;
            case ERREUR:
                break;
        }
        return new Message(Commande.ERREUR,"Unknown Error");
    }



}
