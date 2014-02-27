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
public class LitCode extends ByteCode{
    
    int n;
    String id="";
    
    public void init(Vector<String> args){
            n = Integer.parseInt(args.firstElement());
            if(args.size() > 1){
                id = args.get(args.size()-1);
            }
    }
    
    
    public void execute(VirtualMachine vm){
        vm.vmPush(n);
    }
    
    public int getNum(){
        return n;
    }
    
    public String getID(){
        return id;
    }
    
    public String toString(){
        return "LIT "+n+" "+id;
    }
}
