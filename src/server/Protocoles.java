package server;

import donnees.BaseDeDonnees;
import donnees.Personne;

import java.lang.reflect.InvocationTargetException;
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
        retour.add("ERREUR");
        retour.add("Surnom existe déjà !\n");
        String nom = msg.get(0);
        msg.remove(0);
        Personne nouvelle = new Personne(nom, msg);
        if (bdd.addPersonne(nouvelle)) {
            retour.set(0, "OK");
            retour.set(1, "Opération add effectuée avec succès !\n");
        }
        return retour;
    }

    public ArrayList<String> adds(ArrayList<String> msg) {
        ArrayList<String> retour = new ArrayList<String>();
        String oldSurnom = msg.get(0);
        msg.remove(0);
        if (!bdd.surnomExisting(oldSurnom)) {
            retour.add("ERREUR");
            retour.add("oldSurnom n'existe pas !");
            return retour;
        }
        ArrayList<String> errorSurnom = this.bdd.addSurnom(bdd.getListPersonnes(oldSurnom), (ArrayList<String>) msg);
        retour.add("OK");
        retour.add("Opération adds effectuée avec succès !\nSurnoms pas ajouté car existant :\n" + errorSurnom.toString());
        return retour;
    }

    public ArrayList<String> get(ArrayList<String> msg) {
        ArrayList<String> retour = new ArrayList<String>();
        retour.add("OK");
        if (msg.isEmpty()) {
            retour.add(bdd.toString());
        }
        return retour;
    }

    public ArrayList<String> commande(ArrayList<String> msg) {
        String s = msg.get(0).toLowerCase();
        msg.remove(0);
        try {
            return (ArrayList<String>) this.getClass().getMethod(s, msg.getClass()).invoke(this, msg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

}
