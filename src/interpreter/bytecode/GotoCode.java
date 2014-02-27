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
public class GotoCode extends ByteCode{
    
    String label = "";
    int targetAddress;
    
    public void init(Vector<String> args){
        label = (String)args.firstElement();
    }
    
    public void execute(VirtualMachine vm){
        vm.setPC(targetAddress);
    }
    
    public String toString(){
        return "GOTO "+label;
    }
    
    public void setLabel(String newLabel){
        label = newLabel;
    }
    
    public String getLabel(){
        return label;
    }
    
    public int getTargetAddress(){
        return targetAddress;
    }
    
    public void setTargetAddress(int newAddress){
        targetAddress = newAddress;
    }
    
}
