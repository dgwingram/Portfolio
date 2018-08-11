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

public abstract class Attachment implements IAttachment{
    private float height;
    public  void setHeight(float height){
        if(height>=0&&height<=10) this.height=height;
    }
   
}
