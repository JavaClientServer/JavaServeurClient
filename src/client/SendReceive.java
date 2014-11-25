package client;

import message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by Etienne.
 */
public class SendReceive {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;

    public SendReceive(Socket client){
        try{
            this.client = client;
            this.output = new ObjectOutputStream(this.client.getOutputStream());
            this.input = new ObjectInputStream(this.client.getInputStream());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean send(Serializable msg) {
        try {
            this.output.writeObject(msg);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void receive(){
        try{
            Message msg = (Message)input.readObject();
            System.out.print(msg.getResult());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            this.input.close();
            this.output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
