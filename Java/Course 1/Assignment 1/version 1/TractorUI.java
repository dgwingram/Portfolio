/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Auger;
import Model.Blade;
import Model.Harrow;
import Model.Mower;
import Model.Rake;
import Model.SnowBlower;
import Model.Tractor;
import java.util.Scanner;

/**
 *
 * @author DGWIngram
 */
public class TractorUI {

    Scanner kbd;
    Tractor tractor = new Tractor();

    public TractorUI() {

        kbd = new Scanner(System.in);

        prompt();
        cmdLoop:
        while (kbd.hasNext()) {
            String[] cmds = kbd.nextLine().trim().split(" ");
            System.out.println("\033[36m");
            switch (cmds[0].toLowerCase()) {
                // tractor commands
                case "start":
                    tractor.startEngine();
                    break;
                case "attach":
                    try {
                        switch (cmds[1]) {
                            case "blower":
                                SnowBlower blower = new SnowBlower();
                                tractor.attach(blower);
                                break;
                            case "harrow":
                                Harrow harrow = new Harrow();
                                tractor.attach(harrow);
                                break;
                            case "auger":
                                Auger auger = new Auger();
                                tractor.attach(auger);
                                break;
                            case "rake":
                                Rake rake = new Rake();
                                tractor.attach(rake);
                                break;
                            case "mower":
                                Mower mower = new Mower();
                                tractor.attach(mower);
                                break;
                            case "blade":
                                Blade blade = new Blade();
                                tractor.attach(blade);
                                break;
                            default:
                                System.out.println("\033[31mInvalid option. Use one of the following");
                                System.out.println("\033[31m  blower|harrow|auger|rake|mower|blade");

                        }
                    }catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("\033[31mMust type an attachment. \n\033[31m Available attachments: blower|harrow|auger|rake|mower|blade");
                    }
                    break;
                case "dettach":
                    tractor.removeAttachment();
                    break;
                case "stop":
                    tractor.stopEngine();
                    break;
                case "throttle":
                    try {
                        tractor.setThrottle(Integer.parseInt(cmds[1]));
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                        System.out.println("\t\033[31mInvald command.  Throttle must be followed by a number between 0 and 100");
                        System.out.println("\t\033[31m  example: throttle 80");
                    } catch (Exception e) {
                        System.out.println("\t\033[31m" + e.getMessage());
                        System.out.println("\t\033[31mPlease Input correct format with correct spaces:");
                        System.out.println("\t\033[31mthrottle number");
                    }
                    break;
                case "pto":
                    try {
                        switch (cmds[1]) {
                            case "engage":
                                tractor.engagePTO();
                                break;
                            case "disengage":
                                tractor.disengagePTO();
                                break;
                            default:
                                System.out.println("Invalid option. Use engage or disengage only");
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("\t\033[31m" + e.getMessage());
                        System.out.println("\t\033[31mengage|disengage option missing from pto command");
                    }

                    break;
                case "gear":
                    try {
                        switch (cmds[1]) {
                            case "neutral":
                                tractor.setGear(Tractor.Gear.NEUTRAL);
                                break;
                            case "low":
                                tractor.setGear(Tractor.Gear.LOW);
                                break;
                            case "high":
                                tractor.setGear(Tractor.Gear.HIGH);
                                break;
                            case "mid":
                                tractor.setGear(Tractor.Gear.MID);
                                break;
                            default:
                                System.out.println("\t\033[31mneutral|low|mid|high option missing from gear command. type help for complete command/option list");
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("\t\033[31mPlease provide a gear option with the gear command: ");
                        System.out.println("\t\033[31m  neutral|low|mid|high");
                    }
                    break;
                case "status":
                    System.out.println(tractor);
                    break;
                //Attachment commands
                case "mow-lawn":
                    mowlawn();
                    break;
                case "drill-hole":
                    drillhole();
                    break;
                case "harrow-field":
                    harrowfield();
                    break;
                case "remove-snow":
                    removeSnow();
                    break;
                case "rake":
                    rakeField();
                    break;
                case "furrow":
                    furrowGround();
                    break;

                //System commands
                case "help":
                    displayHelp();
                    break;
                case "exit":
                    break cmdLoop;
                default:
                    System.out.println("\033[31m Invalid command. \n\033[31mType help for a list of commands ...");
            }
            // Remove test output: 
            prompt();
        }
        tractor.stopEngine();
        tractor.setGear(Tractor.Gear.NEUTRAL);

    }

    private void prompt() {

        System.out.print("\033[32mPerfom Command: ");
    }

    private void drillhole() {
        //Attach Auger
        Auger auger = new Auger();
        tractor.attach(auger);

        //Start Engine
        tractor.setThrottle(20);
        tractor.setGear(Tractor.Gear.NEUTRAL);
        tractor.startEngine();

        //Use Auger
        tractor.setThrottle(80);
        tractor.engagePTO();
        auger.use();

        //Stop Tractor
        tractor.disengagePTO();
        tractor.setThrottle(20);
        tractor.stopEngine();

        //remove attachment
        tractor.removeAttachment();

    }

    private void harrowfield() {

        Harrow harrow = new Harrow();

        tractor.attach(harrow);
        tractor.setThrottle(10);
        tractor.setGear(Tractor.Gear.NEUTRAL);

        tractor.startEngine();
        tractor.setGear(Tractor.Gear.MID);
        tractor.setThrottle(60);

        harrow.use();

        tractor.setThrottle(20);
        tractor.stopEngine();
        tractor.removeAttachment();

    }

    private void mowlawn() {
        //Attach mower
        Mower mower = new Mower();
        tractor.attach(mower);

        //Start engine
        tractor.setThrottle(20);
        tractor.setGear(Tractor.Gear.NEUTRAL);
        tractor.startEngine();

        //Use mower
        tractor.setGear(Tractor.Gear.MID);
        tractor.setThrottle(80);
        tractor.engagePTO();
        mower.use();

        //stop mower
        tractor.disengagePTO();
        tractor.setGear(Tractor.Gear.NEUTRAL);
        tractor.setThrottle(20);
        tractor.stopEngine();

        //Dettach mower
        tractor.removeAttachment();

    }

    private void removeSnow() {
        // Attach Snow Blower
        SnowBlower snowBlower = new SnowBlower();
        tractor.attach(snowBlower);

        // Start Tractor
        tractor.setThrottle(20);
        tractor.setGear(Tractor.Gear.NEUTRAL);
        tractor.startEngine();

        // Use Snow Blower
        tractor.engagePTO();
        tractor.setGear(Tractor.Gear.MID);
        tractor.setThrottle(80);

        //snowBlower.setSpeed((24 x 80)รท4 );
        tractor.setThrottle(80);
        snowBlower.use();

        //Stop Tractor
        tractor.disengagePTO();
        tractor.setThrottle(20);
        tractor.setGear(Tractor.Gear.NEUTRAL);

        // Remove Snow Blower
        tractor.removeAttachment();
    }

    private void rakeField() {
        //Attach Rake
        Rake rake = new Rake();
        tractor.attach(rake);

        //Start Tractor
        tractor.setThrottle(20);
        tractor.setGear(Tractor.Gear.NEUTRAL);
        tractor.startEngine();

        //Use Rake
        tractor.setGear(Tractor.Gear.LOW);
        tractor.setThrottle(60);
        rake.use();

        //Stop Tractor
        tractor.setThrottle(20);
        tractor.setGear(Tractor.Gear.NEUTRAL);
        tractor.stopEngine();

        //Remove Rake
        tractor.attach(rake);
    }

    private void furrowGround() {
        //Attach Blade
        Blade blade = new Blade();
        tractor.attach(blade);

        //Start Tractor
        tractor.setGear(Tractor.Gear.NEUTRAL);
        tractor.setThrottle(20);
        tractor.startEngine();
        //Use Blade
        tractor.setThrottle(80);
        tractor.setGear(Tractor.Gear.MID);
        blade.use();
        //Stop Tractor
        tractor.setThrottle(20);
        tractor.setGear(Tractor.Gear.NEUTRAL);
        tractor.stopEngine();
        //Remove blade
        tractor.removeAttachment();
    }

    private void displayHelp() {
        System.out.println("");
        System.out.println("\033[34m\t  *** TRACTOR COMMAND LIST ***\n");
        System.out.println("\033[34m  COMMAND\tOPTIONS\t\t\tDESCRIPTION");
        System.out.println("\033[34m  -------\t-------\t\t\t-----------");

        System.out.println("\033[36m   attach\tattachment\t\t Connect an Attachment:");
        System.out.println("\033[36m   \t\t\t\t\t blower|harrow|auger|rake|mower|blade");
        System.out.println("\033[36m   dettach\t\t\t\t\t Dettach any Attachments");
        System.out.println("\033[36m   drill-hole\t\t\t\t Digs a hole");
        System.out.println("\033[36m   exit\t\t\t\t\t Exit program");
        System.out.println("\033[36m   furrow\t\t\t\t Dig deep ditches for planting seeds");
        System.out.println("\033[36m   gear\t\t neutral|low|mid|high\t Change the Gear");
        System.out.println("\033[36m   harrow-field\t\t\t\t Remove & breakup surface dirt");
        System.out.println("\033[36m   help\t\t\t\t\t Give a list of Valid Commands ");
        System.out.println("\033[36m   mow-lawn\t\t\t\t Cut Grass");
        System.out.println("\033[36m   pto\t\tengage|disengage\t Dis/Engages Power Take-Off ");
        System.out.println("\033[36m   remove-snow\t\t\t\t Snow blows snow");
        System.out.println("\033[36m   rake\t\t\t\t\t Rake or smooth surface of ground");
        System.out.println("\033[36m   start\t\t\t\t Starts Tractor's Engine");
        System.out.println("\033[36m   status\t\t\t\t Displays current state of the Tractor");
        System.out.println("\033[36m   stop\t\t\t\t\t Stops Tractor's Engine");
        System.out.println("\033[36m   throttle\t 0-100\t\t\t Changes the throttle values\n\n");
    }
}
