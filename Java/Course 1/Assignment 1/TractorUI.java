/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Tractor;

/**
 *
 * @author Daniel
 */
public class TractorUI {
    public void startTractor1(){
        Tractor tractor = new Tractor();
        tractor.setThrottle(20);
        tractor.startEngine();
        System.out.println(tractor);
    }
    public void startTractor2(){
        Tractor tractor = new Tractor();
        tractor.setThrottle(5);			// too low to start
        tractor.startEngine();			// tractor will not start
        System.out.println(tractor);

    }
    public void startTractor3(){
        Tractor tractor = new Tractor();
        tractor.setThrottle(15);			// to low to start
        tractor.startEngine();			// tractor will not start
        tractor.setGear(Tractor.Gear.LOW); 	// 
        System.out.println(tractor);	

    }
}
