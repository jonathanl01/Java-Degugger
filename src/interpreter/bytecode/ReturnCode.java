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
public class ReturnCode extends ByteCode{
    
    String funcname="";
    int topValue;
    
    public void init(Vector<String> args){
        if(!args.isEmpty()){
            funcname = args.firstElement();
        }
        
    }
    
    public void execute(VirtualMachine vm){
        
        vm.vmPopFrame();
        vm.setPC(vm.popRetAddrs());
        topValue = vm.vmPeek();
    }
    
    public String toString(){
        return "RETURN "+funcname;
    }
}
