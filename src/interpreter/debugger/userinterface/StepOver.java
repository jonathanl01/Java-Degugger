/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.userinterface;

/**
 *Creates a StepOver object flag.
 */
public class StepOver {
    int stepSize;
    
    public int getStepSize(){
        return stepSize; 
    }
    
    public StepOver(int size){
        
        stepSize = size;
        
    }
    
}
