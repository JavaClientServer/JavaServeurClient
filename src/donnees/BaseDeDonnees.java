package donnees;

import java.util.ArrayList;

/**
 * Created by user on 12/11/2014.
 */
public class BaseDeDonnees {

    private ArrayList<Personne> listPersonnes;

    public BaseDeDonnees() {
        listPersonnes = new ArrayList<Personne>();
    }

    public BaseDeDonnees(ArrayList<Personne> listPersonnes) {
        this.listPersonnes = listPersonnes;
    }

    public ArrayList<Personne> getListPersonnes() {
        return listPersonnes;
    }

    public void setListPersonnes(ArrayList<Personne> listPersonnes) {
        this.listPersonnes = listPersonnes;
    }

    public Personne getListPersonnes(int i) {
        return this.listPersonnes.get(i);
    }

    public boolean surnomExisting(String surnom) {
        for(int i=0;i<listPersonnes.size();i++) {
            if(listPersonnes.get(i).isSurnom(surnom)) return false;
        }
        return true;
    }

    public boolean addSurnom(Personne p,String surnom) {
        if(!surnomExisting(surnom)) {
            listPersonnes.get(listPersonnes.indexOf((Object)p)).addSurnom(surnom);
            return true;
        }
        return false;
    }

    public void addPersonne(Personne p) {
        listPersonnes.add(p);
    }

}
