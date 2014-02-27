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
public class StoreCode extends ByteCode{
    int n;
    String id="";
    
    public void init(Vector<String> args){
        if(args.size()==1){
            n = Integer.parseInt(args.firstElement());
        }else{
            id = args.get(args.size()-1);
            n = Integer.parseInt(args.firstElement());
        }
    }
    
    public void execute(VirtualMachine vm){
        vm.vmStore(n);
    }
    
    public String toString(){
        return "STORE "+n+" "+id;
    }
}
