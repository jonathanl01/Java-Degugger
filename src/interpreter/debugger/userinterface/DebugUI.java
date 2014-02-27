/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.userinterface;

import interpreter.Debugger;
import interpreter.debugger.DebugVM;
import interpreter.debugger.SourceLines;
import interpreter.debugger.userinterface.StepOut;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author Jonathan
 */
public class DebugUI implements UserInterface{
    int breakAt;
    boolean breakMore;
    
    ArrayList breakPoint;
    
    DebugVM dvm;
    
    Scanner scanner;
    
    String userInput;
    
    
    //Contructor
    public DebugUI(DebugVM newdvm) throws FileNotFoundException, IOException{
        
        this.dvm = newdvm;
        scanner = new Scanner(System.in);
        breakPoint = new ArrayList();
        
        
    }
    @Override
    //Primary help menu.
    public void helpMenu() {
        
            for (;;) {
                
                
            System.out.println();
            System.out.println("Type ? for help");
            System.out.print(">");
            userInput = scanner.next();


            if (userInput.equals("?")) {
                System.out.print(
                        "\n1.[bp] Set/Clear breakpoints\n"
                        + "2.[pb] Print line numbers with breakpoints\n"
                        + "3.[fc] Display current Function\n"
                        + "4.[var] Display Variables\n"
                        + "5.[pc] Display Call Stack\n"
                        + "6.[tr] Set trace\n"
                        + "7.[so] Step out of function\n"
                        + "8.[si] Step in a function\n"
                        + "9.[sto] Stepover a line\n"
                        + "10.[pr] Show source code(with breakpoints)\n"
                        + "11.[co] Continue Exection\n"
                        + "12.[ha] Halt\n");
                System.out.print(">");
                userInput = scanner.next();
            }


            if (userInput.equals("bp")) {
                setBreakPoint();
            } else if (userInput.equals("fc")) {
                displayFunction();
            } else if (userInput.equals("var")) {
                displayVariables();
            } else if (userInput.equals("pr")) {
                printSourcefile();
            } else if (userInput.equals("co")) {
                continueEx();
                printSourcefile();
                break;
            } else if (userInput.equals("ha")) {
                quit();
            } else if (userInput.equals("so")) {
                stepOut();
                printSourcefile();
            } else if (userInput.equals("si")) {
                stepIn();
                printSourcefile();
            } else if (userInput.equals("sto")) {
                stepOver();
                printSourcefile();
            } else if (userInput.equals("tr")) {
                trace();
            } else if(userInput.equals("pb")){
                printBreakPoints();
            }else if(userInput.equals("pc")){
                printCallStack();
            }else {
                System.out.println("Try again.");
            }
        }
    }//End Help menu

    @Override
    //Sets breakpoints
    public void setBreakPoint() {
        String moreBreaks;
        
        System.out.println();
        System.out.println("Enter line number(s) that you want add/clear a breakpoint(s), "+
                            "0 to stop/exit, -1 to clear all break points");
        System.out.println();
        System.out.print("Line number: ");
        
        breakMore = true;
        
        try{
            breakAt = scanner.nextInt();
        }catch(Exception e){
            System.out.println("Not a line, try again.");
        }
        
        while(breakMore){
            //No break points
            if(breakAt == 0){
                break;
            }
            else if(breakAt == (-1)){
                dvm.resetBreakPoints();
                breakPoint.clear();
                dvm.okayLine.clear();
                break;
            }
            if(breakAt>dvm.getSourceCodeSize()){
                System.out.println("Line does not exist, try again");
            }else if(breakAt > 0) {
                if(breakPoint.contains(breakAt)){
                    dvm.setBreakPoint(breakAt, false);
                    
                    for(int i = 0; i < breakPoint.size(); i++){
                        if(breakPoint.get(i)== (Integer)breakAt){
                            breakPoint.remove(i);
                        }
                    }
                    
                    System.out.println("Break point removed at " + breakAt);
                }
                else if (dvm.getSourceString(breakAt-1).contains("{") || dvm.getSourceString(breakAt-1).contains("int")
                    || dvm.getSourceString(breakAt-1).contains("boolean") || dvm.getSourceString(breakAt-1).contains("if")
                    || dvm.getSourceString(breakAt-1).contains("while") || dvm.getSourceString(breakAt-1).contains("=")) {
                    
                    breakPoint.add(breakAt);
                    dvm.setBreakPoint(breakAt, true);
                    System.out.println("Breakpoint set at "+breakAt);
                    dvm.okayLine = breakPoint;
                }else{
                    System.out.println("Cannot set break point on that line.");
                }

            }
            
            //Prompt user to enter more 
            
            System.out.print("Line Number: ");
            
            try{
                
                breakAt = scanner.nextInt();
            
            }catch(Exception e){
                System.out.println("Not a line, try again.");
                breakAt = 0;
            }
            
            
            if(breakAt == 0){
                breakMore = false;
            }
            
            
        }//end while loop
        printSourcefile();
    }//End set/clear breakpoints

