/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debugBytecode;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.ReturnCode;
import interpreter.debugger.DebugVM;
import java.util.Vector;

/**
 *Pops the current function environment off the function stack.
 */
public class debugReturnCode extends ReturnCode{
    
    public void execute(VirtualMachine vm){
        
        DebugVM dvm = (DebugVM)vm;
        
        String temp = "";
        
        if(dvm.trace != null){
            
            for(int i = 1 ; i < dvm.erSize(); i++){
                System.out.print(" ");
            }
            
            temp = temp + "exit: " + dvm.erPeek().getName() + ": ";
        }
        
        super.execute(vm);
        
        dvm.erEndFrame();
        
        if(dvm.trace != null){
            
            temp = temp + dvm.vmPeek();
            dvm.setTraceString(temp);
            
        }
        
        if (dvm.stepOut != null) {
            if (dvm.erSize() < dvm.stepOut.getStepSize()) {
                
                dvm.stepOut = null;
                dvm.setRunning(false);

                ByteCode bc = dvm.returnProgram().getCode(dvm.getPC() + 1);
                if (bc instanceof FunctionCode) {
                    bc.execute(dvm);
                }


            }
        }
        
        
    }
    
}
