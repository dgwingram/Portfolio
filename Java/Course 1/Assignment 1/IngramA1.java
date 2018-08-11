/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ingrama1;

import View.TractorUI;

/**
 *
 * @author DGWIngram
 */
 
public class IngramA1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        TractorUI tractor = new TractorUI();
        
        System.out.println("Tractor 1:");
        tractor.startTractor1();
        
        System.out.println("Tractor 2");
        tractor.startTractor2();
        
        System.out.println("Tractor 3");
        tractor.startTractor3();           
    }   
}
