package client;

import SockData.Message;
import SockData.Reply;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author DGWIngram
 */
public class Client {
    
    static int PORT = 1234;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                        
        //Socket not server Socket, 
        try{
            
            Socket client = new Socket("localhost",PORT);
            System.out.println("Socket Open on Port: " + PORT);
            
            InputStream iStream = client.getInputStream();
            System.out.println("Opened input stream");
            
            OutputStream oStream = client.getOutputStream();
            System.out.println("Opened output Stream");
            
            ObjectOutputStream output = new ObjectOutputStream(oStream);
            System.out.println("Got Output Obj");
            
            ObjectInputStream input = new ObjectInputStream(iStream);
            System.out.println("Got Obj Input");
            
            Message msg = new Message();
            msg.setMessage("Hello Server");
            output.writeObject(msg);
            System.out.println("Client: " + msg.getMessage());
            
            Reply reply = (Reply) input.readObject();
            System.out.println("Server: " + reply.getReply());
            
            reply.setReply("Clinet is Replying");
            
           /* 
            msg =(Message) input.readObject();
            System.out.println(msg.getMessage());
            */
           
            System.out.println("input");
            System.out.println(client.getInputStream());
            System.out.println("Accepted");
            client.close();
        }
        catch(IOException ex){
            System.err.println("Failed: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.err.println("Name error");
        }
        System.out.println("Got Here");
    }  
}
