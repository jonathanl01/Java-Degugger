/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.debugger.DebugVM;
//import interpreter.debugger.userinterface.UserInterface;
import java.io.IOException;
import interpreter.ByteCodeLoader;
import interpreter.debugger.userinterface.DebugUI;

/**
 *
 * @author Jonathan
 */
public class Debugger {
    
    Interpreter interpreter;
    DebugVM dvm;
    DebugUI userInter;
    String file;
    String file2;
    static boolean debugging = true;
    
    public Debugger(String codeFile, String sourceFile)
            throws ClassNotFoundException, InstantiationException, 
            IllegalAccessException, IllegalAccessException, 
            IOException, IOException{
        
        file = codeFile;
        file2 = sourceFile;
        
        
    }
    /*
     * Executes the debugging process for the Debug object.
     */
    public void runDebugger() throws ClassNotFoundException, IOException, InstantiationException, IllegalAccessException{
        
        Interpreter interpreter = new Interpreter(file);
        dvm = new DebugVM(interpreter.getProgram(), file2);
        userInter = new DebugUI(dvm);
        
        userInter.printSourcefile();
        
        while(debugging){
            
            
            userInter.helpMenu();
            
        }
        
        System.out.println("Debugging over.");
        
    }
    
    public static void isDebugging(boolean set){
        debugging = set;
    }
    
    
}
