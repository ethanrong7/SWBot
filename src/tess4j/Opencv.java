
package tess4j;

import org.opencv.core.Core; 
import org.opencv.core.Mat; 
import org.opencv.imgcodecs.Imgcodecs; 
import org.opencv.imgproc.Imgproc; 
  
public class Opencv { 
    public static void GreyScale(String file) throws Exception 
    { 
        // To load  OpenCV core library 
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
        String input = "C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\" + file + ".tiff"; 
  
        // To Read the image 
        Mat source = Imgcodecs.imread(input); 
  
        // Creating the empty destination matrix 
        Mat destination = new Mat(); 
  
        // Converting the image to gray scale and  
        // saving it in the dst matrix 
        Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY); 
  
        // Writing the image 
        Imgcodecs.imwrite("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\temporary images\\" + file + ".tiff", destination); 
        
    }
    
    public static void GreyScale2(String folder, String file) throws Exception 
    { 
        // To load  OpenCV core library 
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); 
        String input = "C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\" + folder + "\\" + file + ".tiff";
  
        // To Read the image 
        Mat source = Imgcodecs.imread(input); 
  
        // Creating the empty destination matrix 
        Mat destination = new Mat(); 
  
        // Converting the image to gray scale and  
        // saving it in the dst matrix 
        Imgproc.cvtColor(source, destination, Imgproc.COLOR_RGB2GRAY); 
  
        // Writing the image 
        Imgcodecs.imwrite("C:\\Users\\Owner\\Documents\\NetBeansProjects\\SWBot_v1.00 copy\\" + folder + "\\" + file + ".tiff", destination); 
        
    }
} 
