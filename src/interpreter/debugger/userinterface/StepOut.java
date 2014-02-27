/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.userinterface;
import interpreter.debugger.*;


/**
 *Creates in StepOut object flag.
 */
public class StepOut {
    
    int stepSize;
    boolean done;
    
    public int getStepSize(){
        return stepSize; 
    }
    
    public void setDone(boolean isDone){
        done = isDone;
    }
    
    public boolean getDone(){
        return done;
    }
    
    public StepOut(int size){
        
        stepSize = size;
        done = false;
    }
    
    
}
