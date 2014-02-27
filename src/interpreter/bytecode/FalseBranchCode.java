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
public class FalseBranchCode extends ByteCode {
    
    String label = "";
    int targetAddress;
    
    public void init(Vector<String> args){
        label = (String)args.firstElement();
    }
    
    public void execute(VirtualMachine vm){
        int branch = vm.vmPop();
        if(branch == 0){
            vm.setPC(targetAddress);
        }
    }
    
    public String toString(){
        
        return "FALSE "+label;
    }
    
    public String getLabel(){
        return label;
    }
    
    public void setLabel(String newLabel){
        label = newLabel;
    }
    
    public int getTargetAddress(){
        return targetAddress;
    }
    
    public void setTargetAddress(int newAddress){
        targetAddress = newAddress;
    }
}
