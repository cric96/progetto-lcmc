package ast;
import java.util.ArrayList;

import ast.core.Node;
import ast.core.Type;
import ast.exception.WrongTypeException;

public class ProgNode implements Node {

  private Node exp;
  
  public ProgNode (Node e) {
   exp=e;
  }
  
  public String toPrint(String s) {
    
   return s+"Prog\n" + exp.toPrint(s+"  ") ;
  }
  
  public Type typeCheck() throws WrongTypeException {
	    return exp.typeCheck();
  }
    
  public String codeGeneration() {
	  return exp.codeGeneration()+
			 "halt\n";
  }

}  