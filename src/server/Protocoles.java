package server;

import donnees.BaseDeDonnees;

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


}
