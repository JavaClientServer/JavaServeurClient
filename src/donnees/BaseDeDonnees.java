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

    public int getListPersonnes(String surnom) {
        for (int i = 0; i < listPersonnes.size(); i++) {
            if (listPersonnes.get(i).isSurnom(surnom)) return i;
        }
        return -1;
    }

    public boolean surnomExisting(String surnom) {
        for (int i = 0; i < listPersonnes.size(); i++) {
            if (listPersonnes.get(i).isSurnom(surnom)) return true;
        }
        return false;
    }

    public boolean surnomsExisting(ArrayList<String> surnoms) {
        for (int i = 0; i < surnoms.size(); i++) {
            if (surnomExisting(surnoms.get(i))) return true;
        }
        return false;
    }

    public boolean addSurnom(Personne p, String surnom) {
        if (!surnomExisting(surnom)) {
            listPersonnes.get(listPersonnes.indexOf((Object) p)).addSurnom(surnom);
            return true;
        }
        return false;
    }

    public ArrayList<String> addSurnom(int index, ArrayList<String> surnoms) {
        ArrayList<String> surnomsExisting = new ArrayList<String>();
        for (int i = 0; i < surnoms.size(); i++) {
            if (this.surnomExisting(surnoms.get(i))) {
                surnomsExisting.add(surnoms.get(i));
            } else {
                listPersonnes.get(index).addSurnom(surnoms.get(i));
            }
        }
        return surnomsExisting;
    }

    public boolean addPersonne(Personne p) {
        if (!surnomsExisting(p.getSurnom())) {
            listPersonnes.add(p);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = " ***** Liste de Personnes *****\n";
        for (int i = 0; i < listPersonnes.size(); i++) {
            result += " ---- Personne n°" + (i + 1) + " ----\n" + listPersonnes.get(i).toString() + "\n";
        }
        return result;
    }


}
