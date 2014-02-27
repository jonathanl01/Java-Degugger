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
public class WriteCode extends ByteCode{
    public void init(Vector<String> args){
        
    }
    
    public void execute(VirtualMachine vm){
        int n = vm.vmPeek();
        System.out.println(n);
    }
    
    public String toString(){
        return "WRITE";
    }
}
