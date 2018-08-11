package server;

import SockData.Message;
import SockData.Reply;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author DGWIngram
 */
public class Server  {
    static int PORT = 1234;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int id =1;
        
        ServerSocket listener;
        try{
            //connects to server
            listener = new ServerSocket(PORT);
            
            Socket server;
            server = listener.accept();
            
            // now can show we accepted the connection
            System.out.println("Accepted");
            
            //Write the message
            Message msg;
            
            //Waiting for a message object
            
            //open input stream from the socket:
            InputStream iStream = server.getInputStream(); 
            System.out.println("Got input stream");
            
            OutputStream oStream = server.getOutputStream(); //configure obj output Stream to output to client
            System.out.println("Got output stream");
            
            ObjectInputStream input = new ObjectInputStream(iStream);
            System.out.println("Got Obj Input");
            
            ObjectOutputStream output = new ObjectOutputStream(oStream);
            System.out.println("Got Obj Output");
            
            //now wait for the client to send data to you.  
            msg = (Message) input.readObject();
            System.out.println("Got Message: "+msg.getMessage());
            
            //Send Reply
            Reply reply = new Reply();
            reply.setReply("This is a reply from the server");
            output.writeObject(reply);
            System.out.println("Reply sent.");
            
            //close the server
            server.close();
            // now can close the listener
            listener.close();
            /*
                Could have changed this to a try with resources type of Tyr/Catch
            */
            System.out.println("Everything Closed");
            
        }catch(IOException ex){
            System.err.println("Cannot create server socket");  //serr tab = System.err.println("");
            // good idea to to be specific with the erro as follows
            System.err.println(ex.getMessage()); // 'err' not 'out' - Net Beans writes in Red, console no difference
            
        } catch (ClassNotFoundException ex) {
            System.err.println("Class not found: " + ex.getMessage());
        }
        //for(;;){ //loop for accepting connection and 
          System.out.println("WAITING FOR CONNECTION " + id);  
        //}
    }
}
