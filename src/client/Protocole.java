package client;

import message.Commande;
import message.Message;

/**
 * Created by Etienne.
 */
public class Protocole {

    public Protocole(){}

    private Message generic_add(Commande cmd,String name, String ... nicknames){
        String[] tmp = new String[nicknames.length+1];
        for(int i= 0;i<nicknames.length;i++)tmp[i+1]=nicknames[i];
        tmp[0] = name;
        return new Message(cmd,tmp);
    }
    public Message addNew(String name, String ... nicknames){
        return this.generic_add(Commande.ADD,name,nicknames);
    }

    public Message addNickname(String existing, String ... nicknames){
        return this.generic_add(Commande.ADDS,existing,nicknames);
    }

    public Message get(){
        return new Message(Commande.GET);
    }


}
