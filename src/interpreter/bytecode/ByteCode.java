/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;
import java.util.Vector;
import interpreter.*;

/**
 *
 * @author Jonathan
 */
public abstract class ByteCode {
    public abstract void init(Vector<String> arg);
    public abstract void execute(VirtualMachine vm);

    
}
