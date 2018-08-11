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
public class Auger extends PTOAttachment {

    boolean augerAttached;

    public Auger() {
        augerAttached = false;
    }

    @Override
    public void attach() {
        if (!augerAttached) {
            this.setHeight(10);
            this.setSpeed(0);
            augerAttached = true;
        } else {
            System.out.println("Auger already Attached");
        }

    }

    @Override
    public void remove() {
        if (augerAttached) {
            this.setHeight(10);
            this.setSpeed(0);
            augerAttached = false;
        } else {
            System.out.println("Auger is already not attached.");
        }
    }

    @Override
    public void use() {
        if (this.augerAttached) {
            try {
                this.setHeight(0);
                this.setSpeed(480);
                System.out.print("Drilling hole ");
                Thread.sleep(1000);
                System.out.print(" .");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1000);
                System.out.print(".");
                Thread.sleep(1500);
                System.out.println("");
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                System.out.println("\033[31mAuger Prematurely stoped");
            }

        } else {
            System.out.println("\033[31mAuger needs to be attached to use, unless your Hurcules ...");
        }
    }

    @Override
    public String toString() {
        return "Auger";
    }
}
