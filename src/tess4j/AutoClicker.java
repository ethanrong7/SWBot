
package tess4j;

import java.awt.event.InputEvent;
import java.lang.Thread;
import java.awt.Robot;


public class AutoClicker {
    private Robot robot;
    private int delay;
    
    public AutoClicker() {
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int upper = 300;
        int lower = 100;
        int r = (int) (Math.random() * (upper - lower)) + lower;
        delay = r;       
    }
    
    public void clickMouse(int x, int y, int button) {
        try {
            robot.mouseMove(x, y);
            robot.mousePress(button);
            robot.delay(250);
            robot.mouseRelease(button);
            robot.delay(delay);
            robot.mouseMove(1390, 1059);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setDelay(int ms) {
        this.delay = ms;
    }
    
    public static void pause() {
        int upperX = 2400;
        int lowerX = 1200;
        try {
        Thread.sleep((int)(Math.random()*(upperX-lowerX))+lowerX);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