    @Override
    //Displays the current Function.
    public void displayFunction() {
        System.out.println();
        
        int start = dvm.erPeek().getStart()-1;
        int end = dvm.erPeek().getEnd()+1;
        
        for(int i = start; i <= end-2; i++){
            
            System.out.println(dvm.getSourceString(i));
            
        }
        
        System.out.println();
        
    }//End display function.

    @Override
    //Continues the execution of the program.
    public void continueEx() {
        
        if(dvm.trace != null){
            while(!dvm.trace.getDoneTrace()){
                dvm.setRunning(true);
                dvm.setPause(false);
                dvm.executeProgram();
                if (dvm.getTraceString() != null) {
                    System.out.println(dvm.getTraceString());
                }
                dvm.resetTrace();
            }
            
            
        }else{
            
            System.out.println();
            dvm.setRunning(true);
            dvm.setPause(false);
            dvm.executeProgram();
        
        }
        
        
        
    }//end continue execution

    @Override
    //Diplays the variables of the current function.
    public void displayVariables() {
        
        dvm.erVariables();
        
    }//end display variables

    @Override
    //Halts the program.
    public void quit() {
        System.out.println();
        System.out.println("****Execution Halted****");
        System.out.println("Thanks for debugging");
        dvm.setRunning(false);
        
    }//end quit

    @Override
    //Prints the source code with all the information.
    public void printSourcefile() {
        System.out.println("Source file read:\n");
        
        for(int i = 0; i < dvm.getSourceCodeSize(); i++){
            
            if(breakPoint.contains(i+1)){
                System.out.printf("*%2d."+dvm.getSourceString(i),(i+1));
                if(dvm.environmentStack.peek().getCurrentLine()==(i+1)){
                    System.out.print("<=======");
                }else if(dvm.environmentStack.peek().getCurrentLine()<0){
                    if(dvm.getCurrentLine() == (i+1)){
                        System.out.print("<=======");
                    }
                }
                System.out.println();
            }else{
                System.out.printf("%3d.  "+dvm.getSourceString(i),(i+1));
                if(dvm.environmentStack.peek().getCurrentLine()==(i+1)){
                    System.out.print("<=======");
                }else if(dvm.environmentStack.peek().getCurrentLine()<0){
                    if(dvm.getCurrentLine() == (i+1)){
                        System.out.print("<=======");
                    }
                }
                System.out.println();
            }
            
            
            
        }
        System.out.println();
        
    }

    @Override
    //steps out of the current function.
    public void stepOut() {
        
        dvm.stepOut = new StepOut(dvm.erSize());
        dvm.setIsRunning(true);
        
        
        continueEx();
            
        
    }

    @Override
    //Steps into the next function, or next line of the current function.
    public void stepIn() {
        if(dvm.environmentStack.peek().getName() == null){
            System.out.println("Program hasn't started yet.");
        }else{
            dvm.stepIn = new StepIn(dvm.erSize());
            dvm.setIsRunning(true);
        
            continueEx();
        }
        
    }

    @Override
    //Steps over the current line, and executes everything on that line.
    public void stepOver() {
        dvm.stepOver = new StepOver(dvm.erSize());
        dvm.setIsRunning(true);
        
        continueEx();
    }

    @Override
    //Sets the trace on or off.
    public void trace() {
        if(dvm.trace == null){
            dvm.trace = new Trace();
            System.out.println("Trace on.\n");
            
        }else if( dvm.trace != null){
            dvm.trace = null;
            System.out.println("Trace off.\n");
        }
        
    }

    @Override
    //Prints the line numbers that contain breakpoints.
    public void printBreakPoints() {
        System.out.println();
        System.out.println("Breakpoints on line(s): ");
        for(int i = 0; i < dvm.okayLine.size(); i++){
            System.out.print(" "+dvm.okayLine.get(i));
        }
        System.out.println();
    }

    @Override
    //Prints the call stack.
    public void printCallStack() {
        System.out.println(dvm.getCallStack());
    }


    
}
