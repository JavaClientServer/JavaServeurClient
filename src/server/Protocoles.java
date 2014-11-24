package server;

import donnees.BaseDeDonnees;
import donnees.Personne;
import message.Commande;
import message.Message;

import java.lang.reflect.Array;
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

    public ArrayList<String> add(ArrayList<String> msg) {
        ArrayList<String> retour = new ArrayList<String>();
        retour.add("Erreur");
        retour.add("Surnom existe déjà !");
        String nom = msg.get(0);
        msg.remove(0);
        Personne nouvelle = new Personne(nom, msg);
        if(bdd.addPersonne(nouvelle)) {
            retour.set(0,"Ok");
            retour.set(1,"Opération adds effectuée avec succès !\n");
        }
        return retour;
    }

    public ArrayList<String> adds(ArrayList<String> msg) {
        ArrayList<String> retour = new ArrayList<String>();
        retour.add("Erreur");
        String oldSurnom = msg.get(0);
        msg.remove(0);
        if(!bdd.surnomExisting(oldSurnom)) {
            retour.add("oldSurnom n'existe pas !");
            return retour;
        }
        ArrayList<String> errorSurnom = this.bdd.addSurnom(bdd.getListPersonnes(oldSurnom),(ArrayList<String>)msg);
        retour.set(0,"Ok");
        retour.add("Opération adds effectuée avec succès !\nSurnoms pas ajouté car existant :\n"+errorSurnom.toString());
        return retour;
    }

    public ArrayList<String> get() {
        ArrayList<String> retour = new ArrayList<String>();
        retour.add("Ok");
        retour.add(bdd.toString());
        return retour;
    }

    public ArrayList<String> commande(ArrayList<String> msg) {
        String s = msg.get(0);
        if (s.equals("None")) {}
        else if (s.equals("Add")) {
            msg.remove(0);
            return this.add(msg);
        } else if (s.equals("Adds")) {
            msg.remove(0);
            return this.adds(msg);
        } else if (s.equals("Get")) {
            msg.remove(0);
            return this.get();
        } else if (s.equals("Ok")) {}
        else if (s.equals("Erreur")) {}
        return null;
    }



}
