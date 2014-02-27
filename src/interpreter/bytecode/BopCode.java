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
public class BopCode extends ByteCode{
    
    String binaryOP = "";
    
    public void init(Vector<String> args){
        binaryOP = (String)args.firstElement();
    }
    
    public void execute(VirtualMachine vm){
        
        if(binaryOP.equals("+")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            
            int sum = temp2+temp1;
            vm.vmPush(sum);
        }
        
        else if(binaryOP.equals("-")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            
            int dif = temp2 - temp1;
            vm.vmPush(dif);
        }
        
        else if(binaryOP.equals("*")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            
            int product = temp2 * temp1;
            
            vm.vmPush(product);
        }
        
        else if(binaryOP.equals("/")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            
            int div = temp2/temp1;
            
            vm.vmPush(div);
        }
        
        else if(binaryOP.equals("==")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int equal;
            if(temp2 == temp1){
                equal = 1;
            }else{
                equal = 0;
            }
            
            vm.vmPush(equal);
        }
        
        else if(binaryOP.equals("!=")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int notEqual;
            if(temp2 != temp1){
                notEqual = 1;
            }else{
                notEqual = 0;
            }
            
            vm.vmPush(notEqual);
        }
        
        else if(binaryOP.equals("<=")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int lessEqual;
            
            if(temp2 <= temp1){
                lessEqual = 1;
            }else{
                lessEqual = 0;
            }
            
            vm.vmPush(lessEqual);
        }
        
        else if(binaryOP.equals(">")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int greater;
            
            if(temp2 > temp1){
                greater = 1;
            }else{
                greater = 0;
            }
            
            vm.vmPush(greater);
        }
        
        else if(binaryOP.equals(">=")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int greaterEqual;
            
            if(temp2 >= temp1){
                greaterEqual = 1;
            }else{
                greaterEqual = 0;
            }
            
            vm.vmPush(greaterEqual);
        }
        
        else if(binaryOP.equals("<")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int less;
            
            if(temp2 < temp1){
                less = 1;
            }else{
                less = 0;
            }
            
            vm.vmPush(less);
        }
        
        else if(binaryOP.equals("|")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int or;
            
            if(temp2==0 && temp1==0){
                or = 0;
            }else{
                or = 1;
            }
            
            vm.vmPush(or);
        }
        
        else if(binaryOP.equals("&")){
            int temp1 = vm.vmPop();
            int temp2 = vm.vmPop();
            int and;
            
            if(temp2==1 && temp1==1){
                and = 1;
            }else{
                and = 0;
            }
            
            vm.vmPush(and);
        }
        
    }
    
    
    
    public String toString(){
        return "BOP "+ binaryOP;
    }
    
}
