/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import java.util.Vector;
import interpreter.VirtualMachine;
import interpreter.debugger.DebugVM;

/**
 *
 * @author Jonathan
 */
public class LabelCode extends ByteCode{
    
    String label;
    public void init(Vector<String> args){
        label = (String)args.firstElement();
    }
    
    public void execute(VirtualMachine vm){
        
    }
    
    public String getLabel(){
        return label;
    }
    
    public String toString(){
        
        return "LABEL "+label;
    }
}
