package donnees;

import java.util.ArrayList;

/**
 * Created by user on 12/11/2014.
 */
public class Personne {

    private String nom;
    private ArrayList<String> surnom;

    public Personne() {
        nom = "";
        surnom = new ArrayList<String>();
    }

    public Personne(String nom) {
        this.nom = nom;
        surnom = new ArrayList<String>();
    }

    public Personne(String nom, ArrayList<String> surnom) {
        this.nom = nom;
        this.surnom = surnom;
    }

    public ArrayList<String> getSurnom() {
        return surnom;
    }

    public String getSurnom(int i) {
        return surnom.get(i);
    }

    public void setSurnom(ArrayList<String> surnom) {
        this.surnom = surnom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isSurnom(String surnom) {
        for(int i =0; i<this.getSurnom().size();i++) {
            if(this.getSurnom(i).equals(surnom)) return true;
        }
        return false;
    }

    public boolean addSurnom(String surnom) {
        if(isSurnom(surnom)) return false;
        this.surnom.add(surnom);
        return true;
    }

    @Override
    public String toString() {
        String result = this.nom+": ";
        for(int i=0;i < surnom.size();i++) {
            result+= surnom.get(i)+" ";
        }
        return result;
    }


}
