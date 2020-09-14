package tess4j;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList; 
import java.util.*; 

public class Rune {
    private String runeType;
    private int runeStars;
    private String runeGrade;
    private int runeSlot;
    private String[][] runeSubstat;
    private String runeMainstat;
    private String runeInnateStat;
    private boolean sellRune = false;
    public void Rune() {
    }
    
    public void Criteria() throws Exception {     
            //Rune stars to sell //            
            int [] runeStarArray = {1,2,3,4,5}; // EXAMPLE // 
            
            // Rune grades to sell {rare, hero, legend} //           
            String[] runeGradeArray = {"n", "n", "n"}; // EXAMPLE // 
                            
            // Flat main stats and if it has speed as a substat // 
            String[] runeFlatArray = {"y"};
            
            // Substat choices e.g. too many flat substats, speed in it, minimum speed //
            System.out.println("\nPlease enter your substat preferences: ");
            String[][] runeSubstatArray2D = {{"0", "y", "6", "Resistance Accuracy"},{"2", "y", "4", "null"},{"3", "y", "4", "null"}};
            
            while (sellRune == false) {
                // sell depending on stars //
                for (int i = 0; i < runeStarArray.length; i++) {
                    if (runeStarArray[i] == runeStars) {                   
                        sellRune = true;           
                    } else {
                        sellRune = false;
                    }
                }
                if (sellRune == true) {
                    System.out.println("Rune does not have enough stars");
                    break;
                }
                
                // sell depending on grade //
                if (runeGrade.equals("rare")) {
                
                    if (runeGradeArray[0].equals("y")) {
                        sellRune = true;
                        System.out.println("Sold because rare rune");                
                    } else {
                        sellRune = false;
                    }
                } else if (runeGrade.equals("hero")) {
                    if (runeGradeArray[1].equals("y")) {
                        sellRune = true;
                        System.out.println("Sold because hero rune");                
                    } else {
                        sellRune = false;
                    }
                } else {
                    if (runeGradeArray[2].equals("y")) {
                        sellRune = true;
                        System.out.println("Sold because legend rune");                
                    } else {
                        sellRune = false;
                    }
                }

                if (sellRune == true) {
                    break;
                }

                // sell depending on flat stat //
                boolean spdPresent = false;
                int spdValue = 0;
                
                for (int i = 0; i < runeSubstat.length; i++) {
                    if (runeSubstat[i][0].contains("SPD")) {                   
                        spdPresent = true;
                        spdValue = Integer.parseInt(runeSubstat[i][1]);
                        break;
                    }
                }
                
                if ((runeMainstat.contains("flat") || runeMainstat.contains("Resistance") || runeMainstat.contains("Accuracy")) && (runeSlot == 2 || runeSlot == 4 || runeSlot == 6 ) && 
                        runeGrade.equals("legend") && spdPresent == true) {
                    if (runeFlatArray[0].equals("y")) {
                        sellRune = false;                   
                    } else {
                        sellRune = true;
                        if (sellRune == true) {
                            System.out.println("Selling flat main stat");
                            break;
                        }                 
                    } 
                } else if ((runeMainstat.contains("flat") || runeMainstat.contains("Resistance") || runeMainstat.contains("Accuracy")) 
                && (runeSlot == 2 || runeSlot == 4 || runeSlot == 6 ) && spdPresent == false) {
                    sellRune = true;
                    if (sellRune == true) {
                        System.out.println("Selling flat main stat");
                        break;
                    }                     
                }

                // sell depending on substats // 
                int noOfFlatStats = 0;

                for (int i = 0; i < runeSubstat.length; i++) {
                    if (!runeSubstat[i][1].contains("%") && !runeSubstat[i][0].equals("SPD")) {
                        noOfFlatStats = noOfFlatStats + 1;
                    } 
                }
                
                if (runeGrade.equals("rare")) {
                    if (noOfFlatStats > Integer.parseInt(runeSubstatArray2D[0][0]) && spdPresent == false) {
                        sellRune = true;
                        if (sellRune == true) {
                            System.out.println("Too many flat stats");
                            break;
                        }
                    }
                    if (runeSubstatArray2D[0][1].equals("y") && spdPresent == false) {
                        sellRune = true;
                        if (sellRune == true) {
                            System.out.println("Speed not present");
                            break;
                        }
                    } 
                    if (runeSubstatArray2D[0][1].equals("y") && spdPresent == true && (Integer.parseInt(runeSubstatArray2D[0][2]) > spdValue)) {
                        sellRune = true;
                        if (sellRune == true) {
                            System.out.println("Not enough speed");
                            break;
                        }
                    }
                    if (!runeSubstatArray2D[0][3].contains("null") && spdPresent == false) {
                        for (int i = 0; i < runeSubstat.length; i++) {
                            if (runeSubstatArray2D[0][3].contains(runeSubstat[i][0])) {
                                sellRune = true;
                                if (sellRune == true) {
                                    System.out.println("Too many unwanted stats");
                                    break;
                                }
                            }
                        }
                    }
                } else if (runeGrade.equals("hero")) {
                    if (noOfFlatStats > Integer.parseInt(runeSubstatArray2D[1][0]) && spdPresent == false) {                 
                        sellRune = true;
                        if (sellRune == true) {
                            System.out.println("Too many flat stats");
                            break;
                        }
                    } 
                    if (runeSubstatArray2D[1][1].equals("y") && spdPresent == true && (Integer.parseInt(runeSubstatArray2D[1][2]) > spdValue)) {
                    
                        sellRune = true;
                        if (sellRune == true) {
                            System.out.println("Not enough speed");
                            break;
                        }
                    }
                    if (!runeSubstatArray2D[1][3].contains("null") && spdPresent == false) {
                        for (int i = 0; i < runeSubstat.length; i++) {
                            if (runeSubstatArray2D[1][3].contains(runeSubstat[i][0])) {
                                sellRune = true;
                                if (sellRune == true) {
                                    System.out.println("Too many unwanted stats");
                                    break;
                                }
                            }
                        }
                    }
                } else if (runeGrade.equals("legend")) {
                    if (noOfFlatStats > Integer.parseInt(runeSubstatArray2D[2][0]) && spdPresent == false) {                  
                        sellRune = true;
                        if (sellRune == true) {
                            System.out.println("Too many flat stats");
                            break;
                        }
                    } 
                    if (runeSubstatArray2D[2][1].equals("y") && spdPresent == true && (Integer.parseInt(runeSubstatArray2D[2][2]) > spdValue)) {             
                        sellRune = true;
                        if (sellRune == true) {
                            System.out.println("Not enough speed");
                            break;
                        }
                    }
                    if (!runeSubstatArray2D[2][3].contains("null") && spdPresent == false) {
                        for (int i = 0; i < runeSubstat.length; i++) {
                            if (runeSubstatArray2D[2][3].contains(runeSubstat[i][0])) {
                                sellRune = true;
                                if (sellRune == true) {
                                    System.out.println("Too many unwanted stats");
                                    break;
                                }
                            }
                        }
                    }
                } 
                if (sellRune == false) {
                    break;
                }        
            }
                System.out.println("Should I sell rune? " + sellRune);            
    } 
    
