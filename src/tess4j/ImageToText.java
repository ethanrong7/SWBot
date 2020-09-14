/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tess4j;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core; 

public class ImageToText{
    public static String convert(String file) throws IOException{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        File imageFile = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\" + file + ".tiff");
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\tessdata");

        try {
            String result = instance.doOCR(imageFile);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "not found";
        }       
    }
    
    public static String convert2(String folder, String file) throws IOException{
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        File imageFile = new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\" + folder + "\\" + file + ".tiff");
        ITesseract instance = new Tesseract();
        instance.setDatapath("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\tessdata");      
        
        try {
            String result = instance.doOCR(imageFile);
            return result;
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
            return "not found";
        }       
    }
    
    public static double ImageCompare(String file1, String file2) throws Exception {
        
        BufferedImage img1 = ImageIO.read(new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\fixed images\\" + file1 +".tiff"));
        BufferedImage img2 = ImageIO.read(new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\" + file2 +".tiff"));
        
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
    public static double ImageComparePNG(String file1, String file2) throws Exception {
        
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
    
    public static double ImageCompare2(String folder1, String file1, String folder2, String file2) throws Exception {       
        BufferedImage img1 = ImageIO.read(new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\"
                                                    + folder1 + "\\" + file1 +".tiff"));
        BufferedImage img2 = ImageIO.read(new File("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\"
                                                    + folder2 + "\\" + file2 +".tiff"));
        
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
    
}

