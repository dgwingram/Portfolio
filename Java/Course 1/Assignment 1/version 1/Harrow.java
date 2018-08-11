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
public class Harrow extends Attachment {

    private boolean harrowAttached;

    public Harrow() {
        this.harrowAttached = false;
    }

    @Override
    public void attach() {
        if (!this.harrowAttached) {
            this.setHeight(0);
            this.harrowAttached = true;
        } else {
            System.out.println("Harrow is already attached to something ...");
        }
    }

    @Override
    public void remove() {
        if (this.harrowAttached) {
            this.setHeight(0);
            this.harrowAttached = false;
        } else {
            System.out.println("\033[31mHarrow already attached ...");
        }
    }

    @Override
    public void use() {

        try {
            System.out.println("Beraking up surface of the soil");
            Thread.sleep(1000);
            System.out.print(" .");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1000);
            System.out.print(".");
            Thread.sleep(1500);
            System.out.println("");
        } catch (InterruptedException ex) {
            System.out.println("\033[31mHarrow unexpectedly stopped functioning.");
        }
    }

    @Override
    public String toString() {
        return "Harrow";
    }
}
