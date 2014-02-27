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
public class LoadCode extends ByteCode{
    
    int n;
    String id;
    
    public void init(Vector<String> args){
        if(args.size()==1){
            n = Integer.parseInt(args.firstElement());
        }else{
            id = args.get(args.size()-1);
            n = Integer.parseInt(args.firstElement());
        }
    }
    
    public void execute(VirtualMachine vm){
        vm.vmLoad(n);
    }
    
    public String toString(){
        return "LOAD "+n+" "+id;
    }
}
