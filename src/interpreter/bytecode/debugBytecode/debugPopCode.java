/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debugBytecode;

import interpreter.VirtualMachine;
import interpreter.bytecode.PopCode;
import interpreter.debugger.DebugVM;
import java.util.Vector;

/**
 *Pops the most recent variable off the function environment.
 */
public class debugPopCode extends PopCode{
    
    
    public void execute(VirtualMachine vm){
        
        DebugVM dvm = (DebugVM)vm;
        super.execute(vm);
        
        dvm.erPop(getNumPop());
        
    }
    
}
