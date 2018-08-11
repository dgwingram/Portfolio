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
public class Mower extends PTOAttachment {

    private boolean mowerAttached;

    public Mower() {
        this.mowerAttached = false;
    }

    @Override
    public void attach() {
        if (!this.mowerAttached) {
            this.setHeight(10);
            this.setSpeed(0);
            mowerAttached = true;
        } else {
            System.out.println("Mower is already attached to something ...");
        }
    }

    @Override
    public void remove() {
        if (this.mowerAttached) {
            this.setHeight(10);
            this.setSpeed(0);
            this.mowerAttached = false;
        } else {
            System.out.println("\033[31mMower is not attached to anything");
        }
    }

    @Override
    public void use() {
        if (this.mowerAttached) {
            try {
                this.setHeight(4);
                this.setSpeed(480);
                System.out.println("Mowing ");
                Thread.sleep(1000);
                System.out.print(" .");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1500);
                System.out.println("");
            } catch (InterruptedException ex) {
                System.out.println("\033[31mMowing Suddenly stopped ...");
            }
        } else {
            System.out.println("\033[31mMower must be attached to use. ");
        }
    }

    @Override
    public String toString() {
         return "Mower";
    }
}
