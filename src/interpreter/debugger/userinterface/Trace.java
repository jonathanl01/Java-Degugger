/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.userinterface;

/**
 *Creates a Trace object flag.
 */
public class Trace {
    private boolean doneTrace = false;
    
    public boolean getDoneTrace(){
        return doneTrace;
    }
    
    public void setDoneTrace(boolean set){
        doneTrace = set;
    }
}
