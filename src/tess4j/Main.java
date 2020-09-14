package tess4j;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, Exception {
        
        LoopRun();
        
        
        
    }
    
    public static void LoopRun() throws Exception {
        boolean stopRun = false;
        RuneAuto run = new RuneAuto();
        
        run.evaluate();
        run.Criteria();
    }
    
    public static void RuneExamine() throws Exception {
        Rune rune = new Rune();
        rune.evaluate();
        rune.Criteria();
        System.out.println(rune.getSellRune());
    }  
}
