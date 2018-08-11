/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author DIngram20
 */
public class Blade extends Attachment {

    private boolean bladeAttached;

    public Blade() {
        this.bladeAttached = false;
    }

    @Override
    public void attach() {
        //Check if Blade is attached
        if (!this.bladeAttached) {
            //attach blade
            this.setHeight(4);
            this.bladeAttached = true;
        } else {
            System.out.println("Blade is already attached");
        }
    }

    @Override
    public void remove() {
        if (this.bladeAttached) {
            this.setHeight(4);
            this.bladeAttached = false;
        } else {
            System.out.println("Blade is not attached already ...");
        }
    }

    @Override
    public void use() {
        try {
            
            System.out.print("Using Blade");
            Thread.sleep(1000);
            System.out.print(" .");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1500);
            System.out.println("");
        } catch (InterruptedException ex) {
            System.out.println("Blade hit a rock ....");
        }
    }

    @Override
    public String toString() {
        return "Blade";
    }
}
