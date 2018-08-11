/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DGWIngram
 */
public abstract class PTOAttachment extends Attachment {
    private int speed;

    public  void setSpeed(int speed){
        if(speed>=240&&speed<=2400) this.speed=speed;
    }
    
}
