package ca.on.sl.comp208.assignment3;

import java.util.ArrayList;

/**
 * Created by DGWIngram on 2017-04-12.
 */

public class BookOutline {
    private int numChpAdded;
    private String name;
    ArrayList<Chapter> chp;
    private int numChp;

    public BookOutline(String name, int numChp) {
        chp = new ArrayList<>();
        this.name = name;
        this.numChp = numChp;
        numChpAdded = 0;
    }

    public ArrayList<Chapter> getChp() {
        return chp;
    }

    public String getBookName(){
        return this.name;
    }

    public void addChp(Chapter c){
        this.chp.add(c);
    }
    public int getNumChp(){
        return numChp;
    }

    @Override
    public String toString() {
        String returnString="{\"Book\":\""+this.name+"\",\n"
                           +"\"Chapter\":[\n";
        for(Chapter c:chp){
            returnString+=c.toString();
        }
        returnString +="]}";
        
        return returnString;
    }
}
