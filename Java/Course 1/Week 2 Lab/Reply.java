package SockData;

import java.io.Serializable;

/**
 *
 * @author dingram20
 */
public class Reply implements Serializable {
    
    private String reply;

    /**
     * Get the value of reply
     *
     * @return the value of reply
     */
    public String getReply() {
        return reply;
    }

    /**
     * Set the value of reply
     *
     * @param reply new value of reply
     */
    public void setReply(String reply) {
        this.reply = reply;
    }
}
