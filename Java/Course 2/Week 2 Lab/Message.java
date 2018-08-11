package SockData;

import java.io.Serializable;

/**
 *
 * @author DGWIngram
 */
public class Message implements Serializable {
   
    private String message;

    /**
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
