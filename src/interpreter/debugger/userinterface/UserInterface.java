/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger.userinterface;

import interpreter.Interpreter;
import interpreter.Program;
import interpreter.VirtualMachine;
import interpreter.debugger.DebugVM;
import interpreter.debugger.SourceLines;
import java.util.*;

/**
 *Interface for multiple possible User Interfaces.
 */
interface UserInterface {
    
    public void helpMenu();
    
    public void setBreakPoint();
    
    public void displayFunction();
    
    public void continueEx();
    
    public void displayVariables();
    
    public void quit();
    
    public void printSourcefile();
    
    public void stepOut();
    
    public void stepIn();
    
    public void stepOver();
    
    public void trace();
    
    public void printBreakPoints();
    
    public void printCallStack();
    
}
