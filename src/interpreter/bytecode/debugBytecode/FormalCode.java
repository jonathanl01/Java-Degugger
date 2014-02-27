/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debugBytecode;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebugVM;
import java.util.Vector;

/**
 *Executes the original actions of FormalCode and then updates the variables of
 *the current Function Environment Record with the correct offsets of its values.
 */
public class FormalCode extends ByteCode{
    String formal;
    int value;
    
    public void init(Vector<String> args){
        formal = args.firstElement();
        value = Integer.parseInt(args.lastElement());
    }
    
    @Override
    public void execute(VirtualMachine vm) {
        DebugVM dvm = (DebugVM)vm;
        dvm.erPeek().put(formal,value);
        
    }
}
