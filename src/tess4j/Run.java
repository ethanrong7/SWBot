
package tess4j;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.InputEvent;
import java.lang.Thread;


public class Run {
    
    public void StartRun() throws Exception {
        int upperX = 0;
        int lowerX = 0;
        int upperY = 0;
        int lowerY = 0;
        AutoClicker clicker = new AutoClicker();
        boolean running = true;
        
        StartBattle();
        Opencv.GreyScale("start_battle");           
        if (ImageToText.convert("start_battle").contains("Bat")) {
            // click start button //
            upperX = 1648;
            lowerX = 1431;
            upperY= 799;
            lowerY=703;       
            clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                    ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
        }
        
        while (running == true) {
            boolean endRun = false;
            
            while (endRun == false) {
                try {
                    Victory();
                    Opencv.GreyScale("victory");
                    Failure();
                    
                    // CHECK IF VICTORY OR FAILURE // 
                    
                    // VICTORY RUN // 
                    if (ImageToText.convert("victory").contains("Reward")) {  
                        
                        AutoClicker.pause();
                        
                        upperX = 1447;
                        lowerX = 374;
                        upperY= 870;
                        lowerY=200;       
                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK); 
                        
                        AutoClicker.pause();
                        
                        upperX = 1447;
                        lowerX = 374;
                        upperY= 870;
                        lowerY=200;
                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);

                        AutoClicker.pause();
                        
                        boolean reward = false;
                        
                        // VICTORY REWARD //
                        while (reward == false) {
                            try {
                                Grade();
                                // CHECK IF REWARD IS A RUNE //
                                if (ImageToText.ImageComparePNG("rune_grade_rare", "rune_grade") == 100.0 || 
                                    ImageToText.ImageComparePNG("rune_grade_hero", "rune_grade") == 100.0 ||
                                    ImageToText.ImageComparePNG("rune_grade_legend", "rune_grade") == 100.0) {               
                                Rune rune = new Rune();
                                rune.evaluate();
                                rune.Criteria();
                                System.out.println("Sell rune? " + rune.getSellRune());
                                    //keep rune//
                                    if (rune.getSellRune() == false) {                    
                                        upperX = 1162;
                                        lowerX = 997;
                                        upperY= 868;
                                        lowerY=813;       
                                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);

                                        AutoClicker.pause();

                                        upperX = 781;
                                        lowerX = 413;
                                        upperY= 612;
                                        lowerY=559;       
                                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                                    // sell rune //     
                                    } else if (rune.getSellRune() == true)  {
                                        upperX = 819;
                                        lowerX = 710;
                                        upperY= 862;
                                        lowerY=819;
                                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);

                                        AutoClicker.pause();

                                        upperX = 844;
                                        lowerX = 689;
                                        upperY = 675;
                                        lowerY = 597;
                                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);

                                        AutoClicker.pause();              
                                    }             
                                } else {
                                    // Non runes e.g. rune symbols, scrolls // 

                                    AutoClicker.pause();

                                    // click OK //
                                    upperX = 1032;
                                    lowerX = 819;
                                    upperY= 846;
                                    lowerY=838;       
                                    clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                            ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);

                                    AutoClicker.pause();                        
                                }
                                
                                // check if there is an event reward e.g. rune boxes //
                                CheckIfEvent();
                                if (ImageToText.ImageCompare("CheckIfEvent", "CheckIfEvent") == 100.0) {

                                    AutoClicker.pause();

                                    upperX = 996;
                                    lowerX = 861;
                                    upperY= 763;
                                    lowerY=711;       
                                    clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                            ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);

                                    AutoClicker.pause();
                                }
                                // replay run //
                                upperX = 781;
                                lowerX = 413;
                                upperY= 612;
                                lowerY=559;       
                                clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                        ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                                reward = true;
                            } catch(Exception e) {
                              System.out.println("error");
                            }                           
                        }
                        endRun = true;
                    // FAILURE RUN // 
                    } else if (ImageToText.ImageCompare("failure", "failure") > 93) {
                        AutoClicker.pause();
                        
                        // click no to not revive //
                        upperX = 1314;
                        lowerX = 1063;
                        upperY= 715;
                        lowerY=657;       
                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                        
                        AutoClicker.pause();
                        
                        // defeated screen - click anywhere //
                        upperX = 1575;
                        lowerX = 331;
                        upperY= 869;
                        lowerY=177;       
                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                        
                        AutoClicker.pause();
                        
                        // replay run //
                        upperX = 781;
                        lowerX = 413;
                        upperY= 612;
                        lowerY=559;       
                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                        
                        AutoClicker.pause();
                        
                        // click start button //
                        upperX = 1648;
                        lowerX = 1431;
                        upperY= 799;
                        lowerY=703;       
                        clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                                ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                        
                        AutoClicker.pause();
                        
                        endRun = true;
                    }                                    
                } catch(Exception e) {
                    System.out.println("error");
                }               
            }
            
            boolean crystalRefresh = false;
            
            while (crystalRefresh = false) {
                CheckEnergy();
                Opencv.GreyScale("CheckEnergy");
                if (ImageToText.convert("CheckEnergy").contains("Energy")) {
                 
                    // click shop // 
                    upperX = 839;
                    lowerX = 675;
                    upperY= 682;
                    lowerY=634;       
                    clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                            ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                    
                    // check if enough crystals for another refill //
                    CheckCrystals();
                    //   //
                    
                    AutoClicker.pause();
                    
                    // Recharge Energy // 
                    upperX = 892;
                    lowerX = 663;
                    upperY= 663;
                    lowerY=422;       
                    clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                            ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                    
                    AutoClicker.pause();
                    
                    // Click yes to purchase 30 crystals // 
                    upperX = 848;
                    lowerX = 705;
                    upperY= 670;
                    lowerY=628;       
                    clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                            ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                    
                    AutoClicker.pause(); // double pause because this sometimes lags //
                    AutoClicker.pause();
                    
                    // Click OK to proceed // 
                    upperX = 1002;
                    lowerX = 854;
                    upperY= 663;
                    lowerY=613;       
                    clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                            ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                    
                    AutoClicker.pause();
                    
                    // Close shop window // 
                    upperX = 999;
                    lowerX = 862;
                    upperY= 920;
                    lowerY=879;       
                    clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                            ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);
                    
                    AutoClicker.pause();
                    
                    // click replay // 
                    upperX = 781;
                    lowerX = 413;
                    upperY= 612;
                    lowerY=559;       
                    clicker.clickMouse((int)(Math.random()*(upperX-lowerX))+lowerX
                            ,(int)(Math.random()*(upperY-lowerY))+lowerY,InputEvent.BUTTON1_MASK);                                       
                } else {
                    continue;
                }
                crystalRefresh = true;
            }
        }
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
    
    public void Victory() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       
       trial = robot.createScreenCapture(new Rectangle(843, 377, 182, 52));
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\victory.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
    
    public void Failure() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(1060,659,246,61));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\failure.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    
    public void CheckIfRune() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(608,291,638,58));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\CheckIfRune.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
    
   public void CheckIfNonRune() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(875,843,102,55));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\CheckIfNonRune.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void CheckIfSS() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(851,772,152,59));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\SSOK.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void CheckIfEvent() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(858,715,138,54));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\CheckIfEvent.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void CheckEnergy() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(624,363,604,115));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\CheckEnergy.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void CheckCrystals() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(1533,222,131,43));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\CheckIfEnergy.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void Grade() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       
       trial = robot.createScreenCapture(new Rectangle(1108,379,185,38));
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\rune_grade.png");
       try {
         ImageIO.write(trial, "png", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
