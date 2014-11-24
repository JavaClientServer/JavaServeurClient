package message;

/**
 * @author Etienne
 * Enum reprenant toutes les commandes possibles
 */
public enum Commande {
    NONE,
    ADD,
    ADDS,
    GET,
    OK,
    ERREUR;

    @Override
    public String toString() {
        switch (this) {
            case ADD:
                return "Add";
            case ADDS:
                return "Adds";
            case GET:
                return "Get";
            case OK:
                return "Ok";
            case ERREUR:
                return "Erreur";

        }
        return "None";
    }
}