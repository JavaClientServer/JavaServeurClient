package message;

import java.io.Serializable;

/**
 Class Message
 * Objet transmit entre le client et le serveur
 * Classe à compléter
 */
public class Message implements Serializable {
    // TODO changer le string en enum
    private String msg;
    private static final long serialVersionUID = 69696969L;

    public Message(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Message : "+this.msg;
    }
}
