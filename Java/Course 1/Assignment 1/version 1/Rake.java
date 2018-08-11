/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 * @author DGWIngram
 */
public class Rake extends Attachment {

    private boolean rakeAttached;

    public Rake() {
        this.rakeAttached = false;
    }

    @Override
    public void attach() {
        //Check if Rake is attached
        if (!this.rakeAttached) {
            //Attach Rake
            this.setHeight(4);
            this.rakeAttached = true;
        } else {
            System.out.println("\033[31mRake Already attached");
        }
    }

    @Override
    public void remove() {
        if (this.rakeAttached) {
            this.setHeight(4);
            this.rakeAttached = false;
        } else {
            System.out.println("\033[31mRake is not attached already");
        }
    }

    @Override
    public void use() {
        if (this.rakeAttached) {
            try {

                System.out.println("Raking the ground");
                Thread.sleep(1000);
                System.out.print(" .");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1500);
                System.out.println("");
            } catch (InterruptedException ex) {
                System.out.println("\033[31mRake jammed ...");
            }
        } else {
            System.out.println("\033[31mRake is already attached to something ....");
        }
        
    }

    @Override
    public String toString() {
        
        return "Rake";
    }

}
