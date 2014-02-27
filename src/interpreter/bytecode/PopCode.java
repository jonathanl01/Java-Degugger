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
public class PopCode extends ByteCode{
    
    int n;
    
    public void init(Vector<String> args){
        n = Integer.parseInt(args.firstElement());
    }
    
    public void execute(VirtualMachine vm){
        for(int i = 0; i < n;i++){
            vm.vmPop();
        }
        
    }
    
    public int getNumPop(){
        return n;
    }
    
    public String toString(){
        return "POP "+n;
    }
}
