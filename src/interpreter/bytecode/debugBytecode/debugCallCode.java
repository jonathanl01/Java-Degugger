/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debugBytecode;

import interpreter.VirtualMachine;
import interpreter.bytecode.CallCode;
import java.util.Vector;
import interpreter.debugger.DebugVM;
import interpreter.debugger.FunctionEnvironmentRecord;

/**
 *Pushes a new Function Environment Record onto the function stack.
 */
public class debugCallCode extends CallCode {
    
    
    public void execute(VirtualMachine vm){
        
        DebugVM dvm = (DebugVM)vm;
        
        super.execute(vm);
        
        dvm.erPush(new FunctionEnvironmentRecord());
        
    }
    
}
