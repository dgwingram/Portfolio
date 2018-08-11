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
public class Tractor {
    int engineRPM;
    final int wheelCircumference;

    private void setRPM() {
        this.engineRPM=24*this.throttleControl;
    }

 
    public enum Gear{NUETRAL,LOW,MID,HIGH}
    Gear gear;

    public void setGear(Gear gear) {
        this.gear = gear;
    }
    
    public Tractor() {
        this.throttleControl=0;
        this.gear=Gear.NUETRAL;
        this.wheelCircumference=4;
        this.engineRPM=0;
    }
    public void startEngine(){
        if(this.throttleControl>10&&this.gear==Gear.NUETRAL){
           setRPM();
            
        }
    }
    public void stopEngine(){
        this.engineRPM=0;
    }
    private int throttleControl;

    /**
     * Set the value of throttleControl
     *
     * @param throttleControl new value of throttleControl
     */
    public void setThrottle(int throttleControl) {
        //Validate throttle Control
        if(throttleControl >=0&&throttleControl<=100){
            this.throttleControl = throttleControl;
            //check engine on and, if not on, set rpm 
            if(this.engineRPM!=0){
                this.setRPM();
            }
        }
    }
    private double calculateSpeed(){
        double gearRatio,speed=0.0;
        
        switch(this.gear){
            case LOW: gearRatio=5.0;break;
            case MID: gearRatio=10.;break;
            case HIGH: gearRatio=20.0;break;
            default: gearRatio=0.0;
        }
        if(gearRatio!=0.0)
            speed =(this.engineRPM/gearRatio*this.wheelCircumference*60)/1000 ;
        return speed;
    }
    @Override
    public String toString() {
        String started;
        if(this.engineRPM==0)
            started="not started";
        else
            started="started";
        
        return "Tractor: "+started +", throttle= "+this.throttleControl+", engine rpm= "+this.engineRPM+"kpm, tractor speed = "+calculateSpeed()+", gear = "+this.gear;/* 
    }
    }
}
