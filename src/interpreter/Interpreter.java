package interpreter;

import interpreter.bytecode.ByteCode;
import interpreter.Debugger;
import interpreter.*;
import java.io.*;
import java.util.*;

/**
 * <pre>
 * 
 *  
 *   
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine
 *     
 *   
 *  
 * </pre>
 */
public class Interpreter {

	ByteCodeLoader bcl;
        
        public Program getProgram() throws ClassNotFoundException, 
                InstantiationException, IllegalAccessException, IOException{
            
            Program debugProgram = bcl.loadCodes(true);
            return debugProgram;
            
        }

	public Interpreter(String codeFile) {
		try {
			//CodeTable.init();
			bcl = new ByteCodeLoader(codeFile);
		} catch (IOException e) {
			System.out.println("**** " + e);
		}
	}

	void run() throws ClassNotFoundException, InstantiationException, 
                IllegalAccessException, IllegalAccessException, IOException, IOException {
		Program program = bcl.loadCodes(false);
		VirtualMachine vm = new VirtualMachine(program);
		vm.executeProgram();
	}

	public static void main(String args[]) throws ClassNotFoundException, 
                ClassNotFoundException, InstantiationException, 
                InstantiationException, IllegalAccessException, 
                IllegalAccessException, IOException, IOException {
                
                switch(args.length){
                    case 0:
                        System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
			System.exit(1);
                        break;
                    case 1:
                        (new Interpreter(args[0]+".x.cod")).run();
                        break;
                    case 2:
                        if(args[0].equals("-d")){
                            (new Debugger(args[1]+".x.cod",args[1]+".x")).runDebugger();;
                        }
                        break;
                        
                    default:
                            
                }
                    
            
            
	}
}
