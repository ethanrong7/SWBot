package tess4j;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RunAuto {
    
    public void StartRun() throws Exception {
        AutoClicker clicker = new AutoClicker();
        boolean running = true;
        /*
        // user to press "repeat battle" run, assume they have enough energy //
        clicker.clickMouse((int)(Math.random()*(1688 - 1391))+ 1391
                        ,(int)(Math.random()*(921 - 852))+ 852,InputEvent.BUTTON1_MASK);
        */
        int upperX = 0;
        int lowerX = 0;
        int upperY = 0;
        int lowerY = 0;       

        InsufficientEnergy();
        NoOfRunsCompleted();
        Opencv.GreyScale2("temporary images", "completed_runs");
        System.out.println(ImageToText.convert2("temporary images", "completed_runs"));
        
        int number = 0;
        
        if (ImageToText.convert2("temporary images", "completed_runs").contains("(")) {
            number = Integer.parseInt(ImageToText.convert2("temporary images", "completed_runs").split("\\/")[0]);
        } else if (ImageToText.convert2("temporary images", "completed_runs").contains("/")) {
            number = Integer.parseInt(ImageToText.convert2("temporary images", "completed_runs").split("\\/")[0]);
        }
        
        if (number == 0) {
            System.out.println("You have no rewards");
        } else if (number >= 1) {
            upperX = 1284;
            lowerX = 1198;
            upperY = 491;
            lowerY = 405;
            for (int i = 0; i < (number); i++) {
                RuneAuto rune = new RuneAuto();
                // click on rune //
                clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                        ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);

                AutoClicker.pause();
                
                rune.RuneHeading();
                if (ImageToText.convert2("temporary images","rune_heading").contains("(") && ImageToText.convert2("temporary images","rune_heading").contains(")")) {
                    rune.Evaluate();
                    rune.Criteria();
                    
                    if (rune.getSellRune() == true) {
                        clicker.clickMouse((int)(Math.random()*(880 - 681))+ 681
                        ,(int)(Math.random()*(819 - 742))+ 742,InputEvent.BUTTON1_MASK);
                        
                        AutoClicker.pause();
                        
                        clicker.clickMouse((int)(Math.random()*(877 - 669))+ 669
                        ,(int)(Math.random()*(681 - 605))+ 605,InputEvent.BUTTON1_MASK);
                        
                        AutoClicker.pause();
                    } else {
                        // click exit // 
                        clicker.clickMouse((int)(Math.random()*(1289-1266))+1266
                        ,(int)(Math.random()*(260-235))+235,InputEvent.BUTTON1_MASK);
                    }                   
                } else {
                    // click exit // 
                    clicker.clickMouse((int)(Math.random()*(1289-1266))+1266
                    ,(int)(Math.random()*(260-235))+235,InputEvent.BUTTON1_MASK);
                }

                AutoClicker.pause();
                
                if ((i <= 2) || (i > 3 && i <= 6) || (i > 7 && i <= 9) ) {
                    upperX = upperX + 140;
                    lowerX = lowerX + 140;
                } else if (i == 3 || i == 7) {
                    upperX = 1284;
                    lowerX = 1198;
                    lowerY = lowerY + 139;
                    upperY = upperY + 139;
                }
            } 
        }
        
        
        
        /*
        if (ImageToText.ImageCompare2("fixed auto images", "insufficient_energy", "temporary images", "insufficient_energy") == 100) {
            System.out.println("working");
        }
        */
        /*
        while (running == true) {
            if (image is "10/10") {
                commenceSellingRunes();
                click repeat battle;
                run = false;
            }
            
            
            else if (image is out of energy) {
                click energy button;
                refresh energy x120;
                click repeat battle;
                run = false;
            }
            else if (failed run) {
                repeat battle;
            }
            
        }
        */
    }
    
    public void StartBattle() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       
       trial = robot.createScreenCapture(new Rectangle(1415,749, 255, 52));
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\start_battle.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
    
    public void InsufficientEnergy() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       
       trial = robot.createScreenCapture(new Rectangle(165,721, 926, 52));
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\insufficient_energy.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
    
    public void NoOfRunsCompleted() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       
       trial = robot.createScreenCapture(new Rectangle(332,245, 84, 43));
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\completed_runs.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
