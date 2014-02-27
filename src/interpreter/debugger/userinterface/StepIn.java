/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.userinterface;

/**
 * Creates a StepIn object flag.
 */
public class StepIn {
    int stepSize;
    
    public int getStepSize(){
        return stepSize; 
    }
    
    public StepIn(int size){
        
        stepSize = size;
        
    }
}
