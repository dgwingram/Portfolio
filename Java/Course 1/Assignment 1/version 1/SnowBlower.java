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
public class SnowBlower extends PTOAttachment {

    private boolean blowerAttached;

    public SnowBlower() {
        this.blowerAttached = false;
    }

    @Override
    public void attach() {
        //Attach Snow Blower if not attached
        if (!this.blowerAttached) {
            this.setHeight(8);
            this.blowerAttached = true;
        } else {
            System.out.println("Snow Blower is already attached to something ...");

        }
    }

    @Override
    public void remove() {
        //Removing Snow Blower if attached
        if (this.blowerAttached) {
            this.setHeight(8);
            this.blowerAttached = false;
        } else {
            System.out.println("\033[31mSnow Blower is already attached to something ...");
        }
    }

    @Override
    public void use() {
        //Check Snow Blower is attached
        if (this.blowerAttached) {
            try {
                //Use Snow Blower
                this.setHeight(2);
                this.setSpeed(480);
                System.out.println("Removing snow");
                Thread.sleep(1000);
                System.out.print(" .");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1500);
                System.out.println("");
            } catch (InterruptedException ex) {
                System.out.println("\033[31mSnow Blower jammed ...");
            }
        } else {
            System.out.println("\033[31mSnow Blower must be attached to something to use ...");
        }
    }

    @Override
    public String toString() {
       return "Snow Blower";
    }
}