    public void evaluate() throws Exception {
        
        type();

        grade();

        stars();

        slot();

        substat();

        mainstat();
        
        // determine rune type //
        for (int i = 0; i < 5; i++) {
            String nb10runes[] = {"nemesis", "destroy", "will", "vampire", "rage"};            
            if (ImageCompare("rune_type_" + nb10runes[i], "rune_type") == 100) {
                System.out.println("Rune type: " + nb10runes[i]);
                runeType = nb10runes[i];
            }            
        }
        
        // determine rune grade //         
        for (int i = 0; i < 3; i++) {
            String grade[] = {"rare", "hero", "legend"};            
            if (ImageCompare("rune_grade_" + grade[i], "rune_grade") == 100) {
                System.out.println("Rune grade: " + grade[i]);
                runeGrade = grade[i];
            }            
        }
        
        //determine rune stars //
        for (int i = 0; i < 2; i++) {
            int stars[] = {5, 6};            
            if (ImageCompare("rune_stars_" + stars[i], "rune_stars") >= 95) {
                System.out.println("Rune star(s): " + stars[i]);
                runeStars = stars[i];
            }            
        }
        
        // determine main stat //
        
        Opencv.GreyScale("rune_mainstat");
        String mainstat = ImageToText.convert("rune_mainstat");
        
        if (mainstat.toLowerCase().contains("def") && mainstat.toLowerCase().contains("%")) {
            runeMainstat = "DEF %";            
        } else if (mainstat.toLowerCase().contains("def") && !mainstat.toLowerCase().contains("%")) {
            runeMainstat = "DEF flat";
        } else if (mainstat.toLowerCase().contains("atk") && mainstat.toLowerCase().contains("%")) {
            runeMainstat = "ATK %";
        } else if (mainstat.toLowerCase().contains("atk") && !mainstat.toLowerCase().contains("%")) {
            runeMainstat = "ATK flat";
        } else if (mainstat.toLowerCase().contains("hp") && mainstat.toLowerCase().contains("%")) {
            runeMainstat = "HP %";
        } else if (mainstat.toLowerCase().contains("hp") && !mainstat.toLowerCase().contains("%")) {
            runeMainstat = "HP flat";
        } else if (mainstat.toLowerCase().contains("spd")) {
            runeMainstat = "SPD";
        } else if (mainstat.toLowerCase().contains("cri rate")) {
            runeMainstat = "Cri Rate";
        } else if (mainstat.toLowerCase().contains("cri dmg")) {
            runeMainstat = "Cri Dmg";
        } else if (mainstat.toLowerCase().contains("resistance")) {
            runeMainstat = "Resistance";
        } else if (mainstat.toLowerCase().contains("accuracy")) {
            runeMainstat = "Accuracy";
        } else {
            runeMainstat = null;
        }
        
        System.out.println("Rune mainstat: " + runeMainstat);
        
        // determine slot and innate stat //
       
        Opencv.GreyScale("rune_slot");        
        String slot = ImageToText.convert("rune_slot"); 
        runeSlot = Integer.parseInt(slot.replaceAll("[^0-9]", "")); 
        System.out.println("Rune slot: " + runeSlot);
        
        if (slot.toLowerCase().contains("ferocious")) {
            runeInnateStat = "ATK flat";            
        } else if (slot.toLowerCase().contains("powerful")) {
            runeInnateStat = "ATK %";
        } else if (slot.toLowerCase().contains("sturdy")) {
            runeInnateStat = "DEF flat";
        } else if (slot.toLowerCase().contains("durable")) {
            runeInnateStat = "DEF %";
        } else if (slot.toLowerCase().contains("strong")) {
            runeInnateStat = "HP flat";
        } else if (slot.toLowerCase().contains("tenacious")) {
            runeInnateStat = "HP %";
        } else if (slot.toLowerCase().contains("quick")) {
            runeInnateStat = "SPD";
        } else if (slot.toLowerCase().contains("fatal")) {
            runeInnateStat = "Cri Rate";
        } else if (slot.toLowerCase().contains("cruel")) {
            runeInnateStat = "Cri Dmg";
        } else if (slot.toLowerCase().contains("resistant")) {
            runeInnateStat = "Resistance";
        } else if (slot.toLowerCase().contains("intricate")) {
            runeInnateStat = "Accuracy";
        } else {
            runeInnateStat = null;
        }
    
        
        System.out.println("Rune innate stat: " + runeInnateStat);
        
        // determine substats //
        
        Opencv.GreyScale("rune_substat");       
        
        String[] array = ImageToText.convert("rune_substat").split("\\n"); 
        
        for (int i = 0; i < array.length; i++){             // quick-fix for ImageToText errors
            array[i] = array[i].replace("°/o","%");
            array[i] = array[i].replace("DEF+","DEF +");           
        }
        
        ArrayList<String> array2 = new ArrayList<String>();
        int element= 0;
        
        for (int i = 0; i < array.length; i++) {
            if (array[i].trim().length() == 0) {
                element = i;               
            }                
        }
        
        if (element != 0) {
            int n = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i].trim().length() != 0) {
                    array2.add(n, array[i]);
                    n = n + 1;         
                } else {
                    continue;
                }
            }
        } else {
            for (int i = 0; i < array.length; i++) {
                array2.add(i, array[i]);
            }
        }
        
        runeSubstat = new String[array2.size()][2];

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < array2.size(); i++) {
                if (j == 0) {
                    runeSubstat[i][j] = array2.get(i).substring(0, array2.get(i).indexOf("+"));
                }
                else {
                    runeSubstat[i][j] = array2.get(i).substring(array2.get(i).lastIndexOf("+") + 1);
                }
            }
        }
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < runeSubstat.length; j++) {
                System.out.println(runeSubstat[j][i]);
            }
        }
        
    }

    public static double ImageCompare(String file1, String file2) throws Exception {
        
        BufferedImage img1 = ImageIO.read(new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\fixed images\\" + file1 +".png"));
        BufferedImage img2 = ImageIO.read(new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\" + file2 +".png"));
        
        int w1 = img1.getWidth();
        int w2 = img2.getWidth();
        int h1 = img1.getHeight();
        int h2 = img2.getHeight();
        
        long diff = 0;
        for (int j = 0; j < h1; j++) {
            for (int i = 0; i < w1; i++) {
               //Getting the RGB values of a pixel
               int pixel1 = img1.getRGB(i, j);
               Color color1 = new Color(pixel1, true);
               int r1 = color1.getRed();
               int g1 = color1.getGreen();
               int b1 = color1.getBlue();
               int pixel2 = img2.getRGB(i, j);
               Color color2 = new Color(pixel2, true);
               int r2 = color2.getRed();
               int g2 = color2.getGreen();
               int b2= color2.getBlue();
               //sum of differences of RGB values of the two images
               long data = Math.abs(r1-r2)+Math.abs(g1-g2)+ Math.abs(b1-b2);
               diff = diff+data;
            }
         }
         double avg = diff/(w1*h1*3);
         double percentage = (avg/255)*100;

         return (100 - percentage);
    }
    
    public void type() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       
       trial = robot.createScreenCapture(new Rectangle(596,695,580,71));
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\rune_type.png");
       try {
         ImageIO.write(trial, "png", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }
    
    public void grade() throws Exception {
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
    
   public void stars() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(594,367,98,19));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\rune_stars.png");
       try {
         ImageIO.write(trial, "png", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void slot() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       trial = robot.createScreenCapture(new Rectangle(608,291,638,58));
       
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\rune_slot.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void substat() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       
       trial = robot.createScreenCapture(new Rectangle(584,490,300,180));
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\rune_substat.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public void mainstat() throws Exception {
       Robot robot = null;
       
       try {
           robot = new Robot();
       } catch (AWTException e) {
           e.printStackTrace();
       }
       
       BufferedImage trial = null;
       
       trial = robot.createScreenCapture(new Rectangle(742,370,338,50));
       File output2 = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\rune_mainstat.tiff");
       try {
         ImageIO.write(trial, "tiff", output2);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   public int getRuneStars() {
       return runeStars;
   }
   
   public String getRuneGrade() {
       return runeGrade;
   }
   
   public String[][] getRuneSubstat() {
       return runeSubstat;
   }
   
   public String getRuneMainstat() {
       return runeMainstat;
   }
   
   public int getRuneSlot() {
       return runeSlot;
   }
   
   public boolean getSellRune() {
       return sellRune;
   }
   
}
