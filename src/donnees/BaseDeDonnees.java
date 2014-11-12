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

    public ArrayList<String> addSurnom(Personne p, ArrayList<String> surnoms) {
        ArrayList<String> surnomsExisting = new ArrayList<String>();
        for(int i=0; i < listPersonnes.size(); i++) {
            if(!this.addSurnom(p,surnoms.get(i))) surnomsExisting.add(surnoms.get(i));
        }
        return surnomsExisting;
    }

    public boolean addPersonne(Personne p) {
        for(int i=0;i<listPersonnes.size();i++) {
            if(surnomExisting(p.getSurnom(i))) return false;
        }
        listPersonnes.add(p);
        return true;
    }

    @Override
    public String toString() {
        String result = " ***** Liste de Personnes *****\n";
        for(int i=0;i<listPersonnes.size();i++) {
            result+= " ---- Personne n°"+(i+1)+" ----\n"+listPersonnes.toString()+"\n";
        }
        return result;
    }



}
