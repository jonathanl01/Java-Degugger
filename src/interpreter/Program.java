/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecode.*;
import interpreter.bytecode.debugBytecode.debugCallCode;

import java.util.*;

/**
 *
 * @author Jonathan
 */
public class Program extends Object{
    
    static HashMap<String,Integer> byteCodeLabels;
    
    private ArrayList<Object> byteCodeList;
    
    Program (){
        byteCodeList = new ArrayList();
        byteCodeLabels = new HashMap<String, Integer>();
    }
    
    public void addCode(ByteCode byteCode){
        
        if(byteCode instanceof LabelCode){
            LabelCode labelBranch = (LabelCode)byteCode;
            addLabel(labelBranch.getLabel(),byteCodeList.size());
        }
        
        byteCodeList.add(byteCode);
        
    }
    
    public void resolveAddresses(){
        
        for(int i = 0; i < byteCodeList.size(); i++){
            
            if(byteCodeList.get(i) instanceof GotoCode){
                
                GotoCode changeBranch = (GotoCode)byteCodeList.get(i);
                changeBranch.setTargetAddress(byteCodeLabels.get(changeBranch.getLabel()));
                
                //changeBranch.setLabel((String)byteCodeLabels.get(changeBranch.getLabel()));
                
            }else if(byteCodeList.get(i) instanceof FalseBranchCode){
                
                FalseBranchCode changeBranch = (FalseBranchCode)byteCodeList.get(i);
                changeBranch.setTargetAddress(byteCodeLabels.get(changeBranch.getLabel()));
                
                //changeBranch.setLabel((String)byteCodeLabels.get(changeBranch.getLabel()));
                
            }else if(byteCodeList.get(i) instanceof CallCode){
                
                CallCode changeBranch = (CallCode)byteCodeList.get(i);
                changeBranch.setTargetAddress(byteCodeLabels.get(changeBranch.getLabel()));
                
            }
        
        }
        
    }
    
    static public void addLabel(String key, int branch){
        byteCodeLabels.put(key, branch);
    }
    
    static public String getBranch(String branch){
        
        return byteCodeLabels.get(branch).toString();
    }
    
    public ByteCode getCode(int index){
        return (ByteCode)byteCodeList.get(index);
    }
    
    public int codeListSize(){
        return byteCodeList.size();
    }

    
    
}
