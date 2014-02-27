/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debugBytecode;

import interpreter.VirtualMachine;
import interpreter.bytecode.LitCode;
import interpreter.debugger.DebugVM;
import interpreter.debugger.FunctionEnvironmentRecord;
import java.util.Vector;

/**
 *Puts new variables into the current function environment
 */
public class debugLitCode extends LitCode{
    
    
    public void execute(VirtualMachine vm){
        DebugVM dvm = (DebugVM)vm;
        super.execute(vm);
        
        int offset = (vm.getRunStackSize()-vm.vmPeekFrame())-1;
        
        if(!getID().equals(""))
        {
            dvm.erPeek().put(getID(), offset);
        }
        
    }
    
}
