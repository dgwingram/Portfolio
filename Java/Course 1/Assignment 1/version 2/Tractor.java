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

    boolean engineOn;
    final int wheelCircumference;
    Gear gear;
    int throttleControl;
    //TODO check if 1 or 2 engine variables (engine On/ rpm)

    public Tractor() {
        this.engineOn = false;
        this.throttleControl = 0;
        this.wheelCircumference = 4;
        this.gear = Gear.NEUTRAL;

    }

    public enum Gear {
        NEUTRAL, LOW, MID, HIGH
    }

    public void startEngine() {
        if (this.gear == Gear.NEUTRAL && this.throttleControl >= 10) {
            this.engineOn = true;
        }
    }

    public void stopEngine() {
        this.engineOn = false;

    }

    public void setThrottle(int throttle) {
        if (throttle >= 0 && throttle <= 100) {
            this.throttleControl = throttle;
           
        }
        if (this.engineOn) {
            //TODO Code RPM if need rpm variable
        }
        
    }

    public void setGear(Gear selectedGear) {
        this.gear = selectedGear;
    }

    @Override
    public String toString() {
        String started;
        int rpm = 0;
        if (this.engineOn) {
            rpm = this.throttleControl * 24;
        }
        if (this.engineOn) {
            started = "     started, ";
        } else {
            started = " not started, ";
        }

        double gearRatio, speed = 0.0;

        switch (gear) {
            case LOW:
                gearRatio = 20.0;
                break;
            case MID:
                gearRatio = 10.0;
                break;
            case HIGH:
                gearRatio = 5.0;
                break;
            default:
                gearRatio = 0.0;
        }//NEUTRAL,LOW,MID,HIGH
        if (gearRatio != 0) {
            speed = (rpm / gearRatio * this.wheelCircumference * 60) / 1000;
        }

        return "Tractor" + started + "throttle: " + this.throttleControl + ", engine rpm = " + rpm + ", speed = " + speed + "kpm, gear = " + gear;
    }
}
