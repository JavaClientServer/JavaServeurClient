package message;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 Class Message
 * Objet transmit entre le client et le serveur
 * @author Etienne
 */
public class Message implements Serializable {
    private Commande msg;
    private List<String> args;
    private static final long serialVersionUID = 69696969L;

    /**
     * Constructeur d'un message
     * @param commande la commande du message, les seuls commandes possibles sont :
     *                 - ADD
     *                 - ADDS
     *                 - GET
     *                 - OK
     *                 - ERREUR
     * @param listString une liste variable d'arguments (String)
     */
    public Message(Commande commande,String ... listString) {
        if(commande == null)this.msg = Commande.NONE;
        else this.msg = commande;
        this.args = new LinkedList<String>();
        for(int i=0;i<listString.length;i++){
            args.add(i,listString[i]);
        }
    }

    /**
     * Methode qui renvoi le resultat pour un message de retour du serveur (OK et ERREUR)
     * @return le resultat du message
     */
    public String getResult() {
        if(this.msg == Commande.OK || this.msg == Commande.ERREUR) {
            return this.msg.toString()+":"+this.args.get(0);
        }
        return "pas de resultat pour ce type de message";
    }

    @Override
    /** Methode toString permettant d'afficher l'objet de manière textuelle
     * @return un string decrivant l'objet
     */
    public String toString() {
        return "Commande : "
                +this.msg
                +"| arguments/result : "
                +this.args;
    }
}