/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.*;
import interpreter.debugger.DebugVM;

/**
 *
 * @author Jonathan
 */
public class ReadCode extends ByteCode{
    
    Scanner scanner = new Scanner(System.in);
    
    public void init(Vector<String> args){
        
    }
    
    public void execute(VirtualMachine vm){
        System.out.print("Please enter your number: ");
        
        int userInputer = scanner.nextInt();
        vm.vmPush(userInputer);
    }
    
    public String toString(){
        return "READ";
    }
}
