/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import java.util.*;
import interpreter.*;
import interpreter.bytecode.*;

/**
 *
 * @author Jonathan
 */
public class VirtualMachine {
    protected int pc;
    
    protected Stack<Integer> returnAddrs;
    
    protected boolean isRunning;
    protected boolean dump = false;
    
    protected Program program;
    protected RunTimeStack runStack;
    
    public VirtualMachine(Program prog){
        program = prog;
    }
    
    public void setDumpOn(){
        dump = true;
    }
    
    public void setDumpOff(){
        dump = false;
    }
    
    public void setPC(int target){
        pc = target;
    }
    
    public int getPC(){
        return pc;
    }
    
    public void setRunning(boolean running){
        isRunning = running;
    }
    
    public boolean getRunning(){
        return isRunning;
    }
    
    public int popRetAddrs(){
        return returnAddrs.pop();
    }
    
    public void pushRetAddrs(int addrs){
        returnAddrs.push(addrs);
    }
    
    public void executeProgram(){
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        while(isRunning){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            if (dump && !(code instanceof DumpCode)){
                System.out.println(code.toString());
                runStack.dump();
            }
            
            pc++;
        }
        
    }
    
    public int vmPeek(){
        return runStack.peek();
    }
    
    public int vmPop(){
        return runStack.pop();
    }
    
    public void vmPush(int n){
         runStack.push(n);
    }
    
    public void vmFrameAt(int offset){
        runStack.newFrameAt(offset);
    }
    
    public void vmPopFrame(){
        runStack.popFrame();
    }
    
    public int vmPeekFrame(){
        return runStack.peekFrame();
    }
    
    public int vmStore(int offset){
        return runStack.store(offset);
    }
    
    public int vmLoad(int offset){
        return runStack.load(offset);
    }
    
    public Integer vmPush(Integer i){
        return runStack.push(i);
    }
    
    public int getRunStackSize(){
        return runStack.getRunStackSize();
    }
    
    public RunTimeStack newRunTimeStack(){
       RunTimeStack runTimeStack = new RunTimeStack();
       return runTimeStack;
    }
    
    public Stack newReturnAddrsStack(){
        Stack returnStack = new Stack();
        return returnStack;
    }
    
    
}
