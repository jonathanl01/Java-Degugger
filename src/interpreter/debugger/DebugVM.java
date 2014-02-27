/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import interpreter.debugger.userinterface.*;
import java.util.*;
import interpreter.*;
import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.VirtualMachine;
import interpreter.bytecode.*;
import interpreter.debugger.SourceLines;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jonathan
 */
public class DebugVM extends VirtualMachine{
    
    //array of functions
    public Stack<FunctionEnvironmentRecord> environmentStack;
    //Vector of breakpoints
    protected Vector<SourceLines> breakPoints;
    //Array of source code
    protected ArrayList<String> sourceCode;
    //array of lines that can breakpoint
    public ArrayList okayLine;
    //Pause for breakpoint
    boolean pause;
    boolean dvmIsRunning;
    
    //String that contiains the trace
    private String tracePrint;
    
    //Stepping actions
    public StepOut stepOut;
    public StepIn stepIn;
    public StepOver stepOver;
    
    //Tracing
    public Trace trace;
    
    
    protected BufferedReader sourceCodeReader;
    
    Scanner scanner = new Scanner(System.in);
    String userInput;
    
    public DebugVM(Program debugprogram, String sourceFile) throws IOException{
        super(debugprogram);
        environmentStack = new Stack();
        breakPoints = new Vector();
        sourceCode = new ArrayList();
        okayLine = new ArrayList();
        this.runStack = newRunTimeStack();
        this.returnAddrs = this.newReturnAddrsStack();
        
        dvmIsRunning = true;
        
        pause = false;
        
        environmentStack.push(new FunctionEnvironmentRecord());
        
        sourceCodeReader = new BufferedReader(new FileReader(sourceFile));
        
        String readLine = sourceCodeReader.readLine();
        
        while(readLine!=null){
            sourceCode.add(readLine);
            readLine = sourceCodeReader.readLine();
            breakPoints.add(new SourceLines(sourceCode.get(sourceCode.size()-1), false));
        }
        
    }
    
    //Executes the program bytecodes
    public void executeProgram(){
        
        while(this.getRunning())
        {
            
            if(pause){
                
                setIsRunning(false);
                
            }else{
                
                
                ByteCode code= this.returnProgram().getCode(getPC());
            
                code.execute(this);
            
                this.setPC(getPC()+1);
                
                if(tracePrint != null){
                    setRunning(false);
                }
                
                if(getPC()==this.returnProgram().codeListSize()){
                    System.out.println("Debugging finished");
                    this.setPC(0);
                    if(trace != null){
                        trace.setDoneTrace(true);
                    }
                    this.environmentStack = new Stack();
                    environmentStack.push(new FunctionEnvironmentRecord());
                    
                }
                
                
            }
            
        }
        
        
    }
    
    //Add's breakpoint lines to arrayList.
    public void lineCanBreak(int setBreak){
        
        if(setBreak>=0){
            
            okayLine.add(setBreak);
            
        }
        
    }
    
    //Flags a sourcecode line that has a breakpoint
    public boolean canBreak(int line){
        boolean breaking= false;
        
            if(okayLine.contains(line)){
                breaking = true;
            }
        
        return breaking;
                 
    }
    
    //Retrieves source code string to print.
//    public String getLineString(int lineNum){
//        
//        return sourceCode.get(lineNum);
//        
//    }
    
    //Retrieves source code string to print.
    public String getSourceString(int lineNum){
        return sourceCode.get(lineNum);
    }
    
    //Gets the number of source code lines.
    public int getSourceCodeSize(){
        return sourceCode.size();
    }
    
    //Returns the Environment Record stack size.
    public int erSize(){
        return environmentStack.size();
    }
    
    //Returns the top of the FER stack.
    public FunctionEnvironmentRecord erPeek(){
        return environmentStack.peek();
    }
    
    //Pops the top of the FER stack.
    public void erPop(int pop){
        environmentStack.peek().popScope(pop);
    }
    
    //Pushes a new FER on the FER stack.
    public void erPush(FunctionEnvironmentRecord fer){
        environmentStack.push(fer);
    }
    
    //Pops off the top of the FER stack.
    public void erEndFrame(){
        environmentStack.pop();
    }
    
    //Display the variables of the current function.
    public void erVariables(){
        System.out.println();
        FunctionEnvironmentRecord variables = environmentStack.peek();
        Set<String> s = variables.getVariables().keys();
        for(String key:s){
            System.out.print(key + "=");
            int offset = (Integer)variables.getBineder().get(key)+ vmPeekFrame();
            
            System.out.println(runStack.getValue(offset));
        }
        
        System.out.println();
    }
    
    //Sets the breakpoint of the source code line.
    public void setBreakPoint(int lineNum, boolean lineBreak){
        
        breakPoints.get(lineNum-1).setBreakPt(lineBreak);
        
    }
    
    //Grabs the program.
    public Program returnProgram(){
        return program;
    }
    
    //Clears all the breakpoints
    public void resetBreakPoints(){
        
        breakPoints.clear();
        for(int i = 0; i<sourceCode.size(); i++){
            breakPoints.add(new SourceLines(sourceCode.get(sourceCode.size()-1), false));
        }
        
    }
    
    //Set the pause for DVM.
    public void setPause(boolean isPaused){
        pause = isPaused;
    }
    
    //Set the running for DVM.
    public void setIsRunning(boolean dvmRunning){
        dvmIsRunning = dvmRunning;
    }
    
    //return Step out command.
    public StepOut getStep(){
        return stepOut;
    }
    
    //Grab the current trace string.
    public String getTraceString(){
        return tracePrint;
    }
    
    //Set the trace string.
    public void setTraceString(String setTrace){
        tracePrint = setTrace;
    }
    
    //Clear the trace string.
    public void resetTrace(){
        tracePrint = null;
    }
    
    //Print the call stack.
    public String getCallStack(){
        String callStack = "";
        ListIterator listIterator = environmentStack.listIterator(environmentStack.size());
        while(listIterator.hasPrevious()){
            FunctionEnvironmentRecord fer = (FunctionEnvironmentRecord)listIterator.previous();
            callStack += fer.getName()+":"+ fer.getCurrentLine()+"\n";
            
        }
        
        return callStack;
    }
    
    //Grabs the current line.
    public int getCurrentLine(){
        int currentLine;
        FunctionEnvironmentRecord fer;
        fer = environmentStack.pop();
        currentLine = environmentStack.peek().getCurrentLine();
        environmentStack.push(fer);
        return currentLine;
    }
    
}
