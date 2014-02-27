/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debugBytecode;
import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebugVM;
import interpreter.debugger.FunctionEnvironmentRecord;
import java.util.Vector;

/**
 *Updates the current function record with a functionanme, startline, and endline.
 */
public class FunctionCode extends ByteCode{
    FunctionEnvironmentRecord fer;
    String functionName;
    int startLine;
    int endLine;
    
    public void init(Vector<String> args){
            
            functionName = args.firstElement();
            int index = functionName.indexOf("<");
            if (index != -1){
                functionName = functionName.substring(0, index);
            }
            startLine = Integer.parseInt(args.get(1));
            endLine = Integer.parseInt(args.get(2));
            
    }
    
    public void execute(VirtualMachine vm){
        
        DebugVM dvm = (DebugVM)vm;
        
        //Set the current function with a name, startline, and endline.
        dvm.erPeek().setEverything(functionName,startLine,endLine);
        
        String temp = "";
        
        if(dvm.trace != null){
            
            for(int i = 1; i < dvm.erSize(); i++){
                temp += " ";
            }
            
            ByteCode bc = dvm.returnProgram().getCode(dvm.getPC() + 1);
            if (bc instanceof FormalCode) {
                temp = temp + functionName + " (";
                temp = temp + dvm.vmPeek();
                temp = temp + ")";
                dvm.setTraceString(temp);
            }else{
                //System.out.println(functionName + "()");
                temp = temp + functionName + "()";
                dvm.setTraceString(temp);
            }
            
            
        }
        
        if(dvm.stepIn != null){
            
            if(dvm.environmentStack.peek().getStart()== -1){
                
                if(dvm.environmentStack.peek().getName().equals("Read")){
                    System.out.println("******READ******");
                }else if(dvm.environmentStack.peek().getName().equals("Write")){
                    System.out.println("******WRITE*****");
                }
                
            }
            
            int tempPC = dvm.getPC();
            tempPC++;
            ByteCode bc = dvm.returnProgram().getCode(tempPC);
            while(bc instanceof FormalCode){
                bc.execute(dvm);
                tempPC++;
                bc = dvm.returnProgram().getCode(tempPC);
                
            }
            dvm.stepIn = null;
            dvm.setRunning(false);
            tempPC--;
            dvm.setPC(tempPC);

        }
        
    }

    
}
