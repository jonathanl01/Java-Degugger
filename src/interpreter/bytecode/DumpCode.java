/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.Vector;
import interpreter.debugger.DebugVM;

/**
 *
 * @author Jonathan
 */
public class DumpCode extends ByteCode{
    
    String label;
    public void init(Vector<String> args){
        label = (String)args.firstElement();
    }
    
    public void execute(VirtualMachine vm){
        
        if(label.equals("ON")){
            vm.setDumpOn();
        }else{
            vm.setDumpOff();
        }
        
    }
    
    public String getLabel(){
        return label;
    }
    
    public String toString(){
        
        return "DUMP "+label;
    }
    
}
